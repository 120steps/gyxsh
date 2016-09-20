package com.gyxsh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gyxsh.entities.EnrollTime;


@Repository
public class EnrollTimeDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	/**
	 * 保存、修改时间
	 * @param enrollTime
	 */
	public void saveOrUpdate(EnrollTime enrollTime){
		getSession().saveOrUpdate(enrollTime);
	}
	
	/**
	 * 根据id返回 报名时间类
	 * @param id
	 * @return
	 */
	public EnrollTime getById(int id){
		String hql="FROM EnrollTime e WHERE e.id=?";
		return (EnrollTime) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
	}
}
