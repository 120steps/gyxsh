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
import com.gyxsh.entities.Department;
import com.gyxsh.entities.Member;
import com.gyxsh.entities.PageBean;
import com.gyxsh.entities.User;
import com.gyxsh.entities.UserStatus;
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
public class SecretaryHandleAction extends ActionSupport implements ServletRequestAware
,ModelDriven<Member>,Preparable{
	@Autowired
	private ApplicantService applicantService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
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
	 * 处理 显示 申请者
	 */
	public void secHandle(int statusId,HttpServletRequest request,int page){
		memberHandleAction.loadDept(request);
		User user=(User) request.getSession().getAttribute("session_user");
		Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();		
		String name="";
		try {
			name=request.getParameter("name");
			if(name.trim().isEmpty()||name==""){
				memberHandleAction.queryForPage(statusId, deptId, 8, page,request);
			}else{//模糊查询
				memberHandleAction.queryForPageLikeName(name, statusId, deptId, 8, page,request);
			}
		} catch (Exception e) {
			memberHandleAction.queryForPage(statusId, deptId, 8, page,request);
		}
	}
	
	/**
	 * 处理 显示 被调剂的申请者
	 */
	public void secHandleAdjusted(int statusId,HttpServletRequest request,int page){
		memberHandleAction.loadDept(request);
		User user=(User) request.getSession().getAttribute("session_user");
		Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();
		String name="";
		try {
			name=request.getParameter("name");
			if(name.trim().isEmpty()||name==""){
				memberHandleAction.queryForPageAdjusted(statusId, deptId, 8, page,request);
			}else{//模糊查询
				memberHandleAction.queryForPageLikeNameAdjusted(name, statusId, deptId, 8, page,request);
			}
		} catch (Exception e) {
			memberHandleAction.queryForPageAdjusted(statusId, deptId, 8, page,request);
		}
	}

	/**
	 * 处理 显示 淘汰的申请者
	 */
	public void secHandleFail(HttpServletRequest request,int page){
		memberHandleAction.loadDept(request);
		User user=(User) request.getSession().getAttribute("session_user");
		Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();		
		String name="";
		try {
			name=request.getParameter("name");
			if(name.trim().isEmpty()||name==""){
				memberHandleAction.queryForPageFail(deptId, 8, page,request);
			}else{//模糊查询
				memberHandleAction.queryForPageLikeNameFail(name, deptId, 8, page,request);
			}
		} catch (Exception e) {
			memberHandleAction.queryForPageFail(deptId, 8, page,request);
		}
	}
	
	/**
	 * 申请表审核 显示 状态为待处理的申请者（status id为1）
	 * @return
	 */
	public String secHandleOne(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();
		secHandle(1,request,page);
		request.setAttribute("count", showUnhandled(1,deptId));
		return "secHandleOne";
	}
	
	/**
	 * 申请表审核 显示 状态为申请表审核通过的申请者（status id为2）
	 * @return
	 */
	public String secHandleTwo(){
		secHandle(2,request,page);
		return "secHandleTwo";
	}
	
	/**
	 * 申请表审核 显示 状态为 报名表审核未通过 的申请者（status id为3）
	 * @return
	 */
	public String secHandleThree(){
		secHandle(3,request,page);
		return "secHandleThree";
	}
	
	/**
	 * 第一轮面试  显示 状态为 报名表审核通过 的申请者（status id为2）
	 * @return
	 */
	public String secHandleTwoOne(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();
		secHandle(2,request,page);
		request.setAttribute("count", showUnhandled(2,deptId));
		return "secHandleTwoOne";
	}
	
	/**
	 * 第一轮面试  显示 状态为 第一轮面试通过 的申请者（status id为4）
	 * @return
	 */
	public String secHandleFourOne(){
		secHandle(4,request,page);
		return "secHandleFourOne";
	}
	
	/**
	 * 第一轮面试  显示 状态为 第一轮面试未通过 的申请者（status id为5）
	 * @return
	 */
	public String secHandleFiveOne(){
		secHandle(5,request,page);
		return "secHandleFiveOne";
	}
	
	/**
	 * 第一轮面试 显示状态为 申请表审核通过 的被调剂的申请者（status id为2）
	 * @return
	 */
	public String secHandleTwoOneAdjusted(){
		secHandleAdjusted(2,request,page);
		return "secHandleTwoOneAdjusted";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第一轮面试通过 的申请者（status id为4）
	 * @return
	 */
	public String secHandleFourTwo(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();
		secHandle(4,request,page);
		request.setAttribute("count", showUnhandled(4,deptId));
		return "secHandleFourTwo";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第二轮面试通过 的申请者（status id为6）
	 * @return
	 */
	public String secHandleSixTwo(){
		secHandle(6,request,page);
		return "secHandleSixTwo";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第二轮面试未通过 的申请者（status id为7）
	 * @return
	 */
	public String secHandleSevenTwo(){
		secHandle(7,request,page);
		return "secHandleSevenTwo";
	}
	
	/**
	 * 第二轮面试 显示状态为 第一轮面试通过 的被调剂的申请者（status id为4）
	 * @return
	 */
	public String secHandleFourTwoAdjusted(){
		secHandleAdjusted(4,request,page);
		return "secHandleFourTwoAdjusted";
	}
	
	/**
	 * 第三轮面试  显示 状态为 第二轮面试通过 的申请者（status id为6）
	 * @return
	 */
	public String secHandleSixThree(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();
		secHandle(6,request,page);
		request.setAttribute("count", showUnhandled(6,deptId));
		return "secHandleSixThree";
	}
	
	/**
	 * 第三轮面试 显示状态为第三轮面试通过 的申请者（status id为8）
	 * @return
	 */
	public String secHandleEightThree(){
		secHandle(8,request,page);
		return "secHandleEightThree";
	}
	
	/**
	 * 第三轮面试 显示状态为 第三轮面试未通过 的申请者（status id为9）
	 * @return
	 */
	public String secHandleNineThree(){
		secHandle(9,request,page);
		return "secHandleNineThree";
	}
	
	/**
	 * 第三轮面试 显示状态为 第二轮面试通过 的被调剂的申请者（status id为6）
	 * @return
	 */
	public String secHandleSixThreeAdjusted(){
		secHandleAdjusted(6,request,page);
		return "secHandleSixThreeAdjusted";
	}
	
	/**
	 * 最终通过  显示状态为 第三轮面试通过 的申请者（status id为8）
	 * @return
	 */
	public String secHandleEightFour(){
		secHandle(8,request,page);
		return "secHandleEightFour";
	}
	
	/**
	 * 最终未通过 显示状态为 所有未通过的 申请者（status id为3/5/7/9）
	 * @return
	 */
	public String secHandleFailFour(){
		secHandleFail(request,page);
		return "secHandleFailFour";
	}

	/**
	 * 最终通过 将申请者 数据导入 学生会成员（单个）
	 * @return
	 */
	public String secHandleEightFourTurn(){
		Integer id=Integer.parseInt(request.getParameter("id"));
		User user=userService.getById(id);
		user.setUserStatus(new UserStatus(2));//将账号身份改为 学生会成员 对应statusId->2
		userService.saveOrUpdate(user);
		
		Applicant apt=applicantService.getById(id);
		Member mer=memberHandleAction.aptToMer(apt);
		applicantService.deleteById(id);
		memberService.saveOrUpdate(mer);
		return "secHandleEightFourTurn";
	}
	
	/**
	 * 最终通过 将申请者 数据导入 学生会成员 （所有）
	 * @return
	 */
	public String secHandleEightFourTurnAll(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();		
		List<Applicant> applicants=applicantService.getByStatusAndDept(8,deptId);
		for (Applicant applicant : applicants) {
			user=userService.getById(applicant.getId());
			user.setUserStatus(new UserStatus(2));//将账号身份改为 学生会成员 对应statusId->2
			userService.saveOrUpdate(user);
			
			Member mer=memberHandleAction.aptToMer(applicant);
			applicantService.deleteById(applicant.getId());
			memberService.saveOrUpdate(mer);
		}
		return "secHandleEightFourTurnAll";
	}
	
	/**
	 * 最终未通过 将淘汰的申请者 数据 软删除（单个）
	 * @return
	 */
	public String secHandleFailFourDes(){
		Integer id=Integer.parseInt(request.getParameter("id"));
		userService.deleteById(id);//删除登录账号
		
		applicantService.pretendDeleteById(id);//软删除 申请者信息
		return "secHandleFailFourDes";
	}
	
	/**
	 * 最终未通过 将淘汰的申请者 数据 软删除（所有）
	 * @return
	 */
	public String secHandleFailFourDesAll(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();
		List<Applicant> applicants=applicantService.getByFail(deptId);
		for (Applicant applicant : applicants) {
			Integer id =applicant.getId();
			userService.deleteById(id);//软删除登录账号
			applicantService.pretendDeleteById(id);//软删除 申请者信息
		}
		return "secHandleFailFourDesAll";
	}
	
	/**
	 * 调剂部门
	 */
	public void merDeptAdjust(HttpServletRequest request){
		Integer nowHandleDept_deptId=Integer.parseInt(request.getParameter("nowHandleDept_id"));
		Department dept=departmentService.getById(nowHandleDept_deptId);
		Integer id=Integer.parseInt(request.getParameter("id"));
		Applicant apt=applicantService.getById(id);
		Department aptNowHandleDept=apt.getNowHandleDept();
		if(aptNowHandleDept.getDeptId()!=dept.getDeptId()){
			apt.setPreHandleDept(aptNowHandleDept);
			apt.setNowHandleDept(dept);
			applicantService.saveOrUpdate(apt);
		}
	}
	
	
	/**
	 * 第一轮面试 调剂申请者
	 * @return
	 */
	public String secHandleTwoOneAdjust(){
		merDeptAdjust(request);
		return "secHandleTwoOneAdjust";
	}
	
	/**
	 * 第二轮面试 调剂申请者
	 * @return
	 */
	public String secHandleFourTwoAdjust(){
		merDeptAdjust(request);
		return "secHandleFourTwoAdjust";
	}
	
	/**
	 * 第三轮面试 调剂申请者
	 * @return
	 */
	public String secHandleSixThreeAdjust(){
		merDeptAdjust(request);
		return "secHandleSixThreeAdjust";
	}
	
	/**
	 * 改变成员状态的方法 (包括是否发送邮件 重置为0 未发送)
	 * @param statusId 目标状态的 id
	 */
	public void modifyStatus(int statusId,HttpServletRequest request){
		Integer id=Integer.parseInt(request.getParameter("id"));
		applicantService.updateStatus(id, statusId);
	}
	
	
	/**
	 * 申请表审核 申请表审核通过 1->2
	 * @return
	 */
	public String secHandleOnePass(){
		modifyStatus(2,request);//2为 申请表通过状态
		return "secHandleOnePass";
	}
	
	/**
	 * 申请表审核 申请表审核未通过 1->3
	 * @return
	 */
	public String secHandleOneOut(){
		modifyStatus(3,request);//3为 申请表未通过状态
		return "secHandleOneOut";
	}
	
	/**
	 * 申请表审核 状态 2->3
	 * @return
	 */
	public String secHandleTwoOut(){
		modifyStatus(3,request);//3为 申请表未通过状态
		return "secHandleTwoOut";
	}
	
	/**
	 * 申请表审核 状态 3->2
	 * @return
	 */
	public String secHandleThreePass(){
		modifyStatus(2,request);//2为 申请表通过状态
		return "secHandleThreePass";
	}
	
	/**
	 * 第一轮面试 状态 2->4
	 * @return
	 */
	public String secHandleTwoOnePass(){
		modifyStatus(4,request);//4为 第一轮面试通过
		return "secHandleTwoOnePass";
	}
	
	/**
	 * 第一轮面试 状态2->5
	 * @return
	 */
	public String secHandleTwoOneOut(){
		modifyStatus(5,request);//5为 第一轮面试未通过
		return "secHandleTwoOneOut";
	}
	
	/**
	 * 第一轮面试 状态 4->5
	 * @return
	 */
	public String secHandleFourOneOut(){
		modifyStatus(5,request);
		return "secHandleFourOneOut";
	}
	
	/**
	 * 第一轮面试 状态 5->4
	 * @return
	 */
	public String secHandleFiveOnePass(){
		modifyStatus(4,request);
		return "secHandleFiveOnePass";
	}
	
	/**
	 * 第二轮面试 状态4->6
	 * @return
	 */
	public String secHandleFourTwoPass(){
		modifyStatus(6,request);
		return "secHandleFourTwoPass";
	}
	
	/**
	 * 第二轮面试 状态4->7
	 * @return
	 */
	public String secHandleFourTwoOut(){
		modifyStatus(7,request);
		return "secHandleFourTwoOut";
	}
	
	/**
	 * 第二轮面试 状态6->7
	 * @return
	 */
	public String secHandleSixTwoOut(){
		modifyStatus(7,request);
		return "secHandleSixTwoOut";
	}
	
	/**
	 * 第二轮面试 状态7->6
	 * @return
	 */
	public String secHandleSevenTwoPass(){
		modifyStatus(6,request);
		return "secHandleSevenTwoPass";
	}
	
	/**
	 * 第三轮面试 状态6->8
	 * @return
	 */
	public String secHandleSixThreePass(){
		modifyStatus(8,request);
		return "secHandleSixThreePass";
	}
	
	/**
	 * 第三轮面试 状态6->9
	 * @return
	 */
	public String secHandleSixThreeOut(){
		modifyStatus(9,request);
		return "secHandleSixThreeOut";
	}
	
	/**
	 * 第三轮面试 状态8->9
	 * @return
	 */
	public String secHandleEightThreeOut(){
		modifyStatus(9,request);
		return "secHandleEightThreeOut";
	}
	
	/**
	 * 第三轮面试 状态9->8
	 * @return
	 */
	public String secHandleNineThreePass(){
		modifyStatus(8,request);
		return "secHandleNineThreePass";
	}
	
	
	
	
	/**
	 * 成员修改个人资料(加载)
	 * @return
	 */
	public String secModifyBasicInfo(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer id=user.getId();
		request.setAttribute("member", memberService.getById(id));
		return "secModifyBasicInfo";
	}
	
	/**
	 * 成员修改个人资料(保存)
	 * @return
	 */
	public String secSaveBasicInfo(){
		String result=memberHandleAction.check(request,member);
		if(result=="ok"){
			memberService.saveOrUpdate(member);
			return "secSaveBasicInfo";
		}
		return "input";
	}
	
	public void prepareSecSaveBasicInfo(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer id=user.getId();
		member=memberService.getById(id);
	}
	
	/**
	 * 部长修改 密码
	 * @return
	 */
	public String secChangePassword(){
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
					return "secChangePassword";
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
	
	/**
	 * 根据状态id 和部门id 查询记录数
	 * @param statusId 状态id
	 * @param deptId 部门id
	 * @return
	 */
	public Integer showUnhandled(int statusId,int deptId){
		return applicantService.getRowCountByStatusAndDept(statusId, deptId);
	}
	
	@Override
	public void prepare() throws Exception {}
	private Member member;
	@Override
	public Member getModel() {
		return member;
	}
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
}
