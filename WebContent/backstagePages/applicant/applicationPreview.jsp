<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>报名表预览</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/backstageCss/common.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/backstageCss/applicationPreview.css" />
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top navbar-custom">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand new-brand" href="#"> <img
					src="${pageContext.request.contextPath}/img/logo.jpg" alt="logo"
					title="首页" />
				</a>
				<div class="user-menu pull-right">
					<div class="dropdown">
						<a class="dropdown-toggle" id="dropdownMenu1"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							<i class="fa fa-user"></i> User <span class="caret"></span>
						</a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li class="welcome">欢迎你！${session_user.student.name }</li>
							<li role="separator" class="divider"></li>
							<li><a
								href="${pageContext.request.contextPath }/backstagePages/applicant/modifyPassword.jsp"><i
									class="fa fa-cog"></i> 设置</a></li>
							<li><a href="loginDestory"><i class="fa fa-sign-out"></i>
									注销</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>

	<div class="collapse navbar-collapse leftMenu col-sm-3 col-lg-2"
		id="bs-example-navbar-collapse-1">
		<div class="leftMenuCont">
			<ul class="nav">
				<li><a href="aptShowStatus">报名状态</a></li>
				<li class="active"><a href="aptShow">报名表预览</a></li>
				<li><a href="aptModify">报名表修改</a></li>
			</ul>
		</div>
		<div class="footer">
			<p>309工作室</p>
		</div>
	</div>


	<div
		class="right-bar col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
		<div class="row path">
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-home"></i></a></li>
				<li class="active">报名表预览</li>
			</ol>
		</div>
		<div class="right-content">
			<div class="part row">
				<h3>基本信息</h3>
				<div class="col-sm-9">
					<div class="col-sm-12 form-group">
						<label class="col-sm-4 item-tag">姓名：</label> <label
							class="col-sm-8 item-value">${applicant.name }</label>
					</div>
					<div class="col-sm-12 form-group">
						<label class="col-sm-4 item-tag">性别：</label> <label
							class="col-sm-8 item-value">${applicant.gender }</label>
					</div>
					<div class="col-sm-12 form-group">
						<label class="col-sm-4 item-tag">籍贯：</label> <label
							class="col-sm-8 item-value">${applicant.place }</label>
					</div>
					<div class="col-sm-12 form-group">
						<label class="col-sm-4 item-tag">民族：</label> <label
							class="col-sm-8 item-value">${applicant.nation }</label>
					</div>
					<div class="col-sm-12 form-group">
						<label class="col-sm-4 item-tag">政治面貌：</label> <label
							class="col-sm-8 item-value">${applicant.polity }</label>
					</div>
					<div class="col-sm-12 form-group">
						<label class="col-sm-4 item-tag">出生日期：</label> <label
							class="col-sm-8 item-value"><s:date
								name="#request.applicant.birth" format="yyyy-MM-dd" /> </label>
					</div>
					<div class="col-sm-12 form-group">
						<label class="col-sm-4 item-tag">专业班级：</label> <label
							class="col-sm-8 item-value">${applicant.clazz }</label>
					</div>
					<div class="col-sm-12 form-group">
						<label class="col-sm-4 item-tag">学号：</label> <label
							class="col-sm-8 item-value">${applicant.studentNum }</label>
					</div>
					<div class="col-sm-12 form-group">
						<label class="col-sm-4 item-tag">宿舍：</label> <label
							class="col-sm-8 item-value">${applicant.dormitory }</label>
					</div>
				</div>
				<div class="col-sm-3">
					<img src="${pageContext.request.contextPath}/${applicant.imgSrc }"
						alt="照片">
				</div>
			</div>
			<div class="division"></div>
			<div class="part row">
				<h3>联系方式</h3>
				<div class="col-sm-12 form-group">
					<label class="col-sm-3 item-tag">手机号码：</label> <label
						class="col-sm-8 item-value">${applicant.phone }</label>
				</div>
				<div class="col-sm-12 form-group">
					<label class="col-sm-3 item-tag">QQ：</label> <label
						class="col-sm-8 item-value">${applicant.qq }</label>
				</div>
				<div class="col-sm-12 form-group">
					<label class="col-sm-3 item-tag">邮箱：</label> <label
						class="col-sm-8 item-value">${applicant.email }</label>
				</div>
			</div>
			<div class="division"></div>
			<div class="part row">
				<h3>个人简历</h3>
				<div class="col-sm-12 form-group">
					<label class="col-sm-3 item-tag">第一志愿：</label> <label
						class="col-sm-8 item-value">${applicant.aptFirstTag.deptName }</label>
				</div>
				<div class="col-sm-12 form-group">
					<label class="col-sm-3 item-tag">第二志愿：</label> <label
						class="col-sm-8 item-value">${applicant.aptSecondTag.deptName }</label>
				</div>
				<div class="col-sm-12 form-group">
					<label class="col-sm-3 item-tag">已报名或参加其他社团：</label> <label
						class="col-sm-8 item-value">${applicant.aptOtherOrg }</label>
				</div>
				<div class="col-sm-12 form-group">
					<label class="col-sm-3 item-tag">特长：</label> <label
						class="col-sm-8 item-value">${applicant.aptSpeciality }</label>
				</div>
				<div class="col-sm-12 form-group">
					<label class="col-sm-3 item-tag">兴趣爱好：</label> <label
						class="col-sm-8 item-value">${applicant.aptInterest }</label>
				</div>
				<div class="col-sm-12 form-group">
					<label class="col-sm-3 item-tag">工作经历：</label> <label
						class="col-sm-8 item-value">${applicant.aptExperience }</label>
				</div>
				<div class="col-sm-12 form-group">
					<label class="col-sm-3 item-tag">对学生会的认识：</label> <label
						class="col-sm-8 item-value">${applicant.aptKnow }</label>
				</div>
				<div class="col-sm-12 form-group">
					<label class="col-sm-3 item-tag">工作展望：</label> <label
						class="col-sm-8 item-value">${applicant.aptExpectation }</label>
				</div>
				<div class="col-sm-12 form-group downloadBtn">
					<a href="createWordSingle" class="btn btn-info ">下 载</a>
				</div>
			</div>
		</div>

	</div>


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/backstageJs/common.js"></script>
</body>
</html>
