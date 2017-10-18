package com.freecrm.data.custom;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CustomInfoDaoImpl extends JdbcDaoSupport implements CustomInfoDao {
	@Override
	public void add(CustomInfoEntity p) {
		String sql = "INSERT INTO `freecrm`.`custom_info` (cus_name, cus_type, linkman, address, areas, leader) VALUES(?, ?, ?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_cus_name(), p.get_cus_type(), p.get_linkman(), p.get_address(), p.get_areas(), p.get_leader());
	}
	
	@Override
	public void update(CustomInfoEntity p) {
		String sql = "UPDATE `freecrm`.`custom_info` SET cus_name=?, cus_type=?, linkman=?, address=?, areas=?, leader=? WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_cus_name(), p.get_cus_type(), p.get_linkman(), p.get_address(), p.get_areas(), p.get_leader(), p.get_id());
	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM `freecrm`.`custom_info` WHERE id=?";
		this.getJdbcTemplate().update(sql, id);
	}
	
	@Override
	public List<CustomInfoEntity> find_by_name(String name) {
		String sql = "SELECT * FROM `freecrm`.`custom_info` WHERE cus_name=?";
		return this.getJdbcTemplate().query(sql, new Object[]{name}, new CustomInfoMapper());
	}
	
	@Override
	public List<CustomInfoEntity> find_by_id(int id) {
		String sql = "SELECT * FROM `freecrm`.`custom_info` WHERE id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new CustomInfoMapper());
	}
	
	@Override
	public List<CustomInfoEntity> find_all() {
		String sql = "SELECT * FROM `freecrm`.`custom_info`";
		return this.getJdbcTemplate().query(sql, new CustomInfoMapper());
	}
}
