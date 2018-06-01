package oa.service.impl;

import java.util.List;



import org.springframework.stereotype.Service;


import oa.base.dao.impl.BaseDaoImpl;
import oa.domain.Department;
import oa.service.DepartmentService;
@Service(value="departmentService")
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService{

	@Override
	public List<Department> findChildren(Long oid) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Department d where d.parent.id=:oid").setLong("oid", oid).list();
	}

	@Override
	public List<Department> findTopList() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Department d where d.parent IS NULL").list();
	}

	

}
