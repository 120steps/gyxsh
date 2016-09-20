package com.gyxsh.entities;

public class User {
	private Integer id;
	private String username;
	private String password;
	private Student student;
	//分身：1.管理员  2.学生会成员  3.申请者  
	private UserStatus userStatus;
	//账号是否可用，1为可用 0为不可用（假删除）
	private int able;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public UserStatus getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
	public int getAble() {
		return able;
	}
	public void setAble(int able) {
		this.able = able;
	}
	
}
