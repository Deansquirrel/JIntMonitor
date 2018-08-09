package com.yuansong.taskjob;

import java.util.List;

import org.apache.log4j.Logger;

import com.yuansong.common.CommonFun;
import com.yuansong.common.DateTool;
import com.yuansong.notify.MessageSender;
import com.yuansong.pojo.HealthConfig;

public class TaskWorkerHealth extends TaskWorkerAbstractImpl<HealthConfig> {
	
	private final Logger logger = Logger.getLogger(TaskWorkerHealth.class);

	public TaskWorkerHealth(HealthConfig config, List<MessageSender> list) {
		super(config, list);
	}

	@Override
	protected String check() {
		HealthConfig taskConfig = getConfig();
		if(taskConfig == null) {
			logger.warn("HealthConfig is null");
			return "HealthConfig is null";
		}
		String msg = taskConfig.getMsgContent();
		if(!taskConfig.getMsgTitle().equals("")) {
			msg = taskConfig.getMsgTitle() + "\n" + msg;
		}
		return DateTool.getDateStr() + "\n" + CommonFun.getInternetIp() + "\n" + msg;
	}

}
