package com.yuansong.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.yuansong.common.CommonFun;

public abstract class ConfigServiceAbstractImpl<T> implements ConfigService<T> {
	
	private final Logger logger = Logger.getLogger(ConfigServiceAbstractImpl.class);
	
	private Gson mGson = new Gson();
	
	private Map<String, T> configMap;
	
	public ConfigServiceAbstractImpl() {
		logger.debug("ConfigServiceAbstractImpl Init");
		configMap = new HashMap<String, T>();
	}
	
	@Override
	public Map<String, List<String>> refreshConfigList(String path) {
		Map<String, T> newConfigMap = getConfigList(path);
		
		List<String> addList = new ArrayList<String>();
		List<String> cancelList = new ArrayList<String>();
		List<String> refreshList = new ArrayList<String>();
		
		for(String key : configMap.keySet()) {
			if(newConfigMap.containsKey(key)) {
				String oMD5 = CommonFun.md5Encode(mGson.toJson(configMap.get(key)));
				String nMD5 = CommonFun.md5Encode(mGson.toJson(newConfigMap.get(key)));
				if(!oMD5.equals(nMD5)) {
					refreshList.add(key);
				}
			}
			else {
				cancelList.add(key);
			}
		}
		for(String key : newConfigMap.keySet()) {
			if(!configMap.containsKey(key)) {
				addList.add(key);
			}
		}
		
		configMap = newConfigMap;
		
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		result.put("add", addList);
		result.put("cancel", cancelList);
		result.put("refresh", refreshList);
		return result;
	}
	
	@Override
	public Set<String> getConfigKeyList(){
		return configMap.keySet();
	}
	
	@Override
	public Map<String, T> getConfigMap(){
		return configMap;
	}
	
	@Override
	public T getConfig(String key) {
		if(configMap.containsKey(key)) {
			return configMap.get(key);
		}
		else {
			return null;
		}
	}
	
	protected Map<String, T> getConfigList(String path){
		Map<String, T> map = new HashMap<String, T>();
		
		String reslPath = ConfigServiceIntTaskImpl.class.getClassLoader().getResource(path).getPath();
		File fileDir = new File(reslPath);
		if(!fileDir.isDirectory()) {
			logger.error("taskconfig is not directory");
		}
		else {
			File[] fileList = fileDir.listFiles();
			for(File f : fileList) {
				T t = getConfigFromStr(getStrFromFile(f));
				if(t == null) {
					break;
				}
				if(checkConfig(f.getName(), t)) {
					map.put(CommonFun.md5Encode(path + f.getName()), t);
				}
			}
		}
		return map;
	}
	
	protected abstract T getConfigFromStr(String str);
	
	protected abstract boolean checkConfig(String fileName, T config);
	
	protected String getStrFromFile(File file) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getPath()), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String s = "";
			while ((s =bufferedReader.readLine()) != null) {
	            sb.append(s);
	        }
			bufferedReader.close();
			return sb.toString();
		}catch(Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

}
