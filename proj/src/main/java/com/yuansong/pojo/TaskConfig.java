package com.yuansong.pojo;

public class TaskConfig {
	
	private String server;
	private String dbName;
	private String user;
	private String pwd;
	private String search;
	
	private int checkMax;
	private int checkMin;
	
	private String msgTitle;
	private String msgContent;
	
	private String robotToken;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getCheckMax() {
		return checkMax;
	}

	public void setCheckMax(int checkMax) {
		this.checkMax = checkMax;
	}

	public int getCheckMin() {
		return checkMin;
	}

	public void setCheckMin(int checkMin) {
		this.checkMin = checkMin;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getRobotToken() {
		return robotToken;
	}

	public void setRobotToken(String robotToken) {
		this.robotToken = robotToken;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
