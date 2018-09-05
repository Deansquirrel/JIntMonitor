package com.yuansong.pojo.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yuansong.pojo.JiraSearchConfig;

public class JiraSearchConfigRowMapper implements RowMapper<JiraSearchConfig> {

	@Override
	public JiraSearchConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		JiraSearchConfig config = new JiraSearchConfig();
		config.setId(rs.getString("FId"));
		config.setServer(rs.getString("FServer"));
		config.setJql(rs.getString("FJql"));
		config.setUser(rs.getString("FUser"));
		config.setPwd(rs.getString("FPwd"));
		config.setCron(rs.getString("FCron"));
		config.setMsgTitle("");
		config.setMsgContent("");
		config.setTitle(rs.getString("FTitle"));
		config.setRemark(rs.getString("FRemark"));
		return config;
	}

}
