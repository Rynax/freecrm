package com.freecrm.data.contract;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ContractInfoDaoImpl extends JdbcDaoSupport implements ContractInfoDao {
	@Override
	public void add(ContractInfoEntity p) {
		String sql = "INSERT INTO `freecrm`.`contract_info` VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_product_name(), p.get_product_Cnt(), p.get_company_name(), p.get_price(), p.get_sum_money(), p.get_rebate(), p.get_remark(), p.get_confirm_confirm(), p.get_return_id(), p.get_invoice_id());
	}
	
	@Override
	public void update(ContractInfoEntity p) {
		String sql = "UPDATE `freecrm`.`contract_info` SET product_name=?, product_Cnt=?, company_name=?, price=?, sum_money=? rebate=?, remark=?, confirm_confirm=?, return_id=?, invoice_id=? WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_product_name(), p.get_product_Cnt(), p.get_company_name(), p.get_price(), p.get_sum_money(), p.get_rebate(), p.get_remark(), p.get_confirm_confirm(), p.get_return_id(), p.get_invoice_id(), p.get_id());
	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM `freecrm`.`contract_info` WHERE id=?";
		this.getJdbcTemplate().update(sql, id);
	}
	
	@Override
	public List<ContractInfoEntity> find_by_name(String name) {
		String sql = "SELECT * FROM `freecrm`.`contract_info` WHERE product_name=?";
		return this.getJdbcTemplate().query(sql, new Object[]{name}, new ContractInfoMapper());
	}
	
	@Override
	public List<ContractInfoEntity> find_by_id(int id) {
		String sql = "SELECT * FROM `freecrm`.`contract_info` WHERE id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new ContractInfoMapper());
	}
	
	@Override
	public List<ContractInfoEntity> find_all() {
		String sql = "SELECT * FROM `freecrm`.`contract_info`";
		return this.getJdbcTemplate().query(sql, new ContractInfoMapper());
	}
}
