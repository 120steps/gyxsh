<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>管理后台-${session_user.student.merDepartment.deptName }-新增成员</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/common.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/createMember.css" />
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
    							<li><a href="secModifyBasicInfo"><i class="fa fa-cog"></i> 设置</a></li>
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
           					<li><a href="secHandleOne">报名表审核</a></li>
            				<li><a href="secHandleTwoOne">第一次面试</a></li>
            				<li><a href="secHandleFourTwo">第二次面试</a></li>
            				<li><a href="secHandleSixThree">第三次面试</a></li>
            				<li><a href="secHandleEightFour">已通过</a></li>
            				<li><a href="secHandleFailFour">未通过</a></li>
          				</ul>
        			</li>
        			<li class="active">
          				<a href="#memberControl"  data-toggle="collapse" role="button" aria-haspopup="true" aria-expanded="false">成员管理<i class="fa fa-chevron-up"></i></a>
          				<ul class="collapse control-menu in" id="memberControl">
           					<li class="active"><a href="secCreateMember">新增成员</a></li>
            				<li><a href="secShowMemberAble">现有成员</a></li>
            				<li><a href="secShowMemberDisable">已注销成员</a></li>
          				</ul>
        			</li>
        			<li>
          				<a href="#applySysControl"  data-toggle="collapse" role="button" aria-haspopup="true" aria-expanded="false">报名系统管理<i class="fa fa-chevron-down"></i></a>
          				<ul class="collapse control-menu" id="applySysControl">
           					<li><a href="secEnrollTimeShow">开启报名系统</a></li>
            				<li><a href="secDisposeTimeShow">开启处理系统</a></li>
          				</ul>
        			</li>
        			<li><a href="${pageContext.request.contextPath}/backstagePages/secretariat/download.jsp">下载专区</a></li>
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
  					<li><a >成员管理</a></li>
  					<li class="active">新增成员</li>
				</ol>
			</div>
			<div class="row right-bar-cont">
				<div class="form-table col-sm-12">
					<div class="createCont">
						<form class="form-horizontal"  action="secCreateMemberSave" method="post" name="createForm">
							<div class="form-group">
    							<label for="name" class="col-sm-3 control-label">姓名：</label>
    							<div class="col-sm-6">
      								<input type="text" class="form-control" id="name" name="name" placeholder="如：张三"
      								 value="${member.name }">
    							</div>
    							<label class="col-sm-3 control-label danger-label">${errors.name }</label>
  							</div>
  							<div class="form-group">
    							<label for="majorAndClass" class="col-sm-3 control-label">专业班级：</label>
    							<div class="col-sm-6">
      								<input type="text" class="form-control" id="majorAndClass" name="clazz" 
      								placeholder="如：信管1301班" value="${member.clazz }">
    							</div>
    							<label class="col-sm-3 control-label danger-label">${errors.clazz }</label>
  							</div>
  							<div class="form-group">
    							<label for="userAccount" class="col-sm-3 control-label">学号：</label>
    							<div class="col-sm-6">
      								<input type="text" class="form-control" id="userAccount" name="studentNum" 
      								placeholder="如：0121303490113" vlaue="${member.studentNum }">
    							</div>
    							<label class="col-sm-3 control-label danger-label">${errors.studentNum }</label>
  							</div>
  							<div class="form-group">
    							<label for="jurisdiction" class="col-sm-3 control-label">职位：</label>
    							<div class="col-sm-6">
      								<select id="jurisdiction" name="merStatus.merStatusId" class="selectpicker form-control show-tick" data-size="5">
      									<option <s:if test="#request.member.merStatus.merStatusId==0">selected</s:if> value="0">请选择职位...</option>
      									<option <s:if test="#request.member.merStatus.merStatusId==4">selected</s:if> value="4">副部长</option>
      									<option <s:if test="#request.member.merStatus.merStatusId==5">selected</s:if> value="5">干事</option>
      								</select>
    							</div>
    							<label class="col-sm-3 control-label danger-label"></label>
  							</div>
  							<div class="formBtn">
  								<button class="btn btn-success commitBtn" type="button"><i class="fa fa-check"></i> 提交</button>
  								<button class="btn btn-warning" type="reset"><i class="fa fa-close"></i> 重新填写</button>
  							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
      				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<h4 class="modal-title" id="myModalLabel">提示信息</h4>
      				</div>
      				<div class="modal-body">
        				新创建账号的账号和密码默认为学号！
      				</div>
      				<div class="modal-footer">
        				<button type="button" class="btn btn-primary checkBtn" data-dismiss="modal">知道了</button>
      				</div>
    			</div>
  			</div>
		</div>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-select.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/backstageJs/common.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/backstageJs/createMember.js" ></script>
	</body>
</html>
