package com.yuansong.task;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuansong.notify.DingMessageSender;
import com.yuansong.pojo.DingMessageConfig;
import com.yuansong.pojo.HealthConfig;
import com.yuansong.pojo.IntTaskConfig;
import com.yuansong.service.ConfigService;
import com.yuansong.service.MessageSenderManagerService;
import com.yuansong.service.TaskWorkerManagerService;
import com.yuansong.taskjob.TaskWorkerHealth;
import com.yuansong.taskjob.TaskWorkerInt;

@Component
public class ConfigRefreshTask {
	
	private final Logger logger = Logger.getLogger(ConfigRefreshTask.class);
	
	@Autowired
	private ConfigService<DingMessageConfig> dingMessageConfigService;
	
	@Autowired
	private ConfigService<HealthConfig> healthConfigService;
	
	@Autowired
	private ConfigService<IntTaskConfig> intTaskConfigService;
	
	@Autowired
	private MessageSenderManagerService messageSenderManagerService;
	
	@Autowired
	private TaskWorkerManagerService taskWorkerManagerService;
	
	@Scheduled(cron = "0 0/1 * * * ?")
	public void configReresh() {
		/* 刷新Task前先刷新MessageSender */
		refreshMessageSender();
		refreshTaskWorker();
	}
	
	public void refreshMessageSender() {
		int dingMessageSender = refreshDingMessageSender("notifyConfig\\DingMessage");
		//MessageSender有更新时，刷新所有任务
		if(dingMessageSender > 0) {
			logger.debug("Reset all task");
			resetAllTask();
		}
	}
	
	public void refreshTaskWorker() {
		refreshTaskWorkerInt("taskConfig\\IntTask");
		refrehsTaskWorkerHealth("taskConfig\\Healthconfig");
	}
	
	private void resetAllTask() {
		taskWorkerManagerService.cancelAllTask();
		for(String key : healthConfigService.getConfigKeyList()) {
			taskWorkerManagerService.addTask(
					key, 
					new TaskWorkerHealth(healthConfigService.getConfig(key),messageSenderManagerService.getMessageSenderList()),
					healthConfigService.getConfig(key).getCorn());
		}
		for(String key : intTaskConfigService.getConfigKeyList()) {
			taskWorkerManagerService.addTask(
					key, 
					new TaskWorkerInt(intTaskConfigService.getConfig(key),messageSenderManagerService.getMessageSenderList()),
					intTaskConfigService.getConfig(key).getCorn());
		}
	}

	/**
	 * 刷新DingMessageConfig，并根据配置更新的内容，更新MessageSenderManagerService中的DingMessageSender
	 * @param path 配置文件相对路径
	 * @return 更新的数量
	 */
	private int refreshDingMessageSender(String path) {
		Map<String, List<String>> result = dingMessageConfigService.refreshConfigList(path);
		
		for(String key : result.get("add")) {
			messageSenderManagerService.add(key, 
					new DingMessageSender(dingMessageConfigService.getConfig(key).getRobotToken()));
		}
		for(String key : result.get("cancel")) {
			messageSenderManagerService.del(key);
		}
		for(String key : result.get("refresh")) {
			messageSenderManagerService.add(key, 
					new DingMessageSender(dingMessageConfigService.getConfig(key).getRobotToken()));
		}
		
		return result.get("add").size() + result.get("cancel").size() + result.get("refresh").size();
	}
	
	/**
	 * 刷新IntTaskConfig，并根据配置更新的内容，更新TaskWorkerManagerService中的TaskWorkerInt
	 * @param path 配置文件相对路径
	 */
	private void refreshTaskWorkerInt(String path) {
		Map<String, List<String>> result = intTaskConfigService.refreshConfigList(path);
		for(String key : result.get("add")) {
			taskWorkerManagerService.addTask(
					key, 
					new TaskWorkerInt(intTaskConfigService.getConfig(key),messageSenderManagerService.getMessageSenderList()),
					intTaskConfigService.getConfig(key).getCorn());
		}
		for(String key : result.get("cancel")) {
			taskWorkerManagerService.cancelTask(key);
		}
		for(String key : result.get("refresh")) {
			taskWorkerManagerService.resetTask(
					key, 
					new TaskWorkerInt(intTaskConfigService.getConfig(key),messageSenderManagerService.getMessageSenderList()),
					intTaskConfigService.getConfig(key).getCorn());
		}
	}
	
	/**
	 * 刷新HealthConfig，并根据配置更新的内容，更新TaskWorkerManagerService中的TaskWorkerHealth
	 * @param path 配置文件相对路径
	 */
	private void refrehsTaskWorkerHealth(String path) {
		Map<String, List<String>> result = healthConfigService.refreshConfigList(path);
		
		for(String key : result.get("add")) {
			taskWorkerManagerService.addTask(
					key, 
					new TaskWorkerHealth(healthConfigService.getConfig(key),messageSenderManagerService.getMessageSenderList()),
					healthConfigService.getConfig(key).getCorn());
		}
		for(String key : result.get("cancel")) {
			taskWorkerManagerService.cancelTask(key);
		}
		for(String key : result.get("refresh")) {
			taskWorkerManagerService.resetTask(
					key, 
					new TaskWorkerHealth(healthConfigService.getConfig(key),messageSenderManagerService.getMessageSenderList()),
					healthConfigService.getConfig(key).getCorn());
		}
	}
	
}
