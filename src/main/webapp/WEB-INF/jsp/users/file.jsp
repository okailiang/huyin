<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://ustc.sse.a4print/tags/pages" prefix="p"%>
<jsp:include page="/jsp/template/header.jsp" />
<jsp:include page="/jsp/template/sidebar.jsp" />
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>文件管理</h1>
	</div>
	<div class="order-btn-group">
		<button id="check-all" class="btn btn-warning">
			<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;全选
		</button>
		<button id="del-select-file" class="btn btn-danger">
			<span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp; 删除
		</button>
		<form class="form-horizontal" role="form" method="post"
			id="print-select-file-form" action="getFilesByIds.do">
			<button id="print-select-file" class="btn btn-info" data-loading-text="Loading...">
			<span class="glyphicon glyphicon-paperclip"></span>&nbsp;&nbsp; 打印
		    </button>
		    <div class="form-group hidden" id="select-print-fileid">
			</div>
		</form>
		<form class="form-horizontal" role="form" method="post"
			id="searchFile-form" action="searchUserFiles.do">
			<div id="advanced-search-panel" style="display:inline-block;">
				<input type="date" class="form-control" placeholder="date"
					name="beforeUploadDate" value="${beforeUploadDate}"> 
				<input type="date" class="form-control" placeholder="date" 
				    name="afterUploadDate" value="${afterUploadDate}">
			</div>
			<input type="text" class="form-control" placeholder="请入文件名查询"
				   name="fileName" value="${fileName}">
			<button type="submit" id="check-all" class="btn btn-warning" >
				<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;搜索
			</button>
			<span id="expand-advanced-search"
				class="glyphicon glyphicon-chevron-right" title="点击展开，可以多条件查询"></span>
		</form>
	</div>
	<div class="table-responsive">
	<table class="table table-striped">
		<thead>
			<tr>
				<th></th>
				<th>文件名</th>
				<th>类型</th>
				<th>大小</th>
				<th>页数</th>
				<th>上传时间</th>
				<th>打印</th>
				<th>删除</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${!empty fileList}">
					<c:forEach items="${fileList}" var="item">
						<tr>
							<td><input type="checkbox" value="${item.id}" />
							</td>
							<td title="${item.fileName}">${item.fileName}</td>
							<td title="${item.fileType}">${item.fileType}</td>
							<td title="${item.fileSize}">${item.fileSize}</td>
							<td title="${item.filePages}">${item.filePages}</td>
							<td title="${item.uploadTime}"><fmt:formatDate value="${item.uploadTime}" type="both"/></td>
							<td><a id="print-file" class="print-file" 
							      value="${item.id}"
								href="getFilesByIds.do?fileId=${item.id}"><span class="glyphicon glyphicon-paperclip" title="打印"></span></a>
							</td>
							<td>
								<a class="removeFileByIdUser" 
							      value="${item.id}"
								href="javascript:void(0);"><span class="glyphicon glyphicon-trash error" title="删除"></span></a>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="8" style="text-align:center;" class="error">您还没有上传要打印文件，请上传要打印的文件！</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	</div>
	<!-- 分页 -->
	<div id="page-div" style="text-align:center;margin:0;padding:0;" value="${url}">
		<c:if test="${!empty fileList}">
			<p:pager page="${page}" url="${url}" />
		</c:if>
	</div>
</div>
</div>
<script src="js/fileOperate.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />