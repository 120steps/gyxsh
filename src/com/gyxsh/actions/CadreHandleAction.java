package com.gyxsh.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gyxsh.entities.Applicant;
import com.gyxsh.entities.Member;
import com.gyxsh.entities.PageBean;
import com.gyxsh.entities.User;
import com.gyxsh.service.ApplicantService;
import com.gyxsh.service.DepartmentService;
import com.gyxsh.service.MemberService;
import com.gyxsh.service.UserService;
import com.gyxsh.utils.MD5Util;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@Scope("prototype")
@Component
public class CadreHandleAction extends ActionSupport implements ModelDriven<Member>
,Preparable,ServletRequestAware{
	@Autowired
	private ApplicantService applicantService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
	@Autowired
	private SecretaryHandleAction secHandleAction;
	@Autowired
	private MemberHandleAction memberHandleAction;
	
	private Integer deptId;
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	/*
	 * 分页操作
	 */
	private PageBean pageBean;
	private List<Applicant> applicants;
	private int page=1;
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	public void setApplicants(List<Applicant> applicants) {
		this.applicants = applicants;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	/**
	 * 申请表审核 显示 状态为待处理的申请者（status id为1）
	 * @return
	 */
	public String cadHandleOne(){
		secHandleAction.secHandle(1,request,page);
		return "cadHandleOne";
	}
	
	/**
	 * 申请表审核 显示 状态为申请表审核通过的申请者（status id为2）
	 * @return
	 */
	public String cadHandleTwo(){
		secHandleAction.secHandle(2,request,page);
		return "cadHandleTwo";
	}
	
	/**
	 * 申请表审核 显示 状态为 报名表审核未通过 的申请者（status id为3）
	 * @return
	 */
	public String cadHandleThree(){
		secHandleAction.secHandle(3,request,page);
		return "cadHandleThree";
	}
	
	/**
	 * 第一轮面试  显示 状态为 报名表审核通过 的申请者（status id为2）
	 * @return
	 */
	public String cadHandleTwoOne(){
		secHandleAction.secHandle(2,request,page);
		return "cadHandleTwoOne";
	}
	
	/**
	 * 第一轮面试  显示 状态为 第一轮面试通过 的申请者（status id为4）
	 * @return
	 */
	public String cadHandleFourOne(){
		secHandleAction.secHandle(4,request,page);
		return "cadHandleFourOne";
	}
	
	/**
	 * 第一轮面试  显示 状态为 第一轮面试未通过 的申请者（status id为5）
	 * @return
	 */
	public String cadHandleFiveOne(){
		secHandleAction.secHandle(5,request,page);
		return "cadHandleFiveOne";
	}
	
	/**
	 * 第一轮面试 显示状态为 申请表审核通过 的被调剂的申请者（status id为2）
	 * @return
	 */
	public String cadHandleTwoOneAdjusted(){
		secHandleAction.secHandleAdjusted(2,request,page);
		return "cadHandleTwoOneAdjusted";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第一轮面试通过 的申请者（status id为4）
	 * @return
	 */
	public String cadHandleFourTwo(){
		secHandleAction.secHandle(4,request,page);
		return "cadHandleFourTwo";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第二轮面试通过 的申请者（status id为6）
	 * @return
	 */
	public String cadHandleSixTwo(){
		secHandleAction.secHandle(6,request,page);
		return "cadHandleSixTwo";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第二轮面试未通过 的申请者（status id为7）
	 * @return
	 */
	public String cadHandleSevenTwo(){
		secHandleAction.secHandle(7,request,page);
		return "cadHandleSevenTwo";
	}
	
	/**
	 * 第二轮面试 显示状态为 第一轮面试通过 的被调剂的申请者（status id为4）
	 * @return
	 */
	public String cadHandleFourTwoAdjusted(){
		secHandleAction.secHandleAdjusted(4,request,page);
		return "cadHandleFourTwoAdjusted";
	}
	
	/**
	 * 第三轮面试  显示 状态为 第二轮面试通过 的申请者（status id为6）
	 * @return
	 */
	public String cadHandleSixThree(){
		secHandleAction.secHandle(6,request,page);
		return "cadHandleSixThree";
	}
	
	/**
	 * 第三轮面试 显示状态为第三轮面试通过 的申请者（status id为8）
	 * @return
	 */
	public String cadHandleEightThree(){
		secHandleAction.secHandle(8,request,page);
		return "cadHandleEightThree";
	}
	
	/**
	 * 第三轮面试 显示状态为 第三轮面试未通过 的申请者（status id为9）
	 * @return
	 */
	public String cadHandleNineThree(){
		secHandleAction.secHandle(9,request,page);
		return "cadHandleNineThree";
	}
	
	/**
	 * 第三轮面试 显示状态为 第二轮面试通过 的被调剂的申请者（status id为6）
	 * @return
	 */
	public String cadHandleSixThreeAdjusted(){
		secHandleAction.secHandleAdjusted(6,request,page);
		return "cadHandleSixThreeAdjusted";
	}
	
	/**
	 * 最终通过  显示状态为 第三轮面试通过 的申请者（status id为8）
	 * @return
	 */
	public String cadHandleEightFour(){
		secHandleAction.secHandle(8,request,page);
		return "cadHandleEightFour";
	}
	
	/**
	 * 最终未通过 显示状态为 所有未通过的 申请者（status id为3/5/7/9）
	 * @return
	 */
	public String cadHandleFailFour(){
		secHandleAction.secHandleFail(request,page);
		return "cadHandleFailFour";
	}

	/**
	 * 成员修改个人资料(加载)
	 * @return
	 */
	public String cadModifyBasicInfo(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer id=user.getId();
		request.setAttribute("member", memberService.getById(id));
		return "cadModifyBasicInfo";
	}
	
	/**
	 * 成员修改个人资料(保存)
	 * @return
	 */
	public String cadSaveBasicInfo(){
		String result=memberHandleAction.check(request,member);
		if(result=="ok"){
			memberService.saveOrUpdate(member);
			return "cadSaveBasicInfo";
		}
		return "input";
	}
	
	public void prepareCadSaveBasicInfo(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer id=user.getId();
		member=memberService.getById(id);
	}
	
	/**
	 * 干事、副部长修改 密码
	 * @return
	 */
	public String cadChangePassword(){
		Map<String, String> errors=new HashMap<String,String>();
		String oldPassword=request.getParameter("oldPassword");
		String newPassword=request.getParameter("newPassword");
		String confirmPassword=request.getParameter("confirmPassword");
		if(oldPassword.trim().isEmpty()||oldPassword==""){
			errors.put("oldPassword", "请输入原来的密码");
		}
		if(newPassword.trim().isEmpty()||newPassword==""){
			errors.put("newPassword", "请输入新的密码");
		}
		if(confirmPassword.trim().isEmpty()||confirmPassword==""){
			errors.put("confirmPassword", "请确认新的密码");
		}
		if(errors.size()==0){
			User user=(User) request.getSession().getAttribute("session_user");
			if(MD5Util.validatePassword(user.getPassword(), oldPassword)==true){
				if(newPassword.equals(confirmPassword)){
					user.setPassword(MD5Util.generatePassword(newPassword));
					userService.saveOrUpdate(user);
					return "cadChangePassword";
				}else{
					errors.put("confirmPassword", "两次输入的密码不一致");
				}
			}else{
				errors.put("oldPassword", "原密码不正确！");
			}
		}
		request.setAttribute("errors", errors);
		return "input";	
	}
	
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}

	@Override
	public void prepare() throws Exception {}

	private Member member;
	@Override
	public Member getModel() {
		return member;
	}

}
