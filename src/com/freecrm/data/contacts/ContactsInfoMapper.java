package com.freecrm.data.contacts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ContactsInfoMapper implements RowMapper<ContactsInfoEntity> {
	public ContactsInfoEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		ContactsInfoEntity user = new ContactsInfoEntity();
		user.set_id(rs.getInt("id"));
		user.set_p_name(rs.getString("p_name"));
		user.set_p_duties(rs.getString("p_duties"));
		user.set_p_depart(rs.getString("p_depart"));
		user.set_p_tel(rs.getString("p_tel"));
		user.set_p_mail(rs.getString("p_mail"));
		return user;
	}
}
