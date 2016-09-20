package com.gyxsh.actions;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.gyxsh.entities.Applicant;
import com.gyxsh.entities.Student;
import com.gyxsh.entities.User;
import com.gyxsh.service.ApplicantService;
import com.gyxsh.service.MemberService;
import com.gyxsh.service.UserService;
import com.gyxsh.utils.MD5Util;
import com.opensymphony.xwork2.ActionSupport;
@Scope("prototype")
@Component
public class SendEmailAction extends ActionSupport implements ServletRequestAware{
	@Autowired
	private JavaMailSenderImpl send;
//	@Autowired
//	private MimeMessage msg;
	@Autowired
	private ApplicantService applicantService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private UserService userService;
	
	private Integer deptId;
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	/*
	 * 修改配置文件内容 并发送
	 */
	public void changeConetent(String name,String content,String to) throws Exception{
		Properties props=new Properties();
		props.load(this.getClass().getClassLoader().
				getResourceAsStream("email.properties"));
//		send.setUsername(props.getProperty("email.uname"));
//		send.setPassword(props.getProperty("email.pwd"));
		
//	    Properties prop = new Properties();  
//	    prop.setProperty("mail.transport.protocol", "smtp");  
//	    prop.setProperty("mail.host", props.getProperty("email.host"));  
//	    prop.setProperty("mail.smtps.ssl.enable", "true");  
//	    prop.setProperty("mail.smtp.auth", "true"); 
//		send.setJavaMailProperties(prop);
		
		MimeMessage msg = send.createMimeMessage();  
	    MimeMessageHelper helper = new MimeMessageHelper(msg, "utf-8");  
	    helper.setSubject(props.getProperty("email.subject"));
	    helper.setFrom(props.getProperty("email.from"));
	    helper.setTo(to);
	    String text=props.getProperty("email.text");
	    
	    SimpleDateFormat df=new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");
	    text=MessageFormat.format(text, name,content,df.format(new Date()));
	    helper.setText(text, true);
	    
	    send.send(msg);
	}
	
