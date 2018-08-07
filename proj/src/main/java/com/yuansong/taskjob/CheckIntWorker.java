package com.yuansong.taskjob;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.google.gson.Gson;
import com.yuansong.notify.MessageSender;
import com.yuansong.pojo.IntTaskConfig;

public class CheckIntWorker implements TaskWorker, Runnable {
	
	private final Logger logger = Logger.getLogger(CheckIntWorker.class);
	
	private static final String dirverClass = "net.sourceforge.jtds.jdbc.Driver";
	
	private IntTaskConfig taskConfig;
	
	private List<MessageSender> notifyList;
	
	private Gson mGson = new Gson();
	
	@Override
	protected void finalize() throws Throwable {
		
		super.finalize();
	}
	
	public CheckIntWorker(IntTaskConfig taskConfig, List<MessageSender> list) {
		logger.debug("CheckIntWorker Init - " + mGson.toJson(taskConfig));
		this.taskConfig = taskConfig;
		this.notifyList = list;
	}

	@Override
	public void run() {
		if(taskConfig == null) {
			logger.warn("taskConfig is null");
			return;
		}

		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		Integer value;
		try {
			 value = jdbcTemplate.queryForObject(taskConfig.getSearch(), Integer.class);
		}catch (InvalidResultSetAccessException e){
			logger.error(e.getMessage());
			e.printStackTrace();
			sendMsg(e.getMessage());
			return;
		}catch (DataAccessException e){
			logger.error(e.getMessage());
			e.printStackTrace();
			sendMsg(e.getMessage());
			return;
		}finally {
			try {
				jdbcTemplate.getDataSource().getConnection().close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		
		if(value <= taskConfig.getCheckMin() || value >= taskConfig.getCheckMax()) {
			String msg = taskConfig.getMsgContent();
			msg = msg.replace("title", String.valueOf(value));
			msg = msg.replace("Max", String.valueOf(taskConfig.getCheckMax()));
			msg = msg.replace("Min", String.valueOf(taskConfig.getCheckMin()));
			logger.info(msg);
			sendMsg(taskConfig.getMsgTitle() + "\n" + msg);
		}
	}

	@Override
	public String getTaskConfig() {
		return mGson.toJson(taskConfig);
	}
	
	
	private JdbcTemplate getJdbcTemplate() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource ();
		dataSource.setDriverClassName(dirverClass);
		dataSource.setUrl("jdbc:jtds:sqlserver://" + taskConfig.getServer() + ";DatabaseName=" + taskConfig.getDbName());
		dataSource.setUsername(taskConfig.getUser());
		dataSource.setPassword(taskConfig.getPwd());
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setQueryTimeout(30);
		jdbcTemplate.setDataSource(dataSource);
		
		return jdbcTemplate;
	}
	
	private void sendMsg(String msg) {
		for(MessageSender ms : notifyList) {
			ms.send(msg);
		}
	}

}
