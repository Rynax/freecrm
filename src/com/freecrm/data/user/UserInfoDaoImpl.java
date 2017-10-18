package com.freecrm.data.user;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserInfoDaoImpl extends JdbcDaoSupport implements UserInfoDao {
	@Override
	public void add(UserInfoEntity p) {
		String sql = "INSERT INTO `freecrm`.`user_info` VALUES(null, ?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_login_name(), p.get_login_pwd(), p.get_nick_name(), p.get_user_status());
	}
	
	@Override
	public void update(UserInfoEntity p) {
		String sql = "UPDATE `freecrm`.`user_info` SET login_name=?, login_pwd=?, nick_name=?, user_status=? WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_login_name(), p.get_login_pwd(), p.get_nick_name(), p.get_user_status(), p.get_id());
	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM `freecrm`.`user_info` WHERE id=?";
		this.getJdbcTemplate().update(sql, id);
	}
	
	@Override
	public List<UserInfoEntity> find_by_name(String name) {
		String sql = "SELECT * FROM `freecrm`.`user_info` WHERE login_name=?";
		return this.getJdbcTemplate().query(sql, new Object[]{name}, new UserInfoMapper());
	}
	
	@Override
	public List<UserInfoEntity> find_by_id(int id) {
		String sql = "SELECT * FROM `freecrm`.`user_info` WHERE id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new UserInfoMapper());
	}
	
	@Override
	public List<UserInfoEntity> find_all() {
		String sql = "SELECT * FROM `freecrm`.`user_info`";
		return this.getJdbcTemplate().query(sql, new UserInfoMapper());
	}
}
