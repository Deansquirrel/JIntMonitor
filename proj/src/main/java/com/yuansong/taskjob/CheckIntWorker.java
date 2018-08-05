package com.yuansong.taskjob;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.google.gson.Gson;
import com.yuansong.notify.DingMessageSender;
import com.yuansong.pojo.TaskConfig;

public class CheckIntWorker implements TaskWorker, Runnable {
	
	private final Logger logger = Logger.getLogger(CheckIntWorker.class);
	
	private static final String dirverClass = "net.sourceforge.jtds.jdbc.Driver";
	
	private TaskConfig taskConfig;
	
	private Gson mGson = new Gson();
	
	@Override
	protected void finalize() throws Throwable {
		
		super.finalize();
	}


	private DingMessageSender dms;
	
	public CheckIntWorker(TaskConfig taskConfig) {
		this.taskConfig = taskConfig;
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
			dms = new DingMessageSender(taskConfig.getRobotToken());
			dms.send(e.getMessage());
			return;
		}catch (DataAccessException e){
			logger.error(e.getMessage());
			e.printStackTrace();
			dms = new DingMessageSender(taskConfig.getRobotToken());
			dms.send(e.getMessage());
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
			dms = new DingMessageSender(taskConfig.getRobotToken());
			dms.send(taskConfig.getMsgTitle() + "\n" + msg);
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
		
//		BoneCPDataSource dataSource = new BoneCPDataSource();
//		dataSource.setDriverClass("net.sourceforge.jtds.jdbc.Driver");
//		dataSource.setJdbcUrl("jdbc:jtds:sqlserver://" + taskConfig.getServer() + ";DatabaseName=" + taskConfig.getDbName());
//		dataSource.setUsername(taskConfig.getUser());
//		dataSource.setPassword(taskConfig.getPwd());
//		dataSource.setMaxConnectionsPerPartition(30);
//		dataSource.setMinConnectionsPerPartition(10);
//		dataSource.setPartitionCount(3);
//		dataSource.setAcquireIncrement(5);
//		dataSource.setPoolAvailabilityThreshold(10);
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		jdbcTemplate.setQueryTimeout(30);
//		return jdbcTemplate;
	}
	
//	private void refreshConfig() {
//		BoneCPDataSource dataSource = new BoneCPDataSource();
//		dataSource.setDriverClass("net.sourceforge.jtds.jdbc.Driver");
//		dataSource.setJdbcUrl("jdbc:jtds:sqlserver://" + taskConfig.getServer() + ";DatabaseName=" + taskConfig.getDbName());
//		dataSource.setUsername(taskConfig.getUser());
//		dataSource.setPassword(taskConfig.getPwd());
//		dataSource.setMaxConnectionsPerPartition(30);
//		dataSource.setMinConnectionsPerPartition(10);
//		dataSource.setPartitionCount(3);
//		dataSource.setAcquireIncrement(5);
//		dataSource.setPoolAvailabilityThreshold(10);
//		
//		getJdbcTemplate().setDataSource(dataSource);
//	}

}
