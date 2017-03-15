<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp"/>
<div class="row" id="register">
    <div class="col-xs-12">
        <div class="register-wrapper">
            <div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
                <form class="form-horizontal" role="form" method="post" action="userReg.do" id="register-form">
	                <div class="form-group">
                        <h1>注册 <small>欢迎加入 A4print，注册账户来享受我们的服务。</small></h1>
                        <hr>
	                </div>
	                <div class="form-group">
	                    <label for="username" class="col-md-3 control-label">用户名</label>
	                    <div class="col-md-4">
	                    <input type="text" class="form-control" id="username" name="username" placeholder="输入用户名" autocomplete="off">
	                    </div>
	                    <div class="col-md-5">
	                    	<div class="alert alert-danger hidden" role="alert" id="alert-username">
	                    		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	                    		<span id="alert-username-message"></span>
	                    	</div>
	                    	<div class="alert hidden" role="alert" id="alert-username-ok"></div>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="telenumber" class="col-md-3 control-label">手机号码</label>
	                    <div class="col-md-4">
	                    <input type="text" class="form-control" id="telenumber" name="telenumber" placeholder="输入手机号码" autocomplete="off">
	                    </div>
	                    <div class="col-md-5">
	                    	<div class="alert alert-danger hidden" role="alert" id="alert-telenumber">
	                    		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	                    		<span id="alert-telenumber-message"></span>
	                    	</div>
	                    	<div class="alert hidden" role="alert" id="alert-telenumber-ok"></div>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="email" class="col-md-3 control-label">邮箱</label>
	                    <div class="col-md-4">
	                    <input type="text" class="form-control" id="email" name="email" placeholder="输入邮箱地址" autocomplete="off">
	                    </div>
	                    <div class="col-md-5">
	                    	<div class="alert alert-danger hidden" role="alert" id="alert-email">
	                    		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	                    		<span id="alert-email-message"></span>
	                    	</div>
	                    	<div class="alert hidden" role="alert" id="alert-email-ok"></div>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="password" class="col-md-3 control-label">密码</label>
	                    <div class="col-md-4">
	                    <input type="password" class="form-control" id="password" name="password" placeholder="密码长度不小于6位" autocomplete="off">
	                    </div>
	                    <div class="col-md-5">
	                    	<div class="alert alert-danger hidden" role="alert" id="alert-password">
	                    		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	                    		<span id="alert-password-message"></span>
	                    	</div>
	                    	<div class="alert hidden" role="alert" id="alert-password-ok"></div>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label for="repassword" class="col-md-3 control-label">确认密码</label>
	                    <div class="col-md-4">
	                    <input type="password" class="form-control" id="repassword" name="repassword" placeholder="密码长度不小于6位" autocomplete="off">
	                    </div>
	                    <div class="col-md-5">
	                    	<div class="alert alert-danger hidden" role="alert" id="alert-repassword">
	                    		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	                    		<span id="alert-repassword-message"></span>
	                    	</div>
	                    	<div class="alert hidden" role="alert" id="alert-repassword-ok"></div>
	                    </div>
	                </div>
	                <div class="form-group">
                    	<label for="random-code" class="col-md-3 control-label">验证码</label>
                    	<div class="col-md-4">
                        <input type="text" class="form-control" id="random-code" name="random-code" placeholder="请输入验证码" autocomplete="off">
                        </div>
                        <div class="col-md-5">
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
                        <div class="col-md-offset-3 col-md-4">
                        <img id="vimg"  title="点击更换" src="servlet/AuthImageServlet">
                        </div>
                    </div>
	                <div class="form-group">
	                    <div class="col-md-7 form-btn-group">
	                    <button type="submit" name="submit" class="btn btn-primary pull-right button-stripe" id="register-submit">
	                    <span class="glyphicon glyphicon-plane"></span>&nbsp;&nbsp;立即注册</button>
	                    <samp>${errorinfo }</samp>
	                    </div>
	                </div>
	            </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsp/template/footer.jsp"/>