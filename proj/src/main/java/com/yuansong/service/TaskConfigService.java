package com.yuansong.service;

import java.util.Map;
import java.util.Set;

import com.yuansong.pojo.TaskConfig;

public interface TaskConfigService {
	
	public void refreshConfigList();
	
	public Set<String> getTaskConfigKeyList();
	
	public Map<String, TaskConfig> getTaskConfigMap();
	
	public TaskConfig getConfig(String key);

}
