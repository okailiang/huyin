<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	HttpSession s = request.getSession();
	String role = (String) s.getAttribute("role");
	String username = (String) s.getAttribute("username");
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>A4Print 校园打印</title>
<meta name="description" content="为在校大学生提供便捷可行的远程打印服务">
<meta name="keywords" content="a4print, 打印, 校园打印, 在线打印, 远程打印">
<meta name="format-detection" content="telephone=no">
<meta name="format-detection" content="address=no">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet" href="css/address.css">
<link rel="stylesheet" href="css/dialog.css">
<!--[if lt IE 9]>
        <script src="js/html5.js"></script>
    <![endif]-->
    <script src="js/jquery-1.11.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class="main-container">
		<header>
			<div role="navigation" class="navbar navbar-default topnav">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
			              <span class="sr-only">Toggle navigation</span>
			              <span class="icon-bar"></span>
			              <span class="icon-bar"></span>
			              <span class="icon-bar"></span>
			            </button>
						<span class="navbar-brand">A4Print 后台</span>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<div class="navbar-right">
							<ul class="nav navbar-nav">
								<c:choose>
									<c:when test="${(!empty username)&&(role eq '2')}">
										<li class=""><span>欢迎你，管理员 ${username}</span></li>
										<li class=""><a href="jsp/users/logout.jsp">退出</a></li>
									</c:when>
									<c:otherwise>
									    <li class="" id="login-li"><a href="jsp/admin/adminLogin.jsp">登录</a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</header>
		<div  id="page" class="container">