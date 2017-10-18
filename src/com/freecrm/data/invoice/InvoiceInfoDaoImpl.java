package com.freecrm.data.invoice;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class InvoiceInfoDaoImpl extends JdbcDaoSupport implements InvoiceInfoDao {
	@Override
	public void add(InvoiceInfoEntity p) {
		String sql = "INSERT INTO `freecrm`.`invoice_info` VALUES(null, ?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_invoice_id(), p.get_money(), p.get_invoice_type(), p.get_invoice_date());
	}
	
	@Override
	public void update(InvoiceInfoEntity p) {
		String sql = "UPDATE `freecrm`.`invoice_info` SET invoice_id=?, money=?, invoice_type=?, invoice_date=? WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_invoice_id(), p.get_money(), p.get_invoice_type(), p.get_invoice_date(), p.get_id());
	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM `freecrm`.`invoice_info` WHERE id=?";
		this.getJdbcTemplate().update(sql, id);
	}
	
	@Override
	public List<InvoiceInfoEntity> find_by_id(int id) {
		String sql = "SELECT * FROM `freecrm`.`invoice_info` WHERE id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new InvoiceInfoMapper());
	}
	
	@Override
	public List<InvoiceInfoEntity> find_all() {
		String sql = "SELECT * FROM `freecrm`.`invoice_info`";
		return this.getJdbcTemplate().query(sql, new InvoiceInfoMapper());
	}
}
