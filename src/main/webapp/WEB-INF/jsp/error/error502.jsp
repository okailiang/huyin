<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp"/>

<div class="row" id="login">
    <div class="col-xs-12">
        <div class="login-wrapper">
            <div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
                    <div class="form-group">
	                    <h1>502错误 <small>网关出错</small></h1>
	                    <hr>
                    </div>
                    <div class="form-group">
	                    <p>当用作网关或代理时，服务器将从试图实现此请求时所访问的upstream 服务器中接收无效的响应。</p>
	                    <p>如果问题依然存在，请与服务器的管理员联系。</p>
	                    <p><a href="jsp/users/index.jsp">返回首页</a></p>
                    </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/jsp/template/footer.jsp"/>
