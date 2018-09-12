package com.yuansong.notify;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.yuansong.common.HttpUtils;

public class DingMessageSender implements MessageSender {
	
	private final Logger logger = Logger.getLogger(DingMessageSender.class);
	
	private String robotToken = "";
	
	private Gson mGson = new Gson();
	
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
		
		Map<String,String> textContent = new HashMap<String, String>();
		textContent.put("content",msg);
		Map<String, Object> objMsg = new HashMap<String, Object>();
		objMsg.put("msgtype", "text");
		objMsg.put("text", textContent);
		
		String result = httpUtils.httpPostJson(url, mGson.toJson(objMsg));
		DingMsgResult r = mGson.fromJson(result, DingMsgResult.class);
		if(r == null) {
			logger.debug(result);
		}
		else if(r.errcode != 0) {
			logger.debug("errcode - " + String.valueOf(r.errcode));
			logger.debug("errmsg - " + r.errmsg);
		}
	}
		
	private class DingMsgResult{
		int errcode;
		String errmsg;
	}
}
