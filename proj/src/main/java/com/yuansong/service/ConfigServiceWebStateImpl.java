package com.yuansong.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yuansong.pojo.WebStateTaskConfig;
import com.yuansong.pojo.rowMapper.WebStateTaskConfigRowMapper;

@Service
public class ConfigServiceWebStateImpl extends ConfigService<WebStateTaskConfig> {
	
	private final Logger logger = Logger.getLogger(ConfigServiceWebStateImpl.class);
	
	private static final String SQL_GETLIST = ""
			+ "SELECT [FId]" + 
			"      ,[FUrl]" + 
			"      ,[FCorn]" + 
			"      ,[FMsgTitle]" + 
			"      ,[FMsgContent]" + 
			"  FROM [WebStateTaskConfig]";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ConfigServiceWebStateImpl() {
		super();
		logger.debug("ConfigServiceWebStateImpl Init");
	}

	@Override
	protected List<WebStateTaskConfig> getSetConfigList() {
		return jdbcTemplate.query(SQL_GETLIST, new WebStateTaskConfigRowMapper());
	}

	@Override
	protected boolean checkConfig(WebStateTaskConfig config) {
		return true;
	}



//	@Override
//	protected boolean checkConfig(String fileName, WebStateTaskConfig config) {
//		if(config == null) {
//			logger.error("WebStateTaskConfig is null.【" + fileName + "】");
//			return false;
//		}
//		if(config.getUrl().equals("")) {
//			logger.error("WebStateTaskConfig url can not be null.【" + fileName + "】");
//			return false;
//		}
//		if(config.getCorn().equals("")) {
//			logger.error("WebStateTaskConfig corn can not be null.【" + fileName + "】");
//			return false;
//		}
//		if(config.getMsgContent().equals("")) {
//			logger.error("WebStateTaskConfig msgContent can not be null.【" + fileName + "】");
//			return false;
//		}
//		return true;
//	}

}