	/*
	 * 发送邮件
	 */
	public void sendMail(String to,String name) throws IOException{
		try {
			changeConetent(name, request.getParameter("emailText"), to);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单人发送邮件（不针对 被调剂者）
	 * @return
	 */
	public void emailSingleMethod(){
		try {
			Integer id=Integer.parseInt(request.getParameter("id"));
			Applicant applicant=applicantService.getById(id);
			sendMail(applicant.getEmail(),applicant.getName());
			applicant.setAptEmailed(1);
			applicantService.saveOrUpdate(applicant);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 学生会 主席团
	 */
	public String emailSingleTwoMer(){
		emailSingleMethod();
		return "emailSingleTwoMer";
	}
	public String emailSingleThreeMer(){
		emailSingleMethod();
		return "emailSingleThreeMer";
	}
	public String emailSingleFourMer(){
		emailSingleMethod();
		return "emailSingleFourMer";
	}
	public String emailSingleFiveMer(){
		emailSingleMethod();
		return "emailSingleFiveMer";
	}
	public String emailSingleSixMer(){
		emailSingleMethod();
		return "emailSingleSixMer";
	}
	public String emailSingleSevenMer(){
		emailSingleMethod();
		return "emailSingleSevenMer";
	}
	public String emailSingleEightMer(){
		emailSingleMethod();
		return "emailSingleEightMer";
	}
	public String emailSingleNineMer(){
		emailSingleMethod();
		return "emailSingleNineMer";
	}
	
	/*
	 * 管理员
	 */
	public String emailSingleTwoAdmin(){
		emailSingleMethod();
		return "emailSingleTwoAdmin";
	}
	public String emailSingleThreeAdmin(){
		emailSingleMethod();
		return "emailSingleThreeAdmin";
	}
	public String emailSingleFourAdmin(){
		emailSingleMethod();
		return "emailSingleFourAdmin";
	}
	public String emailSingleFiveAdmin(){
		emailSingleMethod();
		return "emailSingleFiveAdmin";
	}
	public String emailSingleSixAdmin(){
		emailSingleMethod();
		return "emailSingleSixAdmin";
	}
	public String emailSingleSevenAdmin(){
		emailSingleMethod();
		return "emailSingleSevenAdmin";
	}
	public String emailSingleEightAdmin(){
		emailSingleMethod();
		return "emailSingleEightAdmin";
	}
	public String emailSingleNineAdmin(){
		emailSingleMethod();
		return "emailSingleNineAdmin";
	}
	/*
	 * 部长
	 */
	public String emailSingleTwoSec(){
		emailSingleMethod();
		return "emailSingleTwoSec";
	}
	public String emailSingleThreeSec(){
		emailSingleMethod();
		return "emailSingleThreeSec";
	}
	public String emailSingleFourSec(){
		emailSingleMethod();
		return "emailSingleFourSec";
	}
	public String emailSingleFiveSec(){
		emailSingleMethod();
		return "emailSingleFiveSec";
	}
	public String emailSingleSixSec(){
		emailSingleMethod();
		return "emailSingleSixSec";
	}
	public String emailSingleSevenSec(){
		emailSingleMethod();
		return "emailSingleSevenSec";
	}
	public String emailSingleEightSec(){
		emailSingleMethod();
		return "emailSingleEightSec";
	}
	public String emailSingleNineSec(){
		emailSingleMethod();
		return "emailSingleNineSec";
	}
	
	/**
	 * 单人发送邮件（针对 被调剂者）
	 * @return
	 */
	public void emailSingleAdjustedMethod(){
		try {
			Integer id=Integer.parseInt(request.getParameter("id"));
			Applicant applicant=applicantService.getById(id);
			sendMail(applicant.getEmail(),applicant.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 学生会 主席团
	 */
	public String emailSingleTwoAdjustedMer(){
		emailSingleAdjustedMethod();
		return "emailSingleTwoAdjustedMer";
	}
	public String emailSingleFourAdjustedMer(){
		emailSingleAdjustedMethod();
		return "emailSingleFourAdjustedMer";
	}
	public String emailSingleSixAdjustedMer(){
		emailSingleAdjustedMethod();
		return "emailSingleSixAdjustedMer";
	}
	
	/*
	 * 管理员
	 */
	public String emailSingleTwoAdjustedAdmin(){
		emailSingleAdjustedMethod();
		return "emailSingleTwoAdjustedAdmin";
	}
	public String emailSingleFourAdjustedAdmin(){
		emailSingleAdjustedMethod();
		return "emailSingleFourAdjustedAdmin";
	}
	public String emailSingleSixAdjustedAdmin(){
		emailSingleAdjustedMethod();
		return "emailSingleSixAdjustedAdmin";
	}
	/*
	 * 部长
	 */
	public String emailSingleTwoAdjustedSec(){
		emailSingleAdjustedMethod();
		return "emailSingleTwoAdjustedSec";
	}
	public String emailSingleFourAdjustedSec(){
		emailSingleAdjustedMethod();
		return "emailSingleFourAdjustedSec";
	}
	public String emailSingleSixAdjustedSec(){
		emailSingleAdjustedMethod();
		return "emailSingleSixAdjustedSec";
	}
	
	/**
	 * 群发邮件  （不针对 被调剂者） 主席团、管理员
	 * @param statusId 申请者状态 id
	 * @return
	 */
	public void emailAllMethod(int statusId){
		try {
			Integer deptId=Integer.parseInt(request.getParameter("deptId"));
			List<Applicant> applicants=applicantService.getByStatusAndDept(statusId, deptId);
			for (Applicant applicant : applicants) {
				sendMail(applicant.getEmail(),applicant.getName());
				applicant.setAptEmailed(1);
				applicantService.saveOrUpdate(applicant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 学生会 主席团
	 */
	
	/**
	 * 群发邮件 根据状态  （不针对 被调剂者）
	 * @param statusId 申请者状态 id
	 * @return
	 */
	public String emailTwoMer(){
		emailAllMethod(2);
		return "emailTwoMer";
	}
	public String emailThreeMer(){
		emailAllMethod(3);
		return "emailThreeMer";
	}
	public String emailFourMer(){
		emailAllMethod(4);
		return "emailFourMer";
	}
	public String emailFiveMer(){
		emailAllMethod(5);
		return "emailFiveMer";
	}
	public String emailSixMer(){
		emailAllMethod(6);
		return "emailSixMer";
	}
	public String emailSevenMer(){
		emailAllMethod(7);
		return "emailSevenMer";
	}
	public String emailEightMer(){
		emailAllMethod(8);
		return "emailEightMer";
	}
	public String emailNineMer(){
		emailAllMethod(9);
		return "emailNineMer";
	}
	
	/*
	 * 管理员
	 */
	/**
	 * 群发邮件 根据状态  （不针对 被调剂者）
	 * @param statusId 申请者状态 id
	 * @return
	 */
	public String emailTwoAdmin(){
		emailAllMethod(2);
		return "emailTwoAdmin";
	}
	public String emailThreeAdmin(){
		emailAllMethod(3);
		return "emailThreeAdmin";
	}
	public String emailFourAdmin(){
		emailAllMethod(4);
		return "emailFourAdmin";
	}
	public String emailFiveAdmin(){
		emailAllMethod(5);
		return "emailFiveAdmin";
	}
	public String emailSixAdmin(){
		emailAllMethod(6);
		return "emailSixAdmin";
	}
	public String emailSevenAdmin(){
		emailAllMethod(7);
		return "emailSevenAdmin";
	}
	public String emailEightAdmin(){
		emailAllMethod(8);
		return "emailEightAdmin";
	}
	public String emailNineAdmin(){
		emailAllMethod(9);
		return "emailNineAdmin";
	}
	
	
	/**
	 * 群发邮件  （不针对 被调剂者） 部长
	 * @param statusId 申请者状态 id
	 * @return
	 */
	public void emailAllMethodSec(int statusId){
		try {
			User user=(User) request.getSession().getAttribute("session_user");
			Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();
			List<Applicant> applicants=applicantService.getByStatusAndDept(statusId, deptId);
			for (Applicant applicant : applicants) {
				sendMail(applicant.getEmail(),applicant.getName());
				applicant.setAptEmailed(1);
				applicantService.saveOrUpdate(applicant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 部长
	 */
	/**
	 * 群发邮件 根据状态  （不针对 被调剂者） 
	 * @param statusId 申请者状态 id
	 * @return
	 */
	public String emailTwoSec(){
		emailAllMethodSec(2);
		return "emailTwoSec";
	}
	public String emailThreeSec(){
		emailAllMethodSec(3);
		return "emailThreeSec";
	}
	public String emailFourSec(){
		emailAllMethodSec(4);
		return "emailFourSec";
	}
	public String emailFiveSec(){
		emailAllMethodSec(5);
		return "emailFiveSec";
	}
	public String emailSixSec(){
		emailAllMethodSec(6);
		return "emailSixSec";
	}
	public String emailSevenSec(){
		emailAllMethodSec(7);
		return "emailSevenSec";
	}
	public String emailEightSec(){
		emailAllMethodSec(8);
		return "emailEightSec";
	}
	public String emailNineSec(){
		emailAllMethodSec(9);
		return "emailNineSec";
	}
	
	
	
	/**
	 * 群发邮件  （针对 被调剂者）
	 * @param statusId 申请者状态 id
	 * @return
	 */
	public void emailAllMethodAdjusted(int statusId){
		try {
			Integer deptId=Integer.parseInt(request.getParameter("deptId"));
			List<Applicant> applicants=applicantService.getByStatusAndPreDeptAdjusted(statusId, deptId);
			for (Applicant applicant : applicants) {
				sendMail(applicant.getEmail(),applicant.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 学生会 主席团
	 */
	public String emailTwoAdjustedMer(){
		emailAllMethodAdjusted(2);
		return "emailTwoAdjustedMer";
	}
	public String emailFourAdjustedMer(){
		emailAllMethodAdjusted(4);
		return "emailFourAdjustedMer";
	}
	public String emailSixAdjustedMer(){
		emailAllMethodAdjusted(6);
		return "emailSixAdjustedMer";
	}
	
	/*
	 * 管理员
	 */
	public String emailTwoAdjustedAdmin(){
		emailAllMethodAdjusted(2);
		return "emailTwoAdjustedAdmin";
	}
	public String emailFourAdjustedAdmin(){
		emailAllMethodAdjusted(4);
		return "emailFourAdjustedAdmin";
	}
	public String emailSixAdjustedAdmin(){
		emailAllMethodAdjusted(6);
		return "emailSixAdjustedAdmin";
	}
	
	/**
	 * 群发邮件  （针对 被调剂者） 部长
	 * @param statusId 申请者状态 id
	 * @return
	 */
	public void emailAllMethodAdjustedSec(int statusId){
		try {
			User user=(User) request.getSession().getAttribute("session_user");
			Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();
			List<Applicant> applicants=applicantService.getByStatusAndPreDeptAdjusted(statusId, deptId);
			for (Applicant applicant : applicants) {
				sendMail(applicant.getEmail(),applicant.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 部长
	 */
	public String emailTwoAdjustedSec(){
		emailAllMethodAdjustedSec(2);
		return "emailTwoAdjustedSec";
	}
	public String emailFourAdjustedSec(){
		emailAllMethodAdjustedSec(4);
		return "emailFourAdjustedSec";
	}
	public String emailSixAdjustedSec(){
		emailAllMethodAdjustedSec(6);
		return "emailSixAdjustedSec";
	}
	
	/*
	 * 找回密码操作
	 */
	/**
	 * 找回密码 发送激活码
	 * @param to 发送邮件对象
	 * @param name 对象姓名
	 * @param content 邮件自定义内容
	 * @throws IOException
	 */
	public void sendIdentifyingCode(String to,String name,String content) throws IOException{
//		SimpleMailMessage message=new SimpleMailMessage(mailMessage);
//		message.setTo(to);
//		message.setText(changeText(name,content));
//		try {
//			mailSender.send(message);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			changeConetent(name, content, to);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送验证码(MD5 字符串)
	 * @return
	 */
	public String sendICode(){
		try {
			String username=request.getParameter("username");
			String email=request.getParameter("email");
			User user=userService.getByUsername(username);
			String result=checkSendICode(username,email,user);
			if(result.equals("ok")){
				sendIdentifyingCode(email, user.getStudent().getName(), 
						"您正在找回密码，验证码为："+user.getPassword()+"。若非本人操作请忽略本邮件");
				return "sendICode";
			}
			return "sendICodeInput";
		} catch (IOException e) {
			return "sendICodeInput";
		}
	}
	
	/**
	 * 检验 验证码
	 * @return
	 */
	public String checkICode(){
		String username=request.getParameter("username");
		User user=userService.getByUsernameWithoutDelete(username);
		String iCode=request.getParameter("iCode");
		String newPassword=request.getParameter("newPassword");
		String confirmPassword=request.getParameter("confirmPassword");
		String result=checkCheckICode(user, iCode, newPassword,confirmPassword);
		if(result.equals("ok")){
			user.setPassword(MD5Util.generatePassword(newPassword));
			userService.saveOrUpdate(user);
			return "checkICode";
		}
		return "input";
	}
	
	/**
	 * 后台检验
	 */
	public String checkSendICode(String username,String email,User user){
		Map<String, String> errors=new HashMap<String, String>();
		String html="<i class='fa fa-exclamation-circle'></i> ";
		
		if(username==null||username.trim().isEmpty()){
			errors.put("username", html+"请填写用户名");
		}
		
		if(email==null||email.trim().isEmpty()){
			errors.put("email", html+"请填写邮箱");
		}
		
		if(user!=null){
			if(user.getAble()==0){
				errors.put("username", html+"该用户已被注销，无法找回密码");
			}else{
				if(!user.getStudent().getEmail().equals(email)){
					errors.put("username", html+"用户名与邮箱不匹配");
				}
			}
		}else{
			errors.put("username", html+"用户名与邮箱不匹配");
		}
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("username", username);
			request.setAttribute("email", email);
			return "errors";
		}
		return "ok";
	}
	
	/**
	 * 后台检验
	 * @param username
	 * @param iCode
	 * @param newPassword
	 * @return
	 */
	public String checkCheckICode(User user,String iCode,String newPassword,String confirmPassword){
		Map<String, String> errors=new HashMap<String, String>();
		String html="<i class='fa fa-exclamation-circle'></i> ";
		
		if(iCode==null||iCode.trim().isEmpty()){
			errors.put("iCode", html+"请填写验证码");
		}
		if(newPassword==null||newPassword.trim().isEmpty()){
			errors.put("newPassword", html+"请填写新密码");
		}
		
		if(confirmPassword==null||confirmPassword.trim().isEmpty()){
			errors.put("confirmPassword", html+"请确认新密码");
		}
		
		if(!confirmPassword.equals(newPassword)){
			errors.put("confirmPassword", html+"两次密码不相同");
		}
		
		if(!user.getPassword().equals(iCode)){
			errors.put("iCode", html+"验证码错误");
		}
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("iCode", iCode);
			return "errors";
		}
		
		return "ok";
	}
	
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	private HttpServletRequest request;

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
}
