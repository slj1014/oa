package oa.base.action;



import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import oa.domain.User;
import oa.service.DepartmentService;
import oa.service.ForumService;
import oa.service.PrivilegeService;
import oa.service.ReplyService;
import oa.service.RoleService;
import oa.service.TopicService;
import oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	@Resource(name="roleService")
	protected RoleService roleService;
	@Resource(name="departmentService")
	protected DepartmentService departmentService;
	@Resource(name="userService")
	protected UserService userService;
	@Resource(name="privilegeService")
    protected PrivilegeService privilegeService;
	@Resource(name="forumService")
	protected ForumService forumService;
	@Resource(name="topicService")
	protected TopicService topicService;
	@Resource(name="replyService")
	protected ReplyService replyService;
	protected int pageNum=1;//当前页面的页号,默认是1

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	protected T model;

	public BaseAction() {
		try {
			// 得到model的类型信息
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[0];

			// 通过反射生成model的实例
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}
	/**
	 * 获取当前的登录用户
	 */
	protected User getCurrentUser(){
		return (User) ActionContext.getContext().getSession().get("user");
	}
}
