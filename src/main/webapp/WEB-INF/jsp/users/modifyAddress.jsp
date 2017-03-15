<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp" />
<jsp:include page="/jsp/template/sidebar.jsp" />
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header tab-header">
		<h1>个人信息</h1>
		<ul class="nav nav-tabs">
		<li role="presentation"><a href="jsp/users/modifyInfo.jsp">联系方式</a></li>
		<li role="presentation" class="active"><a href="jsp/users/modifyAddress.jsp">地址管理</a></li>
		<li role="presentation"><a href="jsp/users/modifyPassword.jsp">修改密码</a></li>
		</ul>
	</div>
	<div id="print">
		<form class="form-horizontal" role="form" method="post" id="address-form">
			<div class="form-group">
				<label for="address-choice" class="col-md-2 control-label">已保存地址</label>
				<div class="col-md-10" id="address-list"></div>
			</div>
			<div class="form-group" id="add-address-div">
				<div class="col-md-offset-2 col-md-3">
					<button type="button" class="btn btn-primary" id="add-address-btn">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;添加地址
					</button>
				</div>
			</div>
			<div id="add-address-form" class="hidden">
			<div class="form-group">
				<label for="address-choice" class="col-md-2 control-label" id="address-title">创建新地址</label>
				<div class="col-xs-12 col-sm-2 col-md-2">
					<select class="form-control" id="province" name="province"></select>
				</div>
				<div class="col-xs-12 col-sm-2 col-md-2">
					<select type="text" class="form-control" id="city" name="city"></select>
				</div>
				<div class="col-xs-12 col-sm-2 col-md-2">
					<select class="form-control hidden" id="area" name="area"></select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-xs-12 col-sm-7 col-md-7">
					<input type="text" class="form-control" id="street" name="street" autocomplete="off"
						placeholder="街道地址（不能包含特殊字符）">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-xs-12 col-sm-3 col-md-3">
					<input type="text" class="form-control" id="address-telenumber" autocomplete="off"
						name="address-telenumber" placeholder="联系号码">
				</div>
				<div class="col-md-4">
	             	<div class="alert alert-danger hidden" role="alert" id="alert-address-telenumber">
	             		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	             		<span id="alert-address-telenumber-message"></span>
	             	</div>
	             	<div class="alert hidden" role="alert" id="alert-address-telenumber-ok"></div>
            	</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-xs-12 col-sm-3 col-md-3">
					<input type="text" class="form-control" id="address-username" autocomplete="off"
						name="address-username" placeholder="收件姓名">
				</div>
				<div class="col-md-4">
	             	<div class="alert alert-danger hidden" role="alert" id="alert-address-username">
	             		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	             		<span id="alert-address-username-message"></span>
	             	</div>
	             	<div class="alert hidden" role="alert" id="alert-address-username-ok"></div>
            	</div>
			</div>
			<div class="form-group" id="save-address-submit">
				<div class="col-md-offset-2 col-md-3 form-btn-group">
					<button type="button" class="btn btn-primary" id="save-address-btn">
						<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;保存
					</button>
					<button type="button" class="btn btn-default" id="address-cancel-btn1">
						<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;取消
					</button>
				</div>
			</div>
			<div class="form-group hidden" id="modify-address-submit">
				<div class="col-md-offset-2 col-md-3 form-btn-group">
					<input type="text" class="hidden" id="address-id" autocomplete="off" value=""
						name="address-id">
					<button type="button" class="btn btn-primary" id="modify-address-btn">
						<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;修改
					</button>
					<button type="button" class="btn btn-default" id="address-cancel-btn2">
						<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;取消
					</button>
				</div>
			</div>
			</div>
		</form>
	</div>
</div>
</div>
<script src="js/address.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />