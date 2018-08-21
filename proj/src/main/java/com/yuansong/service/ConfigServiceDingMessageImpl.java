package com.yuansong.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yuansong.pojo.DingMessageConfig;
import com.yuansong.pojo.rowMapper.DingMessageConfigRowMapper;

@Service
public class ConfigServiceDingMessageImpl extends ConfigService<DingMessageConfig> {
	
	private final Logger logger = Logger.getLogger(ConfigServiceDingMessageImpl.class);
	
	
	private static final String SQL_GETLIST = ""
			+ "SELECT [FId]" + 
			"      ,[FRobotToken]" + 
			"  FROM [DingMessageSender]";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ConfigServiceDingMessageImpl() {
		super();
		logger.debug("ConfigServiceDingMessageImpl Init. ");
	}

	@Override
	protected List<DingMessageConfig> getSetConfigList() {
		return jdbcTemplate.query(SQL_GETLIST, new DingMessageConfigRowMapper());
	}
	
	
	@Override
	protected boolean checkConfig(DingMessageConfig config) {
		return true;
	}
}
