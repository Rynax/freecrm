package com.freecrm.data.project;

import java.util.List;

public interface ProjectInfoDao {
	public void add(ProjectInfoEntity p);
	public void update(ProjectInfoEntity p);
	public void delete(int id);
	public List<ProjectInfoEntity> find_by_id(int id);
	public List<ProjectInfoEntity> find_all();
}
