package com.yuansong.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuansong.pojo.HealthConfig;
import com.yuansong.pojo.TaskConfig;
import com.yuansong.service.ConfigService;

@Component
public class ConfigRefreshTask {
	
	@Autowired
	private ConfigService<TaskConfig> taskConfigService;
	
	@Autowired
	private ConfigService<HealthConfig> healthConfigService;
	
	@Scheduled(cron = "0 1/5 * * * ?")
	public void taskConfigReresh() {
		taskConfigService.refreshConfigList();
		healthConfigService.refreshConfigList();
	}

}
