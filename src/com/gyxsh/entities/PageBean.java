package com.gyxsh.entities;

import java.util.List;

public class PageBean {
	private List<Applicant> applicants;//当前页记录数
	private Integer tr;//总记录total records
//	private Integer tp;//总页数total pages
	private int cp;//当前页current page
	private Integer ps;//每页记录数page size
	public List<Applicant> getApplicants() {
		return applicants;
	}
	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	}
	public Integer getTr() {
		return tr;
	}
	public void setTr(Integer tr) {
		this.tr = tr;
	}
//	public Integer getTp() {
//		return tp;
//	}
//	public Integer getCp() {
//		return cp;
//	}
	public Integer getPs() {
		return ps;
	}
	public void setPs(Integer ps) {
		this.ps = ps;
	}
	
	/*
	 * 计算总页数
	 */
	public Integer getTp(){
		int tp = tr/ps;
		return tr%ps==0?tp:tp+1;
	}
	
	/*
	 * 计算当前页开始的记录
	 */
	public Integer getOffset(){
		int offset=ps*(cp-1);
		return offset;
	}
	
	/*
	 * 计算当前页 若为0或者请求的URL中没有"?page="则用1代替
	 */
	public int getCp(){
		return cp;
	}
	public void setCp(int cp) {
		this.cp = (cp==0?1:cp);
	}
}
