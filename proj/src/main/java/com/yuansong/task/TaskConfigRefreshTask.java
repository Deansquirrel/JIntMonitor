package com.yuansong.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuansong.service.TaskConfigServiceImpl;

@Component
public class TaskConfigRefreshTask {
	
	@Autowired
	private TaskConfigServiceImpl taskConfigService;
	
	@Scheduled(cron = "0 1/5 * * * ?")
	public void taskConfigReresh() {
		taskConfigService.refreshConfigList();
	}

}
