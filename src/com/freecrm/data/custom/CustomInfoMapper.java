package com.freecrm.data.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomInfoMapper implements RowMapper<CustomInfoEntity> {
	public CustomInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		CustomInfoEntity user = new CustomInfoEntity();
		user.set_id(rs.getInt("id"));
		user.set_cus_name(rs.getString("cus_name"));
		user.set_cus_type(rs.getString("cus_type"));
		user.set_linkman(rs.getString("linkman"));
		user.set_address(rs.getString("address"));
		user.set_areas(rs.getString("areas"));
		user.set_create_time(rs.getTimestamp("create_time"));
		user.set_leader(rs.getString("leader"));
		return user;
	}
}
