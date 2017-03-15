<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="u"%>
<%@ taglib uri="http://ustc.sse.a4print/tags/pages" prefix="p"%>
<jsp:include page="/jsp/template/adminHeader.jsp" />
<jsp:include page="/jsp/template/adminSidebar.jsp" />
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>订单统计</h1>
	</div>
	<!--Step:1 为ECharts准备一个具备大小（宽高）的Dom-->
	<div id="month" style="height:500px;border:1px solid #ccc;padding:5px;"></div>
	<!-- <div id="mainMap" style="height:500px;border:1px solid #ccc;padding:10px;"></div>-->			
</div>
</div>
<!--Step:2 引入echarts.js-->
<script src="./js/echarts.js"></script>
<script src="./js/orderStatistic.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />