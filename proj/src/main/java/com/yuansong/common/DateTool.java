package com.yuansong.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 日期工具类
 * @author yuansong
 *
 */
public class DateTool {
	
	private DateTool() {};
	
	/**
	 * 按默认格式返回当前日期时间字符串
	 * @return
	 */
	public static String getDateStr() {
		return getDateStr(new java.util.Date());
	}
	
	/**
	 * 按默认格式返回指定日期时间字符串
	 * @param date 日期
	 * @return
	 */
	public static String getDateStr(java.util.Date date) {
		return getDateStr(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按指定格式返回当前日期时间字符串
	 * @param format
	 * @return
	 */
	public static String getDateStr(String format){
		return getDateStr(new java.util.Date(), format);
	}
	
	/**
	 * 根据指定格式返回日期时间字符串
	 * @param date 日期
	 * @param format 格式，如 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getDateStr(java.util.Date date,String format) {
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 根据字符串返回日期
	 * @param date 日期字符串
	 * @param format 日期格式
	 * @return java.util.Date
	 * @throws ParseException
	 */
	public static java.util.Date getDateFromStr(String date,String format) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(date);
	}

}
