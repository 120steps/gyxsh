<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>报名状态</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/common.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/applyStatus.css" />
	</head>
	<body>
		<nav class="navbar navbar-default navbar-fixed-top navbar-custom">
  			<div class="container-fluid">
    		<!-- Brand and toggle get grouped for better mobile display -->
    			<div class="navbar-header">
      				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        				<span class="sr-only">Toggle navigation</span>
        				<span class="icon-bar"></span>
        				<span class="icon-bar"></span>
        				<span class="icon-bar"></span>
      				</button>
      				<a class="navbar-brand new-brand" href="#">
      					<img src="${pageContext.request.contextPath}/img/logo.jpg" alt="logo" title="首页"/>
      				</a>
      				<div class="user-menu pull-right">
      					<div class="dropdown">
  							<a class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
    							<i class="fa fa-user"></i> User
    							<span class="caret"></span>
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
    	
    	<div class="collapse navbar-collapse leftMenu col-sm-3 col-lg-2" id="bs-example-navbar-collapse-1">
    	 	<div class="leftMenuCont">
      			<ul class="nav">
      				<li class="active"><a href="aptShowStatus">报名状态</a></li>
        			<li><a href="aptShow">报名表预览</a></li>
        			<li><a href="aptModify">报名表修改</a></li>
      			</ul>
      		</div>
      		<div class="footer">
      			<p>309工作室</p>
      		</div>
      	</div>
      	
    	
		<div class="right-bar col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2">
			<div class="row path">
				<ol class="breadcrumb">
  					<li><a href="#"><i class="fa fa-home"></i></a></li>
  					<li class="active">报名状态</li>
				</ol>
			</div>
			<div class="right-content">
				<div>
					<ul class="status-content">
						<!-- 1 -->
						<s:if test="(#request.statusId==2&&#request.emailed==1)||#request.statusId>3">
							<li class="status-step pass">
						</s:if>
						<s:elseif test="#request.statusId==3&&#request.emailed==1">
							<li class="status-step fail">
						</s:elseif>
						<s:else>
							<li class="status-step current">
						</s:else>
								<div class="num">1</div>
								<div class="title">报名表筛选</div>
							</li>
							
						<!-- 2 -->	
						<s:if test="(#request.statusId==4&&#request.emailed==1)||#request.statusId>5">
							<li class="status-step pass">
						</s:if>
						<s:elseif test="#request.statusId==5&&#request.emailed==1">
							<li class="status-step fail">
						</s:elseif>
						<s:else>
							<li class="status-step">
						</s:else>
								<div class="num">2</div>
								<div class="title">第一轮面试</div>
							</li>
						
						<!-- 3 -->	
						<s:if test="(#request.statusId==6&&#request.emailed==1)||#request.statusId>7">
							<li class="status-step pass">
						</s:if>
						<s:elseif test="#request.statusId==7&&#request.emailed==1">
							<li class="status-step fail">
						</s:elseif>
						<s:else>
							<li class="status-step">
						</s:else>
								<div class="num">3</div>
								<div class="title">第二轮面试</div>
							</li>
						
						
						<!-- 4 -->	
						<s:if test="(#request.statusId==8&&#request.emailed==1)||#request.statusId>9">
							<li class="status-step pass">
						</s:if>
						<s:elseif test="#request.statusId==9&&#request.emailed==1">
							<li class="status-step fail">
						</s:elseif>
						<s:else>
							<li class="status-step">
						</s:else>
							<div class="num">4</div>
							<div class="title">第三轮面试</div>
						</li>
						
						<!-- <li class="status-step">
							<div class="num">5</div>
							<div class="title">报名表筛选</div>
						</li> -->
					</ul>
				</div>
			</div>
			
		</div>
		
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/backstageJs/common.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/backstageJs/applyStatus.js" ></script>
	</body>
</html>
