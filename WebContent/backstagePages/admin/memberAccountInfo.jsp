<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>管理后台-成员账户信息</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/memberBasicInfo.css" />
	</head>
	<body>
		<div class="content container">
			<div class="head-menu clearfix">
				<ul class="clearfix">
					<li><a href="adminShowMemberById?id=<s:property value="#parameters.id[0]"/>">基本信息</a></li>
					<li class="active"><a href="">账户信息</a></li>
				</ul>
			</div>
			<div class="center">
				<form class="form-horizontal" action="adminShowMemberModifyAccount" method="post" name="AccountForm">
					<input type="hidden" name="id" value="${user.id }">
						<div class="form-group">
							<label for="account" class="col-sm-2 control-label">账号：</label>
							<div class="col-sm-6 inputDiv">
								<input type="text" class="form-control" id="account" name="username"
									placeholder="如：0121303490113" value="${user.username }">
							</div>
							<label class="col-sm-4 control-label danger-label">${errors.username }</label>
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">密码：</label>
							<div class="col-sm-6 inputDiv">
								<input type="password" class="form-control" id="password" name="password"
									 value="">
							</div>
							<label class="col-sm-4 control-label danger-label">${errors.password }</label>
						</div>
						<div class="formBtn">
  							<button class="btn btn-success commitBtn" type="button"><i class="fa fa-check"></i> 保存</button>
  							<button class="btn btn-danger" type="button"><i class="fa fa-mail-reply"></i> 取消</button>
  						</div>
					</form>
			</div>
		</div>
		
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/backstageJs/memberAccountInfo.js" ></script>
	</body>
</html>
