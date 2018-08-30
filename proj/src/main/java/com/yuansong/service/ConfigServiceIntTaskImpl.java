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
			"      ,[FCron]" + 
			"      ,[FCheckMax]" + 
			"      ,[FCheckMin]" + 
			"      ,[FMsgTitle]" + 
			"      ,[FMsgContent]" + 
			"      ,[FRemark]" + 
			"      ,[FTitle]" +
			"  FROM [IntTaskConfig]";
	
	private static final String SQL_ADD = ""
			+ "INSERT INTO [JMonitor].[dbo].[IntTaskConfig]" + 
			"           ([FId]" + 
			"           ,[FServer]" + 
			"           ,[FPort]" + 
			"           ,[FDbName]" + 
			"           ,[FDbUser]" + 
			"           ,[FDbPwd]" + 
			"           ,[FSearch]" + 
			"           ,[FCron]" + 
			"           ,[FCheckMax]" + 
			"           ,[FCheckMin]" + 
			"           ,[FMsgTitle]" + 
			"           ,[FMsgContent]" + 
			"           ,[FRemark]" + 
			"           ,[FTitle])" + 
			"     VALUES" + 
			"           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_DEL = ""
			+ "DELETE FROM [IntTaskConfig]" + 
			"      WHERE [FId] = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ConfigServiceIntTaskImpl() {
		super();
		logger.debug("ConfigServiceIntTaskImpl Init");
	}
	
	@Override
	public List<IntTaskConfig> getSetConfigList() {
		List<IntTaskConfig> list = null;
		try{
			list = jdbcTemplate.query(SQL_GETLIST, new IntTaskConfigRowMapper());
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return list;
	}

	@Override
	public String checkConfig(IntTaskConfig config) {
		return "";
	}

	@Override
	public void add(IntTaskConfig config) {
		try {
			jdbcTemplate.update(SQL_ADD, new Object[] {
					config.getId(),
					config.getServer(),
					config.getPort(),
					config.getDbName(),
					config.getUser(),
					config.getPwd(),
					config.getSearch(),
					config.getCron(),
					config.getCheckMax(),
					config.getCheckMin(),
					config.getMsgTitle(),
					config.getMsgContent(),
					config.getRemark(),
					config.getTitle()
			});
		}
		catch(Exception ex) {
			throw ex;
		}
	}

	@Override
	protected String getSqlDel() {
		return SQL_DEL;
	}
	
}
