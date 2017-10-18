package com.freecrm.data.contacts;

import java.util.List;

public interface ContactsInfoDao {
	public void add(ContactsInfoEntity p);
	public void update(ContactsInfoEntity p);
	public void delete(int id);
	public List<ContactsInfoEntity> find_by_name(String name);
	public List<ContactsInfoEntity> find_by_id(int id);
	public List<ContactsInfoEntity> find_all();
}
