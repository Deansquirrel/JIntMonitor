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
			"      ,[FCron]" + 
			"      ,[FMsgTitle]" + 
			"      ,[FMsgContent]" +
			"      ,[FRemark]" + 
			"      ,[FTitle]" + 
			"  FROM [WebStateTaskConfig]";
	
	private static final String SQL_ADD = ""
			+ "INSERT INTO [WebStateTaskConfig]" + 
			"           ([FId]" + 
			"           ,[FUrl]" + 
			"           ,[FCron]" + 
			"           ,[FMsgTitle]" + 
			"           ,[FMsgContent]" + 
			"           ,[FRemark]" + 
			"           ,[FTitle])" + 
			"     VALUES" + 
			"           (?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_DEL = ""
			+ "DELETE FROM [WebStateTaskConfig]" + 
			"      WHERE [FId] = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public ConfigServiceWebStateImpl() {
		super();
		logger.debug("ConfigServiceWebStateImpl Init");
	}

	@Override
	public List<WebStateTaskConfig> getSetConfigList() {
		List<WebStateTaskConfig> list = null;
		try {
			list = jdbcTemplate.query(SQL_GETLIST, new WebStateTaskConfigRowMapper());
		}catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return list;
	}

	@Override
	protected boolean checkConfig(WebStateTaskConfig config) {
		return true;
	}

	@Override
	public void add(WebStateTaskConfig config) {
		try {
			jdbcTemplate.update(SQL_ADD, new Object[] {
					config.getId(),
					config.getUrl(),
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
