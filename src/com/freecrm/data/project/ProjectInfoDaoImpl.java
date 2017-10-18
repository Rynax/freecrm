package com.freecrm.data.project;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ProjectInfoDaoImpl extends JdbcDaoSupport implements ProjectInfoDao {
	@Override
	public void add(ProjectInfoEntity p) {
		String sql = "INSERT INTO `freecrm`.`project_info` VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_proname(), p.get_define_time(), p.get_leader(), p.get_contract_amount(), p.get_expected_month(), p.get_schedule(), 
				p.get_schedule_time(), p.get_actual_amount(), p.get_paid_amount(), p.get_return_amount(), p.get_paymentplan(), p.get_projectcycle(), p.get_contract_id());
	}
	
	@Override
	public void update(ProjectInfoEntity p) {
		String sql = "UPDATE `freecrm`.`project_info` SET proname=?, define_time=?, leader=?, contract_amount=?, expected_month=?, schedule=?, schedule_time=?, actual_amount=?, paid_amount=?, return_amount=?, paymentplan=?, projectcycle=?, contract_id=? WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_proname(), p.get_define_time(), p.get_leader(), p.get_contract_amount(), p.get_expected_month(), p.get_schedule(), 
				p.get_schedule_time(), p.get_actual_amount(), p.get_paid_amount(), p.get_return_amount(), p.get_paymentplan(), p.get_projectcycle(), p.get_contract_id(), p.get_id());
	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM `freecrm`.`project_info` WHERE id=?";
		this.getJdbcTemplate().update(sql, id);
	}
	
	@Override
	public List<ProjectInfoEntity> find_by_id(int id) {
		String sql = "SELECT * FROM `freecrm`.`project_info` WHERE id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new ProjectInfoMapper());
	}
	
	@Override
	public List<ProjectInfoEntity> find_all() {
		String sql = "SELECT * FROM `freecrm`.`project_info`";
		return this.getJdbcTemplate().query(sql, new ProjectInfoMapper());
	}
}
