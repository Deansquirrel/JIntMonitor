package com.yuansong.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class CommonFun {
	
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

}
