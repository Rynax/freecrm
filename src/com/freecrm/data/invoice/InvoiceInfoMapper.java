package com.freecrm.data.invoice;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class InvoiceInfoMapper implements RowMapper<InvoiceInfoEntity> {
	public InvoiceInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		InvoiceInfoEntity user = new InvoiceInfoEntity();
		user.set_id(rs.getInt("id"));
		user.set_invoice_id(rs.getString("invoice_id"));
		user.set_money(rs.getString("money"));
		user.set_invoice_type(rs.getString("invoice_type"));
		user.set_invoice_date(rs.getString("invoice_date"));
		return user;
	}
}
