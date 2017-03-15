<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://ustc.sse.a4print/tags/pages" prefix="p"%>
<jsp:include page="/jsp/template/header.jsp" />
<jsp:include page="/jsp/template/sidebar.jsp" />
<div id="pay-tip" class="register-div">
    <div id="pop-close-div" class="register-div-descript">
	   <p for="userrole">网上支付提示</p>
	</div>
	<div class="alert alert-danger" role="alert">
          支付完成前，请不要关闭此支付验证窗口。</br>
          支付完成后，请根据您支付的情况点击下面按钮。
    </div>
    </br>
	<div class="row-md-8" >
		<button type="button" name="button" id="pay-error-tip"
			class="btn btn-danger pull-left">支付遇到问题
		</button>
	</div>
	<div class="row-md-8">
		<button type="button" name="button" id="pay-success-tip"
			class="btn btn-success pull-right">支付完成
		</button>
	</div>
</div>
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header tab-header">
		<h1>订单管理</h1>
		<ul class="nav nav-tabs">
		<li role="presentation"><a href="javascript:void(0);" onclick="getOrdersByStates(0,'','','','')" id="orderState0">所有</a></li>
		<li role="presentation" class="active"><a href="javascript:void(0);" onclick="getOrdersByStates(1,1,'','','')" id="orderState1">在线订单：未支付</a></li>
		<li role="presentation"><a href="javascript:void(0);" onclick="getOrdersByStates(2,1,'','','')" id="orderState2">在线订单：已支付</a></li>
		<li role="presentation"><a href="javascript:void(0);" onclick="getOrdersByStates(3,1,'','','')" id="orderState2">在线订单：已完成</a></li>
		<li role="presentation"><a href="javascript:void(0);" onclick="getOrdersByStates(1,2,'','','')" id="orderState3">到店打印订单</a></li>
		</ul>
	</div>
	<div class="order-responsive-group" id = "user-order-table">
		<div class="order-btn-group">
			<button id="check-all" class="btn btn-warning">
				<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;全选
			</button>
			<button id="del-select-orders" class="btn btn-danger">
				<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;删除
			</button>
			<form class="form-horizontal" role="form" method="post"
				id="searchFile-form">
				<input type="date" class="form-control" id="user-search-befTime" placeholder="date"
					name="beforeTime" > <input
					type="date" class="form-control" id="user-search-aftTime" placeholder="date"
					name="afterTime" > <input type="text"
					class="form-control" id ="user-search-orderNo" placeholder="请入订单号搜索" name="orderNo"
					> <input type="hidden" name="currentPage" value="1">
				<button type="button" id="user-search-order" class="btn btn-info">
					<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;搜索
				</button>
			   </form>
			    <div class="all-order-details hidden" id="all-order-details"><span class="glyphicon glyphicon glyphicon-chevron-down"></span>&nbsp;全收起</div>
		</div>
	</div>
	<div class="table-responsive">
		<!-- 分页 -->
		<div id="page-div" style="text-align:center;margin:0;padding:0;"
			value="${url}">
			<c:if test="${!empty fileList}">
				<p:pager page="${page}" url="${url}" />
			</c:if>
		</div>
	</div>
</div>
<div id="back-top">
    	<a href="#" class="b_t"><span class="glyphicon glyphicon-menu-up" ></span></a>
</div>
</div>
<script src="js/jquery.qrcode.min.js"></script>
<script src="js/order.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />