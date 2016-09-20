package com.gyxsh.entities;

public class Applicant extends Student {
	private Department aptFirstTag;
	private Department aptSecondTag;
	private Department preHandleDept;
	private Department nowHandleDept;
	private String aptOtherOrg;
	private String aptSpeciality;
	private String aptInterest;
	private String aptExperience;
	private String aptKnow;
	private String aptExpectation;
	private AptStatus aptStatus;
	private int aptEmailed;//表示该申请者 是否邮件通知 0为未通知 1为通知
	private int able;//表示信息是否假删除 0为假删除 1为正常
	private String imgSrc;
	public Department getAptFirstTag() {
		return aptFirstTag;
	}
	public void setAptFirstTag(Department aptFirstTag) {
		this.aptFirstTag = aptFirstTag;
	}
	public Department getAptSecondTag() {
		return aptSecondTag;
	}
	public void setAptSecondTag(Department aptSecondTag) {
		this.aptSecondTag = aptSecondTag;
	}
	public String getAptOtherOrg() {
		return aptOtherOrg;
	}
	public void setAptOtherOrg(String aptOtherOrg) {
		this.aptOtherOrg = aptOtherOrg;
	}
	public String getAptSpeciality() {
		return aptSpeciality;
	}
	public void setAptSpeciality(String aptSpeciality) {
		this.aptSpeciality = aptSpeciality;
	}
	public String getAptInterest() {
		return aptInterest;
	}
	public void setAptInterest(String aptInterest) {
		this.aptInterest = aptInterest;
	}
	public String getAptExperience() {
		return aptExperience;
	}
	public void setAptExperience(String aptExperience) {
		this.aptExperience = aptExperience;
	}
	public String getAptKnow() {
		return aptKnow;
	}
	public void setAptKnow(String aptKnow) {
		this.aptKnow = aptKnow;
	}
	public String getAptExpectation() {
		return aptExpectation;
	}
	public void setAptExpectation(String aptExpectation) {
		this.aptExpectation = aptExpectation;
	}
	public AptStatus getAptStatus() {
		return aptStatus;
	}
	public void setAptStatus(AptStatus aptStatus) {
		this.aptStatus = aptStatus;
	}
	public Department getPreHandleDept() {
		return preHandleDept;
	}
	public void setPreHandleDept(Department preHandleDept) {
		this.preHandleDept = preHandleDept;
	}
	public Department getNowHandleDept() {
		return nowHandleDept;
	}
	public void setNowHandleDept(Department nowHandleDept) {
		this.nowHandleDept = nowHandleDept;
	}
	public int getAptEmailed() {
		return aptEmailed;
	}
	public void setAptEmailed(int aptEmailed) {
		this.aptEmailed = aptEmailed;
	}
	public int getAble() {
		return able;
	}
	public void setAble(int able) {
		this.able = able;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
}
