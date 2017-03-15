<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2 sidebar">
		<ul class="nav-sidebar">
			<li id="users-manage-li" class=""><a href="reloaduserslist.do?currentPage=1">用户信息管理</a></li>
			<li id="file-manage-li" class=""><a href="admin_getAllFiles.do?currentPage=1">用户文件管理</a></li>
			<li id="users-order-li" class=""><a href="jsp/admin/adminUserOrder.jsp">用户订单管理</a></li>
			<li id="printer-address-li" class=""><a href="jsp/admin/adminPrinterAddress.jsp">打印地址管理</a></li>
			<li id="printer-price-li" class=""><a href="getAllPrices.do">打印价格管理</a></li>
			<li id="order-statistic-li" class=""><a href="jsp/admin/adminOrderStatistic.jsp">统计信息管理</a></li>
			<li id="users-feedback-li" class=""><a href="getAllFeedInfos.do">用户反馈管理</a>
			</li>
		</ul>
	</div>