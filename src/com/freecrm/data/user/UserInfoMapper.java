package com.freecrm.data.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserInfoMapper implements RowMapper<UserInfoEntity> {
	public UserInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		UserInfoEntity user = new UserInfoEntity();
		user.set_id(rs.getInt("id"));
		user.set_login_name(rs.getString("login_name"));
		user.set_login_pwd(rs.getString("login_pwd"));
		user.set_nick_name(rs.getString("nick_name"));
		user.set_user_status(rs.getInt("user_status"));
		return user;
	}
}
