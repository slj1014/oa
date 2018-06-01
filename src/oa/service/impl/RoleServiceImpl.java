package oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import oa.base.dao.impl.BaseDaoImpl;

import oa.domain.Role;
import oa.service.RoleService;

@Service(value = "roleService")

public class RoleServiceImpl extends BaseDaoImpl<Role> implements RoleService {
	
}
