package oa.service;

import java.util.List;

import oa.base.dao.BaseDao;
import oa.domain.Privilege;

public interface PrivilegeService extends BaseDao<Privilege>{

	List<Privilege> findTopList();

	/**
	 * 查询所有权限URl的集合
	 * @return
	 */
	List<String> getAllPrivileges();

}
