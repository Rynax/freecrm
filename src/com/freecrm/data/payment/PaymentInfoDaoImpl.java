package com.freecrm.data.payment;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class PaymentInfoDaoImpl extends JdbcDaoSupport implements PaymentInfoDao {
	@Override
	public void add(PaymentInfoEntity p) {
		String sql = "INSERT INTO `freecrm`.`payment_info` VALUES(null, ?, ?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_money(), p.get_proportion(), p.get_residue(), p.get_payment_date(), p.get_type());
	}
	
	@Override
	public void update(PaymentInfoEntity p) {
		String sql = "UPDATE `freecrm`.`payment_info` SET money=?, proportion=?, residue=?, payment_date=?, type=? WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_money(), p.get_proportion(), p.get_residue(), p.get_payment_date(), p.get_type(), p.get_id());
	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM `freecrm`.`payment_info` WHERE id=?";
		this.getJdbcTemplate().update(sql, id);
	}
	
	@Override
	public List<PaymentInfoEntity> find_by_id(int id) {
		String sql = "SELECT * FROM `freecrm`.`payment_info` WHERE id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new PaymentInfoMapper());
	}
	
	@Override
	public List<PaymentInfoEntity> find_all() {
		String sql = "SELECT * FROM `freecrm`.`payment_info`";
		return this.getJdbcTemplate().query(sql, new PaymentInfoMapper());
	}
}
