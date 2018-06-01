package oa.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import oa.domain.Department;

public class DepartmentUtils {

	public static List<Department> getAllDepartments(List<Department> topList) {
		// TODO Auto-generated method stub
		List<Department> departments=new ArrayList<Department>();
		walkDepartmentTrees(topList, "┣", departments);
		return departments;
	}

	private static void walkDepartmentTrees(Collection<Department> topList,
			String prefix, List<Department> departments) {
		// TODO Auto-generated method stub
		for(Department top:topList){
			// 顶点
			Department copy = new Department(); // 原对象是在Session中的对象，是持久化状态，所以要使用副本。
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			departments.add(copy);
			 //子树
		   walkDepartmentTrees(top.getChildren(), "　" + prefix, departments); // 使用的是全角的空格
		}
	}

}
