package com.yuansong.common;

/**
 *  对象转换工具类
 * @author yuansong
 *
 */
public class ConversionTool {
	
	private ConversionTool() {};
	
	/**
	 * 转换 java.sql.Date 至 java.util.Date
	 * @param java.sql.Date date
	 * @return
	 */
	public static java.util.Date convertSqlDateToUtilDate(java.sql.Date date){
		return new java.util.Date(date.getTime());
	}
	
	/**
	 * 转换 java.util.Date 至 java.sql.Date
	 * @param java.util.Date date
	 * @return
	 */
	public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date){
		return new java.sql.Date(date.getTime());
	}

}
