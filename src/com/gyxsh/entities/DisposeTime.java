package com.gyxsh.entities;

import java.util.Date;

public class DisposeTime {
	private Integer id;
	private Date begin;//处理开始时间
	private Date end;//处理结束时间
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
