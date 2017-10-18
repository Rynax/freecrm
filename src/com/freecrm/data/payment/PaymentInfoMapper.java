package com.freecrm.data.payment;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PaymentInfoMapper implements RowMapper<PaymentInfoEntity> {
	public PaymentInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		PaymentInfoEntity user = new PaymentInfoEntity();
		user.set_id(rs.getInt("id"));
		user.set_money(rs.getString("money"));
		user.set_proportion(rs.getString("proportion"));
		user.set_residue(rs.getString("residue"));
		user.set_payment_date(rs.getString("payment_date"));
		user.set_type(rs.getString("type"));
		return user;
	}
}
