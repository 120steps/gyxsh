package com.gyxsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyxsh.dao.DisposeTimeDao;
import com.gyxsh.entities.DisposeTime;

@Service
public class DisposeTimeService {
	@Autowired
	private DisposeTimeDao disposeTimeDao;
	
	/**
	 * 更新 处理时间
	 * @param handleTime
	 */
	public void saveOrUpdate(DisposeTime disposeTime){
		disposeTimeDao.saveOrUpdate(disposeTime);
	}
	
	/**
	 * 根据id 返回处理时间类
	 * @param id
	 * @return
	 */
	public DisposeTime getById(int id){
		return disposeTimeDao.getById(id);
	}
}
