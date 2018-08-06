package com.yuansong.task;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuansong.common.DateTool;
import com.yuansong.notify.DingMessageSender;
import com.yuansong.pojo.HealthConfig;
import com.yuansong.service.ConfigService;

@Component
public class HealthCheckNotifyTask {
	
	private final Logger logger = Logger.getLogger(HealthCheckNotifyTask.class);
	
	private DingMessageSender dms =null;
	
	@Autowired
	private ConfigService<HealthConfig> healthConfigService;
	
	@Scheduled(cron = "0 0 9,13,21 * * ? ")
	public void selfCheckNotifyTask() {
		
		HealthConfig config = null;
		for(String key : healthConfigService.getConfigKeyList()) {
			config = healthConfigService.getConfig(key);
			dms = new DingMessageSender(config.getRobotToken());
			String msg = config.getMsgContent().replace("DateTime", DateTool.getDateStr());
			if(!config.getMsgTitle().equals("")) {
				msg = config.getMsgTitle() + "\n" + msg;
			}
			InetAddress addr;
			String ip = "";
			try {
				addr = InetAddress.getLocalHost();
				ip=addr.getHostAddress().toString();
			} catch (UnknownHostException e) {
				logger.error("IP地址获取失败");
			}
			if(!ip.equals("")) {
				msg = ip + "\n" + msg;
			}
			dms.send(msg);
		}
	}

}
