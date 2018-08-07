package com.yuansong.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuansong.pojo.DingMessageConfig;
import com.yuansong.pojo.HealthConfig;
import com.yuansong.pojo.IntTaskConfig;
import com.yuansong.service.ConfigService;

@Component
public class ConfigRefreshTask {
	
	@Autowired
	private ConfigService<DingMessageConfig> dingMessageConfigService;
	
	@Autowired
	private ConfigService<HealthConfig> healthConfigService;
	
	@Autowired
	private ConfigService<IntTaskConfig> intTaskConfigService;
	
	@Scheduled(cron = "0 1/5 * * * ?")
	public void configReresh() {
		dingMessageConfigService.refreshConfigList("notifyConfig\\DingMessage");
		healthConfigService.refreshConfigList("taskconfig\\Healthconfig");
		intTaskConfigService.refreshConfigList("taskConfig\\IntTask");
	}

}
