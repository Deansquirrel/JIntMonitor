package com.yuansong.taskjob;

import java.util.List;

import org.apache.log4j.Logger;

import com.yuansong.notify.MessageSender;
import com.yuansong.pojo.WebStateTaskConfig;

public class TaskWorkerWebState extends TaskWorkerAbstractImpl<WebStateTaskConfig> {
	
	private final Logger logger = Logger.getLogger(TaskWorkerWebState.class);

	public TaskWorkerWebState(WebStateTaskConfig config, List<MessageSender> list) {
		super(config, list);
	}

	@Override
	protected String check() {
		WebStateTaskConfig taskConfig = getConfig();
		if(taskConfig == null) {
			logger.warn("WebStateTaskConfig is null");
			return "WebStateTaskConfig is null";
		}
		logger.debug(taskConfig.getUrl());
		logger.debug(taskConfig.getMsgTitle());
		logger.debug(taskConfig.getMsgContent());
		logger.debug(taskConfig.getCorn());
		return "";
	}

}
