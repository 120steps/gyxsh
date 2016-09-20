package com.gyxsh.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gyxsh.entities.Applicant;
import com.gyxsh.entities.Member;
import com.gyxsh.entities.User;
import com.gyxsh.service.ApplicantService;
import com.gyxsh.service.MemberService;
import com.gyxsh.service.UserService;
import com.gyxsh.utils.CompressedFileUtil;
import com.gyxsh.utils.WordUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import sun.misc.BASE64Encoder;

@Scope("prototype")
@Component
public class WordAction extends ActionSupport implements ServletRequestAware{
	@Autowired
	private ApplicantService applicantService;
	@Autowired
	private MemberService memberService;

	private String filePath;//word文件路径
	private String zipPath;//zip路径
	private String fileName;//文件名称
	private String fileOnlyName;//文件唯一名称
	private String zipName;//zip名
	private String zipOnlyName;//zip唯一名称
	
	public String checkLogin(){//检查是否登录
		User user=(User) request.getSession().getAttribute("session_user");
		if(user==null){
			return "login";
		}
		return "ok";
	}
	
	/**
	 * 个人 下载报名表 word版
	 * @return
	 */
	public String createWordSingle(){
		//文件路径
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		filePath=ServletActionContext.getServletContext().getRealPath("/")+"uploadSingleWord";
		
		//删除服务器文件
		deleteFile(filePath);
		
		//填充数据
		User user=(User) request.getSession().getAttribute("session_user");
		fullData(applicantService.getById(user.getId()));
		
		return "createWordSingle";
	}
	
	/**
	 * 部长、主席团、管理员 下载所有申请者的报名报 word版
	 * @return
	 */
	public String createWordAll(){
		if(checkLogin().equals("login")){
			return Action.LOGIN;
		}
		//文件路径
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		filePath=ServletActionContext.getServletContext().getRealPath("/")+"uploadAllWord"
		+File.separator+df.format(new Date());
		
		//zip路径
		zipPath=ServletActionContext.getServletContext().getRealPath("/")+"uploadAllWord";
		
		//删除服务器文件
		deleteFile(zipPath);
		
		//zip唯一名
		zipOnlyName=df.format(new Date())+new Date().getTime()+".zip";
		
		//循环遍历所有applicant
		List<Applicant> list=applicantService.getAll();
		for (Applicant applicant : list) {
			fullData(applicant);
		}
		
		return "createWordAll";
	}
	
	public void fullData(Applicant apt){
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		//组装数据
		Map<String, Object> dataMap=new HashMap<String, Object>();
		dataMap.put("applicant", apt);
		dataMap.put("birth", df.format(apt.getBirth()));
		dataMap.put("imgSrc", getImageStr(
				ServletActionContext.getServletContext().getRealPath("/")+apt.getImgSrc()));
		
		//文件名称，唯一字符串
		StringBuffer sb=new StringBuffer();
		sb.append(new Date().getTime());
		
		//文件唯一名称
		fileOnlyName=apt.getName()+"_"+apt.getStudentNum()+"_"+sb+".doc";
				
		//文件名称
		fileName=apt.getName()+"_"+apt.getStudentNum()+".doc";
				
		//生成word
		WordUtil.createWord(dataMap, "applicationForm.ftl", filePath, fileOnlyName);
	}
	
	//下载生成的zip，入口，用来跳转至struts XML配置
	public String downloadZip(){
		try {
			//解决中文乱码问题
			filePath= URLDecoder.decode(filePath, "utf-8");
			zipPath= URLDecoder.decode(zipPath, "utf-8");
			zipOnlyName=URLDecoder.decode(zipOnlyName, "utf-8");
			CompressedFileUtil.compressedFile(filePath, zipPath,zipOnlyName);
			//如何文件不存在，则会跳入异常，进行异常处理
			new FileInputStream(zipPath+File.separator+zipOnlyName);
		} catch (Exception e) {
			e.printStackTrace();
			return "input";
		}
		return "downloadZip";
	}
	
