package com.freecrm.data.contacts;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ContactsInfoDaoImpl extends JdbcDaoSupport implements ContactsInfoDao {
	@Override
	public void add(ContactsInfoEntity p) {
		String sql = "INSERT INTO `freecrm`.`phonebook_info` VALUES(null, ?, ?, ?, ?, ?)";
		this.getJdbcTemplate().update(sql, p.get_p_name(), p.get_p_duties(), p.get_p_depart(), p.get_p_tel(), p.get_p_mail());
	}
	
	@Override
	public void update(ContactsInfoEntity p) {
		String sql = "UPDATE `freecrm`.`phonebook_info` SET p_name=?, p_duties=?, p_depart=?, p_tel=?, p_mail=? WHERE id=?";
		this.getJdbcTemplate().update(sql, p.get_p_name(), p.get_p_duties(), p.get_p_depart(), p.get_p_tel(), p.get_p_mail(), p.get_id());
	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM `freecrm`.`phonebook_info` WHERE id=?";
		this.getJdbcTemplate().update(sql, id);
	}
	
	@Override
	public List<ContactsInfoEntity> find_by_name(String name) {
		String sql = "SELECT * FROM `freecrm`.`phonebook_info` WHERE p_name=?";
		return this.getJdbcTemplate().query(sql, new Object[]{name}, new ContactsInfoMapper());
	}
	
	@Override
	public List<ContactsInfoEntity> find_by_id(int id) {
		String sql = "SELECT * FROM `freecrm`.`phonebook_info` WHERE id=?";
		return this.getJdbcTemplate().query(sql, new Object[]{id}, new ContactsInfoMapper());
	}
	
	@Override
	public List<ContactsInfoEntity> find_all() {
		String sql = "SELECT * FROM `freecrm`.`phonebook_info`";
		return this.getJdbcTemplate().query(sql, new ContactsInfoMapper());
	}
}
