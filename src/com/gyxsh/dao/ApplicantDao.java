package com.gyxsh.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gyxsh.entities.Applicant;

@Repository
public class ApplicantDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	/**
	 * 添加或修改申请者信息(包括假删除)
	 * @param applicant 申请者类
	 */
	public void saveOrUpdate(Applicant applicant){
		getSession().saveOrUpdate(applicant);
	}
	
	/**
	 * 获取所有Applicant的信息
	 * @return Applicant类的List集合
	 */
	public List<Applicant> getAll(){
		String hql="FROM Applicant a WHERE a.able=1";
		return getSession().createQuery(hql).list();
	}
	

	/**
	 * 根据申请者id来查询
	 * @param id 申请者id
	 * @return 对应id的申请者类
	 */
	public Applicant getById(int id){
		String hql="FROM Applicant a WHERE a.id=? AND a.able=1";
		return (Applicant) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
	}
	
	/**
	 * 根据申请者id来删除数据
	 * @param id 申请者id
	 */
	public void deleteById(int id){
		String hql="DELETE FROM Applicant a WHERE a.id=? ";
		getSession().createQuery(hql).setInteger(0, id).executeUpdate();
	}
	
	/**
	 * 根据申请者状态id和 当前处理部门来查询
	 * @param statusId 申请者状态id
	 * @param deptId 当前处理部门id
	 * @return
	 */
	public List<Applicant> getByStatusAndDept(int statusId,int deptId){
		String hql="FROM Applicant a WHERE a.aptStatus=? AND a.nowHandleDept=? AND a.able=1";
		return getSession().createQuery(hql).setInteger(0, statusId)
				.setInteger(1, deptId).list();
	}
	
	/**
	 * 根据申请者状态id来查询
	 * @param statusId 申请者状态id
	 * @return
	 */
	public List<Applicant> getByStatus(int statusId){
		String hql="FROM Applicant a WHERE a.aptStatus=? AND a.able=1";
		return getSession().createQuery(hql).setInteger(0, statusId).list();
	}
	
	/**
	 * 根据被调剂申请者状态id和 上一个处理部门来查询
	 * @param statusId 被调剂申请者状态id
	 * @param deptId 上一个处理部门id
	 * @return
	 */
	public List<Applicant> getByStatusAndPreDeptAdjusted(int statusId,int deptId){
		String hql="FROM Applicant a WHERE a.aptStatus=? AND a.preHandleDept=? "
				+ "AND a.able=1 AND a.preHandleDept!=a.nowHandleDept";
		return getSession().createQuery(hql).setInteger(0, statusId)
				.setInteger(1, deptId).list();
	}
	
	/**
	 * 根据被调剂申请者状态id来查询
	 * @param statusId 被调剂申请者状态id
	 * @return
	 */
	public List<Applicant> getByStatusAdjusted(int statusId){
		String hql="FROM Applicant a WHERE a.aptStatus=? AND a.able=1 "
				+ "AND a.preHandleDept!=a.nowHandleDept";
		return getSession().createQuery(hql).setInteger(0, statusId).list();
	}
	
	
	/**
	 * 根据 淘汰申请状态 查询
	 * @return
	 */
	public List<Applicant> getByFail(){
		String hql="FROM Applicant a WHERE a.able=1 AND (a.aptStatus=3 OR a.aptStatus=5 "
				+ "OR a.aptStatus=7 OR a.aptStatus=9 )";
		return getSession().createQuery(hql).list();
	}
	
	/**
	 * 根据 淘汰申请状态 和 部门id查询
	 * @return
	 */
	public List<Applicant> getByFailAndDept(int deptId){
		String hql="FROM Applicant a WHERE a.able=1 AND a.nowHandleDept=? AND "
				+ "(a.aptStatus=3 OR a.aptStatus=5 OR a.aptStatus=7 OR a.aptStatus=9 )";
		return getSession().createQuery(hql).setInteger(0, deptId).list();
	}
	
	//下面均为分页代码
	/**
	 * 根据申请者状态查询信息(分页)
	 * @param aptStatusId 申请者状态对应的id
	 * @param offset 起始数据数
	 * @param length 每次查询记录数
	 * @return 相应状态的申请者list
	 */
	public List<Applicant> queryForPageByStatus(int statusId,int offset,int length){
		String hql="FROM Applicant a WHERE a.aptStatus=? AND a.able=1";
		return getSession().createQuery(hql).setInteger(0, statusId)
				.setFirstResult(offset).setMaxResults(length).list();
	}
	
	/**
	 * 根据申请者状态查询信息 总记录数(分页)
	 * @param aptStatusId 申请者状态对应的id
	 * @return
	 */
	public Integer getRowCountByStatus(int statusId){
		String hql="FROM Applicant a WHERE a.aptStatus=? AND a.able=1";
		return getSession().createQuery(hql).setInteger(0, statusId).list().size();
	}
	
	
	/**
	 * 根据申请者的 状态和现在处理部门 查询申请者信息(分页查询)
	 * @param statusId 状态id
	 * @param deptId 正在处理部门的id
	 * @param offset 起始数据位置
	 * @param length 每次查询记录数
	 * @return
	 */
	public List<Applicant> queryForPageByStatusAndDept(int statusId,int deptId,
			int offset,int length){
		String hql="FROM Applicant a WHERE a.aptStatus=? AND a.nowHandleDept=? "
				+ "AND a.able=1";
		return getSession().createQuery(hql).setInteger(0, statusId).setInteger(1, deptId)
				.setFirstResult(offset).setMaxResults(length).list();
	}
	
	/**
	 * 根据申请者的 状态和现在处理部门 查询申请者信息记录总条数（分页）
	 * @param statusId
	 * @param deptId
	 * @return
	 */
	public Integer getRowCountByStatusAndDept(int statusId,int deptId){
		String hql="FROM Applicant a WHERE a.aptStatus=? AND a.nowHandleDept=? "
				+ "AND a.able=1";
		return getSession().createQuery(hql).setInteger(0, statusId).setInteger(1, deptId)
				.list().size();
	}
	
	/**
	 * 根据申请者的 状态和现在处理部门 和姓名 进行模糊查询（分页）
	 * @param name 模糊查询的姓名
	 * @param statusId 申请者状态
	 * @param deptId  处理部门id
	 * @param offset 起始数据位置
	 * @param length 每次查询记录数
	 * @return
	 */
	public List<Applicant> queryForPageByLikeNameAndStatusAndDept(String name,int statusId,int deptId
			,int offset,int length){
		String hql="FROM Applicant a WHERE a.name LIKE :name "
				+ "AND a.aptStatus=:statusId AND a.nowHandleDept=:deptId "
				+ "AND a.able=1";
		return getSession().createQuery(hql).setString("name", "%"+name+"%").setInteger("statusId", statusId)
				.setInteger("deptId", deptId).setFirstResult(offset).setMaxResults(length).list();
	}
	
	/**
	 * 根据申请者的 状态和现在处理部门 和姓名 进行模糊查询 返回总记录数(分页)
	 * @param name 模糊查询的姓名
	 * @param statusId 申请者状态
	 * @param deptId 处理部门id
	 * @return 总记录数
	 */
	public Integer getRowCountByLikeNameAndStatusAndDept(String name,int statusId,int deptId){
		String hql="FROM Applicant a WHERE a.name LIKE :name AND a.aptStatus=:statusId "
				+ "AND a.nowHandleDept=:deptId AND a.able=1";
		return getSession().createQuery(hql).setString("name", "%"+name+"%").setInteger("statusId", statusId)
						.setInteger("deptId", deptId).list().size();
	}
	
	/**
	 * 根据申请者的 状态和姓名 进行模糊查询 (分页)
	 * @param name 模糊查询的姓名
	 * @param statusId 状态id
	 * @param offset 第一条记录开始的位置
	 * @param length 每一次查询记录数
	 * @return
	 */
	public List<Applicant> queryForPageByLikeNameAndStatus(String name,int statusId,
			int offset,int length){
		String hql="FROM Applicant a WHERE a.name LIKE ? AND a.aptStatus=? AND a.able=1";
		return getSession().createQuery(hql).setString(0, "%"+name+"%").setInteger(1, statusId)
				.setFirstResult(offset).setMaxResults(length).list();
	}
	
	
	/**
	 * 根据申请者的 状态和姓名 进行模糊查询 返回总记录数(分页)
	 * @param name 模糊查询的姓名
	 * @param statusId 状态id
	 * @return 记录数
	 */
	public Integer getRowCountByLikeNameAndStatus(String name,int statusId){
		String hql="FROM Applicant a WHERE a.name LIKE ? AND a.aptStatus=? "
				+ "AND a.able=1";
		return getSession().createQuery(hql).setString(0, "%"+name+"%").setInteger(1, statusId)
				.list().size();
	}
	
	/**
	 * 根据被调剂的申请者的 状态查询信息
	 * @param statusId 被调剂申请者的状态id
	 * @param offset 起始记录数
	 * @param length 一次查询显示记录数
	 * @return
	 */
	public List<Applicant> queryForPageByStatusAdjusted(int statusId,int offset,int length){
		String hql="FROM Applicant a WHERE a.aptStatus=? "
				+ "AND a.preHandleDept!=a.nowHandleDept AND a.able=1";
		return getSession().createQuery(hql).setInteger(0, statusId)
				.setFirstResult(offset).setMaxResults(length).list();
	}
	
	/**
	 * 根据 被调剂的申请者的 状态 查询 总记录数（分页）
	 * @param statusId 被调剂申请者的状态id
	 * @return
	 */
	public Integer getRowCountByStatusAdjusted(int statusId){
		String hql="FROM Applicant a WHERE a.aptStatus=? "
				+ "AND a.preHandleDept!=a.nowHandleDept AND a.able=1";
		return getSession().createQuery(hql).setInteger(0, statusId).list().size();
	}
	
	/**
	 * 根据被调剂申请者的 状态和上一个处理部门 查询申请者信息(分页查询)
	 * @param statusId 被调剂申请者的状态id
	 * @param deptId 上一个处理部门的id
	 * @param offset 起始数据位置
	 * @param length 每次查询记录数
	 * @return
	 */
	public List<Applicant> queryForPageByStatusAndDeptAdjusted(int statusId,int deptId,
			int offset,int length){
		String hql="FROM Applicant a WHERE a.aptStatus=? AND a.preHandleDept=? "
				+ "AND a.preHandleDept!=a.nowHandleDept AND a.able=1";
		return getSession().createQuery(hql).setInteger(0, statusId).setInteger(1, deptId)
				.setFirstResult(offset).setMaxResults(length).list();
	}
	
	/**
	 * 根据被调剂申请者的 状态和上一个处理部门 查询申请者信息记录总条数（分页）
	 * @param statusId 被调剂申请者状态的id
	 * @param deptId 上一个处理部门的id
	 * @return
	 */
	public Integer getRowCountByStatusAndDeptAdjusted(int statusId,int deptId){
		String hql="FROM Applicant a WHERE a.aptStatus=? AND a.preHandleDept=? "
				+ "AND a.preHandleDept!=a.nowHandleDept AND a.able=1";
		return getSession().createQuery(hql).setInteger(0, statusId).setInteger(1, deptId)
				.list().size();
	}
	
	/**
	 * 根据被调剂申请者的 状态和上一个处理部门 和姓名 进行模糊查询（分页）
	 * @param name 模糊查询的姓名
	 * @param statusId 被调剂申请者状态
	 * @param deptId  上一个处理部门id
	 * @param offset 起始数据位置
	 * @param length 每次查询记录数
	 * @return
	 */
	public List<Applicant> queryForPageByLikeNameAndStatusAndDeptAdjusted(String name
			,int statusId,int deptId,int offset,int length){
		String hql="FROM Applicant a WHERE a.name LIKE :name "
				+ "AND a.aptStatus=:statusId AND a.preHandleDept=:deptId "
				+ "AND a.preHandleDept!=a.nowHandleDept AND a.able=1";
		return getSession().createQuery(hql).setString("name", "%"+name+"%").setInteger("statusId", statusId)
				.setInteger("deptId", deptId).setFirstResult(offset).setMaxResults(length).list();
	}
	
	/**
	 * 根据被调剂申请者的 状态和上一个处理部门 和姓名 进行模糊查询 返回总记录数(分页)
	 * @param name 模糊查询的姓名
	 * @param statusId 被调剂申请者状态
	 * @param deptId 上一个处理部门id
	 * @return 总记录数
	 */
	public Integer getRowCountByLikeNameAndStatusAndDeptAdjusted(String name
			,int statusId,int deptId){
		String hql="FROM Applicant a WHERE a.name LIKE ? AND a.aptStatus=? "
				+ "AND a.preHandleDept=? AND a.preHandleDept!=a.nowHandleDept "
				+ "AND a.able=1";
		return getSession().createQuery(hql).setString(0, "%"+name+"%")
				.setInteger(1, statusId).setInteger(2, deptId).list().size();
	}
	
	/**
	 * 根据被调剂申请者的 状态和姓名 进行模糊查询 (分页)
	 * @param name 模糊查询的姓名
	 * @param statusId 被调剂申请者状态id
	 * @param offset 第一条记录开始的位置
	 * @param length 每一次查询记录数
	 * @return
	 */
	public List<Applicant> queryForPageByLikeNameAndStatusAdjusted(String name,int statusId,
			int offset,int length){
		String hql="FROM Applicant a WHERE a.name LIKE ? AND a.aptStatus=? "
				+ "AND a.preHandleDept!=a.nowHandleDept AND a.able=1";
		return getSession().createQuery(hql).setString(0, "%"+name+"%").setInteger(1, statusId)
				.setFirstResult(offset).setMaxResults(length).list();
	}
	
	
	/**
	 * 根据被调剂申请者的 状态和姓名 进行模糊查询 返回总记录数(分页)
	 * @param name 模糊查询的姓名
	 * @param statusId 被调剂申请者状态id
	 * @return 记录数
	 */
	public Integer getRowCountByLikeNameAndStatusAdjusted(String name,int statusId){
		String hql="FROM Applicant a WHERE a.name LIKE ? AND a.aptStatus=? "
				+ "AND a.preHandleDept!=a.nowHandleDept AND a.able=1";
		return getSession().createQuery(hql).setString(0, "%"+name+"%").setInteger(1, statusId)
				.list().size();
	}
	
	/**
	 * 根据 淘汰 申请者的 状态和当前处理部门 进行查询（分页）
	 * @param statusId  淘汰 申请者状态
	 * @param deptId  当前处理部门id
	 * @param offset 起始数据位置
	 * @param length 每次查询记录数
	 * @return
	 */
	public List<Applicant> queryForPageByFailAndDept(int deptId,int offset,int length){
		String hql="FROM Applicant a WHERE a.nowHandleDept=:deptId AND a.able=1"
				+ "AND (a.aptStatus=3 OR a.aptStatus=5 OR a.aptStatus=7 "
				+ "OR a.aptStatus=9 )";
		return getSession().createQuery(hql).setInteger("deptId", deptId)
				.setFirstResult(offset).setMaxResults(length).list();
	}
	
	/**
	 * 根据 淘汰 申请者的 状态和当前处理部门 进行查询 返回总记录数(分页)
	 * @param statusId 淘汰 申请者状态
	 * @param deptId 当前处理部门id
	 * @return 总记录数
	 */
	public Integer getRowCountByFailAndDept(int deptId){
		String hql="FROM Applicant a WHERE a.nowHandleDept=:deptId "
				+ "AND a.able=1 "
				+ "AND (a.aptStatus=3 OR a.aptStatus=5 OR a.aptStatus=7 "
				+ "OR a.aptStatus=9 )";
		return getSession().createQuery(hql)
				.setInteger("deptId", deptId).list().size();
	}
	
	/**
	 * 根据淘汰申请者的 状态 进行查询 (分页)
	 * @param statusId 淘汰申请者状态id
	 * @param offset 第一条记录开始的位置
	 * @param length 每一次查询记录数
	 * @return
	 */
	public List<Applicant> queryForPageByFail(int offset,int length){
		String hql="FROM Applicant a WHERE a.able=1 "
				+ "AND (a.aptStatus=3 OR a.aptStatus=5 OR a.aptStatus=7 "
				+ "OR a.aptStatus=9 )";
		return getSession().createQuery(hql)
				.setFirstResult(offset).setMaxResults(length).list();
	}
	
	
	/**
	 * 根据淘汰申请者的 状态 进行查询 返回总记录数(分页)
	 * @param statusId 淘汰申请者状态id
	 * @return 记录数
	 */
	public Integer getRowCountByFail(){
		String hql="FROM Applicant a WHERE a.able=1 "
				+ "AND (a.aptStatus=3 OR a.aptStatus=5 OR a.aptStatus=7 "
				+ "OR a.aptStatus=9 )";
		return getSession().createQuery(hql).list().size();
	}
	
	/**
	 * 根据 淘汰 申请者的 状态和当前处理部门 和姓名 进行模糊查询（分页）
	 * @param name 模糊查询的姓名
	 * @param statusId  淘汰 申请者状态
	 * @param deptId  当前处理部门id
	 * @param offset 起始数据位置
	 * @param length 每次查询记录数
	 * @return
	 */
	public List<Applicant> queryForPageByLikeNameAndFailAndDept(String name
			,int deptId,int offset,int length){
		String hql="FROM Applicant a WHERE a.name LIKE :name "
				+ "AND a.nowHandleDept=:deptId AND a.able=1 "
				+ "AND (a.aptStatus=3 OR a.aptStatus=5 OR a.aptStatus=7 "
				+ "OR a.aptStatus=9 )";
		return getSession().createQuery(hql).setString("name", "%"+name+"%")
				.setInteger("deptId", deptId).setFirstResult(offset).setMaxResults(length).list();
	}
	
	/**
	 * 根据 淘汰 申请者的 状态和当前处理部门 和姓名 进行模糊查询 返回总记录数(分页)
	 * @param name 模糊查询的姓名
	 * @param statusId 淘汰 申请者状态
	 * @param deptId 当前处理部门id
	 * @return 总记录数
	 */
	public Integer getRowCountByLikeNameAndFailAndDept(String name
			,int deptId){
		String hql="FROM Applicant a WHERE a.name LIKE :name "
				+ "AND a.nowHandleDept=:deptId AND a.able=1 "
				+ "AND (a.aptStatus=3 OR a.aptStatus=5 OR a.aptStatus=7 "
				+ "OR a.aptStatus=9 )";
		return getSession().createQuery(hql).setString("name", "%"+name+"%")
				.setInteger("deptId", deptId).list().size();
	}
	
	/**
	 * 根据淘汰申请者的 状态和姓名 进行模糊查询 (分页)
	 * @param name 模糊查询的姓名
	 * @param statusId 淘汰申请者状态id
	 * @param offset 第一条记录开始的位置
	 * @param length 每一次查询记录数
	 * @return
	 */
	public List<Applicant> queryForPageByLikeNameAndFail(String name,
			int offset,int length){
		String hql="FROM Applicant a WHERE a.name LIKE ? AND a.able=1 "
				+ "AND (a.aptStatus=3 OR a.aptStatus=5 OR a.aptStatus=7 "
				+ "OR a.aptStatus=9 )";
		return getSession().createQuery(hql).setString(0, "%"+name+"%")
				.setFirstResult(offset).setMaxResults(length).list();
	}
	
	
	/**
	 * 根据淘汰申请者的 状态和姓名 进行模糊查询 返回总记录数(分页)
	 * @param name 模糊查询的姓名
	 * @param statusId 淘汰申请者状态id
	 * @return 记录数
	 */
	public Integer getRowCountByLikeNameAndFail(String name){
		String hql="FROM Applicant a WHERE a.name LIKE ? AND a.able=1 "
				+ "AND (a.aptStatus=3 OR a.aptStatus=5 OR a.aptStatus=7 "
				+ "OR a.aptStatus=9 )";
		return getSession().createQuery(hql).setString(0, "%"+name+"%")
				.list().size();
	}
}
