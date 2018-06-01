package oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Controller;

import oa.base.dao.impl.BaseDaoImpl;
import oa.domain.User;
import oa.service.UserService;
import oa.utils.Md5Util;

@Controller("userService")
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

	@Override
	public User login(String loginName, String password) {
		// TODO Auto-generated method stub
		return (User) getSession()
				.createQuery("from User where loginName=? and password=?")
				.setParameter(0, loginName).setParameter(1, Md5Util.encodeByMd5(password))
				.uniqueResult();
	}

}
