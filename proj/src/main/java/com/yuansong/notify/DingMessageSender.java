package com.yuansong.notify;

import org.apache.log4j.Logger;

import com.yuansong.common.HttpUtils;

public class DingMessageSender implements MessageSender {
	
	private final Logger logger = Logger.getLogger(DingMessageSender.class);
	
	private String robotToken = "";
	
	HttpUtils httpUtils = null;
	
	public DingMessageSender(String robotToken) {
		this.robotToken = robotToken;
		httpUtils = new HttpUtils();
	}

	@Override
	public void send(String msg) {
		if(robotToken.equals("")) {
			logger.warn("robotToken is tempy");
			return;
		}
		
		String url = "https://oapi.dingtalk.com/robot/send?access_token=" + robotToken;
		String data = "{\"msgtype\": \"text\",\"text\": {\"content\": \"" + msg + "\" }}";
		
		httpUtils.httpPostJson(url, data);
		
	}

}
