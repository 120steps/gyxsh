package com.gyxsh.entities;

public class Department {
	private Integer deptId;
	private String deptName;
	//表示部门等级，主席团为0，其他为1
	private int deptStatus;
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getDeptStatus() {
		return deptStatus;
	}
	public void setDeptStatus(int deptStatus) {
		this.deptStatus = deptStatus;
	}
	
}
