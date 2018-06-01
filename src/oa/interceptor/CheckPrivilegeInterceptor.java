package oa.interceptor;

import oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CheckPrivilegeInterceptor implements Interceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 获取当前的用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		// 获取当前访问的URL，并且去掉当前的应用程序的前缀（也就是namespace+actionName）
		String nameSpaceName=invocation.getProxy().getNamespace();
		String actionName=invocation.getProxy().getActionName();
		String privilegeUrl =null;
		if(nameSpaceName.endsWith("/")){
			privilegeUrl=nameSpaceName+actionName;
		}else{
			privilegeUrl=nameSpaceName+"/"+nameSpaceName;
		}
		//去掉开头的“/”(/oa/UserAction)
		//System.out.println(privilegeUrl);
		if(privilegeUrl.startsWith("/")){
			privilegeUrl=privilegeUrl.substring(1);
			//System.out.println(privilegeUrl);
		}
		// 如果用户未登入
		if (user == null) {
			if (privilegeUrl.startsWith("userAction_login")) {
               return invocation.invoke(); 
			}else{
				return "loginUI";
			}
		}//如果当前用户已经登录
		else{
			if(user.hasPrivilegeByUrl(privilegeUrl)){
				//如果有权限就放行
				return invocation.invoke();
			}else{
				return "noPrivilegeError";
			}
			
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
