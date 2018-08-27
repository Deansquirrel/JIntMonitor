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
			"      ,[FCron]" + 
			"      ,[FMsgTitle]" + 
			"      ,[FMsgContent]" + 
			"      ,[FRemark]" + 
			"      ,[FTitle]" +
			"  FROM [HealthTaskConfig]";
	
	private static final String SQL_ADD = ""
			+ "INSERT INTO [HealthTaskConfig]" + 
			"           ([FId]" + 
			"           ,[FCron]" + 
			"           ,[FMsgTitle]" + 
			"           ,[FMsgContent]" + 
			"           ,[FRemark]" + 
			"           ,[FTitle])" + 
			"     VALUES" + 
			"           (?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_DEL = ""
			+ "DELETE FROM [HealthTaskConfig]" + 
			"      WHERE [FId] = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ConfigServiceHealthImpl() {
		super();
		logger.debug("ConfigServiceHealthImpl Init. ");
	}

	@Override
	public List<HealthTaskConfig> getSetConfigList() {
		List<HealthTaskConfig> list = null;
		try{
			list = jdbcTemplate.query(SQL_GETLIST, new HealthTaskConfigRowMapper());
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return list;
	}

	@Override
	protected boolean checkConfig(HealthTaskConfig config) {
		return true;
	}

	@Override
	public void add(HealthTaskConfig config) {
		try {
			jdbcTemplate.update(SQL_ADD, new Object[] {
					config.getId(),
					config.getCron(),
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
