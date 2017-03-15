<%@page import="java.io.Writer"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2 sidebar">
      <ul class="nav-sidebar">
        <li id="print-file-li" class="" ><a href="jsp/users/printFile.jsp">打印文件</a></li>
        <li><span>打印图片</span></li>
		<li id="order-li"><a href="jsp/users/order.jsp">订单管理</a></li>
        <%
        HttpSession s = request.getSession();
        String role = (String) s.getAttribute("role");
        if(!"4".equals(role)){
        %>
        <li id="file-li"><a onclick="window.location.href='<c:url value='/getAllFiles.do'/>?currentPage=0'"
				href="javascript:void(0);">文件管理</a></li>
        <li id="modify-li"><a href="jsp/users/modifyInfo.jsp">个人信息</a></li>
        <%}%>
        <li id="feedback-li"><a href="jsp/users/feedInfo.jsp">反馈</a></li>
      </ul>
    </div>

