package com.yuansong.taskjob;

import java.net.HttpURLConnection;
import java.net.URL;
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
		
		try {  
            URL realUrl = new URL(taskConfig.getUrl());  
            HttpURLConnection httpUrlConn = (HttpURLConnection) realUrl.openConnection();  
   
            httpUrlConn.setConnectTimeout(30 * 1000);
            httpUrlConn.setReadTimeout(30 * 1000);
            httpUrlConn.setUseCaches(false);  
            httpUrlConn.setRequestMethod("GET");
            
            httpUrlConn.connect();
            int httpCode = httpUrlConn.getResponseCode();
            httpUrlConn.disconnect();  
            
            logger.debug("网页链接测试返回码 - " + String.valueOf(httpCode) + " | " + taskConfig.getUrl());
            
            if(httpCode == 200) {
            	return "";
            }
            else {
            	StringBuilder sb = new StringBuilder();
            	if(!taskConfig.getMsgTitle().equals("")) {
            		sb.append(taskConfig.getMsgTitle());
            		sb.append("\n");
            	}
            	sb.append(taskConfig.getUrl());
            	sb.append("\n");
            	sb.append(taskConfig.getMsgContent().replaceAll("title", String.valueOf(httpCode)));
            	return sb.toString();
            }
		}catch(Exception ex) {
			ex.printStackTrace();
			
			StringBuilder sb = new StringBuilder();
        	if(!taskConfig.getMsgTitle().equals("")) {
        		sb.append(taskConfig.getMsgTitle());
        		sb.append("\n");
        	}
        	sb.append(taskConfig.getUrl());
        	sb.append("\n");
        	sb.append("网址打开异常");
        	return sb.toString();
		}
	}

}
