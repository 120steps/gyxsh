<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理后台-成员基本信息</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-select.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery.datetimepicker.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/backstageCss/memberBasicInfo.css" />
</head>
<body>
	<div class="content container">
		<div class="head-menu clearfix">
			<ul class="clearfix">
				<li class="active"><a href="">基本信息</a></li>
				<li><a
					href="adminShowMemberShowAccount?id=<s:property value="#parameters.id[0]"/>">账户信息</a></li>
			</ul>
		</div>
		<div class="center">
			<form class="form-horizontal" action="adminShowMemberByIdSave" method="post" name="memberBasicInfoForm">
				<input type="hidden" name="id"
					value='<s:property value="#parameters.id[0]"/>'>
				<div class="form-group">
					<label for="department" class="col-sm-2 control-label">所属部门：</label>
					<div class="col-sm-6">
						<select class="selectpicker form-control show-tick"
							id="department" name="merDepartment.deptId">
							<s:iterator value="#request.departments">
								<option
									<s:if test="#request.member.merDepartment.deptId==deptId">selected</s:if>
									value="${deptId }">${deptName }</option>
							</s:iterator>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="jurisdiction" class="col-sm-2 control-label">职位：</label>
					<div class="col-sm-6">
						<select class="selectpicker form-control show-tick" id="jurisdiction"
							name="merStatus.merStatusId">
							<option <s:if test="#request.member.merStatus.merStatusId==null">selected</s:if> value="0">请选择职位...</option>
							<option <s:if test="#request.member.merStatus.merStatusId==1">selected</s:if> value="1">主席</option>
      						<option <s:if test="#request.member.merStatus.merStatusId==2">selected</s:if> value="2">副主席</option>
      						<option <s:if test="#request.member.merStatus.merStatusId==3">selected</s:if> value="3">部长</option>
      						<option <s:if test="#request.member.merStatus.merStatusId==4">selected</s:if> value="4">副部长</option>
      						<option <s:if test="#request.member.merStatus.merStatusId==5">selected</s:if> value="5">干事</option>
						</select>
					</div>
					<label class="col-sm-3 control-label danger-label">${errors.merStatus }</label>
				</div>
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">姓名：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="name" name="name"
							placeholder="如：张三" value="${member.name }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.name }</label>
				</div>
				<div class="form-group">
					<label for="sex" class="col-sm-2 control-label">性别：</label>
					<div class="col-sm-6">
						<select class="selectpicker form-control show-tick" id="sex"
							name="gender">
							<option <s:if test='#request.member.gender=="男"'>selected</s:if>
								value="男">男</option>
							<option <s:if test='#request.member.gender=="女"'>selected</s:if>
								value="女">女</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="native" class="col-sm-2 control-label">籍贯：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="native" name="place"
							placeholder="如：湖北省武汉市" value="${member.place }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.place }</label>
				</div>
				<div class="form-group">
					<label for="politics-status" class="col-sm-2 control-label">政治面貌：</label>
					<div class="col-sm-6">
						<select id="politics-status" name="polity"
							class="selectpicker form-control show-tick" data-size="5">
							<option
								<s:if test='#request.member.polity=="中共党员"'>selected</s:if>
								value="中共党员">中共党员</option>
							<option
								<s:if test='#request.member.polity=="预备党员"'>selected</s:if>
								value="预备党员">预备党员</option>
							<option
								<s:if test='#request.member.polity=="入党积极分子"'>selected</s:if>
								value="入党积极分子">入党积极分子</option>
							<option
								<s:if test='#request.member.polity=="共青团员"'>selected</s:if>
								value="共青团员">共青团员</option>
							<option <s:if test='#request.member.polity=="群众"'>selected</s:if>
								value="群众">群众</option>
							<option <s:if test='#request.member.polity=="其他"'>selected</s:if>
								value="其他">其他</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="nation" class="col-sm-2 control-label">民族：</label>
					<div class="col-sm-6">
						<select id="nation" name="nation"
							class="selectpicker form-control show-tick"
							data-live-search="true" data-size="5">
							<option
								<s:if test='#request.member.nation=="汉族"'>selected</s:if>
								value="汉族">汉族</option>
							<option
								<s:if test='#request.member.nation=="蒙古族"'>selected</s:if>
								value="蒙古族">蒙古族</option>
							<option
								<s:if test='#request.member.nation=="回族"'>selected</s:if>
								value="回族">回族</option>
							<option
								<s:if test='#request.member.nation=="藏族"'>selected</s:if>
								value="藏族">藏族</option>
							<option
								<s:if test='#request.member.nation=="维吾尔族"'>selected</s:if>
								value="维吾尔族">维吾尔族</option>
							<option
								<s:if test='#request.member.nation=="苗族"'>selected</s:if>
								value="苗族">苗族</option>
							<option
								<s:if test='#request.member.nation=="彝族"'>selected</s:if>
								value="彝族">彝族</option>
							<option
								<s:if test='#request.member.nation=="壮族"'>selected</s:if>
								value="壮族">壮族</option>
							<option
								<s:if test='#request.member.nation=="布依族"'>selected</s:if>
								value="布依族">布依族</option>
							<option
								<s:if test='#request.member.nation=="朝鲜族"'>selected</s:if>
								value="朝鲜族">朝鲜族</option>
							<option
								<s:if test='#request.member.nation=="满族"'>selected</s:if>
								value="满族">满族</option>
							<option
								<s:if test='#request.member.nation=="侗族"'>selected</s:if>
								value="侗族">侗族</option>
							<option
								<s:if test='#request.member.nation=="瑶族"'>selected</s:if>
								value="瑶族">瑶族</option>
							<option
								<s:if test='#request.member.nation=="白族"'>selected</s:if>
								value="白族">白族</option>
							<option
								<s:if test='#request.member.nation=="土家族"'>selected</s:if>
								value="土家族">土家族</option>
							<option
								<s:if test='#request.member.nation=="哈尼族"'>selected</s:if>
								value="哈尼族">哈尼族</option>
							<option
								<s:if test='#request.member.nation=="哈萨克族"'>selected</s:if>
								value="哈萨克族">哈萨克族</option>
							<option
								<s:if test='#request.member.nation=="傣族"'>selected</s:if>
								value="傣族">傣族</option>
							<option
								<s:if test='#request.member.nation=="黎族"'>selected</s:if>
								value="黎族">黎族</option>
							<option
								<s:if test='#request.member.nation=="傈僳族"'>selected</s:if>
								value="傈僳族">傈僳族</option>
							<option
								<s:if test='#request.member.nation=="佤族"'>selected</s:if>
								value="佤族">佤族</option>
							<option
								<s:if test='#request.member.nation=="畲族"'>selected</s:if>
								value="畲族">畲族</option>
							<option
								<s:if test='#request.member.nation=="高山族"'>selected</s:if>
								value="高山族">高山族</option>
							<option
								<s:if test='#request.member.nation=="拉祜族"'>selected</s:if>
								value="拉祜族">拉祜族</option>
							<option
								<s:if test='#request.member.nation=="水族"'>selected</s:if>
								value="水族">水族</option>
							<option
								<s:if test='#request.member.nation=="东乡族"'>selected</s:if>
								value="东乡族">东乡族</option>
							<option
								<s:if test='#request.member.nation=="纳西族"'>selected</s:if>
								value="纳西族">纳西族</option>
							<option
								<s:if test='#request.member.nation=="景颇族"'>selected</s:if>
								value="景颇族">景颇族</option>
							<option
								<s:if test='#request.member.nation=="柯尔克孜族"'>selected</s:if>
								value="柯尔克孜族">柯尔克孜族</option>
							<option
								<s:if test='#request.member.nation=="土族"'>selected</s:if>
								value="土族">土族</option>
							<option
								<s:if test='#request.member.nation=="达斡尔族"'>selected</s:if>
								value="达斡尔族">达斡尔族</option>
							<option
								<s:if test='#request.member.nation=="仫佬族"'>selected</s:if>
								value="仫佬族">仫佬族</option>
							<option
								<s:if test='#request.member.nation=="羌族"'>selected</s:if>
								value="羌族">羌族</option>
							<option
								<s:if test='#request.member.nation=="布朗族"'>selected</s:if>
								value="布朗族">布朗族</option>
							<option
								<s:if test='#request.member.nation=="撒拉族"'>selected</s:if>
								value="撒拉族">撒拉族</option>
							<option
								<s:if test='#request.member.nation=="毛南族"'>selected</s:if>
								value="毛南族">毛南族</option>
							<option
								<s:if test='#request.member.nation=="仡佬族"'>selected</s:if>
								value="仡佬族">仡佬族</option>
							<option
								<s:if test='#request.member.nation=="锡伯族"'>selected</s:if>
								value="锡伯族">锡伯族</option>
							<option
								<s:if test='#request.member.nation=="阿昌族"'>selected</s:if>
								value="阿昌族">阿昌族</option>
							<option
								<s:if test='#request.member.nation=="普米族"'>selected</s:if>
								value="普米族">普米族</option>
							<option
								<s:if test='#request.member.nation=="塔吉克族"'>selected</s:if>
								value="塔吉克族">塔吉克族</option>
							<option
								<s:if test='#request.member.nation=="怒族"'>selected</s:if>
								value="怒族">怒族</option>
							<option
								<s:if test='#request.member.nation=="乌孜别克族"'>selected</s:if>
								value="乌孜别克族">乌孜别克族</option>
							<option
								<s:if test='#request.member.nation=="俄罗斯族"'>selected</s:if>
								value="俄罗斯族">俄罗斯族</option>
							<option
								<s:if test='#request.member.nation=="鄂温克族"'>selected</s:if>
								value="鄂温克族">鄂温克族</option>
							<option
								<s:if test='#request.member.nation=="德昂族"'>selected</s:if>
								value="德昂族">德昂族</option>
							<option
								<s:if test='#request.member.nation=="保安族"'>selected</s:if>
								value="保安族">保安族</option>
							<option
								<s:if test='#request.member.nation=="裕固族"'>selected</s:if>
								value="裕固族">裕固族</option>
							<option
								<s:if test='#request.member.nation=="京族"'>selected</s:if>
								value="京族">京族</option>
							<option
								<s:if test='#request.member.nation=="塔塔尔族"'>selected</s:if>
								value="塔塔尔族">塔塔尔族</option>
							<option
								<s:if test='#request.member.nation=="独龙族"'>selected</s:if>
								value="独龙族">独龙族</option>
							<option
								<s:if test='#request.member.nation=="鄂伦春族"'>selected</s:if>
								value="鄂伦春族">鄂伦春族</option>
							<option
								<s:if test='#request.member.nation=="赫哲族"'>selected</s:if>
								value="赫哲族">赫哲族</option>
							<option
								<s:if test='#request.member.nation=="门巴族"'>selected</s:if>
								value="门巴族">门巴族</option>
							<option
								<s:if test='#request.member.nation=="珞巴族"'>selected</s:if>
								value="珞巴族">珞巴族</option>
							<option
								<s:if test='#request.member.nation=="基诺族"'>selected</s:if>
								value="基诺族">基诺族</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="birthday" class="col-sm-2 control-label">出生日期：</label>
					<div class="col-sm-6">
						<div class="input-group inputDiv">
							<input type="text" id="birthday" name="birth" class="form-control"
								placeholder="如：1995-01-01" value='<s:date format="yyyy-MM-dd" name="#request.member.birth"/>' /> <span
								class="input-group-addon"><i class="fa fa-calendar"></i></span>
						</div>
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.birth }</label>
				</div>
				<div class="form-group">
					<label for="majorAndClass" class="col-sm-2 control-label">专业班级：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="majorAndClass" name="clazz"
							placeholder="如：信管1301班" value="${member.clazz }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.clazz }</label>
				</div>
				<div class="form-group">
					<label for="studentNum" class="col-sm-2 control-label">学号：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="studentNum" name="studentNum"
							value="${member.studentNum }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.studentNum }</label>
				</div>
				<div class="form-group">
					<label for="dormitory" class="col-sm-2 control-label">宿舍：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="dormitory" name="dormitory"
							placeholder="如：南4-309" value="${member.dormitory }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.dormitory }</label>
				</div>
				<div class="hr"></div>
				<div class="form-group">
					<label for="phoneNumber" class="col-sm-2 control-label">手机：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="phoneNumber" name="phone"
							value="${member.phone }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.phone }</label>
				</div>
				<div class="form-group">
					<label for="qq" class="col-sm-2 control-label">QQ：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="qq" name="qq" value="${member.qq }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.qq }</label>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label">邮箱：</label>
					<div class="col-sm-6 inputDiv">
						<input type="text" class="form-control" id="email" name="email"
							value="${member.email }">
					</div>
					<label class="col-sm-4 control-label danger-label">${errors.email }</label>
				</div>
				<div class="formBtn">
					<button class="btn btn-success commitBtn" type="button">
						<i class="fa fa-check"></i> 保存
					</button>
					<button class="btn btn-danger" type="button">
						<i class="fa fa-mail-reply"></i> 取消
					</button>
				</div>
			</form>
		</div>
	</div>


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-3.0.0.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.datetimepicker.full.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/backstageJs/memberBasicInfo.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/backstageJs/checkMemberPosition.js"></script>
</body>
</html>
