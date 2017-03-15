<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp" />

<div class="row" id="banner">
	<div class="col-xs-12">
		<div class="banner-wrapper">
			<h1>
				<img src="images/icon.png">
			</h1>
			<p>帮助用户实现网络资料打印，文件存储，文件共享，无需U盘，无需零钱，无需排队，自助提取，方便快捷。</p>
			<p class="autor">
				<a href="#">中科大项目支持</a>
			</p>
			<div class="buttons-wrapper">
				<a href="jsp/users/printFile.jsp" class="btn btn-primary">前往打印</a>
				<a href="#" class="btn btn-default button-stripe">了解更多</a>
			</div>
		</div>
	</div>
</div>
<div class="row" id="summary">
	<div class="col-xs-12">
		<div class="row">
			<div class="col-xs-6 col-sm-6 col-md-3 summary-column">
				<img src="images/pin.png" height="32" width="24">
				<h5>地点</h5>
				<p>学校内部固定打印点，远程打印后，随时可以自助提取。</p>
			</div>
			<div class="col-xs-6 col-sm-6 col-md-3 summary-column">
				<img src="images/settings.png" height="32" width="33">
				<h5>自定义</h5>
				<p>文档自行编辑，支持多种不同文档格式，以及支付方式。</p>
			</div>
			<div class="col-xs-6 col-sm-6 col-md-3 summary-column">
				<img src="images/easy.png" height="32" width="34">
				<h5>便捷</h5>
				<p>无需到店排队，联网即可上传打印，省去等待烦恼。</p>
			</div>
			<div class="col-xs-6 col-sm-6 col-md-3 summary-column">
				<img src="images/saturn.png" height="32" width="32">
				<h5>联结</h5>
				<p>未来将会有更多学校加入我们，为同学们提供更加优质服务。</p>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/jsp/template/footer.jsp" />