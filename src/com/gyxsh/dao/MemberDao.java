package com.gyxsh.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gyxsh.entities.Applicant;
import com.gyxsh.entities.Member;

@Repository
public class MemberDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	/**
	 * 通过成员id来查询 成员信息
	 * @param id 成员id
	 * @return
	 */
	public Member getById(int id){
		String hql="FROM Member m  WHERE m.id=? AND m.able=1";
		return (Member) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
	}
	
	/**
	 * 通过成员id来查询 成员信息
	 * @param id 成员id
	 * @return
	 */
	public Member getByIdAll(int id){
		String hql="FROM Member m  WHERE m.id=? ";
		return (Member) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
	}
	
	/**
	 * 新增、修改 学生会成员的信息
	 * @param member 成员类
	 */
	public void saveOrUpdate(Member member){
		getSession().saveOrUpdate(member);
	}
	
	/**
	 * 查询所有成员信息 （able=1）
	 * @return
	 */
	public List<Member> getAll(){
		String hql="FROM Member m WHERE m.able=1";
		return getSession().createQuery(hql).list();
	}
	
	
	/**
	 * 根据成员 部门id查询信息(分页)
	 * @param detpId 部门id
	 * @param offset 起始数据数
	 * @param length 每次查询记录数
	 * @return 相应状态的申请者list
	 */
	public List<Member> queryForPageByDept(int detpId,int offset,int length){
		String hql="FROM Member m left join fetch m.user "
				+ "WHERE m.merDepartment=? AND m.able=1";
		return getSession().createQuery(hql).setInteger(0, detpId)
				.setFirstResult(offset).setMaxResults(length).list();
	}
	
	/**
	 * 根据成员 部门id查询信息 总记录数(分页)
	 * @param deptId 部门id
	 * @return
	 */
	public Integer getRowCountByDept(int deptId){
		String hql="FROM Member m left join fetch m.user "
				+ "WHERE m.merDepartment=? AND m.able=1";
		return getSession().createQuery(hql).setInteger(0, deptId).list().size();
	}
	
	/**
	 * 所有可用成员(able=1) 查询信息(分页)
	 * @param offset 起始数据数
	 * @param length 每次查询记录数
	 * @return 相应状态的申请者list
	 */
	public List<Member> queryForPageAll(int offset,int length){
		String hql="FROM Member m left join fetch m.user WHERE m.able=1";
		return getSession().createQuery(hql).setFirstResult(offset).setMaxResults(length).list();
	}
	
	/**
	 * 所有可用成员（able=1） 总记录数(分页)
	 * @return
	 */
	public Integer getRowCountAll(){
		String hql="FROM Member m left join fetch m.user WHERE m.able=1";
		return getSession().createQuery(hql).list().size();
	}
	
	
	/**
	 * 根据成员 部门id查询信息(分页) able=0
	 * @param detpId 部门id
	 * @param offset 起始数据数
	 * @param length 每次查询记录数
	 * @return 相应状态的申请者list
	 */
	public List<Member> queryForPageByDeptDisable(int detpId,int offset,int length){
		String hql="FROM Member m left join fetch m.user "
				+ "WHERE m.merDepartment=? AND m.able=0";
		return getSession().createQuery(hql).setInteger(0, detpId)
				.setFirstResult(offset).setMaxResults(length).list();
	}
	
	/** 
	 * 根据成员 部门id查询信息 总记录数(分页) able=0
	 * @param deptId 部门id
	 * @return
	 */
	public Integer getRowCountByDeptDisable(int deptId){
		String hql="FROM Member m left join fetch m.user "
				+ "WHERE m.merDepartment=? AND m.able=0";
		return getSession().createQuery(hql).setInteger(0, deptId).list().size();
	}
	
	/**
	 * 所有不可用成员(able=0) 查询信息(分页)
	 * @param offset 起始数据数
	 * @param length 每次查询记录数
	 * @return 相应状态的申请者list
	 */
	public List<Member> queryForPageAllDisable(int offset,int length){
		String hql="FROM Member m left join fetch m.user WHERE m.able=0";
		return getSession().createQuery(hql).setFirstResult(offset).setMaxResults(length).list();
	}
	
	/**
	 * 所有不可用成员（able=0） 总记录数(分页)
	 * @return
	 */
	public Integer getRowCountAllDisable(){
		String hql="FROM Member m left join fetch m.user WHERE m.able=0";
		return getSession().createQuery(hql).list().size();
	}
}
