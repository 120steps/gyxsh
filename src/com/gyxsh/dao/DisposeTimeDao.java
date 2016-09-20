package com.gyxsh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gyxsh.entities.DisposeTime;

@Repository
public class DisposeTimeDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	/**
	 * 保存、修改时间
	 * @param handleTime
	 */
	public void saveOrUpdate(DisposeTime disposeTime){
		getSession().saveOrUpdate(disposeTime);
	}
	
	/**
	 * 根据id返回 处理时间类
	 * @param id
	 * @return
	 */
	public DisposeTime getById(int id){
		String hql="FROM DisposeTime d WHERE d.id=?";
		return (DisposeTime) getSession().createQuery(hql).setInteger(0, id).uniqueResult();
	}
}
