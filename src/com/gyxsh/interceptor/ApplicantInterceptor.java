package com.gyxsh.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gyxsh.entities.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class ApplicantInterceptor extends MethodFilterInterceptor{
	/**
	 * 申请者的登录拦截
	 */
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		User user=(User) request.getSession().getAttribute("session_user");
		if(user!=null&&user.getUserStatus().getUserStatusId()==3){
			return arg0.invoke();//递归调用拦截器
		}else{
			return Action.LOGIN;//返回登录界面
		}
	}
	
}
