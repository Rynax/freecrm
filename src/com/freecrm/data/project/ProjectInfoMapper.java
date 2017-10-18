package com.freecrm.data.project;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProjectInfoMapper implements RowMapper<ProjectInfoEntity> {
	public ProjectInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		ProjectInfoEntity user = new ProjectInfoEntity();
		user.set_id(rs.getInt("id"));
		user.set_proname(rs.getString("proname"));
		user.set_define_time(rs.getTimestamp("define_time"));
		user.set_leader(rs.getString("leader"));
		user.set_contract_amount(rs.getString("contract_amount"));
		user.set_expected_month(rs.getString("expected_month"));
		user.set_schedule(rs.getString("schedule"));
		user.set_schedule_time(rs.getTimestamp("schedule_time"));
		user.set_actual_amount(rs.getString("actual_amount"));
		user.set_paid_amount(rs.getString("paid_amount"));
		user.set_return_amount(rs.getString("return_amount"));
		user.set_paymentplan(rs.getTimestamp("paymentplan"));
		user.set_projectcycle(rs.getInt("projectcycle"));
		user.set_contract_id(rs.getString("contract_id"));
		return user;
	}
}
