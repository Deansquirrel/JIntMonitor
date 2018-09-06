package com.yuansong.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;

public class HttpUtils {
	
	private final Logger logger = Logger.getLogger(HttpUtils.class);
	
	private static final String CHARSET = "utf-8";
		
	public String httpGet(String url, Map<String,String> header) {
		StringBuffer buffer = new StringBuffer();  
        try {  
            URL realUrl = new URL(url);  
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();  
   
            conn.setDoOutput(false);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
   
            conn.setRequestMethod("GET");  
            
            if(header != null && header.size() > 0) {
            	for(String key : header.keySet()) {
            		conn.setRequestProperty(key, header.get(key));
            	}
            }
            
            conn.connect();  
   
            // 将返回的输入流转换成字符串  
            InputStream inputStream = conn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, CHARSET);  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
   
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            conn.disconnect();  
   
        } catch (Exception e) {  
        	logger.error("发送GET请求出现异常！" + e.getMessage());
	        e.printStackTrace();
        }  
        return buffer.toString(); 
	}
	
	public String httpGet(String url) {
		return this.httpGet(url, null);
	}
	
//	private String httpGet(String url, Map<String, String> data) {
//		return "";
//	}
	
	public String httpPostJson(String url, Map<String, String> header, String data) {
		OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = null;
            conn = (HttpURLConnection) realUrl.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");    // POST方法
             
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/json; charset=" + CHARSET);
            
            if(header != null && header.size() > 0) {
            	for(String key : header.keySet()) {
            		conn.setRequestProperty(key, header.get(key));
            	}
            }
             
            conn.connect();
             
            out = new OutputStreamWriter(conn.getOutputStream(), CHARSET);
            out.write(data);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	logger.error("发送 POST 请求出现异常！"+e.getMessage());
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
            	logger.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
        return result;
	}
	
	public String httpPostJson(String url, String data) {
		return this.httpPostJson(url, null, data);
	}	
}
