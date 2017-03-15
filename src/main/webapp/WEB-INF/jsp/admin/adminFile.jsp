<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/WEB-INF/jsp/template/adminHeader.jsp" />
<jsp:include page="/WEB-INF/jsp/template/adminSidebar.jsp" />
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>文件信息</h1>
	</div>
	<div class="order-btn-group">
		<button id="check-all" class="btn btn-warning">
			<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;全选
		</button>
		<button id="del-file-all" class="btn btn-danger"><span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;删除</button>
		<form class="form-horizontal" role="form" method="post"
			id="searchFile-form" action="searchfilesbyparam.do">
			<input type="date" class="form-control" placeholder="date"
				name="beforeTime" value="${beforeTime }"> <input type="date" class="form-control"
				placeholder="date" name="afterTime" value="${afterTime }"> <input type="text"
				class="form-control" placeholder="请入用户名查询" name="userName" value="${userName }">
				<input type="hidden" name="currentPage" value="1">
			<button type="submit" class="btn btn-info">
				<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;搜索
			</button>
		</form>
	</div>
	<div class="table-responsive">
		<table class="table table-striped" id="file-table">
			<thead>
				<tr>
					<th></th>
					<th>用户名</th>
					<th>文件名</th>
					<th>类型</th>
					<th>大小</th>
					<th>时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${!empty fileList}">
						<c:forEach items="${fileList}" var="item">
							<tr id="trd${item.id }">
								<td><input type="checkbox" value="${item.id}" /></td>
								<td title="${item.users.userName}">${item.users.userName}</td>
								<td title="${item.fileName}">${item.fileName}</td>
								<td title="${item.fileType}">${item.fileType}</td>
								<td title="${item.fileSize}">${item.fileSize}</td>
								<td title="${item.uploadTime}"><fmt:formatDate value="${item.uploadTime}" type="both"/></td>
								<td>
								
									
									<a href="javaScript:void(0)" onclick="removeFile('${item.id}')" id="${item.id}">
									<span class="glyphicon glyphicon-trash error" title="删除"></span></a>
									
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="7">
								<div style="color:red;text-align:center;margin:0;padding:0;">
									用户还没有上传要打印文件！</div>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>

			</tbody>
		</table>
		<!-- 分页 -->
	</div>
	<div id="page-div" style="text-align:center;margin:0;padding:0;"  value="${url}">
		<c:if test="${!empty fileList}">
			<p:pager page="${page}" url="${url}" />
		</c:if>
	</div>
</div>
</div>
<script src="js/adminFile.js"></script>
<jsp:include page="/WEB-INF/jsp/template/footer.jsp" />