package com.yuansong.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yuansong.pojo.HealthTaskConfig;
import com.yuansong.pojo.rowMapper.HealthTaskConfigRowMapper;

@Service
public class ConfigServiceHealthImpl extends ConfigService<HealthTaskConfig> {
	
	private final Logger logger = Logger.getLogger(ConfigServiceHealthImpl.class);
	
	private static final String SQL_GETLIST = ""
			+ "SELECT [FId]" + 
			"      ,[FCorn]" + 
			"      ,[FMsgTitle]" + 
			"      ,[FMsgContent]" + 
			"  FROM [HealthTaskConfig]";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ConfigServiceHealthImpl() {
		super();
		logger.debug("ConfigServiceHealthImpl Init. ");
	}

	@Override
	protected List<HealthTaskConfig> getSetConfigList() {
		return jdbcTemplate.query(SQL_GETLIST, new HealthTaskConfigRowMapper());
	}

	@Override
	protected boolean checkConfig(HealthTaskConfig config) {
		return true;
	}

//	@Override
//	protected boolean checkConfig(String fileName, HealthTaskConfig config) {
//		if(config.getCorn().equals("")) {
//			logger.error("HealthConfig Corn can not be null.【" + fileName + "】");
//			return false;
//		}
//		if(config.getMsgContent().equals("")) {
//			logger.error("HealthConfig MsgContent can not be null.【" + fileName + "】");
//			return false;
//		}
//		return true;
//	}
	
	

}
