package com.gyxsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyxsh.dao.DepartmentDao;
import com.gyxsh.entities.Department;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * 新增、修改部门
	 * @param department 部门类
	 */
	public void saveOrUpdate(Department department){
		departmentDao.saveOrUpdate(department);
	}
	
	/**
	 * 获取所有部门
	 * @return 所有部门的list集合
	 */
	public List<Department> getAll(){
		return departmentDao.getAll();
	}
	
	/**
	 * 获取所有可以申请的部门
	 * @return 所有可申请的部门的list集合
	 */
	public List<Department> getAbled(){
		return departmentDao.getAbled();
	}
	
	/**
	 * 根据部门ID删除部门
	 * @param deptId 部门id
	 */
	public void deleteById(int deptId){
		departmentDao.deleteById(deptId);
	}
	
	/**
	 * 根据部门id获取部门
	 * @param deptId 部门id
	 * @return 对应id的相应部门
	 */
	public Department getById(int deptId){
		return departmentDao.getById(deptId);
	}
}
