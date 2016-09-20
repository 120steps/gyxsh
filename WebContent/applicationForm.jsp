<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>报名表</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="css/font-awesome.min.css" />
<link rel="stylesheet" href="css/bootstrap-select.min.css" />
<link rel="stylesheet" href="css/jquery.datetimepicker.css" />
<link rel="stylesheet" href="css/applicationForm.css" />
</head>
<body>
	<nav class="navbar navbar-default navbar-custom navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand new-brand" href="#"> <img
				src="img/logo.jpg" alt="logo" />
			</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav department-menu">
				<li><a href="#" target="_blank">首页<span class="sr-only">(current)</span></a></li>
				<li><a href="index.jsp" target="_blank">招新首页</a></li>
				<li><a href="${pageContext.request.contextPath }/department/chair.jsp" target="_blank">部门介绍</a></li>
				<li class="active"><a href="aptInput" target="_blank">报名表</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<div class="container">
		<div class="headTitle clearfix">
			<h3 class="pull-left">报名表</h3>
		</div>
		<div class="warnDiv">
			<div class="alert alert-danger" role="alert">请认证填写报名表的每一项，我们将对你提供的信息严格保密</div>
		</div>

		<div class="form-content clearfix">
			<form class="form-horizontal col-sm-10 col-sm-offset-2" method="post"
				action="aptRegister" name="appliForm" enctype="multipart/form-data">
				<h3>基本信息</h3>
				<div class="apImage">
					<p class="img-title">个人照片</p>
					<img src="img/default-photo.png" />
					<p>
						<button class="img-btn" type="button">
							<i class="fa fa-plus-square"></i> 上传照片
						</button>
					</p>
					<input type="file" class="img-file" name="img"/>
					<p class="explain">格式：jpg/png/jpeg/gif</p>
					<p class="explain">小于200kb</p>
					<p class="warning-info">${errors.img }</p>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">姓名：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="name" name="name"
							placeholder="如：张三" value="${applicant.name }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.name }</label>
				</div>
				<div class="form-group">
					<label for="sex" class="col-sm-2 control-label">性别：</label>
					<div class="col-sm-6">
						<select class="selectpicker form-control show-tick" id="sex"
							name="gender">
							<option
								<s:if test='#request.applicant.gender=="男"'>selected</s:if>
								value="男">男</option>
							<option
								<s:if test='#request.applicant.gender=="女"'>selected</s:if>
								value="女">女</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="native" class="col-sm-2 control-label">籍贯：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="native" name="place"
							placeholder="如：湖北省武汉市" value="${applicant.place }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.place }</label>
				</div>
				<div class="form-group">
					<label for="politics-status" class="col-sm-2 control-label">政治面貌：</label>
					<div class="col-sm-6">
						<select id="politics-status" name="polity"
							class="selectpicker form-control show-tick" data-size="5">
							<option
								<s:if test="#request.applicant.polity==null">selected</s:if>
								value="0">请选择政治面貌...</option>
							<option
								<s:if test='#request.applicant.polity=="中共党员"'>selected</s:if>
								value="中共党员">中共党员</option>
							<option
								<s:if test='#request.applicant.polity=="预备党员"'>selected</s:if>
								value="预备党员">预备党员</option>
							<option
								<s:if test='#request.applicant.polity=="入党积极分子"'>selected</s:if>
								value="入党积极分子">入党积极分子</option>
							<option
								<s:if test='#request.applicant.polity=="共青团员"'>selected</s:if>
								value="共青团员">共青团员</option>
							<option
								<s:if test='#request.applicant.polity=="群众"'>selected</s:if>
								value="群众">群众</option>
							<option
								<s:if test='#request.applicant.polity=="其他"'>selected</s:if>
								value="其他">其他</option>
						</select>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.polity }</label>
				</div>
				<div class="form-group">
					<label for="nation" class="col-sm-2 control-label">民族：</label>
					<div class="col-sm-6">
						<select id="nation" name="nation"
							class="selectpicker form-control show-tick"
							data-live-search="true" data-size="5">
							<option
								<s:if test="#request.applicant.nation==null">selected</s:if>
								value="0">请选择民族...</option>
							<option
								<s:if test='#request.applicant.nation=="汉族"'>selected</s:if>
								value="汉族">汉族</option>
							<option
								<s:if test='#request.applicant.nation=="蒙古族"'>selected</s:if>
								value="蒙古族">蒙古族</option>
							<option
								<s:if test='#request.applicant.nation=="回族"'>selected</s:if>
								value="回族">回族</option>
							<option
								<s:if test='#request.applicant.nation=="藏族"'>selected</s:if>
								value="藏族">藏族</option>
							<option
								<s:if test='#request.applicant.nation=="维吾尔族"'>selected</s:if>
								value="维吾尔族">维吾尔族</option>
							<option
								<s:if test='#request.applicant.nation=="苗族"'>selected</s:if>
								value="苗族">苗族</option>
							<option
								<s:if test='#request.applicant.nation=="彝族"'>selected</s:if>
								value="彝族">彝族</option>
							<option
								<s:if test='#request.applicant.nation=="壮族"'>selected</s:if>
								value="壮族">壮族</option>
							<option
								<s:if test='#request.applicant.nation=="布依族"'>selected</s:if>
								value="布依族">布依族</option>
							<option
								<s:if test='#request.applicant.nation=="朝鲜族"'>selected</s:if>
								value="朝鲜族">朝鲜族</option>
							<option
								<s:if test='#request.applicant.nation=="满族"'>selected</s:if>
								value="满族">满族</option>
							<option
								<s:if test='#request.applicant.nation=="侗族"'>selected</s:if>
								value="侗族">侗族</option>
							<option
								<s:if test='#request.applicant.nation=="瑶族"'>selected</s:if>
								value="瑶族">瑶族</option>
							<option
								<s:if test='#request.applicant.nation=="白族"'>selected</s:if>
								value="白族">白族</option>
							<option
								<s:if test='#request.applicant.nation=="土家族"'>selected</s:if>
								value="土家族">土家族</option>
							<option
								<s:if test='#request.applicant.nation=="哈尼族"'>selected</s:if>
								value="哈尼族">哈尼族</option>
							<option
								<s:if test='#request.applicant.nation=="哈萨克族"'>selected</s:if>
								value="哈萨克族">哈萨克族</option>
							<option
								<s:if test='#request.applicant.nation=="傣族"'>selected</s:if>
								value="傣族">傣族</option>
							<option
								<s:if test='#request.applicant.nation=="黎族"'>selected</s:if>
								value="黎族">黎族</option>
							<option
								<s:if test='#request.applicant.nation=="傈僳族"'>selected</s:if>
								value="傈僳族">傈僳族</option>
							<option
								<s:if test='#request.applicant.nation=="佤族"'>selected</s:if>
								value="佤族">佤族</option>
							<option
								<s:if test='#request.applicant.nation=="畲族"'>selected</s:if>
								value="畲族">畲族</option>
							<option
								<s:if test='#request.applicant.nation=="高山族"'>selected</s:if>
								value="高山族">高山族</option>
							<option
								<s:if test='#request.applicant.nation=="拉祜族"'>selected</s:if>
								value="拉祜族">拉祜族</option>
							<option
								<s:if test='#request.applicant.nation=="水族"'>selected</s:if>
								value="水族">水族</option>
							<option
								<s:if test='#request.applicant.nation=="东乡族"'>selected</s:if>
								value="东乡族">东乡族</option>
							<option
								<s:if test='#request.applicant.nation=="纳西族"'>selected</s:if>
								value="纳西族">纳西族</option>
							<option
								<s:if test='#request.applicant.nation=="景颇族"'>selected</s:if>
								value="景颇族">景颇族</option>
							<option
								<s:if test='#request.applicant.nation=="柯尔克孜族"'>selected</s:if>
								value="柯尔克孜族">柯尔克孜族</option>
							<option
								<s:if test='#request.applicant.nation=="土族"'>selected</s:if>
								value="土族">土族</option>
							<option
								<s:if test='#request.applicant.nation=="达斡尔族"'>selected</s:if>
								value="达斡尔族">达斡尔族</option>
							<option
								<s:if test='#request.applicant.nation=="仫佬族"'>selected</s:if>
								value="仫佬族">仫佬族</option>
							<option
								<s:if test='#request.applicant.nation=="羌族"'>selected</s:if>
								value="羌族">羌族</option>
							<option
								<s:if test='#request.applicant.nation=="布朗族"'>selected</s:if>
								value="布朗族">布朗族</option>
							<option
								<s:if test='#request.applicant.nation=="撒拉族"'>selected</s:if>
								value="撒拉族">撒拉族</option>
							<option
								<s:if test='#request.applicant.nation=="毛南族"'>selected</s:if>
								value="毛南族">毛南族</option>
							<option
								<s:if test='#request.applicant.nation=="仡佬族"'>selected</s:if>
								value="仡佬族">仡佬族</option>
							<option
								<s:if test='#request.applicant.nation=="锡伯族"'>selected</s:if>
								value="锡伯族">锡伯族</option>
							<option
								<s:if test='#request.applicant.nation=="阿昌族"'>selected</s:if>
								value="阿昌族">阿昌族</option>
							<option
								<s:if test='#request.applicant.nation=="普米族"'>selected</s:if>
								value="普米族">普米族</option>
							<option
								<s:if test='#request.applicant.nation=="塔吉克族"'>selected</s:if>
								value="塔吉克族">塔吉克族</option>
							<option
								<s:if test='#request.applicant.nation=="怒族"'>selected</s:if>
								value="怒族">怒族</option>
							<option
								<s:if test='#request.applicant.nation=="乌孜别克族"'>selected</s:if>
								value="乌孜别克族">乌孜别克族</option>
							<option
								<s:if test='#request.applicant.nation=="俄罗斯族"'>selected</s:if>
								value="俄罗斯族">俄罗斯族</option>
							<option
								<s:if test='#request.applicant.nation=="鄂温克族"'>selected</s:if>
								value="鄂温克族">鄂温克族</option>
							<option
								<s:if test='#request.applicant.nation=="德昂族"'>selected</s:if>
								value="德昂族">德昂族</option>
							<option
								<s:if test='#request.applicant.nation=="保安族"'>selected</s:if>
								value="保安族">保安族</option>
							<option
								<s:if test='#request.applicant.nation=="裕固族"'>selected</s:if>
								value="裕固族">裕固族</option>
							<option
								<s:if test='#request.applicant.nation=="京族"'>selected</s:if>
								value="京族">京族</option>
							<option
								<s:if test='#request.applicant.nation=="塔塔尔族"'>selected</s:if>
								value="塔塔尔族">塔塔尔族</option>
							<option
								<s:if test='#request.applicant.nation=="独龙族"'>selected</s:if>
								value="独龙族">独龙族</option>
							<option
								<s:if test='#request.applicant.nation=="鄂伦春族"'>selected</s:if>
								value="鄂伦春族">鄂伦春族</option>
							<option
								<s:if test='#request.applicant.nation=="赫哲族"'>selected</s:if>
								value="赫哲族">赫哲族</option>
							<option
								<s:if test='#request.applicant.nation=="门巴族"'>selected</s:if>
								value="门巴族">门巴族</option>
							<option
								<s:if test='#request.applicant.nation=="珞巴族"'>selected</s:if>
								value="珞巴族">珞巴族</option>
							<option
								<s:if test='#request.applicant.nation=="基诺族"'>selected</s:if>
								value="基诺族">基诺族</option>
						</select>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.nation }</label>
				</div>
				<div class="form-group">
					<label for="birthday" class="col-sm-2 control-label">出生日期：</label>
					<div class="col-sm-6">
						<div class="input-group inputDiv">
							<input type="text" id="birthday" name="birth"
								class="form-control" placeholder="如：1995-01-01"
								value='<s:date format="yyyy-MM-dd" name="birth"/>' /> <span
								class="input-group-addon"><i class="fa fa-calendar"></i></span>
						</div>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.birth }</label>
				</div>
				<div class="form-group">
					<label for="majorAndClass" class="col-sm-2 control-label">专业班级：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="majorAndClass"
							name="clazz" placeholder="如：信管1301/工商1301/公管1301" value="${applicant.clazz }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.clazz }</label>
				</div>
				<div class="form-group">
					<label for="studentNum" class="col-sm-2 control-label">学号：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="studentNum" name="studentNum"
							placeholder="如：0121303490113"
							value="${applicant.studentNum }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.studentNum }</label>
				</div>
				<div class="form-group">
					<label for="dormitory" class="col-sm-2 control-label">宿舍：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="dormitory"
							name="dormitory" placeholder="如：南4-309"
							value="${applicant.dormitory }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.dormitory }</label>
				</div>
				<div class="hr"></div>
				<h3>联系方式</h3>
				<div class="form-group">
					<label for="phoneNumber" class="col-sm-2 control-label">手机：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="phoneNumber"
							name="phone" value="${applicant.phone }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.phone }</label>
				</div>
				<div class="form-group">
					<label for="qq" class="col-sm-2 control-label">QQ：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="qq" name="qq"
							value="${applicant.qq }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.qq }</label>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label">邮箱：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="email" name="email"
							value="${applicant.email }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.email }</label>
				</div>
				<div class="hr"></div>
				<h3>个人简介</h3>
				<div class="form-group">
					<label for="firstdepartment" class="col-sm-2 control-label">第一志愿：</label>
					<div class="col-sm-6">
						<select id="firstdepartment" name="aptFirstTag_id"
							class="selectpicker form-control show-tick" data-size="5">
							<option
								<s:if test="#request.applicant.aptFirstTag.deptId==null">selected</s:if>
								value="0">请选择第一志愿...</option>
							<s:iterator value="#request.departments">
								<option
									<s:if test="#request.applicant.aptFirstTag.deptId==deptId">selected</s:if>
									value="${deptId }">${deptName }</option>
							</s:iterator>
						</select>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.aptFirstTag }</label>
				</div>
				<div class="form-group">
					<label for="seconddepartment" class="col-sm-2 control-label">第二志愿：</label>
					<div class="col-sm-6">
						<select id="seconddepartment" name="aptSecondTag_id"
							class="selectpicker form-control show-tick" data-size="5">
							<option
								<s:if test="#request.applicant.aptSecondTag.deptId==null">selected</s:if>
								value="0">请选择第二志愿...</option>
							<s:iterator value="#request.departments">
								<option
									<s:if test="#request.applicant.aptSecondTag.deptId==deptId">selected</s:if>
									value="${deptId }">${deptName }</option>
							</s:iterator>
						</select>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.aptSecondTag }</label>
				</div>
				<div class="form-group">
					<label for="otherDepartment" class="col-sm-2 control-label">已报名或参加其他社团：</label>
					<div class="col-sm-6">
						<textarea class="form-control" id="otherDepartment"
							name="aptOtherOrg" rows="1" placeholder="没有就填“无”">${applicant.aptOtherOrg }</textarea>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.aptOtherOrg }</label>
				</div>
				<div class="form-group">
					<label for="speciality" class="col-sm-2 control-label">特长：</label>
					<div class="col-sm-6">
						<textarea class="form-control" id="speciality"
							name="aptSpeciality" rows="1">${applicant.aptSpeciality }</textarea>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.aptSpeciality }</label>
				</div>
				<div class="form-group">
					<label for="interest" class="col-sm-2 control-label">兴趣爱好：</label>
					<div class="col-sm-6">
						<textarea class="form-control" id="interest" name="aptInterest"
							rows="1">${applicant.aptInterest }</textarea>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.aptInterest }</label>
				</div>
				<div class="form-group">
					<label for="workExperience" class="col-sm-2 control-label">工作经历：<br>（简述工作经历，获奖情况等）
					</label>
					<div class="col-sm-6">
						<textarea class="form-control" id="workExperience"
							name="aptExperience" rows="10">${applicant.aptExperience }</textarea>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.aptExperience }</label>
				</div>
				<div class="form-group">
					<label for="cognition" class="col-sm-2 control-label">对学生会的认识：<br>（你对学生会有什么了解？）
					</label>
					<div class="col-sm-6">
						<textarea class="form-control" id="cognition" name="aptKnow"
							rows="8">${applicant.aptKnow }</textarea>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.aptKnow }</label>
				</div>
				<div class="form-group">
					<label for="outlook" class="col-sm-2 control-label">工作展望：<br>（结合你竞选的部门，谈谈你对该部门工作的理解和创新）
					</label>
					<div class="col-sm-6">
						<textarea class="form-control" id="outlook" name="aptExpectation"
							rows="8">${applicant.aptExpectation }</textarea>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.aptExpectation }</label>
				</div>
				<div class="formBtn">
					<button class="btn btn-success commitBtn" type="button">
						<i class="fa fa-check"></i> 提交
					</button>
					<button class="btn btn-danger" type="button">
						<i class="fa fa-mail-reply"></i> 取消
					</button>
				</div>
			</form>
		</div>

	</div>
	<div class="toTop">
		<span class="fa fa-chevron-up fa-2x" title="返回顶部"></span>
	</div>




	<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-select.js"></script>
	<script type="text/javascript"
		src="js/jquery.datetimepicker.full.min.js"></script>
	<script type="text/javascript" src="js/applicationForm.js"></script>
</body>
</html>
