<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>管理后台-系统管理员-下载专区</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/common.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/applicationFormManage.css" />
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
  								<li class="welcome">欢迎你！${session_user.username }</li>
  								<li role="separator" class="divider"></li>
    							<li><a href="${pageContext.request.contextPath }/backstagePages/admin/modifyPassword.jsp"><i class="fa fa-cog"></i> 设置</a></li>
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
      				<li>
          				<a href="#applicationFormControl"  data-toggle="collapse" role="button" aria-haspopup="true" aria-expanded="false">报名表管理<i class="fa fa-chevron-down"></i></a>
          				<ul class="collapse control-menu" id="applicationFormControl">
           					<li><a href="adminHandleOne?deptId=0">报名表审核</a></li>
            				<li><a href="adminHandleTwoOne?deptId=0">第一次面试</a></li>
            				<li><a href="adminHandleFourTwo?deptId=0">第二次面试</a></li>
            				<li><a href="adminHandleSixThree?deptId=0">第三次面试</a></li>
            				<li><a href="adminHandleEightFour?deptId=0">已通过</a></li>
            				<li><a href="adminHandleFailFour?deptId=0">未通过</a></li>
          				</ul>
        			</li>
        			<li>
          				<a href="#memberControl"  data-toggle="collapse" role="button" aria-haspopup="true" aria-expanded="false">成员管理<i class="fa fa-chevron-down"></i></a>
          				<ul class="collapse control-menu " id="memberControl">
           					<li><a href="adminCreateMember">新增成员</a></li>
            				<li><a href="adminShowMemberAble?deptId=0">现有成员</a></li>
            				<li><a href="adminShowMemberDisable?deptId=0">已注销成员</a></li>
          				</ul>
        			</li>
        			<li>
          				<a href="#applySysControl"  data-toggle="collapse" role="button" aria-haspopup="true" aria-expanded="false">报名系统管理<i class="fa fa-chevron-down"></i></a>
          				<ul class="collapse control-menu" id="applySysControl">
           					<li><a href="adminEnrollTimeShow">开启报名系统</a></li>
            				<li><a href="adminDisposeTimeShow">开启处理系统</a></li>
          				</ul>
        			</li>
        			<li class="active"><a href="${pageContext.request.contextPath}/backstagePages/admin/download.jsp">下载专区</a></li>
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
  					<li class="active">下载专区</li>
				</ol>
			</div>
			<div class="row right-bar-cont">
				<div class="form-table col-sm-12">
					<div class="manage-cont" >
						<table class="table table-hover">
  							<thead>
  								<tr>
  									<th>#</th>
  									<th>标题</th>
  									<th>操作</th>
  								</tr>
  							</thead>
  							<tbody>
  								<tr>
  									<td>1</td>
  									<td>所有报名表(Word版)</td>
  									<td>
  										<a href="createWordAll" class="btn btn-info" title="下载"><i class="fa fa-download"></i></a>
  									</td>
  								</tr>
  								<tr>
  									<td>2</td>
  									<td>所有报名信息(Excel版)</td>
  									<td>
  										<a href="createExcelSingle" class="btn btn-info" title="下载"><i class="fa fa-download"></i></a>
  									</td>
  								</tr>
  								<tr>
  									<td>3</td>
  									<td>所有成员信息(Excel版)</td>
  									<td>
  										<a href="createMemberExcelSingle" class="btn btn-info" title="下载"><i class="fa fa-download"></i></a>
  									</td>
  								</tr>
  							</tbody>
						</table>
					</div>
				</div>
			</div>
			
		</div>
		
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/backstageJs/common.js" ></script>
	</body>
</html>
