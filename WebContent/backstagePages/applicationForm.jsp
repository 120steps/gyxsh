<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理后台-报名表</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/backstageCss/applicationForm.css" />
</head>
<body>
	<div class="formCont container">
		<div class="part-one row">
			<h3>基本信息</h3>
			<div class="col-sm-3 col-sm-push-9">
				<img src="${pageContext.request.contextPath}/${applicant.imgSrc }" alt="照片">
			</div>
			<div class="col-sm-9 col-sm-pull-3">
				<div class="col-sm-6 form-group">
					<label class="col-sm-4 item-tag">姓名：</label> <label
						class="col-sm-8 item-value">${applicant.name }</label>
				</div>
				<div class="col-sm-6 form-group">
					<label class="col-sm-4 item-tag">性别：</label> <label
						class="col-sm-8 item-value">${applicant.gender }</label>
				</div>
				<div class="col-sm-6 form-group">
					<label class="col-sm-4 item-tag">籍贯：</label> <label
						class="col-sm-8 item-value">${applicant.place }</label>
				</div>
				<div class="col-sm-6 form-group">
					<label class="col-sm-4 item-tag">政治面貌：</label> <label
						class="col-sm-8 item-value">${applicant.polity }</label>
				</div>
				<div class="col-sm-6 form-group">
					<label class="col-sm-4 item-tag">出生日期：</label> <label
						class="col-sm-8 item-value"><s:date format="yyyy-MM-dd"
							name="#request.applicant.birth" /></label>
				</div>
				<div class="col-sm-6 form-group">
					<label class="col-sm-4 item-tag">专业班级：</label> <label
						class="col-sm-8 item-value">${applicant.clazz }</label>
				</div>
				<div class="col-sm-6 form-group">
					<label class="col-sm-4 item-tag">学号：</label> <label
						class="col-sm-8 item-value">${applicant.studentNum }</label>
				</div>
			</div>
		</div>
		<div class="division"></div>
		<div class="part-two row">
			<h3>联系方式</h3>
			<div class="col-sm-12 form-group">
				<label class="col-sm-2 item-tag">手机号码：</label> <label
					class="col-sm-8 item-value">${applicant.phone }</label>
			</div>
			<div class="col-sm-12 form-group">
				<label class="col-sm-2 item-tag">QQ：</label> <label
					class="col-sm-8 item-value">${applicant.qq }</label>
			</div>
			<div class="col-sm-12 form-group">
				<label class="col-sm-2 item-tag">邮箱：</label> <label
					class="col-sm-8 item-value">${applicant.email }</label>
			</div>
		</div>
		<div class="division"></div>
		<div class="part-three row">
			<h3>个人简历</h3>
			<div class="col-sm-12 form-group">
				<label class="col-sm-2 item-tag">第一志愿：</label> <label
					class="col-sm-8 item-value">${applicant.aptFirstTag.deptName }</label>
			</div>
			<div class="col-sm-12 form-group">
				<label class="col-sm-2 item-tag">第二志愿：</label> <label
					class="col-sm-8 item-value">${applicant.aptSecondTag.deptName }</label>
			</div>
			<div class="col-sm-12 form-group">
				<label class="col-sm-2 item-tag">已报名或参加其他社团：</label> <label
					class="col-sm-8 item-value">${applicant.aptOtherOrg }</label>
			</div>
			<div class="col-sm-12 form-group">
				<label class="col-sm-2 item-tag">特长：</label> <label
					class="col-sm-8 item-value">${applicant.aptSpeciality }</label>
			</div>
			<div class="col-sm-12 form-group">
				<label class="col-sm-2 item-tag">兴趣爱好：</label> <label
					class="col-sm-8 item-value">${applicant.aptInterest }</label>
			</div>
			<div class="col-sm-12 form-group">
				<label class="col-sm-2 item-tag">工作经历：</label> <label
					class="col-sm-8 item-value">${applicant.aptExperience }</label>
			</div>
			<div class="col-sm-12 form-group">
				<label class="col-sm-2 item-tag">对学生会的认识：</label> <label
					class="col-sm-8 item-value">${applicant.aptKnow }</label>
			</div>
			<div class="col-sm-12 form-group">
				<label class="col-sm-2 item-tag">工作展望：</label> <label
					class="col-sm-8 item-value">${applicant.aptExpectation }</label>
			</div>
		</div>
	</div>


</body>
</html>
