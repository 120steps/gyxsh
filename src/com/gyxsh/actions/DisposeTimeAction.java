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

import com.gyxsh.entities.DisposeTime;
import com.gyxsh.entities.Member;
import com.gyxsh.entities.User;
import com.gyxsh.service.DisposeTimeService;
import com.gyxsh.service.MemberService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
@Scope("prototype")
@Component
public class DisposeTimeAction extends ActionSupport implements ServletRequestAware{
	@Autowired
	private DisposeTimeService disposeTimeService;
	@Autowired
	private MemberService memberService;
	
	/**
	 * 显示 处理系统的时间 管理员
	 * @return
	 */
	public String adminDisposeTimeShow(){
		DisposeTime dt=disposeTimeService.getById(1);
		request.setAttribute("disposeTime", dt);
		return "adminDisposeTimeShow";
	}

	/**
	 * 设置 处理系统的时间 管理员
	 * @return
	 */
	public String adminDisposeTimeSet(){
		DisposeTime dt=disposeTimeService.getById(1);
		String result=checkDate(dt,request);
		if(result.equals("ok")){
			disposeTimeService.saveOrUpdate(dt);
			return "adminDisposeTimeSet";
		}
		return "input";
	}
	
	/**
	 * 显示 处理系统的时间 主席团
	 * @return
	 */
	public String merDisposeTimeShow(){
		DisposeTime dt=disposeTimeService.getById(1);
		request.setAttribute("disposeTime", dt);
		return "merDisposeTimeShow";
	}

	/**
	 * 设置 处理系统的时间 主席团
	 * @return
	 */
	public String merDisposeTimeSet(){
		DisposeTime dt=disposeTimeService.getById(1);
		String result=checkDate(dt,request);
		if(result.equals("ok")){
			disposeTimeService.saveOrUpdate(dt);
			return "merDisposeTimeSet";
		}
		return "input";
	}
	
	/**
	 * 显示 处理系统的时间 秘书处部长
	 * @return
	 */
	public String secDisposeTimeShow(){
		User user=(User) request.getSession().getAttribute("session_user");
		Member member = memberService.getById(user.getId());
		if(member.getMerStatus().getMerStatusId()!=3||member.getMerDepartment().getDeptId()!=2){
			return Action.LOGIN;
		}
		DisposeTime dt=disposeTimeService.getById(1);
		request.setAttribute("disposeTime", dt);
		return "secDisposeTimeShow";
	}

	/**
	 * 设置 处理系统的时间 秘书处部长
	 * @return
	 */
	public String secDisposeTimeSet(){
		User user=(User) request.getSession().getAttribute("session_user");
		Member member = memberService.getById(user.getId());
		if(member.getMerStatus().getMerStatusId()!=3||member.getMerDepartment().getDeptId()!=2){
			return Action.LOGIN;
		}
		DisposeTime dt=disposeTimeService.getById(1);
		String result=checkDate(dt,request);
		if(result.equals("ok")){
			disposeTimeService.saveOrUpdate(dt);
			return "secDisposeTimeSet";
		}
		return "input";
	}
	
	
	/**
	 * 后台 检验 
	 * @return
	 */
	public String checkDate(DisposeTime dt,HttpServletRequest request){
		Map<String, String> errors=new HashMap<String, String>();
		
		String html="<i class='fa fa-exclamation-circle'></i> ";
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date begin=dt.getBegin();
		Date end=dt.getEnd();
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
			request.setAttribute("disposeTime", dt);
			return "error";
		}
		dt.setBegin(begin);
		dt.setEnd(end);
		return "ok";
	}
	
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
}
