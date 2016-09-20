<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>忘记密码</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<link rel="stylesheet" href="css/login.css" />
	</head>
	<body>
		<div class="content col-sm-offset-4 col-sm-4">
			<div class="header clearfix">
				<h3 class="pull-left">忘记密码</h3>
			</div>
			<div class="center">
				<form class="form-horizontal" action="sendICode" name="forgetForm">
					<div class="form-group form-item">
    					<div class="col-sm-12">
    						<i class="fa fa-user fa-lg text-in"></i>
      						<input type="text" class="form-control" id="userName" name="username" placeholder="账号"
      						 value="${username }">
    					</div>
    					<label class="col-sm-12 control-label danger-label">${errors.username }</label>
  					</div>
  					<div class="form-group form-item">
    					<div class="col-sm-12">
    						<i class="fa fa-envelope-o fa-lg text-in"></i>
      						<input type="email" class="form-control" id="email" name="email" placeholder="邮箱"
      						value="${email }">
    					</div>
    					<label class="col-sm-12 control-label danger-label">${errors.email }</label>
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
		<script type="text/javascript" src="js/forgotPassword.js" ></script>
	</body>
</html>
