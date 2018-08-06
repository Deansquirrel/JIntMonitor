package com.yuansong.service;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public interface ConfigService<T> {
	
	public void refreshConfigList();
	
	public Set<String> getConfigKeyList();
	
	public Map<String, T> getConfigMap();
	
	public T getConfig(String key);

}
