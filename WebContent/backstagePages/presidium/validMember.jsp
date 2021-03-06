<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>管理后台-主席团-现有成员</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/common.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/applicationFormManage.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/departmentMenu.css" />
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
    							<li><a href="merModifyBasicInfo"><i class="fa fa-cog"></i> 设置</a></li>
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
          				<ul class="collapse control-menu " id="applicationFormControl">
           					<li><a href="merHandleOne?deptId=0">报名表审核</a></li>
            				<li><a href="merHandleTwoOne?deptId=0">第一次面试</a></li>
            				<li><a href="merHandleFourTwo?deptId=0">第二次面试</a></li>
            				<li><a href="merHandleSixThree?deptId=0">第三次面试</a></li>
            				<li><a href="merHandleEightFour?deptId=0">已通过</a></li>
            				<li><a href="merHandleFailFour?deptId=0">未通过</a></li>
          				</ul>
        			</li>
        			<li class="active">
          				<a href="#memberControl"  data-toggle="collapse" role="button" aria-haspopup="true" aria-expanded="false">成员管理<i class="fa fa-chevron-up"></i></a>
          				<ul class="collapse control-menu in" id="memberControl">
           					<li><a href="merCreateMember">新增成员</a></li>
            				<li class="active"><a href="merShowMemberAble?deptId=0">现有成员</a></li>
            				<li><a href="merShowMemberDisable?deptId=0">已注销成员</a></li>
          				</ul>
        			</li>
        			<li>
          				<a href="#applySysControl"  data-toggle="collapse" role="button" aria-haspopup="true" aria-expanded="false">报名系统管理<i class="fa fa-chevron-down"></i></a>
          				<ul class="collapse control-menu " id="applySysControl">
           					<li><a href="merEnrollTimeShow">开启报名系统</a></li>
            				<li><a href="merDisposeTimeShow">开启处理系统</a></li>
          				</ul>
        			</li>
        			<li><a href="${pageContext.request.contextPath}/backstagePages/presidium/download.jsp">下载专区</a></li>
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
  					<li class="active">现有成员</li>
				</ol>
			</div>
			<div class="row right-bar-cont">
				<div class="form-table col-sm-12">
					<div class="department-menu">
						<div class="btn-group btn-group-justified" role="group" >
  							<s:iterator value="#request.departments">
								<div class="btn-group" role="group">
    								<a href="merShowMemberAble?deptId=${deptId }"  class="btn btn-default <s:if test="deptId==#parameters.deptId[0]">active</s:if>">${deptName }</a>
  								</div>
							</s:iterator>
						</div>
					</div>
					<div class="manage-cont" >
						<table class="table table-hover">
  							<thead>
  								<tr>
  									<th>#</th>
  									<th>账号</th>
  									<th>姓名</th>
  									<th>专业班级</th>
  									<th>所属部门</th>
  									<th>权限</th>
  									<th>状态</th>
  									<th>操作</th>
  								</tr>
  							</thead>
  							<tbody>
  								<s:iterator value="#request.pageBeanMember.member" status="m">
  									<tr>
  										<td><s:property value="%{#m.getIndex()+1}"/></td>
  										<td>${user.username }</td>
  										<td>${name }</td>
  										<td>${clazz }</td>
  										<td>${merDepartment.deptName }</td>
  										<td>${merStatus.merStatusName }</td>
  										<td>
  											<s:if test="able==1">可用</s:if> 
  											<s:else>不可用</s:else>
  										</td>
  										<td>
  											<a href="showMemberById?id=${id }" target="_blank" class="btn btn-info" title="查看基本信息"><i class="fa fa-search"></i></a>
  											<a href="#" class="btn btn-danger" title="注销成员" data-toggle="modal" data-target="#<s:property value="%{#m.getIndex()+1}"/>myModal"><i class="fa fa-trash-o"></i></a>
  											<div class="modal fade" id="<s:property value="%{#m.getIndex()+1}"/>myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  												<div class="modal-dialog" role="document">
								    				<div class="modal-content">
      													<div class="modal-header">
        													<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        													<h4 class="modal-title" id="myModalLabel">提示信息</h4>
      													</div>
      													<div class="modal-body">
        													注销该账号之后，该成员将不能再登陆系统！
      													</div>
      													<div class="modal-footer">
      														<form action="merShowMemberAbleOut">
      															<input type="hidden" name="deptId" value="<s:property value="#parameters.deptId[0]"/>">
        														<input type="hidden" name="id" value="${id }">
        														<button type="submit" class="btn btn-primary">确认</button>
      														</form>
      													</div>
    												</div>
  												</div>
											</div>
  										</td>
  									</tr>
  								</s:iterator>
  							</tbody>
						</table>
						<s:iterator value="#request.pageBeanMember">
							<div class="paging">
								<s:if test="%{cp < 2}">
									<span>首页</span>
									<span>上一页</span>
								</s:if>
								<s:else>
									<a href="merShowMemberAble?deptId=<s:property value="#parameters.deptId[0]"/>&page=1">首页</a>
									<a href="merShowMemberAble?deptId=<s:property value="#parameters.deptId[0]"/>&page=<s:property value="%{cp-1}"/>">上一页</a>
								</s:else>

								<!-- 显示页码,计算begin、end -->
								<!-- 假如不足5页 则将所有的都显示出来 -->
								<s:if test="%{tp < 5}">
									<s:set name="begin" value="1"></s:set>
									<s:set name="end" value="tp"></s:set>
								</s:if>
								<!-- 如果总页数大于5 则通过计算得到 -->
								<s:else>
									<s:set name="begin" value="cp-2"></s:set>
									<s:set name="end" value="cp+2"></s:set>
									<!-- 头溢出 -->
									<s:if test="%{#begin < 1}">
										<s:set name="begin" value="1"></s:set>
										<s:set name="end" value="5"></s:set>
									</s:if>
									<!-- 尾溢出 -->
									<s:if test="%{#end > tp}">
										<s:set name="begin" value="tp-4"></s:set>
										<s:set name="end" value="tp"></s:set>
									</s:if>
								</s:else>
								
								
								<!-- 显示页码，遍历页码 -->
								<s:iterator var="i" begin="begin" end="end">
								<s:if test="%{#i == cp}">
									<a href="" class="active">${i }</a>
								</s:if>
								<s:else>
									<a href="merShowMemberAble?deptId=<s:property value="#parameters.deptId[0]"/>&page=<s:property value="#i"/>">${i }</a>
								</s:else>
								</s:iterator>
								
								<s:if test="%{cp < tp}">
									<a href="merShowMemberAble?deptId=<s:property value="#parameters.deptId[0]"/>&page=<s:property value="%{cp+1}"/>">下一页</a>
									<a href="merShowMemberAble?deptId=<s:property value="#parameters.deptId[0]"/>&page=<s:property value="tp"/>">尾页</a>
								</s:if>
								<s:else>
 					        		<span>下一页</span>
									<span>尾页</span>
 					        	</s:else>
							</div>
						</s:iterator>
					</div>
				</div>
			</div>
		</div>
		
		
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/backstageJs/common.js" ></script>
	</body>
</html>
