<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"/>

<div class="row" id="login">
    <div class="col-xs-12">
        <div class="login-wrapper">
            <div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
                <form class="form-horizontal" role="form" method="post" id="login-form" action="user/login">
                    <div class="form-group">
                        <h2>登录
                            <small>如果注册过 wein 的账户，在这里进行登录。</small>
                        </h2>
                        <hr>
                    </div>
                    <div class="form-group">
                        <label for="login-email" class="col-md-3 control-label">邮箱</label>

                        <div class="col-md-4">
                            <input type="text" class="form-control" id="login-email" name="account"
                                   placeholder="注册时填写的邮箱" autocomplete="on" required autofocus>
                        </div>
                        <div class="col-md-5">
                            <c:choose>
                                <c:when test="${!empty errorinfo}">
                                    <div class="alert alert-danger" role="alert" id="alert-login-email">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
                                        <span id="alert-login-email-message">${errorinfo}</span>
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
                        <label for="login-password" class="col-md-3 control-label">密码</label>

                        <div class="col-md-4">
                            <input type="password" class="form-control" id="login-password" name="password"
                                   placeholder="密码长度不小于6位" autocomplete="off" required>
                        </div>
                        <div class="col-md-5">
                            <div class="alert alert-danger hidden" role="alert" id="alert-login-password">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
                                <span id="alert-login-password-message"></span>
                            </div>
                            <div class="alert hidden" role="alert" id="alert-login-password-ok"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="kaptchaCode" class="col-md-3 control-label">验证码</label>

                        <div class="col-md-4">
                            <input type="hidden" id="kaptchaFlag" name="kaptchaFlag" value="1">
                            <input type="hidden" id="type" name="type" value="1">
                            <input type="text" class="form-control" id="kaptchaCode" name="kaptchaCode"
                                   placeholder="验证码" autocomplete="off" required>
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
                        <label> <input type="checkbox" id="checkFlag"
                                       name="checkFlag"> 记住账号3天
                        </label>
                            </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-4">
                            <img title="点击更换" id="kaptchaId" alt="验证码" src="kaptcha" onclick="kaptchaLoad()">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-7 form-btn-group">
                            <button type="submit" name="submit" class="btn btn-primary pull-left" id="submit-login">
                                <span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;登录
                            </button>
                            <a class="btn btn-default pull-left" href="anonymouslogin.do?anonymous=anonymous">
                                <span class="glyphicon glyphicon-sunglasses"></span>&nbsp;&nbsp;匿名</a>
                            <a href="jsp/users/register.jsp" class="btn btn-link pull-left">注册</a>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    function kaptchaLoad() {
        $("#kaptchaId").attr('src', $("#kaptchaId").attr("src") + "?nocache=" + new Date().getTime());
    }
</script>
<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
        a = s.createElement(o),
                m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

    ga('create', ${googleAnalyticsId}, 'auto');
    ga('send', 'pageview');

</script>
<jsp:include page="/WEB-INF/jsp/template/footer.jsp"/>