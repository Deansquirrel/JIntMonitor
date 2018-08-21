package com.yuansong.pojo.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yuansong.pojo.WebStateTaskConfig;

public class WebStateTaskConfigRowMapper implements RowMapper<WebStateTaskConfig> {

	@Override
	public WebStateTaskConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		WebStateTaskConfig config = new WebStateTaskConfig();
		config.setId(rs.getString("FId"));
		config.setUrl(rs.getString("FUrl"));
		config.setCorn(rs.getString("FCorn"));
		config.setMsgTitle(rs.getString("FMsgTitle"));
		config.setMsgContent(rs.getString("FMsgContent"));
		return config;
	}

}
