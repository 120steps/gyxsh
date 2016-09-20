package com.gyxsh.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gyxsh.entities.EnrollTime;
import com.gyxsh.entities.Member;
import com.gyxsh.entities.User;
import com.gyxsh.service.EnrollTimeService;
import com.gyxsh.service.MemberService;
import com.gyxsh.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@Scope("prototype")
@Component
public class EnrollTimeAction extends ActionSupport implements ServletRequestAware{
	@Autowired
	private EnrollTimeService enrollTimeService;
	@Autowired
	private MemberService memberService;
	
	/**
	 * 显示 报名系统的时间 管理员
	 * @return
	 */
	public String adminEnrollTimeShow(){
		EnrollTime et=enrollTimeService.getById(1);
		request.setAttribute("enrollTime", et);
		return "adminEnrollTimeShow";
	}

	/**
	 * 设置 报名系统的时间 管理员
	 * @return
	 */
	public String adminEnrollTimeSet(){
		EnrollTime et=enrollTimeService.getById(1);
		String result=checkDate(et,request);
		if(result.equals("ok")){
			enrollTimeService.saveOrUpdate(et);
			return "adminEnrollTimeSet";
		}
		return "input";
	}
	
	/**
	 * 显示 报名系统的时间 主席团
	 * @return
	 */
	public String merEnrollTimeShow(){
		EnrollTime et=enrollTimeService.getById(1);
		request.setAttribute("enrollTime", et);
		return "merEnrollTimeShow";
	}

	/**
	 * 设置 报名系统的时间 主席团
	 * @return
	 */
	public String merEnrollTimeSet(){
		EnrollTime et=enrollTimeService.getById(1);
		String result=checkDate(et,request);
		if(result.equals("ok")){
			enrollTimeService.saveOrUpdate(et);
			return "merEnrollTimeSet";
		}
		return "input";
	}
	
	/**
	 * 显示 报名系统的时间 秘书处部长
	 * @return
	 */
	public String secEnrollTimeShow(){
		User user=(User) request.getSession().getAttribute("session_user");
		Member member = memberService.getById(user.getId());
		if(member.getMerStatus().getMerStatusId()!=3||member.getMerDepartment().getDeptId()!=2){
			return Action.LOGIN;
		}
		EnrollTime et=enrollTimeService.getById(1);
		request.setAttribute("enrollTime", et);
		return "secEnrollTimeShow";
	}

	/**
	 * 设置 报名系统的时间 秘书处部长
	 * @return
	 */
	public String secEnrollTimeSet(){
		User user=(User) request.getSession().getAttribute("session_user");
		Member member = memberService.getById(user.getId());
		if(member.getMerStatus().getMerStatusId()!=3||member.getMerDepartment().getDeptId()!=2){
			return Action.LOGIN;
		}
		EnrollTime et=enrollTimeService.getById(1);
		String result=checkDate(et,request);
		if(result.equals("ok")){
			enrollTimeService.saveOrUpdate(et);
			return "secEnrollTimeSet";
		}
		return "input";
	}
	
	
	/**
	 * 后台 检验 
	 * @return
	 */
	public String checkDate(EnrollTime et,HttpServletRequest request){
		Map<String, String> errors=new HashMap<String, String>();
		
		String html="<i class='fa fa-exclamation-circle'></i> ";
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date begin=et.getBegin();
		Date end=et.getEnd();
		try {
			if(request.getParameter("begin")==""||request.getParameter("begin").trim().isEmpty()){
				errors.put("begin", html+"请输入出生日期");
			}else{
				begin = dateFormat.parse(request.getParameter("begin"));
			}
			
			if(request.getParameter("end")==""||request.getParameter("end").trim().isEmpty()){
				errors.put("end", html+"请输入出生日期");
			}else{
				end = dateFormat.parse(request.getParameter("end"));
			}
			if(begin.after(end)){
				errors.put("end", html+"结束时间不可以早于开始时间");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("enrollTime", et);
			return "error";
		}
		et.setBegin(begin);
		et.setEnd(end);
		return "ok";
	}
	
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
}
