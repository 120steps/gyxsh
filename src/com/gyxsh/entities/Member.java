package com.gyxsh.entities;

public class Member extends Student {
	private MerStatus merStatus;
	private Department merDepartment;
	private int able;
	public MerStatus getMerStatus() {
		return merStatus;
	}
	public void setMerStatus(MerStatus merStatus) {
		this.merStatus = merStatus;
	}
	public Department getMerDepartment() {
		return merDepartment;
	}
	public void setMerDepartment(Department merDepartment) {
		this.merDepartment = merDepartment;
	}
	public int getAble() {
		return able;
	}
	public void setAble(int able) {
		this.able = able;
	}
	
}
