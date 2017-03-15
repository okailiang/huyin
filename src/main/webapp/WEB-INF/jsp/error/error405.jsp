<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp"/>

<div class="row" id="login">
    <div class="col-xs-12">
        <div class="login-wrapper">
            <div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
                    <div class="form-group">
	                    <h1>405错误 <small>请求方法不允许</small></h1>
	                    <hr>
                    </div>
                    <div class="form-group">
	                    <p>Web服务器不允许您的请求。请检查URL以确保路径正确。</p>
	                    <p>如果问题依然存在，请与服务器的管理员联系。</p>
	                    <p><a href="jsp/users/index.jsp">返回首页</a></p>
                    </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsp/template/footer.jsp"/>