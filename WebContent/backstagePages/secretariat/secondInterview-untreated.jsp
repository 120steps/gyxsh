<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>管理后台-${session_user.student.merDepartment.deptName }-第二次面试-未处理</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/common.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/applicationFormManage.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/backstageCss/interviewManage.css" />
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
      				<li class="active">
          				<a href="#applicationFormControl"  data-toggle="collapse" role="button" aria-haspopup="true" aria-expanded="false">报名表管理<i class="fa fa-chevron-up"></i></a>
          				<ul class="collapse control-menu in" id="applicationFormControl">
           					<li><a href="secHandleOne">报名表审核</a></li>
            				<li><a href="secHandleTwoOne">第一次面试</a></li>
            				<li class="active"><a href="secHandleFourTwo">第二次面试</a></li>
            				<li><a href="secHandleSixThree">第三次面试</a></li>
            				<li><a href="secHandleEightFour">已通过</a></li>
            				<li><a href="secHandleFailFour">未通过</a></li>
          				</ul>
        			</li>
        			<li>
          				<a href="#memberControl"  data-toggle="collapse" role="button" aria-haspopup="true" aria-expanded="false">成员管理<i class="fa fa-chevron-down"></i></a>
          				<ul class="collapse control-menu " id="memberControl">
           					<li><a href="secCreateMember">新增成员</a></li>
            				<li><a href="secShowMemberAble">现有成员</a></li>
            				<li><a href="secShowMemberDisable">已注销成员</a></li>
          				</ul>
        			</li>
        			<s:if test="#session.session_user.student.merDepartment.deptId==2">
        			<li>
          				<a href="#applySysControl"  data-toggle="collapse" role="button" aria-haspopup="true" aria-expanded="false">报名系统管理<i class="fa fa-chevron-down"></i></a>
          				<ul class="collapse control-menu" id="applySysControl">
           					<li><a href="secEnrollTimeShow">开启报名系统</a></li>
            				<li><a href="secDisposeTimeShow">开启处理系统</a></li>
          				</ul>
        			</li></s:if>
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
  					<li><a >报名表管理</a></li>
  					<li class="active">第二次面试</li>
				</ol>
			</div>
			<div class="row right-bar-cont">
				<div class="form-table col-sm-12">
					<div class="manage-menu clearfix">
						<ul class="clearfix">
							<li class="active"><a href="secHandleFourTwo">未处理</a></li>
							<li><a href="secHandleSixTwo">已通过</a></li>
							<li><a href="secHandleSevenTwo">未通过</a></li>
							<li><a href="secHandleFourTwoAdjusted">已调剂</a></li>
						</ul>
					</div>
					<div class="searchCont clearfix">
						<form action="secHandleFourTwo">
							<div class="search-form">
								<input type="text" placeholder="请输入想搜索的申请者姓名..." name="name"/>
								<button type="submit">搜 索</button>
							</div>
						</form>
					</div>
					<s:if test="#request.count>0">
					<div class="alert alert-danger alert-dismissible" role="alert">
  						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  						你有 <strong>${count }</strong> 份报名表没有处理，请尽快处理！
					</div>
					</s:if>
					<div class="manage-cont ">
						<table class="table table-hover">
  							<thead>
  								<tr>
  									<th>#</th>
  									<th>姓名</th>
  									<th>专业班级</th>
  									<th>第一志愿</th>
  									<th>第二志愿</th>
  									<th>上一个处理部门</th>
  									<th>正在处理部门</th>
  									<th>状态</th>
  									<th>操作</th>
  								</tr>
  							</thead>
  							<tbody>
  								<s:iterator value="#request.pageBean.applicants" status="m">
  									<tr>
  										<td><s:property value="%{#m.getIndex()+1}"/></td>
  										<td>${name }</td>
  										<td>${clazz }</td>
  										<td>${aptFirstTag.deptName }</td>
  										<td>${aptSecondTag.deptName }</td>
  										<td>${preHandleDept.deptName }</td>
  										<td>${nowHandleDept.deptName }</td>
  										<td>${aptStatus.aptStatusName }</td>
  										<td class="operationTd">
  											<s:if test="aptEmailed==1">
  												<a href="merAptShow?id=${id}" target="_blank" class="btn btn-info" title="查看报名表"><i class="fa fa-search"></i></a>
  												<a href="secHandleFourTwoPass?id=${id}&name=<s:property value="#parameters.name[0]"/>" class="btn btn-success" title="通过"><i class="fa fa-smile-o"></i></a>
  												<a href="secHandleFourTwoOut?id=${id}&name=<s:property value="#parameters.name[0]"/>" class="btn btn-warning" title="不通过"><i class="fa fa-frown-o"></i></a>
  												<a class="btn btn-primary" title="调剂至" data-toggle="collapse" data-target="#adjustItem<s:property value="%{#m.getIndex()+1}"/>"><i class="fa fa-exchange"></i></a>
  												<div class="adjustCont collapse " id="adjustItem<s:property value="%{#m.getIndex()+1}"/>">
  													<form action="secHandleFourTwoAdjust">
  														<input type="hidden" name="id" value="${id }">
  														<select class="pull-left" name="nowHandleDept_id">
  															<s:iterator value="#request.departments">
    	  														<option <s:if test="#request.pageBean.applicants[#m.index].aptSecondTag.deptId==deptId">selected</s:if> value="${deptId }">${deptName }</option>
      														</s:iterator>
  														</select>
  														<button type="submit" class="pull-left">确定</button>
  													</form>
  												</div>
  											</s:if>
  											<s:else>
  												尚未邮件通知
  											</s:else>
  										</td>
  									</tr>
  								</s:iterator>
  							</tbody>
						</table>
						<s:iterator value="#request.pageBean">
							<div class="paging">
								<s:if test="%{cp < 2}">
									<span>首页</span>
									<span>上一页</span>
								</s:if>
								<s:else>
									<a href="secHandleFourTwo?page=1&name=<s:property value="#parameters.name[0]"/>">首页</a>
									<a href="secHandleFourTwo?page=<s:property value="%{cp-1}"/>&name=<s:property value="#parameters.name[0]"/>">上一页</a>
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
									<a href="secHandleFourTwo?page=<s:property value="#i"/>&name=<s:property value="#parameters.name[0]"/>">${i }</a>
								</s:else>
								</s:iterator>
								
								<s:if test="%{cp < tp}">
									<a href="secHandleFourTwo?page=<s:property value="%{cp+1}"/>&name=<s:property value="#parameters.name[0]"/>">下一页</a>
									<a href="secHandleFourTwo?page=<s:property value="tp"/>&name=<s:property value="#parameters.name[0]"/>">尾页</a>
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/backstageJs/applicationFormManage.js" ></script>
	</body>
</html>
