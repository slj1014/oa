package oa;

import java.util.ArrayList;
import java.util.List;

import oa.domain.Privilege;
import oa.service.PrivilegeService;
import oa.utils.SpringUtils;

import org.junit.Test;

public class BeanTest extends SpringUtils{
	@Test
public void beanTest(){
	PrivilegeService f=(PrivilegeService) context.getBean("privilegeService");
    List<Privilege> list=f.findTopList();
}
}
