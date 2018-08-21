package com.yuansong.taskjob;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.yuansong.notify.MessageSender;
import com.yuansong.pojo.IntTaskConfig;

public class TaskWorkerInt extends TaskWorkerAbstractImpl<IntTaskConfig> {
	
	private final Logger logger = Logger.getLogger(TaskWorkerInt.class);
	
	private static final String dirverClass = "net.sourceforge.jtds.jdbc.Driver";

	public TaskWorkerInt(IntTaskConfig config, List<MessageSender> list) {
		super(config, list);
	}

	@Override
	protected String check() {
		IntTaskConfig taskConfig = getConfig();
		if(taskConfig == null) {
			logger.warn("IntTaskConfig is null");
			return "IntTaskConfig is null";
		}
		
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		Integer value;
		
		try {
			 value = jdbcTemplate.queryForObject(taskConfig.getSearch(), Integer.class);
		}catch (InvalidResultSetAccessException e){
			logger.error(e.getMessage());
			e.printStackTrace();
			return e.getMessage();
		}catch (DataAccessException e){
			logger.error(e.getMessage());
			e.printStackTrace();
			return e.getMessage();
		}finally {
			try {
				jdbcTemplate.getDataSource().getConnection().close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		
		if(value <= getConfig().getCheckMin() || value >= taskConfig.getCheckMax()) {
			String msg = taskConfig.getMsgContent();
			msg = msg.replace("title", String.valueOf(value));
			msg = msg.replace("Max", String.valueOf(taskConfig.getCheckMax()));
			msg = msg.replace("Min", String.valueOf(taskConfig.getCheckMin()));
			logger.info(msg);
			if(!taskConfig.getMsgTitle().equals("")) {
				msg = taskConfig.getMsgTitle() + "\n" + msg; 
			}
			return msg;
		}
		else {
			return "";
		}
	}
	
	private JdbcTemplate getJdbcTemplate() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource ();
		dataSource.setDriverClassName(dirverClass);
		dataSource.setUrl("jdbc:jtds:sqlserver://" + getConfig().getServer() + ":" + getConfig().getPort() +";DatabaseName=" + getConfig().getDbName());
		dataSource.setUsername(getConfig().getUser());
		dataSource.setPassword(getConfig().getPwd());
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setQueryTimeout(30);
		jdbcTemplate.setDataSource(dataSource);
		
		return jdbcTemplate;
	}

}
