<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>管理后台-成员基本信息</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/MemberInfo.css" />
	</head>
	<body>
			<div class="infoCont">
				<h3>基本信息</h3>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">所属部门：</label>
					<label class="itemRight col-sm-6">${member.merDepartment.deptName }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">职位：</label>
					<label class="itemRight col-sm-6">${member.merStatus.merStatusName }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">姓名：</label>
					<label class="itemRight col-sm-6">${member.name }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">性别：</label>
					<label class="itemRight col-sm-6">${member.gender }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">政治面貌：</label>
					<label class="itemRight col-sm-6">${member.polity }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">民族：</label>
					<label class="itemRight col-sm-6">${member.nation }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">籍贯：</label>
					<label class="itemRight col-sm-6">${member.polity }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">出生日期：</label>
					<label class="itemRight col-sm-6"><s:date name="#request.member.birth" format="yyyy-MM-dd"/></label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">学号：</label>
					<label class="itemRight col-sm-6">${member.studentNum }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">专业班级：</label>
					<label class="itemRight col-sm-6">${member.clazz }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">寝室：</label>
					<label class="itemRight col-sm-6">${member.dormitory }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">手机号：</label>
					<label class="itemRight col-sm-6">${member.phone }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">QQ：</label>
					<label class="itemRight col-sm-6">${member.qq }</label>
				</div>
				<div class="itemCont row">
					<label class="itemLeft col-sm-6">邮箱：</label>
					<label class="itemRight col-sm-6">${member.email }</label>
				</div>
			</div>
		
		
	</body>
</html>
