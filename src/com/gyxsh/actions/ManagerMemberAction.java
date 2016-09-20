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

import com.gyxsh.entities.Member;
import com.gyxsh.entities.MerStatus;
import com.gyxsh.entities.PageBeanMember;
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
public class ManagerMemberAction extends ActionSupport implements ModelDriven<Member>,
Preparable,ServletRequestAware{
	@Autowired
	private MemberService memberService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
	@Autowired
	private ApplicantService applicantService;
	private Integer deptId;
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 创建学生会成员 加载部门 管理员
	 * @return
	 */
	public String adminCreateMember(){
		loadDeptAll(request);
		return "adminCreateMember";
	}
	
	/**
	 * 创建学生会成员 管理员
	 * @return
	 */
	public String adminCreateMemberSave(){
		String result=checkCreatMember(request);
		if(result.equals("ok")){
			User user=null;
			if(userService.getByUsername(member.getStudentNum())!=null){
				user=userService.getByUsername(member.getStudentNum());
			}else{
				user=new User();
			}
			user.setUsername(member.getStudentNum());
			user.setPassword(MD5Util.generatePassword(member.getStudentNum()));
			user.setUserStatus(new UserStatus(2));
			user.setAble(1);
			userService.saveOrUpdate(user);
			
			member.setUser(user);
			member.setAble(1);
			memberService.saveOrUpdate(member);
			return "adminCreateMemberSave";
		}
		return "adminCreateMemberInput";
	}
	
	public void prepareAdminCreateMemberSave(){
		member=new Member();
	}
	
	/**
	 * 创建学生会成员 加载部门 主席团
	 * @return
	 */
	public String merCreateMember(){
		loadDept(request);
		return "merCreateMember";
	}
	/**
	 * 创建学生会成员 主席团
	 * @return
	 */
	public String merCreateMemberSave(){
		String result=checkCreatMember(request);
		if(result.equals("ok")){
			User user=null;
			if(userService.getByUsername(member.getStudentNum())!=null){
				user=userService.getByUsername(member.getStudentNum());
			}else{
				user=new User();
			}
			user.setUsername(member.getStudentNum());
			user.setPassword(MD5Util.generatePassword(member.getStudentNum()));
			user.setUserStatus(new UserStatus(2));
			user.setAble(1);
			userService.saveOrUpdate(user);
			
			member.setUser(user);
			member.setAble(1);
			memberService.saveOrUpdate(member);
			return "merCreateMemberSave";
		}
		return "merCreateMemberInput";
	}
	
	public void prepareMerCreateMemberSave(){
		member=new Member();
	}
	
	public String secCreateMember(){
		return "secCreateMember";
	}
	/**
	 * 创建学生会成员 部长
	 * @return
	 */
	public String secCreateMemberSave(){
		String result=checkCreatMemberSec(request);
		if(result.equals("ok")){
			User user=null;
			if(userService.getByUsername(member.getStudentNum())!=null){
				user=userService.getByUsername(member.getStudentNum());
			}else{
				user=new User();
			}
			user.setUsername(member.getStudentNum());
			user.setPassword(MD5Util.generatePassword(member.getStudentNum()));
			user.setUserStatus(new UserStatus(2));
			user.setAble(1);
			userService.saveOrUpdate(user);
			
			member.setUser(user);
			member.setAble(1);
			user=(User) request.getSession().getAttribute("session_user");
			Integer deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();
			member.setMerDepartment(departmentService.getById(deptId));
			memberService.saveOrUpdate(member);
			return "secCreateMemberSave";
		}
		return "secCreateMemberInput";
	}
	
	public void prepareSecCreateMemberSave(){
		member=new Member();
	}
	
	/*
	 * 分页操作
	 */
	private PageBeanMember pageBeanMember;
	private List<Member> members;
	private int page=1;
	public void setPageBeanMember(PageBeanMember pageBeanMember) {
		this.pageBeanMember = pageBeanMember;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	/**
	 * 分页操作-显示成员 able=1
	 * @param deptId 部门id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 */
	public void queryForPageAble(HttpServletRequest request){
		loadDeptAll(request);
		Integer deptId=Integer.parseInt(request.getParameter("deptId"));
		pageBeanMember=memberService.queryForPageByDept(deptId, 8, page);
		request.setAttribute("pageBeanMember", pageBeanMember);
	}
	
	/**
	 * 分页操作-显示注销成员 able=0
	 * @param deptId 部门id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 */
	public void queryForPageDisable(HttpServletRequest request){
		loadDeptAll(request);
		Integer deptId=Integer.parseInt(request.getParameter("deptId"));
		pageBeanMember=memberService.queryForPageByDeptDisable(deptId, 8, page);
		request.setAttribute("pageBeanMember", pageBeanMember);
	}
	
	/**
	 * 显示成员（able=1） 管理员
	 * @return
	 */
	public String adminShowMemberAble(){
		queryForPageAble(request);
		return "adminShowMemberAble";
	}
	
	/**
	 * 显示成员（able=0） 管理员
	 * @return
	 */
	public String adminShowMemberDisable(){
		queryForPageDisable(request);
		return "adminShowMemberDisable";
	}
	
	/**
	 * 显示成员（able=1） 主席团
	 * @return
	 */
	public String merShowMemberAble(){
		queryForPageAble(request);
		return "merShowMemberAble";
	}
	
	/**
	 * 显示成员（able=0） 主席团
	 * @return
	 */
	public String merShowMemberDisable(){
		queryForPageDisable(request);
		return "merShowMemberDisable";
	}
	
	/**
	 * 分页操作-显示成员 able=1 部长
	 * @param deptId 部门id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 */
	public void queryForPageAbleSec(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("session_user");
		int deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();
		pageBeanMember=memberService.queryForPageByDept(deptId, 8, page);
		request.setAttribute("pageBeanMember", pageBeanMember);
	}
	
	/**
	 * 分页操作-显示注销成员 able=0 部长
	 * @param deptId 部门id
	 * @param ps 一页显示记录数
	 * @param cp 当前页
	 */
	public void queryForPageDisableSec(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("session_user");
		int deptId=memberService.getById(user.getId()).getMerDepartment().getDeptId();
		pageBeanMember=memberService.queryForPageByDeptDisable(deptId, 8, page);
		request.setAttribute("pageBeanMember", pageBeanMember);
	}
	
	/**
	 * 显示成员（able=1） 部长
	 * @return
	 */
	public String secShowMemberAble(){
		queryForPageAbleSec(request);
		return "secShowMemberAble";
	}
	
	/**
	 * 显示成员（able=0） 部长
	 * @return
	 */
	public String secShowMemberDisable(){
		queryForPageDisableSec(request);
		return "secShowMemberDisable";
	}
	
	/**
	 * 注销 成员 管理员
	 * @return
	 */
	public String adminShowMemberAbleOut(){
		showMemberAbleOut(request);
		return "adminShowMemberAbleOut";
	}
	
	/**
	 * 注销 成员 主席团
	 * @return
	 */
	public String merShowMemberAbleOut(){
		showMemberAbleOut(request);
		return "merShowMemberAbleOut";
	}
	
	/**
	 * 注销 成员 部长
	 * @return
	 */
	public String secShowMemberAbleOut(){
		showMemberAbleOut(request);
		return "secShowMemberAbleOut";
	}
	
	/**
	 * 注销成员
	 * @param request
	 */
	public void showMemberAbleOut(HttpServletRequest request){
		Integer id=Integer.parseInt(request.getParameter("id"));
		Member member=memberService.getById(id);
		member.setAble(0);
		memberService.saveOrUpdate(member);
		User user=userService.getById(id);
		user.setAble(0);
		userService.saveOrUpdate(user);
	}
	
	/**
	 * 恢复 成员 管理员
	 * @return
	 */
	public String adminShowMemberDisablePass(){
		showMemberDisablePass(request);
		return "adminShowMemberDisablePass";
	}
	
	/**
	 * 恢复 成员 管理员
	 * @return
	 */
	public String merShowMemberDisablePass(){
		showMemberDisablePass(request);
		return "merShowMemberDisablePass";
	}
	
	/**
	 * 恢复 成员 管理员
	 * @return
	 */
	public String secShowMemberDisablePass(){
		showMemberDisablePass(request);
		return "secShowMemberDisablePass";
	}
	
	/**
	 * 恢复 成员
	 * @param request
	 */
	public void showMemberDisablePass(HttpServletRequest request){
		Integer id=Integer.parseInt(request.getParameter("id"));
		Member member=memberService.getByIdAll(id);
		member.setAble(1);
		memberService.saveOrUpdate(member);
		User user=userService.getByIdAll(id);
		user.setAble(1);
		userService.saveOrUpdate(user);
	}
	
	/**
	 * 根据jsp页面的id 查询成员信息
	 */
	public String showMemberById(){
		Integer id=Integer.parseInt(request.getParameter("id"));
		Member member = memberService.getByIdAll(id);
		request.setAttribute("member", member);
		return "showMemberById";
	}
	
	/**
	 * 在jsp页面显示 成员基本信息 管理员
	 * @return
	 */
	public String adminShowMemberById(){
		Integer id=Integer.parseInt(request.getParameter("id"));
		Member member = memberService.getByIdAll(id);
		loadDeptAll(request);
		request.setAttribute("member", member);
		return "adminShowMemberById";
	}
	
	/**
	 * 保存成员基本信息  管理员
	 * @return
	 */
	public String adminShowMemberByIdSave(){
		String result=checkMember(request);
		if(result.equals("ok")){
			memberService.saveOrUpdate(member);
			return "adminShowMemberByIdSave";
		}
		return "adminShowMemberByIdInput";
	}
	
	public void prepareAdminShowMemberByIdSave(){
		Integer id=Integer.parseInt(request.getParameter("id"));
		member=memberService.getByIdAll(id);
	}
	
	/**
	 * 在jsp页面 显示成员账户信息
	 * @return
	 */
	public String adminShowMemberShowAccount(){
		Integer id= Integer.parseInt(request.getParameter("id"));
		User user=userService.getByIdAll(id);
		request.setAttribute("user", user);
		return "adminShowMemberShowAccount";
	}
	
	/**
	 * 修改账户信息 管理员
	 * @return
	 */
	public String adminShowMemberModifyAccount(){
		Integer id= Integer.parseInt(request.getParameter("id"));
		User user=userService.getByIdAll(id);
		String result=checkAccount(user, request);
		if(result.equals("ok")){
			userService.saveOrUpdate(user);
			return "adminShowMemberModifyAccount";
		}
		return "adminShowMemberShowAccountInput";
	}
	
	/**
	 * 后台检验 账户修改
	 * @param user
	 * @return
	 */
	public String checkAccount(User user,HttpServletRequest request){
		Map<String, String> errors=new HashMap<String, String>();
		
		String html="<i class='fa fa-exclamation-circle'></i> ";
		
		String username=request.getParameter("username");
		if(username==null||username.trim().isEmpty()){
			errors.put("username", html+"请输入账号");
		}else if(!username.matches("\\d{13}")){
			errors.put("username", html+"填写的账号格式不正确，请填写13位数字");
		}else if(userService.getByUsername(username)!=null){
			errors.put("username", html+"该账号已存在，请重新输入账号");
		}
		
		String password=request.getParameter("password");
		if(password==null||password.trim().isEmpty()){
			errors.put("password", html+"请输入密码");
		}
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			return "error";
		}
		user.setUsername(username);
		user.setPassword(MD5Util.generatePassword(password));
		
		return "ok";
	}
	
	
	/**
	 * 后台检查 成员信息
	 * @param applicant 
	 * @return
	 */
	public String checkMember(HttpServletRequest request){
		Map<String, String> errors=new HashMap<String, String>();
		
		String html="<i class='fa fa-exclamation-circle'></i> ";
		
		String name=member.getName();
		if(name==null||name.trim().isEmpty()){
			errors.put("name", html+"请输入姓名");
		}else if(!name.matches("[\u4e00-\u9fa5]+")){
			errors.put("name", html+"填写的姓名不是有效的中文名");
		}
		
		String place=member.getPlace();
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
		
		Integer merStatusId=member.getMerStatus().getMerStatusId();
		if(merStatusId==0){
			errors.put("merStatus", html+"请选择职位");
		}else{
			member.setMerStatus(new MerStatus(merStatusId));
		}
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("member", member);
			loadDeptAll(request);
			return "error";
		}
		
		return "ok";
	}
	
	/**
	 * 后台检查 新增成员（管理员、主席团）
	 * @param applicant 
	 * @return
	 */
	public String checkCreatMember(HttpServletRequest request){
		Map<String, String> errors=new HashMap<String, String>();
		
		String html="<i class='fa fa-exclamation-circle'></i> ";
		
		String name=member.getName();
		if(name==null||name.trim().isEmpty()){
			errors.put("name", html+"请输入姓名");
		}else if(!name.matches("[\u4e00-\u9fa5]+")){
			errors.put("name", html+"填写的姓名不是有效的中文名");
		}
		
		String studentNum=member.getStudentNum();
		if(studentNum==null||studentNum.trim().isEmpty()){
			errors.put("studentNum", html+"请输入学号");
		}else if(!studentNum.matches("\\d{13}")){
			errors.put("studentNum", html+"填写的学号格式不正确");
		}else if(userService.getByUsername(studentNum)!=null){
			User user=userService.getByUsername(studentNum);
			Integer userStatusId=user.getUserStatus().getUserStatusId();
			if(userStatusId==2){
				errors.put("studentNum", html+"该学号已经被注册,请在学生会成员中寻找相关人员");
			}else if(userStatusId==1){
				errors.put("studentNum", html+"该学号无法注册");
			}else if(userStatusId==3){
				if(applicantService.getById(user.getId())!=null){
					errors.put("studentNum", html+"该学号尚为申请者账号，无法注册为学生会成员");
				}else{
					applicantService.deleteById(user.getId());
				}
			}
		}
		
		String clazz=member.getClazz();
		if(clazz==null||clazz.trim().isEmpty()){
			errors.put("clazz", html+"请输入专业和班级");
		}
		
		Integer deptId=member.getMerDepartment().getDeptId();
		if(deptId==0){
			errors.put("merDepartment", html+"请选择部门");
		}else{
			member.setMerDepartment(departmentService.getById(deptId));
		}
		
		Integer merStatusId=member.getMerStatus().getMerStatusId();
		if(merStatusId==0){
			errors.put("merStatus", html+"请选择职位");
		}else{
			member.setMerStatus(new MerStatus(merStatusId));
		}
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("member", member);
			loadDeptAll(request);
			return "error";
		}
		
		return "ok";
	}
	
	/**
	 * 后台检查 新增成员（部长）
	 * @param applicant 
	 * @return
	 */
	public String checkCreatMemberSec(HttpServletRequest request){
		Map<String, String> errors=new HashMap<String, String>();
		
		String html="<i class='fa fa-exclamation-circle'></i> ";
		
		String name=member.getName();
		if(name==null||name.trim().isEmpty()){
			errors.put("name", html+"请输入姓名");
		}else if(!name.matches("[\u4e00-\u9fa5]+")){
			errors.put("name", html+"填写的姓名不是有效的中文名");
		}
		
		String studentNum=member.getStudentNum();
		if(studentNum==null||studentNum.trim().isEmpty()){
			errors.put("studentNum", html+"请输入学号");
		}else if(!studentNum.matches("\\d{13}")){
			errors.put("studentNum", html+"填写的学号格式不正确");
		}else if(userService.getByUsername(studentNum)!=null){
			User user=userService.getByUsername(studentNum);
			Integer userStatusId=user.getUserStatus().getUserStatusId();
			if(userStatusId==2){
				errors.put("studentNum", html+"该学号已经被注册,请在学生会成员中寻找相关人员");
			}else if(userStatusId==1){
				errors.put("studentNum", html+"该学号无法注册");
			}else if(userStatusId==3){
				if(applicantService.getById(user.getId())!=null){
					errors.put("studentNum", html+"该学号尚为申请者账号，无法注册为学生会成员");
				}else{
					applicantService.deleteById(user.getId());
				}
			}
		}
		
		String clazz=member.getClazz();
		if(clazz==null||clazz.trim().isEmpty()){
			errors.put("clazz", html+"请输入专业和班级");
		}
		
		Integer merStatusId=member.getMerStatus().getMerStatusId();
		if(merStatusId==0){
			errors.put("merStatus", html+"请选择职位");
		}else{
			member.setMerStatus(new MerStatus(merStatusId));
		}
		
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.setAttribute("member", member);
			loadDeptAll(request);
			return "error";
		}
		
		return "ok";
	}
	
	/**
	 * 为页面从数据库加载部门(包括主席团)
	 */
	public void loadDeptAll(HttpServletRequest request){
		request.setAttribute("departments", departmentService.getAll());
	}
	
	/**
	 * 为页面从数据库加载部门(不包括主席团)
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
