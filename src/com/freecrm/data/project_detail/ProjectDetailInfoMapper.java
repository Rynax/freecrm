package com.freecrm.data.project_detail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProjectDetailInfoMapper implements RowMapper<ProjectDetailInfoEntity> {
	public ProjectDetailInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		ProjectDetailInfoEntity user = new ProjectDetailInfoEntity();
		user.set_id(rs.getInt("id"));
		user.set_contact_time(rs.getTimestamp("contact_time"));
		user.set_contactway(rs.getString("contactway"));
		user.set_participant(rs.getString("participant"));
		user.set_salescontent(rs.getString("salescontent"));
		user.set_propulsionplan(rs.getString("propulsionplan"));
		user.set_propulstime(rs.getTimestamp("propulstime"));
		return user;
	}
}
