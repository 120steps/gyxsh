package com.gyxsh.actions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gyxsh.entities.Applicant;
import com.gyxsh.entities.AptStatus;
import com.gyxsh.entities.User;
import com.gyxsh.entities.UserStatus;
import com.gyxsh.service.ApplicantService;
import com.gyxsh.service.DepartmentService;
import com.gyxsh.service.UserService;
import com.gyxsh.utils.MD5Util;
import com.gyxsh.utils.REimg;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@Scope("prototype")
@Component
public class ApplicantAction extends ActionSupport implements RequestAware,
Preparable,ModelDriven<Applicant>,ServletRequestAware{
	@Autowired
	private ApplicantService applicantService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
	
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
	//获取jsp页面的applicant的id
	private Integer id;
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 进入到报名报页面
	 */
	public String aptInput(){
		loadDepartment(request);
		return "aptInput";
	}
	
	/**
	 * 报名报提交
	 * @return
	 */
	public String aptRegister(){
		String result=check(request);
		if(!result.equals("error")){
			applicant.setImgSrc(result);//保存上传图片路径
			applicant.setAptStatus(new AptStatus(1));//待审核
			
			//设置第一志愿、第二志愿、上一个处理部门、当前处理部门
			applicant.setPreHandleDept(departmentService.getById(aptFirstTag_id));//上一个处理部门，默认为第一志愿
			applicant.setNowHandleDept(departmentService.getById(aptFirstTag_id));//现在处理部门，默认为第一志愿
			
			//给该用户添加user 并创建user账号
			User user=new User();
			user.setUsername(applicant.getStudentNum());
			user.setPassword(MD5Util.generatePassword(applicant.getStudentNum()));//密码加密
			user.setUserStatus(new UserStatus(3));
			user.setAble(1);
			userService.saveOrUpdate(user);
			applicant.setUser(user);
			
			applicant.setAble(1);
			applicantService.saveOrUpdate(applicant);
			request.put("applicant", applicant);
			return "aptRegister";
		}
		return "aptInput";
	}
	
	public void prepareAptRegister(){
		applicant=new Applicant();
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
		}else if(!dormitory.matches("[\u4e00-\u9fa5]{1}[0-9]{1}(-)[0-9]{3}")){
			errors.put("dormitory", html+"填写的寝室号格式不正确");
		}
		
		String studentNum=applicant.getStudentNum();
		if(studentNum==null||studentNum.trim().isEmpty()){
			errors.put("studentNum", html+"请输入学号");
		}else if(!studentNum.matches("\\d{13}")){
			errors.put("studentNum", html+"填写的学号格式不正确");
		}else if(userService.getByUsername(studentNum)!=null){
			errors.put("studentNum", html+"该学号已经被注册");
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
			if(img.length()<=200*1024){
				path=upload();
			}else{
				errors.put("img", html+"图片大小不可以超过200KB");
			}
		}
		
		if(errors.size()>0){
			request.put("errors", errors);
			request.put("applicant", applicant);
			if(img!=null){
				request.put("img", img.getPath());
			}
			loadDepartment(request);
			return "error";
		}
		
		return path;
	}
	
	/**
	 * 加载department信息
	 */
	public void loadDepartment(Map<String, Object> request){
		request.put("departments", departmentService.getAbled());
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
     * 申请者预览 自己的报名信息
     * @return
     */
	public String aptShow(){
		User user=(User) req.getSession().getAttribute("session_user");
		Applicant apt=applicantService.getById(user.getId());
		req.setAttribute("applicant", apt);
		return "aptShow";
	}
	
	/**
	 * 申请者预览 自己状态
	 * @return 状态id
	 */
	public String aptShowStatus(){
		User user=(User) req.getSession().getAttribute("session_user");
		Applicant apt=applicantService.getById(user.getId());
		req.setAttribute("statusId", apt.getAptStatus().getAptStatusId());
		req.setAttribute("emailed", apt.getAptEmailed());
		return "aptShowStatus";
	}
	
	/**
	 * 申请者修改信息的回显
	 * @return
	 */
	public String aptModify(){
		User user=(User) req.getSession().getAttribute("session_user");
		Applicant apt=applicantService.getById(user.getId());
		request.put("applicant", apt);
		loadDepartment(request);
		return "aptModify";
	}
	
	/**
	 * 申请者修改信息 保存
	 * @return
	 */
	public String aptModifySave(){
		String result=aptCheck(request);
		if(!result.equals("error")){
			if(!result.equals("none")){
				applicant.setImgSrc(result);//保存上传图片路径
			}
			applicantService.saveOrUpdate(applicant);
			request.put("applicant", applicant);
			return "aptModifySave";
		}
		return "aptModifySaveInput";
	}
	
	public void prepareAptModifySave(){
		User user=(User) req.getSession().getAttribute("session_user");
		applicant=applicantService.getById(user.getId());
	}
	
	/**
	 * 后台检查申请者 修改信息
	 * @param applicant 
	 * @return
	 */
	public String aptCheck(Map<String, Object> request){
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
		}else if(!place.matches("[\u4e00-\u9fa5]+")){
			errors.put("place", html+"填写的籍贯不是有效的中文名");
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
			loadDepartment(request);
			return "error";
		}
		
		return path;
	}
	
	
	private Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request=arg0;
	}
	
	private Applicant applicant;
	@Override
	public Applicant getModel() {
		return applicant;
	}
	@Override
	public void prepare() throws Exception {}

	private HttpServletRequest req;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.req=arg0;
	}
	
}
