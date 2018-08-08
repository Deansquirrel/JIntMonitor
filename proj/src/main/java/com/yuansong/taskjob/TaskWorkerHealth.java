package com.yuansong.taskjob;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.apache.log4j.Logger;

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
		String msg = taskConfig.getMsgContent();
		if(!taskConfig.getMsgTitle().equals("")) {
			msg = taskConfig.getMsgTitle() + "\n" + msg;
		}
		return DateTool.getDateStr() + "\n" + getIP() + "\n" + msg;
	}
	
	private String getIP() {
		String ip = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString();
		} catch (UnknownHostException e) {
			logger.error("IP地址获取失败");
		}
		return ip;
	}	

}
