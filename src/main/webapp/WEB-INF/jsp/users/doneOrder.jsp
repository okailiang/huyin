<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/jsp/template/header.jsp"/>
<jsp:include page="/jsp/template/sidebar.jsp" />
    <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
        <div class="page-header">
            <h1>完成订单</h1>
            <p><span> 上传文件</span> → <span></span><span>提交订单</span> → <span>在线支付</span> → <span class="label label-success">完成订单</span></p>
        </div>
        <div id="print-order-success">
        	<div class="hidden" id="done-order-number" value="${param.orderNo}"></div>
            <form class="form-horizontal" role="form" method="post">
           		<div class="form-group"  id="order-paid-table">
            		<div class="col-xs-12 col-sm-6 col-md-6">
						<div class="alert alert-success alert-dismissible" role="alert">
						<span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;
						恭喜你！付款成功！<strong>请等待打印件配送！</strong></div>
			        </div>
           		</div>
           		<p>凭下方二维码取件：</p> 
           		<div id="qrcode"></div>
        	</form>
        </div>
    </div>
</div>
<script src="js/jquery.qrcode.min.js"></script>
<script src="js/orderPayTip.js"></script>
<jsp:include page="/jsp/template/footer.jsp"/>