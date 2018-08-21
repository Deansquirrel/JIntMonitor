package com.yuansong.pojo.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yuansong.pojo.DingMessageConfig;

public class DingMessageConfigRowMapper implements RowMapper<DingMessageConfig> {

	@Override
	public DingMessageConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		DingMessageConfig config = new DingMessageConfig();
		config.setId(rs.getString("FId"));
		config.setRobotToken(rs.getString("FRobotToken"));
		return config;
	}

}
