<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>个人设置-修改密码</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/common.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/modifyPassword.css" />
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
					src="${pageContext.request.contextPath}/img/logo.jpg" alt="logo" title="首页" />
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
							<li><a href="${pageContext.request.contextPath }/backstagePages/applicant/modifyPassword.jsp"><i class="fa fa-cog"></i> 设置</a></li>
							<li><a href="loginDestory"><i class="fa fa-sign-out"></i> 注销</a></li>
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
        		<li><a href="aptShow">报名表预览</a></li>
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
				<li><a>个人设置</a></li>
				<li class="active">修改密码</li>
			</ol>
		</div>
		<div class="row right-bar-cont">
			<div class="form-table col-sm-12">
				<div class="infoCont">
					<form class="form-horizontal" method="post" name="changePasswordForm" action="aptChangePassword">
						<div class="form-group">
							<label for="oldPassword" class="col-sm-3 control-label">原密码：</label>
							<div class="col-sm-5 inputDiv">
								<input type="password" class="form-control" id="oldPassword" name="oldPassword">
							</div>
							<label class="col-sm-4 control-label danger-label">${errors.oldPassword }</label>
						</div>
						<div class="form-group">
							<label for="newPassword" class="col-sm-3 control-label">新密码：</label>
							<div class="col-sm-5 inputDiv">
								<input type="password" class="form-control" id="newPassword" name="newPassword">
							</div>
							<label class="col-sm-4 control-label danger-label">${errors.newPassword }</label>
						</div>
						<div class="form-group">
							<label for="confirmPassword" class="col-sm-3 control-label">确认密码：</label>
							<div class="col-sm-5 inputDiv">
								<input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
							</div>
							<label class="col-sm-4 control-label danger-label">${errors.confirmPassword }</label>
						</div>
						<div class="formBtn">
  							<button class="btn btn-success commitBtn" type="button"><i class="fa fa-check"></i> 保存</button>
  							<button class="btn btn-danger" type="button"><i class="fa fa-mail-reply"></i> 取消</button>
  						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/backstageJs/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/backstageJs/modifyPassword.js" ></script>
</body>
</html>
