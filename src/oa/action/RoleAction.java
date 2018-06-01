package oa.action;

import java.util.HashSet;
import java.util.List;

import oa.base.action.BaseAction;
import oa.domain.Privilege;
import oa.domain.Role;
import oa.domain.User;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller(value = "roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	private Long[] privilegeIds;

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	/** 列表 */
	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "addUI";
	}

	/** 添加 */
	public String add() throws Exception {

		// 保存到数据库中
		roleService.save(model);

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role); // 放到栈顶
		return "editUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 从数据库中取出原对象
		Role role = roleService.getById(model.getId());

		// 设置要修改的属性
		role.setName(model.getName());
		role.setDescription(model.getDescription());

		// 更新到数据库中
		roleService.update(role);

		return "toList";
	}

	// -----------------
	/**
	 * 设置权限页面
	 * 
	 * @return
	 */
	public String setPrivilegeUI() {
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().put("role", role);
		List<Privilege> privilegesList = privilegeService.findTopList();
		ActionContext.getContext().put("privilegeList", privilegesList);
		privilegeIds = new Long[role.getPrivileges().size()];
		int index=0;
		for(Privilege p:role.getPrivileges()){
			privilegeIds[index++]=p.getId();
		}
		return "setPrivilegeUI";
	}

	public String setPrivilege() {
		System.out.println("OKOK");
		Role role = roleService.getById(model.getId());
		System.out.println(privilegeIds.length);
		List<Privilege> privileges = privilegeService
				.getByIds(this.privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privileges));
		roleService.save(role);
		return "toList";
	}
}
