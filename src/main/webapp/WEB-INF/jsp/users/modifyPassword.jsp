<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/jsp/template/header.jsp"/>
<jsp:include page="/jsp/template/sidebar.jsp" />
    <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
      <div class="page-header tab-header">
		<h1>个人信息</h1>
		<ul class="nav nav-tabs">
		<li role="presentation"><a href="jsp/users/modifyInfo.jsp">联系方式</a>
		</li>
		<li role="presentation"><a href="jsp/users/modifyAddress.jsp">地址管理</a>
		</li>
		<li role="presentation" class="active"><a href="jsp/users/modifyPassword.jsp">修改密码</a>
		</li>
		</ul>
	</div>
      <form class="form-horizontal" role="form" method="post" action="changepwd.do" id="change-password-form">
        <div class="form-group">
            <label for="pwd" class="col-md-2 control-label">旧密码</label>
            <div class="col-md-4">
            <input type="password" class="form-control" id="pwd" name="pwd" placeholder="确认身份">
            </div>
            <div class="col-md-4">
				<c:choose>
				<c:when test="${empty changepwd}">
               	<div class="alert alert-danger hidden" role="alert" id="alert-pwd">
               		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
               		<span id="alert-pwd-message"></span>
               	</div>
               	<div class="alert hidden" role="alert" id="alert-pwd-ok"></div>
               	</c:when>
				<c:when test="${changepwd eq '修改成功！'}">
				<div class="alert alert-danger hidden" role="alert" id="alert-pwd">
               		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
               		<span id="alert-pwd-message"></span>
               	</div>
               	<div class="alert" role="alert" id="alert-pwd-ok">
               		<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp;&nbsp;
               		<span id="alert-login-email-message">${changepwd}</span>
               	</div>
               	</c:when>
				<c:otherwise>
				<div class="alert alert-danger" role="alert" id="alert-pwd">
               		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
               		<span id="alert-pwd-message">${changepwd}</span>
               	</div>
               	<div class="alert hidden" role="alert" id="alert-pwd-ok"></div>
               	</c:otherwise>
               	</c:choose>
        	</div>
        </div>
        <div class="form-group">
            <label for="newpwd" class="col-md-2 control-label">新密码</label>
            <div class="col-md-4">
            <input type="password" class="form-control" id="newpwd" name="newpwd" placeholder="输入要修改的密码">
            </div>
            <div class="col-md-4">
             	<div class="alert alert-danger hidden" role="alert" id="alert-newpwd">
             		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
             		<span id="alert-newpwd-message"></span>
             	</div>
             	<div class="alert hidden" role="alert" id="alert-newpwd-ok"></div>
             </div>
        </div>
        <div class="form-group">
            <label for="repwd" class="col-md-2 control-label">确认密码</label>
            <div class="col-md-4">
            <input type="password" class="form-control" id="repwd" name="repwd" placeholder="确认要修改的密码">
            </div>
            <div class="col-md-4">
             	<div class="alert alert-danger hidden" role="alert" id="alert-repwd">
             		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
             		<span id="alert-repwd-message"></span>
             	</div>
             	<div class="alert hidden" role="alert" id="alert-repwd-ok"></div>
             </div>
        </div>
        <div class="form-group">
            <div class="col-md-offset-2 col-md-4 form-btn-group">
            <button type="submit" name="submit" class="btn btn-primary button-stripe" id="change-password-submit">
            <span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;确认修改</button>
            </div>
        </div>
    </form>
    </div>
</div>
<jsp:include page="/jsp/template/footer.jsp"/>