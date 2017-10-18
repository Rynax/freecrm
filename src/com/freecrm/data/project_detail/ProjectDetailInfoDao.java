package com.freecrm.data.project_detail;

import java.util.List;

public interface ProjectDetailInfoDao {
	public void add(ProjectDetailInfoEntity p);
	public void update(ProjectDetailInfoEntity p);
	public void delete(int id);
	public List<ProjectDetailInfoEntity> find_by_id(int id);
	public List<ProjectDetailInfoEntity> find_all();
}
