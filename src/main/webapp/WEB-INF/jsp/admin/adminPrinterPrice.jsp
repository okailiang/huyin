<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="u"%>
<%@ taglib uri="http://ustc.sse.a4print/tags/pages" prefix="p"%>
<jsp:include page="/jsp/template/adminHeader.jsp" />
<jsp:include page="/jsp/template/adminSidebar.jsp" />
<div id="pop-div" class="register-div">
    <div id="pop-div-title" class="register-div-descript">
	   <p for="userrole">添加打印价格</p>
	</div>
	<div id="pop-close-div" class="register-div-close">
		<span class="glyphicon glyphicon-remove"
			onclick="javascript:closeDiv()"></span>
	</div>
	<form class="form-horizontal" role="form" method="post">
		<div class="form-group">
			<label for="printer-name" class="col-md-3 control-label">用户名</label>
			<div class="col-md-9">
			<select class="form-control" name="printer-name" id="printer-name" autocomplete="off">
			    <option value ='0'>请选择</option>
			</select>
			</div>
		</div>
		<div class="form-group"> 
		<label for="file-type" class="col-md-3 control-label">文件类型</label>
			<div class="col-md-9 ">
			<select class="form-control" name="file-type" id="filetype-choice" autocomplete="off">
				<option value="1">doc/docx</option>
				<option value="2">ppt/pptx</option>
				<option value="3">pdf</option>
				<option value="4">png/jpg/jpeg</option>
			</select>
			</div>
		</div>
		<div class="form-group"> 
		<label for="price-type" class="col-md-3 control-label">价格类型</label>
			<div class="col-md-9 ">
			<select class="form-control" name="price-type" autocomplete="off" id="pricetype-choice">
				<option value="黑白单面">黑白单面</option>
				<option value="黑白双面">黑白双面</option>
				<option value="彩色单面">彩色单面</option>
				<option value="彩色双面">彩色双面</option>
			</select>
			</div>
		</div>
		<div class="form-group">
			<label for="price" class="col-md-3 control-label">价格</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="price" name="price"
					placeholder="小数点后两位，如 0.00" autocomplete="off">
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-8">
				<button type="button" name="button"
					class="btn btn-primary pull-right" id="admin-add-price">
					<span class="glyphicon glyphicon-plane"></span>&nbsp;&nbsp;确定
				</button>
			</div>
		</div>
	</form>
</div>

<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>打印价格管理</h1>
	</div>
	<div class="order-btn-group">
		<button id="check-all" class="btn btn-warning">
			<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;全选
		</button>
		<button id="admin-del-multi-price" class="btn btn-danger">
			<span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;删除
		</button>
		<button id="add-price" class="btn btn-info"
			onclick="javascript:showDiv()">
			<span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;添加
		</button>
		<form action="searchPrintPrice.do">
			<input
				type="text" name=userName class="form-control" value="${userName}" placeholder="用户名精确查询">
			<input type="hidden" name="currentPage" value="1">
			<button type="submit" id="check-all" class="btn btn-warning">
				<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;搜索
			</button>
			<samp>${errorinfo }</samp>
		</form>
	</div>
	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th></th>
					<th>用户名</th>
					<th>文件类型</th>
					<th>价格类型</th>
					<th>价格</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<u:choose>
					<u:when test="${!empty pricesList}">
						<u:forEach items="${pricesList}" var="item">
							<tr id="tr${item.id}">
								<td id="tr${item.id}td1"><input type="checkbox" value="${item.id}" /></td>
								<td>${item.users.userName}</td>
								<td>${(item.fileType eq 1) ? "doc/docx" :((item.fileType eq 2)?"ppt/pptx":((item.fileType eq 3)?"pdf":"png/jpg/jpeg"))}</td>
								<td>${item.priceType}</td>
								<td>${item.price}</td>
								<td>
									<a href="javascript:void(0)" onclick="editPrice('${item.id}')"
									id="${item.id}"><span class="glyphicon glyphicon-edit" title="编辑"></span></a>
									<a href="javascript:void(0)" onclick="removePrice('${item.id}')"
									id="${item.id}"><span class="glyphicon glyphicon-trash error" title="删除"></span></a>
									
								</td>
							</tr>
						</u:forEach>
					</u:when>
					<u:otherwise>
						<tr>
							<td colspan="8">
								<div style="color:red;text-align:center;margin:0;padding:0;">
									数据库中没有该数据！</div>
							</td>
						</tr>
					</u:otherwise>
				</u:choose>
			</tbody>
		</table>
		<!-- 分页 -->
		<div style="text-align:center;margin:0;padding:0;">
			<p:pager page="${page}" url="${url}" />
		</div>
	</div>
</div>
<div id="back-top">
    	<a href="#" class="b_t"><span class="glyphicon glyphicon-menu-up" ></span></a>
</div>
</div>
</div>
</div>
<script src="js/adminPrice.js"></script>
<script src="js/jquery.keyframes.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />