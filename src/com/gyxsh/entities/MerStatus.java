package com.gyxsh.entities;

public class MerStatus {
	//表明成员身份
	/*
	 * 1-主席
	 * 2-副主席
	 * 3-部长
	 * 4-副部长
	 * 5-干事
	 */
	private Integer merStatusId;
	private String merStatusName;
	public Integer getMerStatusId() {
		return merStatusId;
	}
	public void setMerStatusId(Integer merStatusId) {
		this.merStatusId = merStatusId;
	}
	public String getMerStatusName() {
		return merStatusName;
	}
	public void setMerStatusName(String merStatusName) {
		this.merStatusName = merStatusName;
	}
	public MerStatus(Integer merStatusId) {
		super();
		this.merStatusId = merStatusId;
	}
	public MerStatus() {
		super();
	}
	
}
