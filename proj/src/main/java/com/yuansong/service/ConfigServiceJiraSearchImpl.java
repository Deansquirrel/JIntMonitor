package com.yuansong.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yuansong.pojo.JiraSearchConfig;
import com.yuansong.pojo.rowMapper.JiraSearchConfigRowMapper;

@Service
public class ConfigServiceJiraSearchImpl extends ConfigService<JiraSearchConfig> {
	
	private final Logger logger = Logger.getLogger(ConfigServiceJiraSearchImpl.class);
	
	private static final String SQL_GETLIST = ""
			+ "SELECT [FId]" + 
			"      ,[FServer]" + 
			"      ,[FJql]" + 
			"      ,[FUser]" + 
			"      ,[FPwd]" + 
			"      ,[FCron]" + 
			"      ,[FRemark]" + 
			"      ,[FTitle]" + 
			"  FROM [JiraSearchConfig]";
	
	private static final String SQL_ADD = ""
			+ "INSERT INTO [JiraSearchConfig]" + 
			"           ([FId]" + 
			"           ,[FServer]" + 
			"           ,[FJql]" + 
			"           ,[FUser]" + 
			"           ,[FPwd]" + 
			"           ,[FCron]" + 
			"           ,[FRemark]" + 
			"           ,[FTitle])" + 
			"     VALUES" + 
			"           (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_DEL = ""
			+ "DELETE FROM [JiraSearchConfig]" + 
			"      WHERE [FId] = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ConfigServiceJiraSearchImpl() {
		super();
		logger.debug("ConfigServiceJiraSearchImpl Init");
	}

	@Override
	public List<JiraSearchConfig> getSetConfigList() {
		List<JiraSearchConfig> list = null;
		try {
			list = jdbcTemplate.query(SQL_GETLIST, new JiraSearchConfigRowMapper());
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return list;
	}

	@Override
	public String checkConfig(JiraSearchConfig config) {
		return "";
	}

	@Override
	public void add(JiraSearchConfig config) {
		try {
			jdbcTemplate.update(SQL_ADD, new Object[] {
					config.getId(),
					config.getServer(),
					config.getJql(),
					config.getUser(),
					config.getPwd(),
					config.getCron(),
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
