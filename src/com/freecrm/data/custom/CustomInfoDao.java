package com.freecrm.data.custom;

import java.util.List;

public interface CustomInfoDao {
	public void add(CustomInfoEntity p);
	public void update(CustomInfoEntity p);
	public void delete(int id);
	public List<CustomInfoEntity> find_by_name(String name);
	public List<CustomInfoEntity> find_by_id(int id);
	public List<CustomInfoEntity> find_all();
}
