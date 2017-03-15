<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp"/>

<div class="row" id="login">
    <div class="col-xs-12">
        <div class="login-wrapper">
            <div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
                    <div class="form-group">
	                    <h1>500错误 <small>服务器的内部错误</small></h1>
	                    <hr>
                    </div>
                    <div class="form-group">
	                    <p>Web 服务器不能执行此请求。请稍后重试此请求。</p>
	                    <p>如果问题依然存在，请与服务器的管理员联系。</p>
	                    <p><a href="jsp/users/index.jsp">返回首页</a></p>
                    </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsp/template/footer.jsp"/>
