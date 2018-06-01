package oa.action;

import java.util.HashSet;
import java.util.List;

import oa.base.action.BaseAction;
import oa.domain.Department;
import oa.domain.Role;
import oa.domain.User;
import oa.utils.DepartmentUtils;
import oa.utils.HqlHelper;
import oa.utils.Md5Util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private Long departmentId;
	private Long[] roleIds;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * 列表
	 */
	public String list() {
	new HqlHelper(User.class).buildPageBeanForStruts2(pageNum, userService);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/**
	 * 添加页面
	 */
	public String addUI() {
		// 准备数据：departmentList，显示为树状结构
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据：roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "saveUI";
	}

	public String add() throws Exception {
		// 1，新建对象并设置属性（也可以使用model）
		Department department = departmentService.getById(departmentId);
		model.setDepartment(department);
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		String passwdMD5 = Md5Util.encodeByMd5("1234");
		model.setPassword(passwdMD5); // 默认密码为1234，应使用MD5摘要
		userService.save(model);
		return "toList";
	}

	public String editUI() {
		// 准备数据：departmentList，显示为树状结构
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		// 准备数据：roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles().size() > 0) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "saveUI";
	}
	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性
		// >> 普通属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >> 所属部门
		Department department = departmentService.getById(departmentId);
		user.setDepartment(department);
		// >> 关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}
	/** 初始化密码为“1234” */
	public String initPassword() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性（要使用MD5摘要）
		String passwdMD5 = Md5Util.encodeByMd5("1234");
		user.setPassword(passwdMD5);

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}
	//=================
	//登录
	/**
	 * 跳转到登陆界面
	 * @return
	 */
	public String loginUI(){
		return "loginUI";
	}
	/**
	 * 登录
	 * @return
	 */
	public String login(){
		User user=userService.login(model.getLoginName(),model.getPassword());
		if(user==null){
			this.addFieldError("login", "用户名密码不正确");
			return "loginUI";
		}else{
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
	}
	/**
	 * 退出登录
	 * @return
	 */
	public String loginout(){
		ActionContext.getContext().getSession().remove("user");
		return "loginOut";
	}
}
