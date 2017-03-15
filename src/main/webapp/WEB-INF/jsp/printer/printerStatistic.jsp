<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/printerHeader.jsp" />
<jsp:include page="/jsp/template/printerSidebar.jsp" />
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>订单信息统计</h1>
		<p>
			<span>一些相关的统计信息！</span>
		</p>
	</div>
	<!--Step:1 为ECharts准备一个具备大小（宽高）的Dom-->
	<div id="printer-month-order" style="height:500px;border:1px solid #ccc;padding:5px;"></div>
	<!-- <div id="mainMap" style="height:500px;border:1px solid #ccc;padding:10px;"></div>-->			
	<div class="col-xs-12 col-sm-10 col-md-7">
	
	</div>
</div>
</div>
<!--Step:2 引入echarts.js-->
<script src="./js/echarts.js"></script>
<script src="./js/printerStatistic.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />