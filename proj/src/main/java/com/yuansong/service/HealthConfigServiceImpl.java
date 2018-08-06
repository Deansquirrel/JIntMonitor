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
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yuansong.common.CommonFun;
import com.yuansong.pojo.HealthConfig;

@Service
public class HealthConfigServiceImpl implements ConfigService<HealthConfig> {
	
	private final Logger logger = Logger.getLogger(HealthConfigServiceImpl.class);
	
	private Gson mGson = new Gson();
	
	private Map<String, HealthConfig> configMap;
	
	public HealthConfigServiceImpl() {
		logger.debug("HealthConfigServiceImpl Init");
		configMap = new HashMap<String, HealthConfig>();
	}

	@Override
	public void refreshConfigList() {
		
		Map<String, HealthConfig> newConfigList = new HashMap<String, HealthConfig>();
		
		newConfigList.putAll(getConfigList());
		
		List<String> addList = new ArrayList<String>();
		List<String> cancelList = new ArrayList<String>();
		List<String> refreshList = new ArrayList<String>();
		
		for(String key : configMap.keySet()) {
			if(newConfigList.containsKey(key)) {
				String oMD5 = CommonFun.md5Encode(mGson.toJson(configMap.get(key)));
				String nMD5 = CommonFun.md5Encode(mGson.toJson(newConfigList.get(key)));
				if(!oMD5.equals(nMD5)) {
					refreshList.add(key);
				}
			}
			else {
				cancelList.add(key);
			}
		}
		for(String key : newConfigList.keySet()) {
			if(!configMap.containsKey(key)) {
				addList.add(key);
			}
		}
		for(String key : addList) {
			configMap.put(key, newConfigList.get(key));
		}
		for(String key : cancelList) {
			configMap.remove(key);
		}
		for(String key : refreshList) {
			configMap.put(key, newConfigList.get(key));
		}
		
	}

	@Override
	public Set<String> getConfigKeyList() {
		return configMap.keySet();
	}

	@Override
	public Map<String, HealthConfig> getConfigMap() {
		return configMap;
	}

	@Override
	public HealthConfig getConfig(String key) {
		if(configMap.containsKey(key)) {
			return configMap.get(key);
		}
		else {
			return null;
		}
	}
	
	private Map<String, HealthConfig> getConfigList(){
		Map<String, HealthConfig> map = new HashMap<String, HealthConfig>();
		
		String path = TaskConfigServiceImpl.class.getClassLoader().getResource("healthconfig").getPath();
		File fileDir = new File(path);
		if(!fileDir.isDirectory()) {
			logger.error("healthconfig is not directory");
		}
		else {
			File[] fileList = fileDir.listFiles();
			for(File f : fileList) {
				HealthConfig config = getHealthConfigDingFromFile(f);
				if(config == null) {
					break;
				}
				if(checkHealthConfigDing(f.getName(), config)) {
					map.put(CommonFun.md5Encode(f.getName()), config);
				}
			}
		}
		return map;
	}
	
	private HealthConfig getHealthConfigDingFromFile(File file) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getPath()), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String s = "";
			while ((s =bufferedReader.readLine()) != null) {
	            sb.append(s);
	        }
			bufferedReader.close();
			return mGson.fromJson(sb.toString(), HealthConfig.class);
			
		}catch(Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}
	
	private boolean checkHealthConfigDing(String fileName, HealthConfig config) {
		if(config == null) {
			logger.error("taskConfig is null.【" + fileName + "】");
			return false;
		}
		if(config.getMsgContent().equals("")) {
			logger.error("MsgContent can not be null.【" + fileName + "】");
			return false;
		}
		if(config.getRobotToken().equals("")) {
			logger.error("RobotToken can not be null.【" + fileName + "】");
			return false;
		}
		return true;
	}

}
