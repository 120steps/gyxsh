package com.gyxsh.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gyxsh.entities.Department;

@Repository
public class DepartmentDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	/**
	 * 新增、修改部门
	 * @param department department类
	 */
	public void saveOrUpdate(Department department){
		getSession().saveOrUpdate(department);
	}
	
	/**
	 * 查询所有的部门
	 * @return 部门的list集合
	 */
	public List<Department> getAll(){
		String hql="FROM Department";
		return getSession().createQuery(hql).list();
	}
	
	/**
	 * 查询所有可以申请的部门(除主席团)
	 * @return 可申请部门的list集合
	 */
	public List<Department> getAbled(){
		String hql="FROM Department d WHERE d.deptStatus=1";
		return getSession().createQuery(hql).list();
	}
	
	/**
	 * 根据部门id删除部门
	 * @param deptId 部门id
	 */
	public void deleteById(int deptId){
		String hql="DELETE Department d WHERE d.deptId=?";
		getSession().createQuery(hql).setInteger(0, deptId).executeUpdate();
	}
	
	/**
	 * 根据部门id查询部门
	 * @param deptId 部门id
	 * @return 相应部门类
	 */
	public Department getById(int deptId){
		String hql="FROM Department d WHERE d.deptId=?";
		return (Department) getSession().createQuery(hql).setInteger(0, deptId).uniqueResult();
	}
	
}
