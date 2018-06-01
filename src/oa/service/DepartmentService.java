package oa.service;

import java.util.List;

import oa.base.dao.BaseDao;
import oa.domain.Department;

public interface DepartmentService extends BaseDao<Department>{

	

	List<Department> findChildren(Long oid);

	List<Department> findTopList();
	
}
