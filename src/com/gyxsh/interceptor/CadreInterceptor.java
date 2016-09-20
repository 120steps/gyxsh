package com.gyxsh.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gyxsh.entities.Member;
import com.gyxsh.entities.User;
import com.gyxsh.service.MemberService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class CadreInterceptor extends MethodFilterInterceptor {

	private MemberService memberService;
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}


	/**
	 * 干事、副部长的登录拦截器
	 */
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		User user=(User) request.getSession().getAttribute("session_user");
		if(user!=null&&user.getUserStatus().getUserStatusId()==2){
			Member mer=memberService.getById(user.getId());
			int statusId=mer.getMerStatus().getMerStatusId();
			if(statusId==4||statusId==5){
				return arg0.invoke();//递归调用拦截器
			}else{
				return Action.LOGIN;//返回登录界面
			}
		}else{
			return Action.LOGIN;//返回登录界面
		}
	}

}
