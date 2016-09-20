<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>登陆</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
	</head>
	<body>
		<div class="content col-sm-4 col-sm-offset-4">
			<div class="header clearfix">
				<h3 class="pull-left">登 陆</h3>
			</div>
			<div class="center">
				<form class="form-horizontal" action="login" method="post" name="loginForm">
  					<div class="form-group form-item">
    					<div class="col-sm-12">
    						<i class="fa fa-user fa-lg text-in"></i>
      						<input type="email" class="form-control" id="userName" name="username" placeholder="账号" value="${username }">
    					</div>
    					<label class="col-sm-12 control-label danger-label">${errors.username }</label>
  					</div>
  					<div class="form-group form-item">
    					<div class="col-sm-12">
    						<i class="fa fa-lock fa-lg text-in"></i>
      						<input type="password" class="form-control" id="password" name="password" placeholder="密码">
    					</div>
    					<label class="col-sm-12 control-label danger-label">${error }${errors.password }</label>
  					</div>
  					<div class="form-group form-item">
    					<div class="col-sm-12">
    						<i class="fa fa-edit fa-lg text-in"></i>
      						<input type="text" class="form-control verificationCode" id="verificationCode" name="verifyCode" placeholder="验证码" value="${verifyCode }">
      						<a href="javascript:_change()"><img id="vCode" src="<s:url value="/VerifyCodeServlet.servlet"></s:url>"alt="验证码" title="点击切换" /></a>
    					</div>
    					<label class="col-sm-12 control-label danger-label">${errors.verifyCode }</label>
  					</div>
  					<div class="form-group">
  						<div class="col-sm-12">
  							<button type="reset" class="btn btn-warning form-control">重 置</button>
    					</div>
  					</div>
  					<div class="form-group">
  						<div class="col-sm-12">
  							<button type="button" class="btn btn-success form-control commitBtn">提 交</button>
    					</div>
  					</div>
  					<div class="form-group">
  						<div class="col-sm-12">
  							<a href="${pageContext.request.contextPath}/forgotPassword.jsp" class="pull-right"><i class="fa fa-question-circle"></i> 忘记密码</a>
  						</div>
  					</div>
				</form>
			</div>
		</div>
		
		<div class="modal fade" id="success">
  			<div class="modal-dialog">
    			<div class="modal-content">
      				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<h4 class="modal-title">提交成功！</h4>
      				</div>
      				<div class="modal-body">
        				<p>你可以登入系统查看自己的录取状态，初始账号和密码都是你在报名表中填写的学号</p>
      				</div>
      				<div class="modal-footer">
        			<button type="button" class="btn btn-default" data-dismiss="modal">确 定</button>
      				</div>
    			</div> <!--  /.modal-content -->
  			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js" ></script>
		
		<script type="text/javascript">
		function _change(){
			var imgEl=document.getElementById("vCode");
			imgEl.src="<s:url value='/VerifyCodeServlet.servlet'></s:url>?xxx="+new Date().getTime();
		}
	</script>
	</body>
</html>
