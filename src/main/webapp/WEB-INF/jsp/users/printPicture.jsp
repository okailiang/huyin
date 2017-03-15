<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp" />
<jsp:include page="/jsp/template/sidebar.jsp" />
<link rel="stylesheet" href="css/jquery.Jcrop.min.css">
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>打印图片</h1>
		<p>
			<span class="label label-success"> 上传图片</span> → <span></span><span>提交订单</span>
			→ <span>在线支付</span> → <span>完成订单</span>
		</p>
	</div>
	<div id="print">
		<form class="form-horizontal" role="form" method="post" id="picture-form">
			<div class="form-group">
				<label for="upload" class="col-md-2 control-label">上传图片</label>
				<div class="col-md-10">
					<p class="print-tip">从电脑中选择你喜欢的照片：<br>你可以上传 JPG、JPEG 或者 PNG 文件。</p>
					<div class="upload-picture-btn btn btn-sm btn-default">
						<span class="glyphicon glyphicon-picture" aria-hidden="true"></span> 选择图片
						<input class="upload" id="upload-picture-input" accept="image/jpeg,image/x-png" name="avatar" type="file">
					</div>
					<div class="size-choose-btn btn btn-sm">
						<span class="glyphicon glyphicon-resize-full" aria-hidden="true"></span> 选择尺寸：
						<label class="radio-inline">
						  <input type="radio" class="picture-size" name="picture-size" id="inch1" value="inch1" checked> 一寸
						</label>
						<label class="radio-inline">
						  <input type="radio" class="picture-size" name="picture-size" id="inch2" value="inch2"> 两寸
						</label>
						<label class="radio-inline">
						  <input type="radio" class="picture-size" name="picture-size" id="inch5" value="inch5"> 五寸
						</label>
						<label class="radio-inline">
						  <input type="radio" class="picture-size" name="picture-size" id="inch10" value="inch10"> 十寸
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-6">
					<img id="base-picture" src="/A4print/images/blank.png">
				</div>
				<div id="preview-picture">
					<div id="preview-pane">
			          <div class="preview-container">
			            <img src="" class="jcrop-preview">
			          </div>
			        </div>
				</div>
			</div>
		</form>
		<form class="form-horizontal" role="form" method="post" id="file-form"
			action="getFilesByIds.do">
			<input id="fileIds" type="text" class="hidden" value="" name="fileId">
			<div class="form-group">
				<div class="col-md-offset-2 col-md-5 form-btn-group">
					<span class="btn btn-primary button-stripe" id="picture-submit">裁剪并上传</span>
				</div>
			</div>
		</form>
	</div>
</div>
</div>
<script src="js/jquery.Jcrop.min.js"></script>

<script>
// jcrop_api 裁剪框接口，boundx、boundy 图片展示框实际长宽
// file、reader、image、url 读取上传图片数据
// x、y、w、h 坐标及长宽, ratio 图片缩放比例
// size 裁剪框长宽比例
var jcrop_api, boundx, boundy, file, reader, image, url, x, y, w, h, ratio, size = 1;

// 获取预览框元素
var $preview = $('#preview-pane'),
$pcnt = $('#preview-pane .preview-container'),
$pimg = $('#preview-pane .preview-container img'),
// xszie、ysize 预览框长宽
xsize = $pcnt.width(),
ysize = $pcnt.height();

// 修改预览框，调整裁剪尺寸
function updatePreview(c){
	if (parseInt(c.w) > 0) {
		var rx = xsize / c.w;
		var ry = ysize / c.h;
		x = ratio * c.x;
		y = ratio * c.y;
		w = ratio * c.w;
		h = ratio * c.h;
		$pimg.css({
			width: Math.round(rx * boundx) + 'px',
			height: Math.round(ry * boundy) + 'px',
			marginLeft: '-' + Math.round(rx * c.x) + 'px',
			marginTop: '-' + Math.round(ry * c.y) + 'px'
		});
	}
};

function setSize(width,height){
	$pcnt.css({width:width+'px',height:height+'px'});
	xsize = $pcnt.width(),
	ysize = $pcnt.height();
}

// 初始化 Jcrop 插件
function initJcrop(){
	$("#base-picture").Jcrop({
       		onChange: updatePreview,
  			onSelect: updatePreview,
  			aspectRatio: xsize / ysize
       },function(){
       	// 获取图片展示框的实际尺寸
	    var bounds = this.getBounds();
	    boundx = bounds[0];
	    boundy = bounds[1];
	    ratio = image.width/boundx;
	    jcrop_api = this;
	    jcrop_api.animateTo([10, 10, xsize+10, ysize+10]);
	    $preview.appendTo(jcrop_api.ui.holder);
	    $pimg.attr("src",url);
  	});
};

// 进行裁剪
function getClippedPicture(){
	// 新建 Canvas 元素，通过 drawImage 函数来进行裁剪
	var canvas = $('<canvas width="'+w+'" height="'+h+'"></canvas>')[0];
    var ctx = canvas.getContext('2d');
    ctx.drawImage(image,x,y,w,h,0,0,w,h);
  	var data=canvas.toDataURL();
	// dataURL 的格式为 “data:image/png;base64,****”,逗号之前都是一些说明性的文字，我们只需要逗号之后的就行了
	data=data.split(',')[1];
	data=window.atob(data);
	var ia = new Uint8Array(data.length);
	for (var i = 0; i < data.length; i++) {
	    ia[i] = data.charCodeAt(i);
	};
	// canvas.toDataURL 返回的默认格式就是 image/png
	var blob = new Blob([ia], {type:"image/png"});
	var fd = new FormData();
	fd.append('file',blob);
	fd.append('imageSize',size);
	
	var xhr = new XMLHttpRequest();
	
	xhr.open("POST","addUploadFiles.do", true);
	xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
	xhr.send(fd);
	
	xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var pictureInfo = JSON.parse(xhr.responseText);
            var pid = pictureInfo["result"][0]["id"];
            $("#fileIds").val(pid);
            $("#file-form").submit();
        }
    }
};

$(document).ready(function(){
	// 上传图片之后，进行预览
	$("#upload-picture-input").change(function(){
		file = this.files[0];
	    reader = new FileReader();
	    reader.onload=function(){
	    	// 通过 reader.result 来访问生成的 DataURL
	        url = reader.result;
	        $("#base-picture").attr("src",url);
	        // 新建 Image 元素
	        image = new Image();
	        image.src = url;
	        $(".size-choose-btn").css("display","inline-block");
	        initJcrop();
	    };
	    reader.readAsDataURL(file);
	});
	// 改变尺寸后，重新初始化裁剪框
	$(".picture-size").change(function(){
		var $selectedvalue = $("input[name='picture-size']:checked").val();
		switch($selectedvalue){
			case "inch1":
				setSize(100,140);
				size = 1;
				initJcrop();
				break;
			case "inch2":
				setSize(140,212);
				size = 2;
				initJcrop();
				break;
			case "inch5":
				setSize(254,178);
				size = 5;
				initJcrop();
				break;
			case "inch10":
				setSize(254,203);
				size = 10;
				initJcrop();
				break;
		}
	});
	// 提交时进行裁剪上传
	$("#picture-submit").click(function(){
		getClippedPicture();
	});
});
</script>
<jsp:include page="/jsp/template/footer.jsp" />