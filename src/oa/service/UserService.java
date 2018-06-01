package oa.service;


import oa.base.dao.BaseDao;
import oa.domain.User;

public interface UserService extends BaseDao<User>{

	User login(String loginName, String password);

	

}
