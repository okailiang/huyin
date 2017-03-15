<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp"/>

<div class="row" id="login">
    <div class="col-xs-12">
        <div class="login-wrapper">
            <div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
                    <div class="form-group">
	                    <h1>权限访问错误 <small></small></h1>
	                    <hr>
                    </div>
                    <div class="form-group">
	                    <p>您无权限访问该页面！</p>
	                    <p><a href="javascript:history.back(-1);">返回上一页</a></p>
	                    <p><a href="jsp/users/index.jsp">返回首页</a></p>
                    </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsp/template/footer.jsp"/>
