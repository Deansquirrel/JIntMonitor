package com.yuansong.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yuansong.common.DateTool;
import com.yuansong.notify.DingMessageSender;

@Component
public class SelfCheckNotifyTask {
	
	private static final String robotToken = "7a84d09b83f9633ad37866505d2c0c26e39f4fa916b3af8f6a702180d3b9906b";
	private DingMessageSender dms =null;
	
	public SelfCheckNotifyTask() {
		dms = new DingMessageSender(robotToken);
	}
	
	@Scheduled(cron = "0 0 9,13,21 * * ? ")
	public void selfCheckNotifyTask() {
		dms.send(DateTool.getDateStr() + "\n" + "Service is still on line");
	}

}
