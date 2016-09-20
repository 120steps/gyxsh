package com.gyxsh.actions;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gyxsh.entities.Applicant;
import com.gyxsh.entities.Member;
import com.gyxsh.entities.PageBean;
import com.gyxsh.entities.User;
import com.gyxsh.entities.UserStatus;
import com.gyxsh.service.ApplicantService;
import com.gyxsh.service.DepartmentService;
import com.gyxsh.service.MemberService;
import com.gyxsh.service.UserService;
import com.gyxsh.utils.MD5Util;
import com.gyxsh.utils.REimg;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@Scope("prototype")
@Component
public class AdminHandleAction extends ActionSupport implements ServletRequestAware,ModelDriven<Applicant>
,Preparable,RequestAware{
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
	@Autowired
	private ApplicantAction applicantAction;
	
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

	//获取jsp页面第一志愿的 部门id
	private int aptFirstTag_id;
	public void setAptFirstTag_id(int aptFirstTag_id) {
		this.aptFirstTag_id = aptFirstTag_id;
	}
	//获取jsp页面第二志愿的 部门id
	private int aptSecondTag_id;
	public void setAptSecondTag_id(int aptSecondTag_id) {
		this.aptSecondTag_id = aptSecondTag_id;
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
	public String adminHandleOne(){
		memberHandleAction.merHandle(1,request,page);
		return "adminHandleOne";
	}
	
	/**
	 * 申请表审核 显示 状态为申请表审核通过的申请者（status id为2）
	 * @return
	 */
	public String adminHandleTwo(){
		memberHandleAction.merHandle(2,request,page);
		return "adminHandleTwo";
	}
	
	/**
	 * 申请表审核 显示 状态为 报名表审核未通过 的申请者（status id为3）
	 * @return
	 */
	public String adminHandleThree(){
		memberHandleAction.merHandle(3,request,page);
		return "adminHandleThree";
	}
	
	/**
	 * 第一轮面试  显示 状态为 报名表审核通过 的申请者（status id为2）
	 * @return
	 */
	public String adminHandleTwoOne(){
		memberHandleAction.merHandle(2,request,page);
		return "adminHandleTwoOne";
	}
	
	/**
	 * 第一轮面试  显示 状态为 第一轮面试通过 的申请者（status id为4）
	 * @return
	 */
	public String adminHandleFourOne(){
		memberHandleAction.merHandle(4,request,page);
		return "adminHandleFourOne";
	}
	
	/**
	 * 第一轮面试  显示 状态为 第一轮面试未通过 的申请者（status id为5）
	 * @return
	 */
	public String adminHandleFiveOne(){
		memberHandleAction.merHandle(5,request,page);
		return "adminHandleFiveOne";
	}
	
	/**
	 * 第一轮面试 显示状态为 申请表审核通过 的被调剂的申请者（status id为2）
	 * @return
	 */
	public String adminHandleTwoOneAdjusted(){
		memberHandleAction.merHandleAdjusted(2,request,page);
		return "adminHandleTwoOneAdjusted";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第一轮面试通过 的申请者（status id为4）
	 * @return
	 */
	public String adminHandleFourTwo(){
		memberHandleAction.merHandle(4,request,page);
		return "adminHandleFourTwo";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第二轮面试通过 的申请者（status id为6）
	 * @return
	 */
	public String adminHandleSixTwo(){
		memberHandleAction.merHandle(6,request,page);
		return "adminHandleSixTwo";
	}
	
	/**
	 * 第二轮面试  显示 状态为 第二轮面试未通过 的申请者（status id为7）
	 * @return
	 */
	public String adminHandleSevenTwo(){
		memberHandleAction.merHandle(7,request,page);
		return "adminHandleSevenTwo";
	}
	
	/**
	 * 第二轮面试 显示状态为 第一轮面试通过 的被调剂的申请者（status id为4）
	 * @return
	 */
	public String adminHandleFourTwoAdjusted(){
		memberHandleAction.merHandleAdjusted(4,request,page);
		return "adminHandleFourTwoAdjusted";
	}
	
	/**
	 * 第三轮面试  显示 状态为 第二轮面试通过 的申请者（status id为6）
	 * @return
	 */
	public String adminHandleSixThree(){
		memberHandleAction.merHandle(6,request,page);
		return "adminHandleSixThree";
	}
	
	/**
	 * 第三轮面试 显示状态为第三轮面试通过 的申请者（status id为8）
	 * @return
	 */
	public String adminHandleEightThree(){
		memberHandleAction.merHandle(8,request,page);
		return "adminHandleEightThree";
	}
	
	/**
	 * 第三轮面试 显示状态为 第三轮面试未通过 的申请者（status id为9）
	 * @return
	 */
	public String adminHandleNineThree(){
		memberHandleAction.merHandle(9,request,page);
		return "adminHandleNineThree";
	}
	
	/**
	 * 第三轮面试 显示状态为 第二轮面试通过 的被调剂的申请者（status id为6）
	 * @return
	 */
	public String adminHandleSixThreeAdjusted(){
		memberHandleAction.merHandleAdjusted(6,request,page);
		return "adminHandleSixThreeAdjusted";
	}
	
	/**
	 * 最终通过  显示状态为 第三轮面试通过 的申请者（status id为8）
	 * @return
	 */
	public String adminHandleEightFour(){
		memberHandleAction.merHandle(8,request,page);
		return "adminHandleEightFour";
	}
	
	/**
	 * 最终未通过 显示状态为 所有未通过的 申请者（status id为3/5/7/9）
	 * @return
	 */
	public String adminHandleFailFour(){
		memberHandleAction.merHandleFail(request,page);
		return "adminHandleFailFour";
	}

	/**
	 * 最终通过 将申请者 数据导入 学生会成员（单个）
	 * @return
	 */
	public String adminHandleEightFourTurn(){
		Integer id=Integer.parseInt(request.getParameter("id"));
		User user=userService.getById(id);
		user.setUserStatus(new UserStatus(2));//将账号身份改为 学生会成员 对应statusId->2
		userService.saveOrUpdate(user);
		
		Applicant apt=applicantService.getById(id);
		Member mer=memberHandleAction.aptToMer(apt);
		applicantService.deleteById(id);
		memberService.saveOrUpdate(mer);
		return "adminHandleEightFourTurn";
	}
	
	/**
	 * 最终通过 将申请者 数据导入 学生会成员 （所有）
	 * @return
	 */
	public String adminHandleEightFourTurnAll(){
		Integer deptId=Integer.parseInt(request.getParameter("deptId"));
		List<Applicant> applicants=applicantService.getByStatusAndDept(8,deptId);
		for (Applicant applicant : applicants) {
			User user=userService.getById(applicant.getId());
			user.setUserStatus(new UserStatus(2));//将账号身份改为 学生会成员 对应statusId->2
			userService.saveOrUpdate(user);
			
			Member mer=memberHandleAction.aptToMer(applicant);
			applicantService.deleteById(applicant.getId());
			memberService.saveOrUpdate(mer);
		}
		return "adminHandleEightFourTurnAll";
	}
	
	/**
	 * 最终未通过 将淘汰的申请者 数据 软删除（单个）
	 * @return
	 */
	public String adminHandleFailFourDes(){
		Integer id=Integer.parseInt(request.getParameter("id"));
		userService.deleteById(id);//删除登录账号
		
		applicantService.pretendDeleteById(id);//软删除 申请者信息
		return "adminHandleFailFourDes";
	}
	
	/**
	 * 最终未通过 将淘汰的申请者 数据 软删除（所有）
	 * @return
	 */
	public String adminHandleFailFourDesAll(){
		Integer deptId=Integer.parseInt(request.getParameter("deptId"));
		List<Applicant> applicants=applicantService.getByFail(deptId);
		for (Applicant applicant : applicants) {
			Integer id =applicant.getId();
			userService.deleteById(id);//软删除登录账号
			applicantService.pretendDeleteById(id);//软删除 申请者信息
		}
		return "adminHandleFailFourDesAll";
	}
	
	/**
	 * 第一轮面试 调剂申请者
	 * @return
	 */
	public String adminHandleTwoOneAdjust(){
		memberHandleAction.merDeptAdjust(request);
		return "adminHandleTwoOneAdjust";
	}
	
	/**
	 * 第二轮面试 调剂申请者
	 * @return
	 */
	public String adminHandleFourTwoAdjust(){
		memberHandleAction.merDeptAdjust(request);
		return "adminHandleFourTwoAdjust";
	}
	
	/**
	 * 第三轮面试 调剂申请者
	 * @return
	 */
	public String adminHandleSixThreeAdjust(){
		memberHandleAction.merDeptAdjust(request);
		return "adminHandleSixThreeAdjust";
	}
	
	/**
	 * 申请表审核 申请表审核通过 1->2
	 * @return
	 */
	public String adminHandleOnePass(){
		memberHandleAction.modifyStatus(2,request);//2为 申请表通过状态
		return "adminHandleOnePass";
	}
	
	/**
	 * 申请表审核 申请表审核未通过 1->3
	 * @return
	 */
	public String adminHandleOneOut(){
		memberHandleAction.modifyStatus(3,request);//3为 申请表未通过状态
		return "adminHandleOneOut";
	}
	
	/**
	 * 申请表审核 状态 2->3
	 * @return
	 */
	public String adminHandleTwoOut(){
		memberHandleAction.modifyStatus(3,request);//3为 申请表未通过状态
		return "adminHandleTwoOut";
	}
	
	/**
	 * 申请表审核 状态 3->2
	 * @return
	 */
	public String adminHandleThreePass(){
		memberHandleAction.modifyStatus(2,request);//2为 申请表通过状态
		return "adminHandleThreePass";
	}
	
	/**
	 * 第一轮面试 状态 2->4
	 * @return
	 */
	public String adminHandleTwoOnePass(){
		memberHandleAction.modifyStatus(4,request);//4为 第一轮面试通过
		return "adminHandleTwoOnePass";
	}
	
	/**
	 * 第一轮面试 状态2->5
	 * @return
	 */
	public String adminHandleTwoOneOut(){
		memberHandleAction.modifyStatus(5,request);//5为 第一轮面试未通过
		return "adminHandleTwoOneOut";
	}
	
	/**
	 * 第一轮面试 状态 4->5
	 * @return
	 */
	public String adminHandleFourOneOut(){
		memberHandleAction.modifyStatus(5,request);
		return "adminHandleFourOneOut";
	}
	
	/**
	 * 第一轮面试 状态 5->4
	 * @return
	 */
	public String adminHandleFiveOnePass(){
		memberHandleAction.modifyStatus(4,request);
		return "adminHandleFiveOnePass";
	}
	
	/**
	 * 第二轮面试 状态4->6
	 * @return
	 */
	public String adminHandleFourTwoPass(){
		memberHandleAction.modifyStatus(6,request);
		return "adminHandleFourTwoPass";
	}
	
	/**
	 * 第二轮面试 状态4->7
	 * @return
	 */
	public String adminHandleFourTwoOut(){
		memberHandleAction.modifyStatus(7,request);
		return "adminHandleFourTwoOut";
	}
	
	/**
	 * 第二轮面试 状态6->7
	 * @return
	 */
	public String adminHandleSixTwoOut(){
		memberHandleAction.modifyStatus(7,request);
		return "adminHandleSixTwoOut";
	}
	
	/**
	 * 第二轮面试 状态7->6
	 * @return
	 */
	public String adminHandleSevenTwoPass(){
		memberHandleAction.modifyStatus(6,request);
		return "adminHandleSevenTwoPass";
	}
	
	/**
	 * 第三轮面试 状态6->8
	 * @return
	 */
	public String adminHandleSixThreePass(){
		memberHandleAction.modifyStatus(8,request);
		return "adminHandleSixThreePass";
	}
	
	/**
	 * 第三轮面试 状态6->9
	 * @return
	 */
	public String adminHandleSixThreeOut(){
		memberHandleAction.modifyStatus(9,request);
		return "adminHandleSixThreeOut";
	}
	
	/**
	 * 第三轮面试 状态8->9
	 * @return
	 */
	public String adminHandleEightThreeOut(){
		memberHandleAction.modifyStatus(9,request);
		return "adminHandleEightThreeOut";
	}
	
	/**
	 * 第三轮面试 状态9->8
	 * @return
	 */
	public String adminHandleNineThreePass(){
		memberHandleAction.modifyStatus(8,request);
		return "adminHandleNineThreePass";
	}
	
	
	
	
	
	
	
	
	/**
	 * 在jsp页面显示 申请者 详细信息 
	 * @return
	 */
	public String adminAptShow(){
		memberHandleAction.loadDept(request);
		Integer id =Integer.parseInt(request.getParameter("id"));
		request.setAttribute("applicant", applicantService.getById(id));
		return "adminAptShow";
	}
	
	/**
	 * 在jsp页面修改 申请者信息
	 * @return
	 */
	public String adminAptModify(){
		String result=check(req);
		if(!result.equals("error")){
			if(!result.equals("none")){
				applicant.setImgSrc(result);//保存上传图片路径
			}
			applicantService.saveOrUpdate(applicant);
			req.put("applicant", applicant);
			return "adminAptModify";
		}
		return "adminAptModifyInput";
	}
	
	public void prepareAdminAptModify(){
		applicant=applicantService.getById(id);
	}
	
	/**
	 * 后台检查报名报信息
	 * @param applicant 
	 * @return
	 */
	public String check(Map<String, Object> request){
		Map<String, String> errors=new HashMap<String, String>();
		
		String html="<i class='fa fa-exclamation-circle'></i> ";
		
		String name=applicant.getName();
		if(name==null||name.trim().isEmpty()){
			errors.put("name", html+"请输入姓名");
		}else if(!name.matches("[\u4e00-\u9fa5]+")){
			errors.put("name", html+"填写的姓名不是有效的中文名");
		}
		
		String place =applicant.getPlace();
		if(place==null||place.trim().isEmpty()){
			errors.put("place", html+"请输入籍贯");
		}else if(!place.matches("[\u4e00-\u9fa5]{1,5}(省|市|自治区)[\u4e00-\u9fa5]{1,5}(市|县|区)")){
			errors.put("place", html+"填写的籍贯格式不正确");
		}
		
		String polity=applicant.getPolity();
		if(polity.equals("0")||polity=="0"){
			errors.put("polity", html+"请选择你的政治面貌");
		}
		
		String nation=applicant.getNation();
		if(nation.equals("0")||nation=="0"){
			errors.put("nation", html+"请选择你的民族");
		}
		
		String dormitory=applicant.getDormitory();
		if(dormitory==null||dormitory.trim().isEmpty()){
			errors.put("dormitory", html+"请输入寝室号");
		}else if(!dormitory.matches("[\u4E00-\u9FA5]{1}[0-9]{1}(-)[0-9]{3}")){
			errors.put("dormitory", html+"填写的寝室号格式不正确");
		}
		
		String studentNum=applicant.getStudentNum();
		if(studentNum==null||studentNum.trim().isEmpty()){
			errors.put("studentNum", html+"请输入学号");
		}else if(!studentNum.matches("\\d{13}")){
			errors.put("studentNum", html+"填写的学号格式不正确");
		}
		
		Date birth=applicant.getBirth();
		if(birth == null){
			errors.put("birth", html+"请输入出生日期");
		}
		
		String clazz=applicant.getClazz();
		if(clazz==null||clazz.trim().isEmpty()){
			errors.put("clazz", html+"请输入专业和班级");
		}else if(!clazz.matches("[\u4E00-\u9FA5]{2}[0-9]{4}")){
			errors.put("clazz", html+"填写的专业班级格式不正确");
		}
		
		String phone=applicant.getPhone();
		if(phone==null||phone.trim().isEmpty()){
			errors.put("phone", html+"请输入手机号码");
		}else if(!phone.matches("1[3|4|5|7|8]\\d{9}")){
			errors.put("phone", html+"填写的手机号码格式不正确");
		}
		
		String qq=applicant.getQq();
		if(qq==null||qq.trim().isEmpty()){
			errors.put("qq", html+"请输入QQ号");
		}else if(!qq.matches("[1-9][0-9]{4,9}")){
			errors.put("qq", html+"填写的QQ号格式不正确");
		}
		
		String email=applicant.getEmail();
		if(email==null||email.trim().isEmpty()){
			errors.put("email", html+"请输入邮箱");
		}else if(!email.matches("\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+")){
			errors.put("email", html+"填写的邮箱格式不正确");
		}
		
		if(aptFirstTag_id==0){
			errors.put("aptFirstTag", html+"请选择第一志愿");
		}else{
			applicant.setAptFirstTag(departmentService.getById(aptFirstTag_id));
		}
		
		if(aptSecondTag_id==0){
			errors.put("aptSecondTag", html+"请选择第二志愿");
		}else{
			applicant.setAptSecondTag(departmentService.getById(aptSecondTag_id));
		}
		
		String aptOtherOrg = applicant.getAptOtherOrg();
		if(aptOtherOrg==null||aptOtherOrg.trim().isEmpty()){
			errors.put("aptOtherOrg", html+"请填写已报名或参加其他社团");
		}
		
		String aptSpeciality = applicant.getAptSpeciality();
		if(aptSpeciality==null||aptSpeciality.trim().isEmpty()){
			errors.put("aptSpeciality", html+"请填写特长");
		}
		
		String aptInterest = applicant.getAptInterest();
		if(aptInterest==null||aptInterest.trim().isEmpty()){
			errors.put("aptInterest", html+"请填写兴趣爱好");
		}
		
		String aptExperience = applicant.getAptExperience();
		if(aptExperience==null||aptExperience.trim().isEmpty()){
			errors.put("aptExperience", html+"请填写工作经历");
		}
		
		String aptKnow = applicant.getAptKnow();
		if(aptKnow==null||aptKnow.trim().isEmpty()){
			errors.put("aptKnow", html+"请填写对学生会的认识");
		}
		
		String aptExpectation = applicant.getAptExpectation();
		if(aptExpectation==null||aptExpectation.trim().isEmpty()){
			errors.put("aptExpectation", html+"请填写工作展望");
		}
		
		String path=null;
		if(errors.size()==0){
			if(img==null){
				return "none";
			}else if(img.length()<=200*1024){
				path=upload();
			}else{
				errors.put("img", html+"图片大小不可以超过200KB");
			}
		}
		
		if(errors.size()>0){
			request.put("errors", errors);
			request.put("applicant", applicant);
			request.put("img", img.getPath());
			applicantAction.loadDepartment(request);
			return "error";
		}
		
		return path;
	}
	
	/*
	 * 照片上传
	 */
	private File img;  
    public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	public String getImgFileName() {  
        return imgFileName;  
    }  
    public void setImgFileName(String imgFileName) {  
        this.imgFileName = imgFileName;  
    }  
    public String getImgContentType() {  
        return imgContentType;  
    }  
    public void setImgContentType(String imgContentType) {  
        this.imgContentType = imgContentType;  
    }  
  
    //文件名称  
    private String imgFileName;  
    //文件类型  
    private String imgContentType;  
    //注意：文件名称和文件类型的名称前缀必须相同，  
	
	public String upload(){
		//文件保存的相对地址
		String path=null;
		//目标路径
		String tagPath=null;
    	try {
    		//获取需要上传文件的文件路径  
            File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("/")+"uploadImg");
            long sb=new Date().getTime();
            //保存相对路径
            path= "uploadImg" + File.separator +sb+imgFileName;
            tagPath=uploadFile + File.separator +sb+imgFileName;
            //判断文件是否上传，如果上传的话将会创建该目录  
            if(!uploadFile.exists()){  
                uploadFile.mkdir(); //创建该目录  
            }  
            
            //改变图片的长宽
            REimg.resizeImage(img.getPath(),tagPath, 100, 140);
            
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
//			try {
//				input.close();
//				out.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
        return path;
    }
	
	
	/**
	 * 管理员修改 密码
	 * @return
	 */
	public String adminChangePassword(){
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
					return "adminChangePassword";
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
	
	
	@Override
	public void prepare() throws Exception {}

	private Applicant applicant;
	@Override
	public Applicant getModel() {
		return applicant;
	}

	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
	
	private Map<String, Object> req;
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.req=arg0;
	}

}
