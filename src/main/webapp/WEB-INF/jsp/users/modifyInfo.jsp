<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp" />
<jsp:include page="/jsp/template/sidebar.jsp" />
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header tab-header">
		<h1>个人信息</h1>
		<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="jsp/users/modifyInfo.jsp">联系方式</a>
		</li>
		<li role="presentation"><a href="jsp/users/modifyAddress.jsp">地址管理</a>
		</li>
		<li role="presentation"><a href="jsp/users/modifyPassword.jsp">修改密码</a>
		</li>
		</ul>
	</div>
	<form class="form-horizontal" role="modify-form" method="post"
		id="modify-form" action="updateemailandtelenumber.do">
		<div class="form-group">
			<label for="modify-email" class="col-md-2 control-label">邮箱</label>
			<div class="col-md-4">
				<input type="text" class="form-control" id="modify-email"
					name="modify-email" value="${email}" autocomplete="off">
			</div>
			<div class="col-md-4">
				<div class="alert alert-danger hidden" role="alert"
					id="alert-modify-email">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
					<span id="alert-modify-email-message"></span>
				</div>
				<div class="alert hidden" role="alert" id="alert-modify-email-ok"></div>
			</div>
		</div>
		<div class="form-group">
			<label for="modify-telenumber" class="col-md-2 control-label">手机号码</label>
			<div class="col-md-4">
				<input type="text" class="form-control" id="modify-telenumber"
					name="modify-telenumber" value="${teleNumber}" autocomplete="off">
			</div>
			<div class="col-md-4">
				<div class="alert alert-danger hidden" role="alert"
					id="alert-modify-telenumber">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
					<span id="alert-modify-telenumber-message"></span>
				</div>
				<div class="alert hidden" role="alert"
					id="alert-modify-telenumber-ok"></div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-2 col-md-4 form-btn-group">
				<button type="submit" name="submit"
					class="btn btn-primary button-stripe" id="modify-submit">
					<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;保存信息
				</button>
				<samp>${errorinfo }</samp>
			</div>
		</div>
	</form>
</div>
</div>
<jsp:include page="/jsp/template/footer.jsp" />