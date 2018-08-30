package com.yuansong.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonFun {
	
	private CommonFun() {};
	
	/**
	 * 获取字符串MD5
	 * @param inStr
	 * @return
	 */
	public static String md5Encode(String inStr){
	    MessageDigest md5 = null;
	    try {
	        md5 = MessageDigest.getInstance("MD5");
		} 
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		byte[] byteArray = null;
		try {
			byteArray = inStr.getBytes("UTF-8");
		} 
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
	
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
		    int val = ((int) md5Bytes[i]) & 0xff;
		    if (val < 16) {
		        hexValue.append("0");
		    }
		    hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toUpperCase();
	}
	
	/**
	 * 获取GUID
	 * @return
	 */
	public static String UUID() {
		return  java.util.UUID.randomUUID().toString().toUpperCase();
	}
	
	/**
     * 获得内网IP
     * @return 内网IP
     */
    public static String getIntranetIp(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得外网IP
     * @return 外网IP
     */
    public static String getInternetIp(){
    	String ip = "";
        String chinaz = "http://ip.chinaz.com";
        
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(5 * 1000);
                urlConnection.setReadTimeout(5 * 1000);
                in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            } catch (Exception e) {
                return getIntranetIp();
            }
            while((read=in.readLine())!=null){
                inputLine.append(read+"\r\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if(m.find()){
            String ipstr = m.group(1);
            ip = ipstr;
        }
        if ("".equals(ip)) {
            return getIntranetIp();
        }
        return ip;
    }
}
