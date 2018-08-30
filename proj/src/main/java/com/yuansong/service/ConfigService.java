package com.yuansong.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yuansong.common.CommonFun;
import com.yuansong.pojo.Config;

@Service
public abstract class ConfigService<T extends Config>{
	
	private final Logger logger = Logger.getLogger(ConfigService.class);
	
	private Map<String, T> configMap;
	
	private Gson mGson = new Gson();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ConfigService() {
		logger.debug("ConfigServiceAbstractImpl Init");
		configMap = new HashMap<String, T>();
	}
	
	public interface RefreshCallBack<T>{
		public void configAddList(List<T> list);
		public void configCancelList(List<T> list);
		public void configRefreshList(List<T> list);
	}
		
	public void refreshConfigList(RefreshCallBack<T> callBack) {
		List<T> list = getSetConfigList();
		if(list == null) {
			list = new ArrayList<T>();
		}
		
		Map<String, T> newConfigMap = new HashMap<String, T>();
		for(T config : list) {
			if(checkConfig(config).equals("")) {
				newConfigMap.put(config.getId(), config);				
			}
		}
		
		List<T> addList = new ArrayList<T>();
		List<T> cancelList = new ArrayList<T>();
		List<T> refreshList = new ArrayList<T>();
		
		for(String key : configMap.keySet()) {
			if(newConfigMap.containsKey(key)) {
				String oMD5 = CommonFun.md5Encode(getStrConfig(configMap.get(key)));
				String nMD5 = CommonFun.md5Encode(getStrConfig(newConfigMap.get(key)));
				if(!oMD5.equals(nMD5)) {
					refreshList.add(newConfigMap.get(key));
				}
			}
			else {
				cancelList.add(configMap.get(key));
			}
		}
		for(String key : newConfigMap.keySet()) {
			if(!configMap.containsKey(key)) {
				addList.add(newConfigMap.get(key));
			}
		}
		
		configMap = newConfigMap;
		
		callBack.configAddList(addList);
		callBack.configCancelList(cancelList);
		callBack.configRefreshList(refreshList);
	}

	public Set<String> getConfigKeyList(){
		return configMap.keySet();
	}
	
	public Map<String, T> getConfigMap(){
		return configMap;
	}
	
	public T getConfig(String key) {
		if(configMap.containsKey(key)) {
			return configMap.get(key);
		}
		else {
			return null;
		}
	}
	
//	protected Map<String, T> getCurrentConfigMap(){
//		return configMap;
//	}
	
	public abstract List<T> getSetConfigList();
	
//	protected T getCurrentConfig(String key) {
//		return configMap.get(key);
//	}
	
	protected String getStrConfig(T config) {
		return mGson.toJson(config);
	}
	
	public abstract String checkConfig(T config);
	
	public abstract void add(T config);
	
	public void del(T config) {
		try {
			jdbcTemplate.update(getSqlDel(), new Object[] {config.getId()});
		}catch(Exception ex){
			throw ex;
		}
	}
	
	protected abstract String getSqlDel();

}