	//下载生成的zip文档
    //该方法是struts.xml文件中的： <param name="inputName">zipFile</param> 中自动对应的get方法，该方法自动调用
	public InputStream getZipFile(){
		try {
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss");
			zipName=df.format(new Date())+".zip";
			
			//返回最終的流
			return new FileInputStream(zipPath+File.separator+zipOnlyName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	//下载生成的word/excel文档，入口，用来跳转至struts XML配置
	public String downloadWord(){
		//先判断文件是否生成
		try{
			//解决中文乱码
			filePath= URLDecoder.decode(filePath, "utf-8");
			fileOnlyName=URLDecoder.decode(fileOnlyName, "utf-8");
            fileName=URLDecoder.decode(fileName, "utf-8");
			//如何文件不存在，则会跳入异常，进行异常处理
			new FileInputStream(filePath+File.separator+fileOnlyName);
		}catch(Exception e){
			e.printStackTrace();
			return "input";
		}
		return "downloadWord";
	}
	
	//下载生成的word/excel文档
    //该方法是struts.xml文件中的： <param name="inputName">wordFile</param> 中自动对应的get方法，该方法自动调用
	public InputStream getWordFile(){
		try {
			//处理火狐浏览器的乱码问题
			String agent = (String)request.getHeader("USER-AGENT");  
            if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)
            {
            	BASE64Encoder base64Encoder=new BASE64Encoder();
            	fileName="=?utf-8?B?"
						+ base64Encoder.encode(fileName.getBytes("utf-8"))
						+ "?=";
            }
            else
            {
            	fileName=URLEncoder.encode(fileName,"utf-8");
            }
			//返回最終的流
			return new FileInputStream(filePath+File.separator+fileOnlyName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	private String getImageStr(String imgFile) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			return "";
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
	
	/*
	 * 删除服务器文件
	 */
	public void deleteFile(String zipPath){
		File file=new File(zipPath);
		WordUtil.deleteDir(file);
	}
	
	/**
	 * 下载报名信息表 excel版
	 * @return
	 */
	public String createExcelSingle(){
		if(checkLogin().equals("login")){
			return Action.LOGIN;
		}
		//文件路径
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		filePath=ServletActionContext.getServletContext().getRealPath("/")+"uploadExcel";
		
		//删除服务器文件
		deleteFile(filePath);
		
		//循环遍历所有applicant
		List<Applicant> list=applicantService.getAll();
		for (Applicant applicant : list) {
			applicant.setYyyyMMdd(df.format(applicant.getBirth()));
		}
		fullDataExcel(list);
		
		return "createExcelSingle";
	}
	
	public void fullDataExcel(List<Applicant> apts){
		//组装数据
		Map<String, Object> dataMap=new HashMap<String, Object>();
		dataMap.put("apts", apts);
		
		//文件名称，唯一字符串
		StringBuffer sb=new StringBuffer();
		sb.append(new Date().getTime());
		
		//文件唯一名称
		fileOnlyName=apts.get(0).getName()+"_"+apts.get(0).getStudentNum()+"_"+sb+".xls";
				
		//文件名称
		fileName=apts.get(0).getName()+"_"+apts.get(0).getStudentNum()+".xls";
				
		//生成word
		WordUtil.createWord(dataMap, "message.ftl", filePath, fileOnlyName);
	}
	
	
	/**
	 * 下载成员信息表 excel版
	 * @return
	 */
	public String createMemberExcelSingle(){
		if(checkLogin().equals("login")){
			return Action.LOGIN;
		}
		//文件路径
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		filePath=ServletActionContext.getServletContext().getRealPath("/")+"uploadMemberExcel";
		
		//删除服务器文件
		deleteFile(filePath);
		
		//循环遍历所有member
		List<Member> list=memberService.getAll();
		fullDataMemberExcel(list);
		
		return "createMemberExcelSingle";
	}
	
	public void fullDataMemberExcel(List<Member> members){
		//组装数据
		Map<String, Object> dataMap=new HashMap<String, Object>();
		dataMap.put("members", members);
		
		//文件名称，唯一字符串
		StringBuffer sb=new StringBuffer();
		sb.append(new Date().getTime());
		
		//文件唯一名称
		fileOnlyName=members.get(0).getName()+"_"+members.get(0).getStudentNum()+"_"+sb+".xls";
				
		//文件名称
		fileName=members.get(0).getName()+"_"+members.get(0).getStudentNum()+".xls";
				
		//生成word
		WordUtil.createWord(dataMap, "message_member.ftl", filePath, fileOnlyName);
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileOnlyName() {
		return fileOnlyName;
	}
	public void setFileOnlyName(String fileOnlyName) {
		this.fileOnlyName = fileOnlyName;
	}
	public String getZipPath() {
		return zipPath;
	}
	public void setZipPath(String zipPath) {
		this.zipPath = zipPath;
	}
	public String getZipOnlyName() {
		return zipOnlyName;
	}
	public void setZipOnlyName(String zipOnlyName) {
		this.zipOnlyName = zipOnlyName;
	}
	public String getZipName() {
		return zipName;
	}
	public void setZipName(String zipName) {
		this.zipName = zipName;
	}


	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}

}
