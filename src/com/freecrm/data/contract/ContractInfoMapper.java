package com.freecrm.data.contract;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ContractInfoMapper implements RowMapper<ContractInfoEntity> {
	public ContractInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		ContractInfoEntity user = new ContractInfoEntity();
		user.set_id(rs.getInt("id"));
		user.set_product_name(rs.getString("product_name"));
		user.set_product_Cnt(rs.getString("product_Cnt"));
		user.set_company_name(rs.getString("company_name"));
		user.set_price(rs.getString("price"));
		user.set_sum_money(rs.getString("sum_money"));
		user.set_rebate(rs.getString("rebate"));
		user.set_remark(rs.getString("remark"));
		user.set_confirm_confirm(rs.getString("confirm_confirm"));
		user.set_return_id(rs.getString("return_id"));
		user.set_invoice_id(rs.getString("invoice_id"));
		return user;
	}
}
