package com.yuansong.notify;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DingMessageSender implements MessageSender {
	
	private final Logger logger = Logger.getLogger(DingMessageSender.class);
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	private String robotToken = "";
	
	public DingMessageSender(String robotToken) {
		this.robotToken = robotToken;
	}

	@Override
	public void send(String msg) {
		if(robotToken.equals("")) {
			logger.warn("robotToken is tempy");
			return;
		}
		
		String url = "https://oapi.dingtalk.com/robot/send?access_token=" + robotToken;
		String data = "{\"msgtype\": \"text\",\"text\": {\"content\": \"" + msg + "\" }}";
		
		RequestBody body = RequestBody.create(JSON, data);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.connectTimeout(30, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS)
				.build();
		
		Response response = null;
		try {
			response = okHttpClient.newCall(request).execute();
			logger.debug(response.body().toString());
		}catch(Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}finally {
			if(response != null) {
				response.close();
			}
		}
	}

}
