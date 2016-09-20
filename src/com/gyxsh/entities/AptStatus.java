package com.gyxsh.entities;

public class AptStatus {
	/*
	 * 1-待审核
	 * 2-报名报审核通过
	 * 3-报名报审核未通过
	 * 4-第一轮面试通过
	 * 5-第一轮面试未通过
	 * 6-第二轮面试通过
	 * 7-第二轮面试未通过
	 */
	private Integer aptStatusId;
	private String aptStatusName;
	public Integer getAptStatusId() {
		return aptStatusId;
	}
	public void setAptStatusId(Integer aptStatusId) {
		this.aptStatusId = aptStatusId;
	}
	public String getAptStatusName() {
		return aptStatusName;
	}
	public void setAptStatusName(String aptStatusName) {
		this.aptStatusName = aptStatusName;
	}
	public AptStatus(Integer aptStatusId) {
		super();
		this.aptStatusId = aptStatusId;
	}
	public AptStatus() {
		super();
	}
	
}
