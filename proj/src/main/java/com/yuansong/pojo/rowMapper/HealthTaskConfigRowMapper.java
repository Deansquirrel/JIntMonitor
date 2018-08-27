package com.yuansong.pojo.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yuansong.pojo.HealthTaskConfig;

public class HealthTaskConfigRowMapper implements RowMapper<HealthTaskConfig> {

	@Override
	public HealthTaskConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		HealthTaskConfig config = new HealthTaskConfig();
		config.setId(rs.getString("FId"));
		config.setCorn(rs.getString("FCorn"));
		config.setMsgTitle(rs.getString("FMsgTitle"));
		config.setMsgContent(rs.getString("FMsgContent"));
		config.setTitle(rs.getString("FTitle"));
		config.setRemark(rs.getString("FRemark"));
		return config;
	}

}
