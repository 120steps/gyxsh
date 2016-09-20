package com.gyxsh.actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gyxsh.entities.User;
import com.gyxsh.service.UserService;
import com.gyxsh.utils.MD5Util;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@Scope("prototype")
@Component
public class UserAction extends ActionSupport implements ServletRequestAware{
	@Autowired
	private UserService userService;

	/**
	 * 登录
	 * @return
	 */
	public String login(){
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String verifyCode=request.getParameter("verifyCode");
		String result=check(username, password,verifyCode);
		if(result=="ok"){//通过后台验证
			User user=userService.getByUsernameWithoutDelete(username);
			if(user==null){
				request.setAttribute("username", username);
				request.setAttribute("verifyCode", verifyCode);
				request.setAttribute("error", "账号与密码不匹配");
			}else if(MD5Util.validatePassword(user.getPassword(), password)){//匹配
				request.getSession().setAttribute("session_user", user);
				int status=user.getUserStatus().getUserStatusId();
				if(status==1){//管理员登录
					return "adminLogin";
				}else if(status==2){//学生会成员登录
					return "merLogin";
				}else if(status==3){//申请者登录
					return "aptLogin";
				}else{//出现意外情况，无法登录
					return "input";
				}
			}else{
				request.setAttribute("username", username);
				request.setAttribute("verifyCode", verifyCode);
				request.setAttribute("error", "用户名与密码不匹配");
			}
		}
		return "input";
	}
	
	/**
	 * 后台检验
	 * @return
	 */
	public String check(String username ,String password,String verifyCode){
		Map<String, String> errors=new HashMap<String, String>();
		if(username.trim().isEmpty()||username==""){
			errors.put("username", "请输入账号");
		}
		if(password.trim().isEmpty()||password==""){
			errors.put("password", "请输入账号");
		}
		String vcode=(String) request.getSession().getAttribute("vcode");
		if(verifyCode.trim().isEmpty()||verifyCode==""){
			errors.put("verifyCode", "请输入验证码");
		}else if(!vcode.equalsIgnoreCase(verifyCode)){
			errors.put("verifyCode", "验证码错误");
		}
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("username",username);
			request.setAttribute("verifyCode", verifyCode);
			return "error";
		}
		return "ok";
	}
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
}
