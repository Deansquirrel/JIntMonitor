package com.yuansong.task;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuansong.notify.DingMessageSender;
import com.yuansong.pojo.DingMessageConfig;
import com.yuansong.pojo.HealthTaskConfig;
import com.yuansong.pojo.IntTaskConfig;
import com.yuansong.pojo.WebStateTaskConfig;
import com.yuansong.service.ConfigService;
import com.yuansong.service.ConfigService.RefreshCallBack;
import com.yuansong.service.MessageSenderManagerService;
import com.yuansong.service.TaskWorkerManagerService;
import com.yuansong.taskjob.TaskWorkerHealth;
import com.yuansong.taskjob.TaskWorkerInt;
import com.yuansong.taskjob.TaskWorkerWebState;

@Component
public class ConfigRefreshTask {
	
	private final Logger logger = Logger.getLogger(ConfigRefreshTask.class);
	
	@Autowired
	private ConfigService<DingMessageConfig> dingMessageConfigService;
	
	@Autowired
	private ConfigService<HealthTaskConfig> healthConfigService;
	
	@Autowired
	private ConfigService<IntTaskConfig> intTaskConfigService;
	
	@Autowired
	private ConfigService<WebStateTaskConfig> webStateTaskConfigService;
	
	@Autowired
	private MessageSenderManagerService messageSenderManagerService;
	
	@Autowired
	private TaskWorkerManagerService taskWorkerManagerService;
	
	@Scheduled(cron="0 0/1 * * * ?")
	public synchronized void configReresh() {
		/* 刷新Task前先刷新MessageSender */
		logger.debug("Config Refresh");
		refreshMessageSender();
		refreshTaskWorker();
	}
	
	private void refreshMessageSender() {
		dingMessageConfigService.refreshConfigList(new RefreshCallBack<DingMessageConfig>() {

			@Override
			public void configAddList(List<DingMessageConfig> list) {
				if(list.size() > 0) {
					for(DingMessageConfig config : list) {
						messageSenderManagerService.add(config.getId(), new DingMessageSender(config.getRobotToken()));
					}
					taskWorkerManagerService.cancelAllTask();
				}
			}

			@Override
			public void configCancelList(List<DingMessageConfig> list) {
				if(list.size() > 0) {
					for(DingMessageConfig config : list) {
						messageSenderManagerService.del(config.getId());
					}
					taskWorkerManagerService.cancelAllTask();					
				}
			}

			@Override
			public void configRefreshList(List<DingMessageConfig> list) {
				if(list.size() > 0) {
					for(DingMessageConfig config : list) {
						messageSenderManagerService.del(config.getId());
						messageSenderManagerService.add(config.getId(), new DingMessageSender(config.getRobotToken()));
					}
					taskWorkerManagerService.cancelAllTask();
				}
			}
		});
	}
	
	private void refreshTaskWorker() {
		
		healthConfigService.refreshConfigList(new RefreshCallBack<HealthTaskConfig>() {

			@Override
			public void configAddList(List<HealthTaskConfig> list) {
				for(HealthTaskConfig config : list) {
					taskWorkerManagerService.addTask(config.getId(), new TaskWorkerHealth(config, messageSenderManagerService.getMessageSenderList()), config.getCron());
				}
			}

			@Override
			public void configCancelList(List<HealthTaskConfig> list) {
				for(HealthTaskConfig config : list) {
					taskWorkerManagerService.cancelTask(config.getId());
				}
			}

			@Override
			public void configRefreshList(List<HealthTaskConfig> list) {
				for(HealthTaskConfig config : list) {
					taskWorkerManagerService.cancelTask(config.getId());
					taskWorkerManagerService.addTask(config.getId(), new TaskWorkerHealth(config, messageSenderManagerService.getMessageSenderList()), config.getCron());
				}
			}
			
		});
		
		intTaskConfigService.refreshConfigList(new RefreshCallBack<IntTaskConfig>(){

			@Override
			public void configAddList(List<IntTaskConfig> list) {
				for(IntTaskConfig config : list) {
					taskWorkerManagerService.addTask(config.getId(), new TaskWorkerInt(config, messageSenderManagerService.getMessageSenderList()), config.getCron());
				}
			}

			@Override
			public void configCancelList(List<IntTaskConfig> list) {
				for(IntTaskConfig config : list) {
					taskWorkerManagerService.cancelTask(config.getId());
				}
			}

			@Override
			public void configRefreshList(List<IntTaskConfig> list) {
				for(IntTaskConfig config : list) {
					taskWorkerManagerService.cancelTask(config.getId());
					taskWorkerManagerService.addTask(config.getId(), new TaskWorkerInt(config, messageSenderManagerService.getMessageSenderList()), config.getCron());
				}
			}
			
		});
		
		webStateTaskConfigService.refreshConfigList(new RefreshCallBack<WebStateTaskConfig>() {

			@Override
			public void configAddList(List<WebStateTaskConfig> list) {
				for(WebStateTaskConfig config : list) {
					taskWorkerManagerService.addTask(config.getId(), new TaskWorkerWebState(config, messageSenderManagerService.getMessageSenderList()), config.getCron());
				}
			}

			@Override
			public void configCancelList(List<WebStateTaskConfig> list) {
				for(WebStateTaskConfig config : list) {
					taskWorkerManagerService.cancelTask(config.getId());
				}
			}

			@Override
			public void configRefreshList(List<WebStateTaskConfig> list) {
				for(WebStateTaskConfig config : list) {
					taskWorkerManagerService.cancelTask(config.getId());
					taskWorkerManagerService.addTask(config.getId(), new TaskWorkerWebState(config, messageSenderManagerService.getMessageSenderList()), config.getCron());
				}
			}
			
		});
	}
	
}
