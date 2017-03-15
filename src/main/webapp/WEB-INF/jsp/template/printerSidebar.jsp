<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="row">
    <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2 sidebar">
      <ul class="nav-sidebar">
        <li id="printer-order-statistic-li"><a href="jsp/printer/printerStatistic.jsp">订单信息统计</a></li>
        <li id="printer-order-li"><a href="jsp/printer/printerOrder.jsp">订单信息管理</a></li>
        <li id="printer-price"><a href="getPricesByPrinter.do">打印价格管理</a>
      </ul>
    </div>

