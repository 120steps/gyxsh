package com.gyxsh.actions;

import java.util.Date;
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
import com.gyxsh.entities.MerStatus;
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
public class MemberHandleAction extends ActionSupport implements ServletRequestAware,ModelDriven<Member>
,Preparable{
	@Autowired
	private ApplicantService applicantService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
	
	private Integer deptId;
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * 学生会成员登录action 根据session_user中的信息判断所属身份和部门
	 * @return
	 */
	public String merLogin(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer id=user.getId();
		Member mer=memberService.getById(id);
		int merId=mer.getMerStatus().getMerStatusId();
		if(merId==1){//主席
			return "chairpersonLogin";
		}else if(merId==2){//副主席
			return "viceChairpersonLogin";
		}else if(merId==3){//部长
			return "ministerLogin";
		}else if(merId==4){//副部长
			return "viceMinisterLogin";
		}else{//干事
			return "cadre";
		}
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
	 * 分页操作-显示申请者
	 * @param statusId 申请者状态id
	 * @param deptId 部门id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 */
	public void queryForPage(int statusId,int deptId,int ps,int cp,HttpServletRequest request){
		pageBean=applicantService.queryForPageByStatusAndDept(statusId, deptId, ps, cp);
		request.setAttribute("pageBean", pageBean);
	}
	
	/**
	 * 分页操作-申请者 模糊查询
	 * @param name 模糊查询的姓名
	 * @param statusId 状态id
	 * @param deptId 部门id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 */
	public void queryForPageLikeName(String name,int statusId,int deptId,int ps,int cp
			,HttpServletRequest request){
		pageBean=applicantService.queryForPageByLikeNameAndStatusAndDept(name, statusId, deptId, ps, cp);
		request.setAttribute("pageBean", pageBean);
	}
	
	/**
	 * 分页操作-显示被调剂申请者
	 * @param statusId 被调剂申请者状态id
	 * @param deptId 上一个处理部门id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 */
	public void queryForPageAdjusted(int statusId,int deptId,int ps,int cp
			,HttpServletRequest request){
		pageBean=applicantService.queryForPageByStatusAndDeptAdjusted(statusId, deptId, ps, cp);
		request.setAttribute("pageBean", pageBean);
	}
	
	/**
	 * 分页操作-被调剂申请者 模糊查询
	 * @param name 被调剂申请者 模糊查询的姓名
	 * @param statusId 被调剂申请者状态id
	 * @param deptId 上一个处理部门id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 */
	public void queryForPageLikeNameAdjusted(String name,int statusId,int deptId,int ps,int cp
			,HttpServletRequest request){
		pageBean=applicantService.queryForPageByLikeNameAndStatusAndDeptAdjusted(name, statusId, deptId, ps, cp);
		request.setAttribute("pageBean", pageBean);
	}
	
	/**
	 * 分页操作-显示 淘汰的申请者
	 * @param deptId 部门id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 */
	public void queryForPageFail(int deptId,int ps,int cp,HttpServletRequest request){
		pageBean=applicantService.queryForPageByFailAndDept(deptId, ps, cp);
		request.setAttribute("pageBean", pageBean);
	}
	
	/**
	 * 分页操作-淘汰的申请者 模糊查询
	 * @param name 模糊查询的姓名
	 * @param deptId 部门id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 */
	public void queryForPageLikeNameFail(String name,int deptId,int ps,int cp,HttpServletRequest request){
		pageBean=applicantService.queryForPageByLikeNameAndFailAndDept(name, deptId, ps, cp);
		request.setAttribute("pageBean", pageBean);
	}
	
	/**
	 * 处理 显示 申请者
	 */
	public void merHandle(int statusId,HttpServletRequest request,int page){
		loadDept(request);
		Integer deptId=Integer.parseInt(request.getParameter("deptId"));
		String name="";
		try {
			name=request.getParameter("name");
			if(name.trim().isEmpty()||name==""){
				queryForPage(statusId, deptId, 8, page,request);
			}else{//模糊查询
				queryForPageLikeName(name, statusId, deptId, 8, page,request);
			}
		} catch (Exception e) {
			queryForPage(statusId, deptId, 8, page,request);
		}
	}
	
	/**
	 * 处理 显示 被调剂的申请者
	 */
	public void merHandleAdjusted(int statusId,HttpServletRequest request,int page){
		loadDept(request);
		Integer deptId=Integer.parseInt(request.getParameter("deptId"));
		String name="";
		try {
			name=request.getParameter("name");
			if(name.trim().isEmpty()||name==""){
				queryForPageAdjusted(statusId, deptId, 8, page,request);
			}else{//模糊查询
				queryForPageLikeNameAdjusted(name, statusId, deptId, 8, page,request);
			}
		} catch (Exception e) {
			queryForPageAdjusted(statusId, deptId, 8, page,request);
		}
	}

	/**
	 * 处理 显示 淘汰的申请者
	 */
	public void merHandleFail(HttpServletRequest request,int page){
		loadDept(request);
		Integer deptId=Integer.parseInt(request.getParameter("deptId"));
		String name="";
		try {
			name=request.getParameter("name");
			if(name.trim().isEmpty()||name==""){
				queryForPageFail(deptId, 8, page,request);
			}else{//模糊查询
				queryForPageLikeNameFail(name, deptId, 8, page,request);
			}
		} catch (Exception e) {
			queryForPageFail(deptId, 8, page,request);
		}
	}
	
	
	
	/**
	 * 申请表审核 显示 状态为待处理的申请者（status id为1）
	 * @return
	 */
	public String merHandleOne(){
		merHandle(1,request,page);
		return "merHandleOne";
	}
	
	/**
	 * 处理申请表审核 显示 状态为 报名表审核通过 的申请者（status id为2）
	 * @return
	 */
	public String merHandleTwo(){
		merHandle(2,request,page);
		return "merHandleTwo";
	}
	
	/**
	 * 申请表审核 显示 状态为 报名表审核未通过 的申请者（status id为3）
	 * @return
	 */
	public String merHandleThree(){
		merHandle(3,request,page);
		return "merHandleThree";
	}
	
	/**
	 * 第一轮面试  显示 状态为 报名表审核通过 的申请者（status id为2）
	 * @return
	 */
	public String merHandleTwoOne(){
		merHandle(2,request,page);
		return "merHandleTwoOne";
	}
	
	/**
	 * 第一轮面试  显示 状态为 第一轮面试通过 的申请者（status id为4）
	 * @return
	 */
	public String merHandleFourOne(){
		merHandle(4,request,page);
		return "merHandleFourOne";
	}
	
	/**
	 * 第一轮面试  显示 状态为 第一轮面试未通过 的申请者（status id为5）
	 * @return
	 */
	public String merHandleFiveOne(){
		merHandle(5,request,page);
		return "merHandleFiveOne";
	}
	
	/**
	 * 第一轮面试 显示状态为 申请表审核通过 的被调剂的申请者（status id为2）
	 * @return
	 */
	public String merHandleTwoOneAdjusted(){
		merHandleAdjusted(2,request,page);
		return "merHandleTwoOneAdjusted";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第一轮面试通过 的申请者（status id为4）
	 * @return
	 */
	public String merHandleFourTwo(){
		merHandle(4,request,page);
		return "merHandleFourTwo";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第二轮面试通过 的申请者（status id为6）
	 * @return
	 */
	public String merHandleSixTwo(){
		merHandle(6,request,page);
		return "merHandleSixTwo";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第二轮面试未通过 的申请者（status id为7）
	 * @return
	 */
	public String merHandleSevenTwo(){
		merHandle(7,request,page);
		return "merHandleSevenTwo";
	}
	
	/**
	 * 第二轮面试 显示状态为 第一轮面试通过 的被调剂的申请者（status id为4）
	 * @return
	 */
	public String merHandleFourTwoAdjusted(){
		merHandleAdjusted(4,request,page);
		return "merHandleFourTwoAdjusted";
	}
	
	/**
	 * 第三轮面试  显示 状态为 第二轮面试通过 的申请者（status id为6）
	 * @return
	 */
	public String merHandleSixThree(){
		merHandle(6,request,page);
		return "merHandleSixThree";
	}
	
	/**
	 * 第三轮面试 显示状态为第三轮面试通过 的申请者（status id为8）
	 * @return
	 */
	public String merHandleEightThree(){
		merHandle(8,request,page);
		return "merHandleEightThree";
	}
	
	/**
	 * 第三轮面试 显示状态为 第三轮面试未通过 的申请者（status id为9）
	 * @return
	 */
	public String merHandleNineThree(){
		merHandle(9,request,page);
		return "merHandleNineThree";
	}
	
	/**
	 * 第三轮面试 显示状态为 第二轮面试通过 的被调剂的申请者（status id为6）
	 * @return
	 */
	public String merHandleSixThreeAdjusted(){
		merHandleAdjusted(6,request,page);
		return "merHandleSixThreeAdjusted";
	}
	
	/**
	 * 最终通过  显示状态为 第三轮面试通过 的申请者（status id为8）
	 * @return
	 */
	public String merHandleEightFour(){
		merHandle(8,request,page);
		return "merHandleEightFour";
	}
	
	/**
	 * 最终未通过 显示状态为 所有未通过的 申请者（status id为3/5/7/9）
	 * @return
	 */
	public String merHandleFailFour(){
		merHandleFail(request,page);
		return "merHandleFailFour";
	}
	
	/**
	 * 最终通过 将申请者 数据导入 学生会成员（单个）
	 * @return
	 */
	public String merHandleEightFourTurn(){
		Integer id=Integer.parseInt(request.getParameter("id"));
		User user=userService.getById(id);
		user.setUserStatus(new UserStatus(2));//将账号身份改为 学生会成员 对应statusId->2
		userService.saveOrUpdate(user);
		
		Applicant apt=applicantService.getById(id);
		Member mer=aptToMer(apt);
		applicantService.deleteById(id);
		memberService.saveOrUpdate(mer);
		return "merHandleEightFourTurn";
	}
	
	/**
	 * 最终通过 将申请者 数据导入 学生会成员 （所有）
	 * @return
	 */
	public String merHandleEightFourTurnAll(){
		Integer deptId=Integer.parseInt(request.getParameter("deptId"));
		List<Applicant> applicants=applicantService.getByStatusAndDept(8,deptId);
		for (Applicant applicant : applicants) {
			User user=userService.getById(applicant.getId());
			user.setUserStatus(new UserStatus(2));//将账号身份改为 学生会成员 对应statusId->2
			userService.saveOrUpdate(user);
			
			Member mer=aptToMer(applicant);
			applicantService.deleteById(applicant.getId());
			memberService.saveOrUpdate(mer);
		}
		return "merHandleEightFourTurnAll";
	}
	
	/**
	 * 最终未通过 将淘汰的申请者 数据 软删除（单个）
	 * @return
	 */
	public String merHandleFailFourDes(){
		Integer id=Integer.parseInt(request.getParameter("id"));
		userService.deleteById(id);//删除登录账号
		
		applicantService.pretendDeleteById(id);//软删除 申请者信息
		return "merHandleFailFourDes";
	}
	
	/**
	 * 最终未通过 将淘汰的申请者 数据 软删除（所有）
	 * @return
	 */
	public String merHandleFailFourDesAll(){
		Integer deptId=Integer.parseInt(request.getParameter("deptId"));
		List<Applicant> applicants=applicantService.getByFail(deptId);
		for (Applicant applicant : applicants) {
			Integer id =applicant.getId();
			userService.deleteById(id);//软删除登录账号
			applicantService.pretendDeleteById(id);//软删除 申请者信息
		}
		return "merHandleFailFourDesAll";
	}
	
	/**
	 * 将applicant类转化为member类
	 * @param apt
	 * @return
	 */
	public Member aptToMer(Applicant apt){
		Member mer=new Member();
		mer.setName(apt.getName());
		mer.setGender(apt.getGender());
		mer.setBirth(apt.getBirth());
		mer.setPlace(apt.getPlace());
		mer.setPolity(apt.getPolity());
		mer.setClazz(apt.getClazz());
		mer.setPhone(apt.getPhone());
		mer.setQq(apt.getQq());
		mer.setEmail(apt.getEmail());
		mer.setUser(apt.getUser());
		mer.setNation(apt.getNation());
		mer.setStudentNum(apt.getStudentNum());
		mer.setDormitory(apt.getDormitory());
		mer.setMerStatus(new MerStatus(5));//干事
		mer.setMerDepartment(apt.getNowHandleDept());
		mer.setAble(1);
		return mer;
	}
	
	
	
	/**
	 * 调剂部门
	 */
	public void merDeptAdjust(HttpServletRequest request){
		deptId=Integer.parseInt(request.getParameter("deptId"));
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
	public String merHandleTwoOneAdjust(){
		merDeptAdjust(request);
		return "merHandleTwoOneAdjust";
	}
	
	/**
	 * 第二轮面试 调剂申请者
	 * @return
	 */
	public String merHandleFourTwoAdjust(){
		merDeptAdjust(request);
		return "merHandleFourTwoAdjust";
	}
	
	/**
	 * 第三轮面试 调剂申请者
	 * @return
	 */
	public String merHandleSixThreeAdjust(){
		merDeptAdjust(request);
		return "merHandleSixThreeAdjust";
	}
	
	/**
	 * 在jsp页面显示 申请者 详细信息
	 * @return
	 */
	public String merAptShow(){
		Integer id =Integer.parseInt(request.getParameter("id"));
		request.setAttribute("applicant", applicantService.getById(id));
		return "merAptShow";
	}
	
	
	/**
	 * 改变成员状态的方法 (包括是否发送邮件 重置为0 未发送)
	 * @param statusId 目标状态的 id
	 */
	public void modifyStatus(int statusId,HttpServletRequest request){
		Integer id=Integer.parseInt(request.getParameter("id"));
		deptId=Integer.parseInt(request.getParameter("deptId"));
		applicantService.updateStatus(id, statusId);
	}
	
	/**
	 * 申请表审核 申请表审核通过 1->2
	 * @return
	 */
	public String merHandleOnePass(){
		modifyStatus(2,request);//2为 申请表通过状态
		return "merHandleOnePass";
	}
	
	/**
	 * 申请表审核 申请表审核未通过 1->3
	 * @return
	 */
	public String merHandleOneOut(){
		modifyStatus(3,request);//3为 申请表未通过状态
		return "merHandleOneOut";
	}
	
	/**
	 * 申请表审核 状态 2->3
	 * @return
	 */
	public String merHandleTwoOut(){
		modifyStatus(3,request);//3为 申请表未通过状态
		return "merHandleTwoOut";
	}
	
	/**
	 * 申请表审核 状态 3->2
	 * @return
	 */
	public String merHandleThreePass(){
		modifyStatus(2,request);//2为 申请表通过状态
		return "merHandleThreePass";
	}
	
	/**
	 * 第一轮面试 状态 2->4
	 * @return
	 */
	public String merHandleTwoOnePass(){
		modifyStatus(4,request);//4为 第一轮面试通过
		return "merHandleTwoOnePass";
	}
	
	/**
	 * 第一轮面试 状态2->5
	 * @return
	 */
	public String merHandleTwoOneOut(){
		modifyStatus(5,request);//5为 第一轮面试未通过
		return "merHandleTwoOneOut";
	}
	
	/**
	 * 第一轮面试 状态 4->5
	 * @return
	 */
	public String merHandleFourOneOut(){
		modifyStatus(5,request);
		return "merHandleFourOneOut";
	}
	
	/**
	 * 第一轮面试 状态 5->4
	 * @return
	 */
	public String merHandleFiveOnePass(){
		modifyStatus(4,request);
		return "merHandleFiveOnePass";
	}
	
	/**
	 * 第二轮面试 状态4->6
	 * @return
	 */
	public String merHandleFourTwoPass(){
		modifyStatus(6,request);
		return "merHandleFourTwoPass";
	}
	
	/**
	 * 第二轮面试 状态4->7
	 * @return
	 */
	public String merHandleFourTwoOut(){
		modifyStatus(7,request);
		return "merHandleFourTwoOut";
	}
	
	/**
	 * 第二轮面试 状态6->7
	 * @return
	 */
	public String merHandleSixTwoOut(){
		modifyStatus(7,request);
		return "merHandleSixTwoOut";
	}
	
	/**
	 * 第二轮面试 状态7->6
	 * @return
	 */
	public String merHandleSevenTwoPass(){
		modifyStatus(6,request);
		return "merHandleSevenTwoPass";
	}
	
	/**
	 * 第三轮面试 状态6->8
	 * @return
	 */
	public String merHandleSixThreePass(){
		modifyStatus(8,request);
		return "merHandleSixThreePass";
	}
	
	/**
	 * 第三轮面试 状态6->9
	 * @return
	 */
	public String merHandleSixThreeOut(){
		modifyStatus(9,request);
		return "merHandleSixThreeOut";
	}
	
	/**
	 * 第三轮面试 状态8->9
	 * @return
	 */
	public String merHandleEightThreeOut(){
		modifyStatus(9,request);
		return "merHandleEightThreeOut";
	}
	
	/**
	 * 第三轮面试 状态9->8
	 * @return
	 */
	public String merHandleNineThreePass(){
		modifyStatus(8,request);
		return "merHandleNineThreePass";
	}
	
	
	/**
	 * 成员修改个人资料(加载)
	 * @return
	 */
	public String merModifyBasicInfo(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer id=user.getId();
		request.setAttribute("member", memberService.getById(id));
		return "merModifyBasicInfo";
	}
	
	/**
	 * 成员修改个人资料(保存)
	 * @return
	 */
	public String merSaveBasicInfo(){
		String result=check(request,member);
		if(result=="ok"){
			memberService.saveOrUpdate(member);
			return "merSaveBasicInfo";
		}
		return "input";
	}
	
	public void prepareMerSaveBasicInfo(){
		User user=(User) request.getSession().getAttribute("session_user");
		Integer id=user.getId();
		member=memberService.getById(id);
	}
	
	/**
	 * 主席团修改 密码
	 * @return
	 */
	public String merChangePassword(){
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
					return "merChangePassword";
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
	 * 登录注销（退出登录）
	 * @return
	 */
	public String loginDestory(){
		request.getSession().invalidate();
		return "loginDestory";
	}
	
	/**
	 * 后台检查 成员 信息
	 * @param member 
	 * @return
	 */
	public String check(HttpServletRequest request,Member member){
		Map<String, String> errors=new HashMap<String, String>();
		
		String html="<i class='fa fa-exclamation-circle'></i> ";
		
		String name=member.getName();
		if(name==null||name.trim().isEmpty()){
			errors.put("name", html+"请输入姓名");
		}else if(!name.matches("[\u4e00-\u9fa5]+")){
			errors.put("name", html+"填写的姓名不是有效的中文名");
		}
		
		String place =member.getPlace();
		if(place==null||place.trim().isEmpty()){
			errors.put("place", html+"请输入籍贯");
		}else if(!place.matches("[\u4e00-\u9fa5]{1,5}(省|市|自治区)[\u4e00-\u9fa5]{1,5}(市|县|区)")){
			errors.put("place", html+"填写的籍贯格式不正确");
		}
		
		String polity=member.getPolity();
		if(polity.equals("0")||polity=="0"){
			errors.put("polity", html+"请选择你的政治面貌");
		}
		
		String nation=member.getNation();
		if(nation.equals("0")||nation=="0"){
			errors.put("nation", html+"请选择你的民族");
		}
		
		String dormitory=member.getDormitory();
		if(dormitory==null||dormitory.trim().isEmpty()){
			errors.put("dormitory", html+"请输入寝室号");
		}else if(!dormitory.matches("[\u4E00-\u9FA5]{1}\\d{1}(-)\\d{3}")){
			errors.put("dormitory", html+"填写的寝室号格式不正确");
		}
		
		String studentNum=member.getStudentNum();
		if(studentNum==null||studentNum.trim().isEmpty()){
			errors.put("studentNum", html+"请输入学号");
		}else if(!studentNum.matches("\\d{13}")){
			errors.put("studentNum", html+"填写的学号格式不正确");
		}else if(userService.getByUsername(studentNum)!=null){
			errors.put("studentNum", html+"该学号已经被注册，请直接登录");
		}
		
		Date birth=member.getBirth();
		if(birth == null){
			errors.put("birth", html+"请输入出生日期");
		}
		
		String clazz=member.getClazz();
		if(clazz==null||clazz.trim().isEmpty()){
			errors.put("clazz", html+"请输入专业和班级");
		}else if(!clazz.matches("[\u4E00-\u9FA5]{2}\\d{4}")){
			errors.put("clazz", html+"填写的专业班级格式不正确");
		}
		
		String phone=member.getPhone();
		if(phone==null||phone.trim().isEmpty()){
			errors.put("phone", html+"请输入手机号码");
		}else if(!phone.matches("1[3|4|5|7|8]\\d{9}")){
			errors.put("phone", html+"填写的手机号码格式不正确");
		}
		
		String qq=member.getQq();
		if(qq==null||qq.trim().isEmpty()){
			errors.put("qq", html+"请输入QQ号");
		}else if(!qq.matches("[1-9][0-9]{4,9}")){
			errors.put("qq", html+"填写的QQ号格式不正确");
		}
		
		String email=member.getEmail();
		if(email==null||email.trim().isEmpty()){
			errors.put("email", html+"请输入邮箱");
		}else if(!email.matches("\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+")){
			errors.put("email", html+"填写的邮箱格式不正确");
		}
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("member", member);
			return "error";
		}
		return "ok";
	} 
	
	/**
	 * 为页面从数据库加载部门
	 */
	public void loadDept(HttpServletRequest request){
		request.setAttribute("departments", departmentService.getAbled());
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
