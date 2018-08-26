package com.yuansong.pojo.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yuansong.pojo.IntTaskConfig;

public class IntTaskConfigRowMapper implements RowMapper<IntTaskConfig> {

	@Override
	public IntTaskConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
		IntTaskConfig config = new IntTaskConfig();
		config.setId(rs.getString("FId"));
		config.setServer(rs.getString("FServer"));
		config.setPort(rs.getString("FPort"));
		config.setDbName(rs.getString("FDbName"));
		config.setUser(rs.getString("FDbUser"));
		config.setPwd(rs.getString("FDbPwd"));
		config.setSearch(rs.getString("FSearch"));
		config.setCorn(rs.getString("FCorn"));
		config.setCheckMax(rs.getInt("FCheckMax"));
		config.setCheckMin(rs.getInt("FCheckMin"));;
		config.setMsgTitle(rs.getString("FMsgTitle"));
		config.setMsgContent(rs.getString("FMsgContent"));
		config.setTitle(rs.getString("FTitle"));
		config.setRemark(rs.getString("FRemark"));
		return config;
	}
}
