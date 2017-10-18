package com.freecrm.data.project_detail;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ProjectDetailInfoDaoImpl extends JdbcDaoSupport implements ProjectDetailInfoDao {
	@Override
	public void add(ProjectDetailInfoEntity p) {
		String sql = "INSERT INTO `freecrm`.`project_detail` VALUES(null, ?, ?, ?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_contact_time(), p.get_contactway(), p.get_participant(), p.get_salescontent(), p.get_propulsionplan(), p.get_propulstime());
	}
	
	@Override
	public void update(ProjectDetailInfoEntity p) {
		String sql = "UPDATE `freecrm`.`project_detail` SET contact_time=?, contactway=?, participant=?, salescontent=?, propulsionplan=?, propulstime=? WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_contact_time(), p.get_contactway(), p.get_participant(), p.get_salescontent(), p.get_propulsionplan(), p.get_propulstime(), p.get_id());
	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM `freecrm`.`project_detail` WHERE id=?";
		this.getJdbcTemplate().update(sql, id);
	}
	
	@Override
	public List<ProjectDetailInfoEntity> find_by_id(int id) {
		String sql = "SELECT * FROM `freecrm`.`project_detail` WHERE id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new ProjectDetailInfoMapper());
	}
	
	@Override
	public List<ProjectDetailInfoEntity> find_all() {
		String sql = "SELECT * FROM `freecrm`.`project_detail`";
		return this.getJdbcTemplate().query(sql, new ProjectDetailInfoMapper());
	}
}
