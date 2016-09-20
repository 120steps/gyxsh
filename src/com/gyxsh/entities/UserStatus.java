package com.gyxsh.entities;

public class UserStatus {
	/*
	 * 1-管理员
	 * 2-学生会成员
	 * 3-申请者
	 */
	private Integer userStatusId;
	private String userStatusName;
	public Integer getUserStatusId() {
		return userStatusId;
	}
	public void setUserStatusId(Integer userStatusId) {
		this.userStatusId = userStatusId;
	}
	public String getUserStatusName() {
		return userStatusName;
	}
	public void setUserStatusName(String userStatusName) {
		this.userStatusName = userStatusName;
	}
	public UserStatus(Integer userStatusId) {
		super();
		this.userStatusId = userStatusId;
	}
	public UserStatus() {
		super();
	}
	
}
