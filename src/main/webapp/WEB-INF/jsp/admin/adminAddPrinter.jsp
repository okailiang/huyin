<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/jsp/template/adminHeader.jsp"/>
<jsp:include page="/jsp/template/adminSidebar.jsp" />
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
    <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
      	<div class="page-header">
			<h1>增添打印店</h1>
		</div>
		<c:if test="${!empty errorInfo}">
		<script type="text/javascript">
            alert("请求数据有错！");
        </script></c:if>
		<form class="form-horizontal" role="form" method="post" action="savePrinterInfo.do" enctype="multipart/form-data">
			<div class="form-group">
                <label for="printer-name" class="col-md-3 control-label">打印店名</label>
                <div class="col-md-4">
                <input type="text" class="form-control" id="printer-name" name="printer-name" value="${printShopName}" placeholder="输入中文、字母或数字，长度最大32" autocomplete="off">
                </div>
                <div class="col-md-5">
                	<div class="alert alert-danger hidden" role="alert" id="alert-printer-name">
                		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
                		<span id="alert-printer-name-message"></span>
                	</div>
                	<div class="alert hidden" role="alert" id="alert-printer-name-ok"></div>
                </div>
            </div>
            <div class="form-group">
                <label for="printer-username" class="col-md-3 control-label">店主名</label>
                <div class="col-md-4">
                <input type="text" class="form-control" id="printer-username" name="printer-username" value="${printerName}" placeholder="输入中文、字母或数字，长度最大32" autocomplete="off">
                </div>
                <div class="col-md-5">
                	<div class="alert alert-danger hidden" role="alert" id="alert-printer-username">
                		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
                		<span id="alert-printer-username-message"></span>
                	</div>
                	<div class="alert hidden" role="alert" id="alert-printer-username-ok"></div>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-md-3 control-label">密码</label>
                <div class="col-md-4">
                <input type="password" class="form-control" id="password" name="password" placeholder="密码长度不小于6位" autocomplete="off">
                </div>
                <div class="col-md-5">
                	<div class="alert alert-danger hidden" role="alert" id="alert-password">
                		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
                		<span id="alert-password-message"></span>
                	</div>
                	<div class="alert hidden" role="alert" id="alert-password-ok"></div>
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-md-3 control-label">邮箱</label>
                <div class="col-md-4">
                <input type="text" class="form-control" id="email" name="email" value="${printerEmail}" placeholder="输入邮箱地址" autocomplete="off">
                </div>
                <div class="col-md-5">
                	<div class="alert alert-danger hidden" role="alert" id="alert-email">
                		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
                		<span id="alert-email-message"></span>
                	</div>
                	<div class="alert hidden" role="alert" id="alert-email-ok"></div>
                </div>
            </div>
            <div class="form-group">
				<label for="address-choice" class="col-md-3 control-label" id="address-title">选择地址</label>
				<div class="col-xs-12 col-sm-2 col-md-2">
					<select class="form-control" id="province" name="province"></select>
				</div>
				<div class="col-xs-12 col-sm-2 col-md-2">
					<select type="text" class="form-control" id="city" name="city"></select>
				</div>
				<div class="col-xs-12 col-sm-2 col-md-2">
					<select class="form-control hidden" id="area" name="area"></select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-3 col-xs-12 col-sm-7 col-md-7">
					<input type="text" class="hidden" id="city-area-id" name="city-area-id" autocomplete="off">
					<input type="text" class="hidden" id="cascade-address" name="cascade-address" autocomplete="off">
					<input type="text" class="form-control" id="street" name="street" value="${street}" autocomplete="off" placeholder="街道地址（不能包含特殊字符）">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-3 col-xs-12 col-sm-3 col-md-3">
					<input type="text" class="form-control" id="address-telenumber" autocomplete="off"
						name="address-telenumber" value="${teleNumber}" placeholder="联系号码">
				</div>
				<div class="col-md-4">
	             	<div class="alert alert-danger hidden" role="alert" id="alert-address-telenumber">
	             		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
	             		<span id="alert-address-telenumber-message"></span>
	             	</div>
	             	<div class="alert hidden" role="alert" id="alert-address-telenumber-ok"></div>
            	</div>
			</div>
			<div class="form-group">
			<label for="longitude" class="col-md-3 control-label">地图</label>
			<div class="col-md-7" id="printer-delivery-address-map">
				<span class="btn btn-sm btn-default" id="address-map-btn"> <span
					class="glyphicon glyphicon-globe"></span>&nbsp;&nbsp;打开地图拾取经纬度 </span>
				<div id="l-map" class="hidden"></div>
			</div>
			</div>
			<div class="form-group">
			<label for="longitude" class="col-md-3 control-label">经度</label>
                <div class="col-md-3">
                <input type="text" class="form-control" id="longitude" name="longitude" value="${longitude}" placeholder="输入经度" autocomplete="off">
                </div>
                <div class="col-md-5">
                	<div class="alert alert-danger hidden" role="alert" id="alert-longitude">
                		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
                		<span id="alert-longitude-message"></span>
                	</div>
                	<div class="alert hidden" role="alert" id="alert-longitude-ok"></div>
                </div>
            </div>
            <div class="form-group">
                <label for="latitude" class="col-md-3 control-label">纬度</label>
                <div class="col-md-3">
                <input type="text" class="form-control" id="latitude" name="latitude" value="${latitude}" placeholder="输入纬度" autocomplete="off">
                </div>
                <div class="col-md-5">
                	<div class="alert alert-danger hidden" role="alert" id="alert-latitude">
                		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
                		<span id="alert-latitude-message"></span>
                	</div>
                	<div class="alert hidden" role="alert" id="alert-latitude-ok"></div>
                </div>
            </div>
            <div class="form-group">
            	<label for="shop-image" class="col-md-3 control-label">上传照片</label>
            	<div class="col-md-2">
            		<div class="avatar">
            		<c:choose>
						<c:when test="${(!empty printShopImage)}">
						    <img src="${printShopImage}" width="150" height="150">
						</c:when>
						<c:otherwise>
							<img src="images/photo.png" width="150" height="150">
						</c:otherwise>
					</c:choose>
					</div>
            	</div>
	            <div class="col-md-6">
					<p class="print-tip">从电脑中选择你喜欢的照片:<br>你可以上传JPG、JPEG、GIF、PNG或BMP文件。</p>
					<div class="avatar-upload btn btn-sm btn-default">
						<span>选择照片</span>
						<input class="upload" id="shop-image" name="shop-image" type="file">
					</div>
					<p id="shop-image-name" class="print-tip"></p>
				</div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-3 col-md-3 form-btn-group">
                <button type="submit" name="submit" class="btn btn-primary" id="printer-submit">
                <span class="glyphicon glyphicon-plane"></span>&nbsp;&nbsp;开店</button>
                </div>
            </div>
		</form>
    </div>
</div>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=GpDZDSdhW0GYDfDMgV2eYHLO"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<script src="js/adminAddPrinter.js"></script>
<script src="js/map.js"></script>
<jsp:include page="/jsp/template/footer.jsp"/>