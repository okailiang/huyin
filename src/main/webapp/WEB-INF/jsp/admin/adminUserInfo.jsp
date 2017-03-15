<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://ustc.sse.a4print/tags/pages" prefix="p"%>
<jsp:include page="/jsp/template/adminHeader.jsp" />
<jsp:include page="/jsp/template/adminSidebar.jsp" />
<div id="pop-div" class="register-div">
         
    <div id="pop-close-div" class="register-div-descript">
	   <p for="userrole">添加用户</p>
	</div>
	<div id="pop-close-div" class="register-div-close">
	
		<span class="glyphicon glyphicon-remove"
			onclick="javascript:closeDiv()"></span>
	</div>
	
	<form class="form-horizontal" role="form" method="post"
		action="adminAddUser.do">
		
		<div class="form-group">
			<label for="username" class="col-md-3 control-label">用户名</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="username"
					name="username" placeholder="输入用户名" autocomplete="off">
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-md-3 control-label">邮箱</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="email" name="email"
					placeholder="输入邮箱地址" autocomplete="off">
			</div>
		</div>
		<div class="form-group">
			<label for="telenumber" class="col-md-3 control-label">手机号码</label>
			<div class="col-md-9">
				<input type="text" class="form-control" id="telenumber"
					name="telenumber" placeholder="输入手机号码" autocomplete="off">
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-md-3 control-label">密码</label>
			<div class="col-md-9">
				<input type="password" class="form-control" id="password"
					name="password" placeholder="密码长度不小于6位" autocomplete="off">
			</div>
		</div>
		<div class="form-group">
			<label for="repassword" class="col-md-3 control-label">确认密码</label>
			<div class="col-md-9">
				<input type="password" class="form-control" id="repassword"
					name="repassword" placeholder="密码长度不小于6位" autocomplete="off">
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-8">
				<button type="submit" name="submit"
					class="btn btn-primary pull-right" id="add-submit">
					<span class="glyphicon glyphicon-plane"></span>&nbsp;&nbsp;确定
				</button>
			</div>
		</div>
	</form>
</div>

<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>用户信息</h1>
	</div>
	<div class="order-btn-group">
		<button id="check-all" class="btn btn-warning">
			<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;全选
		</button>
		<button id="del-all" class="btn btn-danger">
			<span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;删除
		</button>
		<button  class="btn btn-info"
			onclick="javascript:showDiv()">
			<span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;添加
		</button>
		<form action="userInfoSearch.do">
			<input type="date" name="pre-datetime" value="${pretime }"
				class="form-control" placeholder="Search">
			<input type="date" name="aft-datetime" value="${afttime }"
				class="form-control" placeholder="Search"> <input
				type="text" name="username" class="form-control" placeholder="用户名">
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
					<th>密码</th>
					<th>邮箱</th>
					<th>电话</th>
					<th>注册时间</th>					
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<u:choose>
					<u:when test="${!empty usersList}">
						<u:forEach items="${usersList}" var="item">
							<tr id="tr${item.id}">
								<td id="tr${item.id}td1"><input type="checkbox" value="${item.id}" /></td>
								<td>${item.userName}</td>
								<td>******</td>
								<td>${item.email}</td>
								<td>${item.teleNumber}</td>
								<td title="${item.createTime}"><fmt:formatDate value="${item.createTime}" type="both"/></td>
								<td>
									<a href="javascript:void(0)" onclick="modifyUser(this)"
									id="${item.id}"><span class="glyphicon glyphicon-edit" title="编辑"></span></a>
									<a href="javascript:void(0)" onclick="removeUser(this)"
									id="${item.id}"><span class="glyphicon glyphicon-trash error" title="删除"></span></a>
								</td>
							</tr>
						</u:forEach>
					</u:when>
					<u:otherwise>
						<tr>
							<td colspan="8">
								<div style="color:red;text-align:center;margin:0;padding:0;">
									数据库中没有用户的注册信息！</div>
							</td>
						</tr>
					</u:otherwise>
				</u:choose>
			</tbody>
		</table>
	</div>
	<!-- 分页 -->
	<div id="page-div" style="text-align:center;margin:0;padding:0;" value="${url}">
		<p:pager page="${page}" url="${url}" />
	</div>
</div>
</div>
</div>
</div>
<script src="js/loadUser.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />