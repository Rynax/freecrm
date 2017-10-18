package com.freecrm.data.contract;

import java.util.List;

public interface ContractInfoDao {
	public void add(ContractInfoEntity p);
	public void update(ContractInfoEntity p);
	public void delete(int id);
	public List<ContractInfoEntity> find_by_name(String name);
	public List<ContractInfoEntity> find_by_id(int id);
	public List<ContractInfoEntity> find_all();
}
