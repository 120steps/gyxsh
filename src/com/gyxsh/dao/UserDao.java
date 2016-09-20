package com.gyxsh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gyxsh.entities.User;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	/**
	 * 添加、修改User信息
	 * @param user
	 */
	public void saveOrUpdate(User user){
		getSession().saveOrUpdate(user);
	}
	
	/**
	 * 根据用户名查询信息(假删除也存在)
	 * @param username 用户名
	 * @return
	 */
	public User getByUsername(String username){
		String hql="FROM User u WHERE u.username=?";
		return (User) getSession().createQuery(hql).setParameter(0, username).uniqueResult();
	}
	
	/**
	 * 根据用户名 查询信息（不包括假删除）
	 * @param username 用户名
	 * @return
	 */
	public User getByUsernameWithoutDelete(String username){
		String hql="FROM User u WHERE u.username=? AND u.able=1";
		return (User) getSession().createQuery(hql).setParameter(0, username).uniqueResult();
	}
	
	/**
	 * 根据用户 id 查询信息
	 * @param id
	 * @return
	 */
	public User getById(int id){
		String hql="FROM User u WHERE u.id=? AND u.able=1";
		return (User) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
	}
	
	/**
	 * 根据用户 id 查询信息 包括假删除
	 * @param id
	 * @return
	 */
	public User getByIdAll(int id){
		String hql="FROM User u WHERE u.id=? ";
		return (User) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
	}
}
