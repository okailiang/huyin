<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/jsp/template/adminHeader.jsp" />
<div class="row" id="login">
	<div class="col-xs-12">
		<div class="login-wrapper">
			<div class="col-xs-12 col-sm-10 col-md-10">
				<form class="form-horizontal" role="form" method="post" action="adminlogin.do" id="admin-login-form">
					<div class="form-group">
						<div class="col-md-offset-2">
							<h1>管理员登录</h1>
							<hr>
						</div>
					</div>
					<div class="form-group">
                        <label for="login-email" class="col-md-4 control-label">邮箱</label>
                        <div class="col-md-4">
                        <input type="text" class="form-control" id="login-email" name="login-email" placeholder="注册时填写的邮箱" autocomplete="off">
                        </div>
                        <div class="col-md-4">
                        	<c:choose>
							<c:when test="${!empty adminlogincheck}">
	                    	<div class="alert alert-danger" role="alert" id="alert-login-email">
	                    		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	                    		<span id="alert-login-email-message">${adminlogincheck}</span>
	                    	</div>
	                    	</c:when>
							<c:otherwise>
	                    	<div class="alert alert-danger hidden" role="alert" id="alert-login-email">
	                    		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	                    		<span id="alert-login-email-message"></span>
	                    	</div>
	                    	</c:otherwise>
	                    	</c:choose>
	                    	<div class="alert hidden" role="alert" id="alert-login-email-ok"></div>
	                    </div>
                    </div>
                    <div class="form-group">
                        <label for="login-password" class="col-md-4 control-label">密码</label>
                        <div class="col-md-4">
                        <input type="password" class="form-control" id="login-password" name="login-password" placeholder="密码长度不小于6位" autocomplete="off">
                        </div>
                        <div class="col-md-4">
	                    	<div class="alert alert-danger hidden" role="alert" id="alert-login-password">
	                    		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	                    		<span id="alert-login-password-message"></span>
	                    	</div>
	                    	<div class="alert hidden" role="alert" id="alert-login-password-ok"></div>
	                    </div>
                    </div>
                    <div class="form-group">
                    	<label for="random-code" class="col-md-4 control-label">验证码</label>
                    	<div class="col-md-4">
                        <input type="text" class="form-control" id="random-code" name="random-code" placeholder="请输入验证码" autocomplete="off">
                        </div>
                        <div class="col-md-4">
	                    	<div class="alert alert-danger hidden" role="alert" id="alert-vcode">
	                    		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	                    		<span id="alert-vcode-message"></span>
	                    	</div>
	                    	<div class="alert hidden" role="alert" id="alert-vcode-ok">
	                    		<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;&nbsp;
	                    	</div>
	                    </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-4">
                        <img id="vimg"  title="点击更换" src="servlet/AuthImageServlet">
                        </div>
                    </div>
					<div class="form-group">
						<div class="col-md-offset-4 col-md-4 form-btn-group">
							<button type="submit" name="submit"
								class="btn btn-primary pull-right button-stripe" id="admin-login-submit">
								<span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;登录
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</div>
</div>
<jsp:include page="/jsp/template/footer.jsp" />