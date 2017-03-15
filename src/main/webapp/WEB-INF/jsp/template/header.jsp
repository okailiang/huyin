<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    HttpSession s = request.getSession(false);
    if (s != null) {
        String role = (String) s.getAttribute("role");
        String username = (String) s.getAttribute("username");
    }
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>微印社</title>
    <meta name="description" content="为在校大学生提供便捷可行的远程打印服务">
    <meta name="keywords" content="wein, 微印, 微印社, 打印, 校园打印, 在线打印, 远程打印">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="address=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/custom.css">
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
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="jsp/users/index.jsp" class="navbar-brand">
                        <img src="images/icon.png"/>
                    </a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="" id="index-li"><a href="jsp/users/index.jsp">首页</a></li>
                        <li class="" id="print-li"><a href="jsp/users/printFile.jsp">前往打印</a></li>
                    </ul>
                    <div class="navbar-right">
                        <ul class="nav navbar-nav">
                            <c:choose>
                                <c:when test="${(!empty username)&&(role eq '1')}">
                                    <li class=""><span>欢迎你，${username}</span></li>
                                    <li class=""><a href="jsp/users/logout.jsp">退出</a></li>
                                </c:when>
                                <c:when test="${(!empty username)&&(role eq '2')}">
                                    <li class=""><span>欢迎你，管理员 ${username}</span></li>
                                    <li class=""><a href="jsp/users/logout.jsp">退出</a></li>
                                </c:when>
                                <c:when test="${(!empty username)&&(role eq '4')}">
                                    <li class=""><span>匿名号 ${username}</span></li>
                                    <li class=""><a href="jsp/users/logout.jsp">退出</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="" id="register-li"><a href="jsp/users/register.jsp">注册</a></li>
                                    <li class="" id="login-li"><a href="jsp/users/login.jsp">登录</a></li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div id="page" class="container">