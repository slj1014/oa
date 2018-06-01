package oa.action;

import java.util.List;

import javax.annotation.Resource;

import oa.base.action.BaseAction;
import oa.domain.Department;
import oa.service.DepartmentService;
import oa.utils.DepartmentUtils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import freemarker.core.DebugBreak;

@Controller(value="departmentAction")
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{
	private Long parentId;
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 列表
	 */
public String list(){
	List<Department> departmentList=null;
	if(parentId==null){
		departmentList=departmentService.findTopList();
	}else{
		departmentList=departmentService.findChildren(parentId);
		Department department=departmentService.getById(parentId);
		ActionContext.getContext().put("department", department);
	}
	ActionContext.getContext().put("departmentList", departmentList);
	return "list";
}
/**
 * 添加
 * @return
 */
public String addUI(){
	List<Department> topList=departmentService.findTopList();//显示第一层的部门
	List<Department> departmentList=DepartmentUtils.getAllDepartments(topList);
	ActionContext.getContext().put("departmentList", departmentList);
	return "addUI";
}
public String add(){
	//新建对象属性
	if(parentId!=null){
	  model.setParent(departmentService.getById(parentId));
	}
	departmentService.save(model);
	return "toList";
}
public String editUI(){
	Department department = departmentService.getById(model.getId());
	ActionContext.getContext().getValueStack().push(department);
	List<Department> departmentList=departmentService.findAll();
	ActionContext.getContext().put("departmentList", departmentList);
	if (department.getParent() != null) {
		parentId = department.getParent().getId();
	}
	return "addUI";
}
public String edit(){
	Department department=departmentService.getById(model.getId());
	department.setName(model.getName());
	department.setDescription(model.getDescription());
	department.setParent(departmentService.getById(parentId));
	departmentService.update(department);
	return "toList";
}
public String delete(){
	departmentService.delete(model.getId());
	return "toList";
}
}
