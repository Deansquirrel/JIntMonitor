package com.yuansong.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yuansong.pojo.IntTaskConfig;
import com.yuansong.pojo.rowMapper.IntTaskConfigRowMapper;

@Service
public class ConfigServiceIntTaskImpl extends ConfigService<IntTaskConfig>{
	
	private final Logger logger = Logger.getLogger(ConfigServiceIntTaskImpl.class);
	
	private static final String SQL_GETLIST = ""
			+ "SELECT [FId]" + 
			"      ,[FServer]" + 
			"      ,[FPort]" + 
			"      ,[FDbName]" + 
			"      ,[FDbUser]" + 
			"      ,[FDbPwd]" + 
			"      ,[FSearch]" + 
			"      ,[FCorn]" + 
			"      ,[FCheckMax]" + 
			"      ,[FCheckMin]" + 
			"      ,[FMsgTitle]" + 
			"      ,[FMsgContent]" + 
			"  FROM [IntTaskConfig]";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ConfigServiceIntTaskImpl() {
		super();
		logger.debug("ConfigServiceIntTaskImpl Init");
	}
	
	@Override
	protected List<IntTaskConfig> getSetConfigList() {
		return jdbcTemplate.query(SQL_GETLIST, new IntTaskConfigRowMapper());
	}

	@Override
	protected boolean checkConfig(IntTaskConfig config) {
		return true;
	}

//	@Override
//	protected boolean checkConfig(String fileName, IntTaskConfig config) {
//		if(config == null) {
//			logger.error("IntTaskConfig is null.【" + fileName + "】");
//			return false;
//		}
//		if(config.getServer().equals("")) {
//			logger.error("IntTaskConfig Server can not be null.【" + fileName + "】");
//			return false;
//		}
//		if(config.getDbName().equals("")) {
//			logger.error("IntTaskConfig DbName can not be null.【" + fileName + "】");
//			return false;
//		}
//		if(config.getUser().equals("")) {
//			logger.error("IntTaskConfig User can not be null.【" + fileName + "】");
//			return false;
//		}
//		if(config.getSearch().equals("")) {
//			logger.error("IntTaskConfig Search can not be null.【" + fileName + "】");
//			return false;
//		}
//		if(config.getCorn().equals("")) {
//			logger.error("IntTaskConfig Corn can not be null.【" + fileName + "】");
//			return false;
//		}
//		if(config.getMsgContent().equals("")) {
//			logger.error("IntTaskConfig MsgContent can not be null.【" + fileName + "】");
//			return false;
//		}
//		return true;
//	}
	
}
