package com.yuansong.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yuansong.common.CommonFun;
import com.yuansong.pojo.TaskConfig;


@Service
public class TaskConfigService {
	
	private final Logger logger = Logger.getLogger(TaskConfigService.class);
	
	private Map<String, TaskConfig> configMap;
	
	public TaskConfigService() {
		logger.debug("TaskConfig Init");
		configMap = new HashMap<String, TaskConfig>();
	}
	
	public void refreshConfigList() {
		
		String path = TaskConfigService.class.getClassLoader().getResource("taskconfig").getPath();
		
		File fileDir = new File(path);
		
		if(!fileDir.isDirectory()) {
			logger.error("taskconfig is not directory");
			return;
		}
		
		Properties properties;
		BufferedReader bufferedReader = null;
		TaskConfig taskConfig;
		
		File[] fileList = fileDir.listFiles();
		for(File f : fileList) {
			properties = new Properties();
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(f.getPath()), "UTF-8"));
				properties.load(bufferedReader);
			}catch(Exception ex) {
				logger.error(ex.getMessage());
				ex.printStackTrace();
				break;
			}
			
			taskConfig = new TaskConfig();
			taskConfig.setServer(properties.getProperty("SQL.Server",""));
			taskConfig.setDbName(properties.getProperty("SQL.DBName",""));
			taskConfig.setUser(properties.getProperty("SQL.User",""));
			taskConfig.setPwd(properties.getProperty("SQL.Pwd",""));
			taskConfig.setSearch(properties.getProperty("SQL.Search",""));
			taskConfig.setCheckMax(Integer.valueOf(properties.getProperty("CHECK.Max","100")));
			taskConfig.setCheckMin(Integer.valueOf(properties.getProperty("CHECK.Min","0")));
			taskConfig.setMsgTitle(properties.getProperty("MSG.Title",""));
			taskConfig.setMsgContent(properties.getProperty("MSG.Content",""));
			taskConfig.setRobotToken(properties.getProperty("DING.RobotToken",""));
			
			if(checkTaskConfig(f.getName(), taskConfig)) {
				configMap.put(CommonFun.md5Encode(f.getName()), taskConfig);
			}
		}
	}
	
	public Set<String> getTaskConfigKeyList(){
		return configMap.keySet();
	}
	
	public Map<String, TaskConfig> getTaskConfigMap(){
		return configMap;
	}
	
	public TaskConfig getConfig(String key) {
		if(configMap.containsKey(key)) {
			return configMap.get(key);
		}
		else {
			return null;
		}
	}
	
	private boolean checkTaskConfig(String fileName, TaskConfig taskConfig) {
		if(taskConfig == null) {
			logger.error("taskConfig is null.【" + fileName + "】");
			return false;
		}
		if(taskConfig.getServer().equals("")) {
			logger.error("Server can not be null.【" + fileName + "】");
//			return false;
		}
		if(taskConfig.getDbName().equals("")) {
			logger.error("DbName can not be null.【" + fileName + "】");
			return false;
		}
		if(taskConfig.getUser().equals("")) {
			logger.error("User can not be null.【" + fileName + "】");
			return false;
		}
		if(taskConfig.getSearch().equals("")) {
			logger.error("Search can not be null.【" + fileName + "】");
			return false;
		}
		if(taskConfig.getMsgTitle().equals("")) {
			logger.error("MsgTitle can not be null.【" + fileName + "】");
			return false;
		}
		if(taskConfig.getMsgContent().equals("")) {
			logger.error("MsgContent can not be null.【" + fileName + "】");
			return false;
		}
		if(taskConfig.getRobotToken().equals("")) {
			logger.error("RobotToken can not be null.【" + fileName + "】");
			return false;
		}
		return true;
	}

}
