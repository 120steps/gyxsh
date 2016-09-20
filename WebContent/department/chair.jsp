<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>部门介绍-主席团</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="../css/font-awesome.min.css" />
		<link rel="stylesheet" href="../css/departmentInfo.css" />
	</head>
	<body>
		<nav class="navbar navbar-default navbar-custom">
  			<div class="container">
    			<div class="navbar-header">
      				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        				<span class="sr-only">Toggle navigation</span>
        				<span class="icon-bar"></span>
        				<span class="icon-bar"></span>
        				<span class="icon-bar"></span>
      				</button>
      				<a class="navbar-brand new-brand" href="#">
      					<img src="../img/logo.jpg" alt="logo" />
      				</a>
    			</div>

    			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      				<ul class="nav navbar-nav department-menu">
        				<li class="active"><a href="chair.jsp">主席团 <span class="sr-only">(current)</span></a></li>
        				<li><a href="secretary.jsp">秘书处</a></li>
        				<li><a href="literature.jsp">文艺部</a></li>
        				<li><a href="life.jsp">生活部</a></li>
        				<li><a href="pe.jsp">体育部</a></li>
        				<li><a href="learn.jsp">学习部</a></li>
        				<li><a href="girl.jsp">女生部</a></li>
        				<li><a href="connection.jsp">外宣部</a></li>
      				</ul>
    			</div><!-- /.navbar-collapse -->
  			</div><!-- /.container-fluid -->
		</nav>
		
		<div class="container">
			<div class="department-mien">
				<div class="content-title clearfix">
					<h3 class="pull-left">部门风采</h3>
				</div>
				<div class="department-mien-con">
					<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  						<!-- Indicators -->
  						<ol class="carousel-indicators">
    						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
    						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
  						</ol>

  						<!-- Wrapper for slides -->
  						<div class="carousel-inner" role="listbox">
    						<div class="item active">
      							<img src="../img/secretary/carousel1.jpg" alt="...">
    						</div>
    						<div class="item">
      							<img src="../img/secretary/carousel2.jpg" alt="...">
    						</div>
    						<div class="item">
      							<img src="../img/secretary/carousel1.jpg" alt="...">
    						</div>
  						</div>

  						<!-- Controls -->
 						 <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    						<span class="fa fa-chevron-left chevron" aria-hidden="true"></span>
    						<span class="sr-only">Previous</span>
  						</a>
  						<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    						<span class="fa fa-chevron-right chevron" aria-hidden="true"></span>
    						<span class="sr-only">Next</span>
  						</a>
					</div>
				</div>
			</div>
			<div class="department-intro">
				<div class="content-title clearfix">
					<h3 class="pull-left">部门简介</h3>
				</div>
				<div class="intro-content alert alert-info">
					<div class="media">
  						<div class="media-left">
      						<img class="media-object" src="../img/secretary.png" alt="...">
  						</div>
  						<div class="media-body">
    						<h4 class="media-heading">秘书处</h4>
    						<p>穿梭于学生会各部，行走于文字之间，专注于内部建设，服务于学生工作，“人力资源+财务+办公室”综合体，无处不在的秘书处是也！我们是认真精明，在文案和数字中绽放的高冷玫瑰。</p>
    						<p>秘书处期待和你一起精致出品</p>
  						</div>
					</div>
				</div>
			</div>
			<div class="department-photo">
				<div class="content-title clearfix">
					<h3 class="pull-left">部门时光轴</h3>
				</div>
				<div class="time-line clearfix">
					<div class="time-menu">
						<ul>
							<li>2015-2016</li>
							<li>2014-2015</li>
						</ul>
					</div>
					<div class="time-content">
						<span class="year">2015-2016</span>
						<div class="time-item">
							<span class="circle-dot"></span>
							<span class="item-arrow"></span>
							<div class="item-content clearfix">
								<div class="imgBox">
									<img src="../img/secretary/sb.jpg" >
									<p><i>部长</i><i>滕良文</i></p>
								</div>
								<div class="imgBox">
									<img src="../img/secretary/sb.jpg" >
									<p><i>副部长</i><i>滕良文</i></p>
								</div>
								<div class="imgBox">
									<img src="../img/secretary/sb.jpg" >
									<p><i>部长</i><i>滕良文</i></p>
								</div>
								<div class="imgBox">
									<img src="../img/secretary/sb.jpg" >
									<p><i>部长</i><i>滕良文</i></p>
								</div>
								<div class="imgBox">
									<img src="../img/secretary/sb.jpg" >
									<p><i>部长</i><i>滕良文</i></p>
								</div>
							</div>
						</div>
					</div>
					
					<div class="time-content">
						<span class="year">2014-2015</span>
						<div class="time-item">
							<span class="circle-dot"></span>
							<span class="item-arrow"></span>
							<div class="item-content clearfix">
								<div class="imgBox">
									<img src="../img/secretary/sb.jpg" >
									<p><i>部长</i><i>滕良文</i></p>
								</div>
								<div class="imgBox">
									<img src="../img/secretary/sb.jpg" >
									<p><i>副部长</i><i>滕良文</i></p>
								</div>
								<div class="imgBox">
									<img src="../img/secretary/sb.jpg" >
									<p><i>部长</i><i>滕良文</i></p>
								</div>
								<div class="imgBox">
									<img src="../img/secretary/sb.jpg" >
									<p><i>部长</i><i>滕良文</i></p>
								</div>
								<div class="imgBox">
									<img src="../img/secretary/sb.jpg" >
									<p><i>部长</i><i>滕良文</i></p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="toTop">
			<span class="fa fa-chevron-up fa-2x" title="返回顶部"></span>
		</div>
		
		<script type="text/javascript" src="../js/jquery-3.0.0.min.js" ></script>
		<script type="text/javascript" src="../js/bootstrap.min.js" ></script>
		<script type="text/javascript" src="../js/departmentInfo.js" ></script>
	</body>
</html>
