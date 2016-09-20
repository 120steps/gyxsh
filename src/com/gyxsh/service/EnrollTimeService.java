package com.gyxsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyxsh.dao.EnrollTimeDao;
import com.gyxsh.entities.EnrollTime;

@Service
public class EnrollTimeService {
	@Autowired
	private EnrollTimeDao enrollTimeDao;
	
	/**
	 * 更新 处理时间
	 * @param enrollTime
	 */
	public void saveOrUpdate(EnrollTime enrollTime){
		enrollTimeDao.saveOrUpdate(enrollTime);
	}
	
	/**
	 * 根据id 获取 报名时间类
	 * @param id
	 * @return
	 */
	public EnrollTime getById(int id){
		return enrollTimeDao.getById(id);
	}
}
