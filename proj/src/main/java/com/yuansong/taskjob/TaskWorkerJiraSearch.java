package com.yuansong.taskjob;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.yuansong.common.DateTool;
import com.yuansong.notify.MessageSender;
import com.yuansong.pojo.JiraSearchConfig;

public class TaskWorkerJiraSearch extends TaskWorkerAbstractImpl<JiraSearchConfig> {
	
	private final Logger logger = Logger.getLogger(TaskWorkerJiraSearch.class);
	private final Gson mGson = new Gson();

	public TaskWorkerJiraSearch(JiraSearchConfig config, List<MessageSender> list) {
		super(config, list);
	}

	@Override
	protected String check() {
		JiraSearchConfig config = getConfig();
		String msg = "";
		try {
			boolean loginTestResult = jiraLoginTest(config.getServer());	
			logger.debug(loginTestResult);
			if(!loginTestResult) {
				logger.debug("Jira Login");
				boolean loginResult = jiraLogin(config.getServer(),config.getUser(),config.getPwd());
				logger.debug(loginResult);
				if(!loginResult) {
					throw new Exception("login failed");
				}
			}
			String jsonStr = jiraGetData(config.getServer(),config.getJql());
			JiraData jData = mGson.fromJson(jsonStr, JiraData.class);
			if(jData == null) {
				String errMsg = "Jira返回数据异常 - " + jsonStr;
				logger.warn(errMsg);
				throw new Exception(errMsg);
			}
			if(jData.total > 0) {
				msg = DateTool.getDateStr() + "    " + String.valueOf(jData.total) + "\n";
				int max = 0;
				if(jData.total > 5) {
					max = 5;
				}
				else {
					max = jData.total;
				}
				JiraIssues issues;
				for(int i=0;i<max;i++) {
					issues = jData.issues.get(i);
					msg = msg + "\n";
					msg = msg + issues.fields.summary + "\n";
					msg = msg + "Creator: " + issues.fields.creator.displayName + "\n";
					msg = msg + "Priority: " + issues.fields.priority.name + "\n";
					msg = msg + config.getServer() + "/browse/" + issues.key + "\n";
				}
			}
		}
		catch(Exception ex) {
			msg = "获取Jira数据时发生错误" + "\n";
			msg = msg + "Server:" + config.getServer() + "\n";
			msg = msg + "Jql:" + config.getJql() + "\n";
			msg = msg + "Exception:" + ex.getMessage();
			logger.debug(msg);
		}
		
		return msg;
	}
	
	private boolean jiraLoginTest(String server) throws Exception {
		URL realUrl = new URL(server + "/rest/auth/1/session");  
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();  

        conn.setDoOutput(false);  
        conn.setDoInput(true);  
        conn.setUseCaches(false);  

        conn.setRequestMethod("GET");  
       
        conn.connect();
        
        int httpCode = conn.getResponseCode();
        switch (httpCode){
		    case 200:
		    	return true;
		    case 401:
		    	return false;
        	default:
        		return false;
        }
	}
	
	private boolean jiraLogin(String server, String user, String pwd) throws Exception {
		
		Map<String,String> data = new HashMap<String, String>();
		data.put("username", user);
		data.put("password", pwd);
		
		CookieManager manager = new CookieManager();
		CookieHandler.setDefault(manager);
		
		URL realUrl = new URL(server + "/rest/auth/1/session");  
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");    // POST方法
        
        conn.setRequestProperty("Content-Type", "application/json");

        conn.connect();
        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
        out.write(mGson.toJson(data));
        out.flush();        
        
        int httpCode = conn.getResponseCode();
        logger.debug("login httpCode - " + String.valueOf(httpCode));
        switch(httpCode) {
	        case 200:
	        	return true;
	        case 401:
	        	return false;
	        case 403:
	        	return false;
        	default:
        		return false;
        }
	}
	
	private String jiraGetData(String server, String jql) throws Exception {
		
		List<String> fields =new ArrayList<String>();
		fields.add("key");
		fields.add("summary");
		fields.add("priority");
		fields.add("creator");
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("jql", jql);
		data.put("fields", fields);
		
		OutputStreamWriter out = null;
        BufferedReader in = null;
		String result = "";
		
		try {
			URL realUrl = new URL(server + "/rest/api/2/search");  
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
	        
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        conn.setRequestMethod("POST");    // POST方法
	        
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("Accept-Charset", "utf-8");
	        conn.setRequestProperty("Accept-Language", "zh");
	        conn.setRequestProperty("Accept-Language", "zh");
	        
	        conn.connect();
	        out = new OutputStreamWriter(conn.getOutputStream(),"utf-8");
	        out.write(mGson.toJson(data));
	        out.flush();
	        
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
	        String line;
	        while ((line = in.readLine()) != null) {
	            result += line;
	        }
		}
		catch(Exception ex) {
			throw ex;
		}
		finally{
			if(out!=null){
	            out.close();
	        }
	        if(in!=null){
	            in.close();
	        }
		}
		
        return result;
	}
	
	private class JiraData{
		int total;
		List<JiraIssues> issues;
	}
	
	private class JiraIssues{
		private String key;
		JiraIssuesFields fields;
	}
	
	private class JiraIssuesFields{
		String summary;
		JiraIssuesCreator creator;
		JiraIssuesPriority priority;
	}
	
	private class JiraIssuesCreator{
		String displayName;
	}
	
	private class JiraIssuesPriority{
		String name;
	}

}
