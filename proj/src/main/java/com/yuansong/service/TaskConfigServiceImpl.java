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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yuansong.common.CommonFun;
import com.yuansong.pojo.TaskConfig;
import com.yuansong.taskjob.CheckIntWorker;


@Service
public class TaskConfigServiceImpl implements TaskConfigService {
	
	private final Logger logger = Logger.getLogger(TaskConfigServiceImpl.class);
	
	private Gson mGson = new Gson();
	
	private Map<String, TaskConfig> configMap;
	
	@Autowired
	private TaskManagerService taskManagerService;
	
	public TaskConfigServiceImpl() {
		logger.debug("TaskConfig Init");
		configMap = new HashMap<String, TaskConfig>();
	}
	
	@Override
	public void refreshConfigList() {
		Map<String, TaskConfig> newConfigList = getConfigList();
		
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
			taskManagerService.addTask(key, new CheckIntWorker(newConfigList.get(key)), newConfigList.get(key).getCorn());
			configMap.put(key, newConfigList.get(key));
		}
		for(String key : cancelList) {
			taskManagerService.cancelTask(key);
			configMap.remove(key);
		}
		for(String key : refreshList) {
			taskManagerService.resetTask(key, new CheckIntWorker(newConfigList.get(key)), newConfigList.get(key).getCorn());
			configMap.put(key, newConfigList.get(key));
		}
	}
	
	@Override
	public Set<String> getTaskConfigKeyList(){
		return configMap.keySet();
	}
	
	@Override
	public Map<String, TaskConfig> getTaskConfigMap(){
		return configMap;
	}
	
	@Override
	public TaskConfig getConfig(String key) {
		if(configMap.containsKey(key)) {
			return configMap.get(key);
		}
		else {
			return null;
		}
	}
	
	private TaskConfig getTaskConfigFromFile(File file) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.getPath()), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String s = "";
			while ((s =bufferedReader.readLine()) != null) {
	            sb.append(s);
	        }
			bufferedReader.close();
			return mGson.fromJson(sb.toString(), TaskConfig.class);
			
		}catch(Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
//		TaskConfig taskConfig = new TaskConfig();
//		taskConfig.setServer(properties.getProperty("SQL.Server",""));
//		taskConfig.setDbName(properties.getProperty("SQL.DBName",""));
//		taskConfig.setUser(properties.getProperty("SQL.User",""));
//		taskConfig.setPwd(properties.getProperty("SQL.Pwd",""));
//		taskConfig.setSearch(properties.getProperty("SQL.Search",""));
//		taskConfig.setCheckMax(Integer.valueOf(properties.getProperty("CHECK.Max","100")));
//		taskConfig.setCheckMin(Integer.valueOf(properties.getProperty("CHECK.Min","0")));
//		taskConfig.setMsgTitle(properties.getProperty("MSG.Title",""));
//		taskConfig.setMsgContent(properties.getProperty("MSG.Content",""));
//		taskConfig.setRobotToken(properties.getProperty("DING.RobotToken",""));
//		return taskConfig;
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
	
	private Map<String, TaskConfig> getConfigList(){
		Map<String, TaskConfig> map = new HashMap<String, TaskConfig>();
		
		String path = TaskConfigServiceImpl.class.getClassLoader().getResource("taskconfig").getPath();
		File fileDir = new File(path);
		if(!fileDir.isDirectory()) {
			logger.error("taskconfig is not directory");
		}
		else {
			File[] fileList = fileDir.listFiles();
			for(File f : fileList) {
				TaskConfig taskConfig = getTaskConfigFromFile(f);
				if(taskConfig == null) {
					break;
				}
				if(checkTaskConfig(f.getName(), taskConfig)) {
					map.put(CommonFun.md5Encode(f.getName()), taskConfig);
				}
			}
		}
		return map;
	}

}
