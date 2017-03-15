<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://ustc.sse.a4print/tags/pages" prefix="p"%>
<jsp:include page="/jsp/template/adminHeader.jsp" />
<jsp:include page="/jsp/template/adminSidebar.jsp" />
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>反馈信息</h1>
	</div>
	<div class="table-responsive">
		<table class="table table-striped" id="file-table">
			<thead>
				<tr>
					<th>用户名</th>
					<th>反馈时间</th>
					<th>反馈信息</th>
					<th>操作</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			  <c:choose>
					<c:when test="${!empty feedInfoList}">
						<c:forEach items="${feedInfoList}" var="item">
							<tr>
								</td>
								<td title="${item.users.userName}">${item.users.userName}</td>
								<td title="${item.feedTime}"><fmt:formatDate value="${item.feedTime}" type="both"/></td>
								<td title="<c:out value="${item.feedInfo}" />"><c:out value="${item.feedInfo}" /> </td>
								<td>
									<a class="del-feedInfo" value="${item.id}"
									href="removeFeedInfo.do?id=${item.id}">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6">
								<div style="color:red;text-align:center;margin:0;padding:0;">
									目前还没有用户反馈信息！</div></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>
</div>
<jsp:include page="/jsp/template/footer.jsp" />