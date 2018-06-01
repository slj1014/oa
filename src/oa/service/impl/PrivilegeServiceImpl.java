package oa.service.impl;

import java.util.List;

import oa.base.dao.impl.BaseDaoImpl;
import oa.domain.Privilege;
import oa.service.PrivilegeService;

import org.springframework.stereotype.Service;



@Service("privilegeService")
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege> implements PrivilegeService {

	@SuppressWarnings("unchecked")
	public List<Privilege> findTopList() {
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL")//
				.list();
	}

	@Override
	public List<String> getAllPrivileges() {
		// TODO Auto-generated method stub
		return getSession().createQuery("select distinct p.url from Privilege p where p.url is not null").list();
	}

}