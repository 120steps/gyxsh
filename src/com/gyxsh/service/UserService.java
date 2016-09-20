package com.gyxsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyxsh.dao.UserDao;
import com.gyxsh.entities.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	/**
	 * 新增、修改User信息
	 * @param user
	 */
	public void saveOrUpdate(User user){
		userDao.saveOrUpdate(user);
	}
	
	/**
	 * 根据用户名查询 用户是否存在(假删除也存在)
	 * @param username 用户名
	 * @return
	 */
	public User getByUsername(String username){
		return userDao.getByUsername(username);
	}
	
	/**
	 * 根据用户名查询 用户信息（不包括假删除）
	 * @param username
	 * @return
	 */
	public User getByUsernameWithoutDelete(String username){
		return userDao.getByUsernameWithoutDelete(username);
	}
	
	/**
	 * 根据用户id查询信息
	 * @param id 用户id
	 * @return
	 */
	public User getById(int id){
		return userDao.getById(id);
	}
	
	/**
	 * 根据用户id查询信息 包括软删除
	 * @param id 用户id
	 * @return
	 */
	public User getByIdAll(int id){
		return userDao.getByIdAll(id);
	}
	
	/**
	 * 根据用户id 软删除
	 */
	public void deleteById(int id){
		User user=userDao.getById(id);
		user.setAble(0);
		userDao.saveOrUpdate(user);
	}
}
