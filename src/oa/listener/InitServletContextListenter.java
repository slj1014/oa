package oa.listener;


import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import oa.domain.Privilege;
import oa.service.PrivilegeService;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitServletContextListenter implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		ServletContext application=arg0.getServletContext();
		//得到service实例对象
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(application);//在监听器的容器中查找Application容器
		PrivilegeService privilegeService=(PrivilegeService) ac.getBean("privilegeService");
		//准备所有顶级权限的集
		List<Privilege> topPrivilegeList=privilegeService.findTopList();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		System.out.println("--准备好顶级权限的数据--");
		//准备所有全年URL的集合
	     List<String> allPrivilegeUrls=privilegeService.getAllPrivileges();
	     application.setAttribute("allPrivileges", allPrivilegeUrls);
	     System.out.println("--已经准备所有权限URL的数据--");

	}

}
