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
			"      ,[FRemark]" + 
			"      ,[FTitle]" +
			"  FROM [DingMessageSender]";
	
	private static final String SQL_ADD = ""
			+ "INSERT INTO [DingMessageSender]" + 
			"           ([FId]" + 
			"           ,[FRobotToken]" + 
			"           ,[FRemark]" + 
			"           ,[FTitle])" + 
			"     VALUES" + 
			"           (? , ? , ? , ?)";
	
	private static final String SQL_DEL = ""
			+ "DELETE FROM [DingMessageSender]" + 
			"      WHERE [FId] = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ConfigServiceDingMessageImpl() {
		super();
		logger.debug("ConfigServiceDingMessageImpl Init. ");
	}

	@Override
	public List<DingMessageConfig> getSetConfigList() {
		List<DingMessageConfig> list = null;
		try {
			list = jdbcTemplate.query(SQL_GETLIST, new DingMessageConfigRowMapper());
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return list;
	}
	
	
	@Override
	public String checkConfig(DingMessageConfig config) {
		return "";
	}

	@Override
	public void add(DingMessageConfig config) {
		try {
			jdbcTemplate.update(SQL_ADD, new Object[] {
					config.getId(),
					config.getRobotToken(),
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
