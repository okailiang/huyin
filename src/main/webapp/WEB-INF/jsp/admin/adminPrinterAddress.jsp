<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="u"%>
<%@ taglib uri="http://ustc.sse.a4print/tags/pages" prefix="p"%>
<jsp:include page="/jsp/template/adminHeader.jsp" />
<jsp:include page="/jsp/template/adminSidebar.jsp" />
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>打印地址管理</h1>
	</div>
	<div class="order-btn-group">
		<span class="btn btn-info">
			<a href="jsp/admin/adminAddPrinter.jsp"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;添加</a>
		</span>
	</div>
	<div class="row" id="printer-list"></div>
</div>
<div id="back-top">
    	<a href="#" class="b_t"><span class="glyphicon glyphicon-menu-up" ></span></a>
</div>
</div>
<script src="js/adminPrinterAddress.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />