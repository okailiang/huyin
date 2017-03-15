<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp" />
<jsp:include page="/jsp/template/sidebar.jsp" />
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>用户反馈</h1>
	</div>
	<div class="col-xs-12 col-sm-10 col-md-7" id="feedback-form">
		<form class="form-horizontal" role="form" method="post" id="feedback-info-form" >
			<div class="form-group" id="feedback-info">
				<textarea class="form-control" rows="4" name="feedInfo"
					placeholder="请输入您要反馈的信息，谢谢！"></textarea>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-warning pull-right" id="feedInfo-submit">
					<span class="glyphicon glyphicon-send"></span>&nbsp;&nbsp;提交
				</button>
			</div>
		</form>
	</div>
</div>
</div>
<script src="js/feedInfo.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />