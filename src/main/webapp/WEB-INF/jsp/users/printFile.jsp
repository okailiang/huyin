<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp" />
<jsp:include page="/jsp/template/sidebar.jsp" />
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>打印文件</h1>
		<p>
			<span class="label label-success"> 上传文件</span> → <span></span><span>提交订单</span>
			→ <span>在线支付</span> → <span>完成订单</span>
		</p>
	</div>
	<div id="print">
		<form class="form-horizontal">
			<div class="form-group">
				<label for="upload" class="col-md-2 control-label">上传文件</label>
				<div class="col-md-7">
					<p class="print-tip">目前只能累计上传 1G 大小的文件，支持 pdf、docx、doc 格式文件。
					<br><strong>温馨提示：</strong>docx、doc、ppt、pptx格式文件转换为 pdf打印效果更佳。
					<span class="alert alert-danger"><a href="https://smallpdf.com" target="_blank">pdf在线转换</a></span>
					<br>如果您使用时遇到页面解析错误，请先把您的文档转化为PDF格式，以确保正确进行打印。
					</p>
					<div id="uploadFile" class="uploadFile"></div>
				</div>
			</div>
		</form>
		<form class="form-horizontal" role="form" method="post" id="file-form"
			action="getFilesByIds.do">
			<div id="fileIds"></div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-7 form-btn-group">
					<button type="submit" name="submit"
						class="btn btn-primary pull-right button-stripe" id="file-submit" >
						下一步&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-right"></span>
					</button>
				</div>
			</div>
		</form>
	</div>
</div>
</div>
<!-- 上传文件 -->
<!-- 引用核心层插件 -->
<script src="js/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script src="js/zyUpload.js"></script>
<!-- 引用初始化JS -->
<script src="js/uploadFile.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />