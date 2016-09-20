<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>重设密码</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<link rel="stylesheet" href="css/login.css" />
	</head>
	<body>
		<div class="content col-sm-offset-4 col-sm-4">
			<div class="header clearfix">
				<h3 class="pull-left">重设密码</h3>
			</div>
			<div class="center">
				<form class="form-horizontal" action="checkICode" name="resetForm" method="post">
					<input type="hidden" name="username" value='<s:property value="#parameters.username[0]"/>'>
					<div class="form-group form-item">
    					<div class="col-sm-12">
    						<i class="fa fa-edit fa-lg text-in"></i>
      						<input type="text" class="form-control" id="verificationCode" name="iCode" placeholder="邮箱收到的验证码"
      						 value="${iCode }">
    					</div>
    					<label class="col-sm-12 control-label danger-label">${errors.iCode }</label>
  					</div>
  					<div class="form-group form-item">
    					<div class="col-sm-12">
    						<i class="fa fa-lock fa-lg text-in"></i>
      						<input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="新密码">
    					</div>
    					<label class="col-sm-12 control-label danger-label">${errors.newPassword }</label>
  					</div>
  					<div class="form-group form-item">
    					<div class="col-sm-12">
    						<i class="fa fa-lock fa-lg text-in"></i>
      						<input type="password" class="form-control" id="affirmPassword" name="confirmPassword" placeholder="确认密码">
    					</div>
    					<label class="col-sm-12 control-label danger-label">${errors.confirmPassword }</label>
  					</div>
  					<div class="form-group">
  						<div class="col-sm-12">
  							<button type="button" class="btn btn-success form-control commitBtn">提 交</button>
    					</div>
  					</div>
				</form>
			</div>
		</div>
		
		<script type="text/javascript" src="js/jquery-3.0.0.min.js" ></script>
		<script type="text/javascript" src="js/bootstrap.min.js" ></script>
		<script type="text/javascript" src="js/resetPassword.js" ></script>
	</body>
</html>
