package com.gyxsh.entities;

import java.util.Date;

public class EnrollTime {
	private Integer id;
	private Date begin;//报名开始时间
	private Date end;//报名结束时间
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
