<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/jsp/template/header.jsp" />
<jsp:include page="/jsp/template/sidebar.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<link rel="stylesheet" href="css/select2.min.css">
<div id="pay-tip" class="register-div">
    <div id="pop-close-div" class="register-div-descript">
	   <p for="userrole">网上支付提示</p>
	</div>
	<div class="alert alert-danger" role="alert">
      支付完成前，请不要关闭此支付验证窗口。</br>
      支付完成后，请根据您支付的情况点击下面按钮。
    </div>
    </br>
	<button type="button" id="pay-error-tip" class="btn btn-danger pull-left">支付遇到问题</button>
	<button type="button" id="pay-success-tip" class="btn btn-success pull-right">支付完成</button>
</div>
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
	<div class="page-header">
		<h1>提交订单</h1>
		<p>
			<span> 上传文件</span> → <span></span><span class="label label-success">提交订单</span>
			→ <span>在线支付</span> → <span>完成订单</span>
		</p>
	</div>
	<div id="print">
		<form class="form-horizontal" role="form" method="post">
		    <div class="form-group" id="printer-delivery-address">
		    	<label for="printer-address-choice" class="col-md-2 control-label">选择打印店</label>
				<div class="col-md-10" id="printer-address-list">
					<div class="form-group">
						<div class="col-xs-12 col-sm-3 col-md-3">
							<select class="form-control" id="printer-province" name="printer-province"></select>
						</div>
						<div class="col-xs-12 col-sm-2 col-md-2">
							<select type="text" class="form-control" id="printer-city" name="printer-city"></select>
						</div>
						<div class="col-xs-12 col-sm-7 col-md-7">
							<select class="form-control hidden" id="printer-name" name="printer-name" style="width:100%;"></select>
							<div class="alert alert-danger hidden" role="alert" id="alert-printer-address">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
								该区域没有我们的打印店，请换个区域试一试！
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-offset-2 col-md-10" id="printer-delivery-address-map">
					<span class="btn btn-sm btn-default" id="address-map-btn">
						<span class="glyphicon glyphicon-globe"></span>&nbsp;&nbsp;地图
					</span>
					<div id="l-map" class="hidden"></div>					
				</div>
			</div>
			<div class="form-group">
				<label for="address-choice" class="col-md-2 control-label">订单详情</label>
				<div class="col-md-10">
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>文件名</th>
									<th>总页数</th>
									<th>打印份数</th>
									<th>打印要求</th>
									<th>单价（元）</th>
									<th>小计（元）</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="file-list">
								<c:choose>
									<c:when test="${!empty fileList}">
										<c:forEach items="${fileList}" var="item">
											<tr id="${item.id}">
												<td class="order-file-name" title="${item.fileName}" id="file-name">${item.fileName}</td>
												<td id="print-page">${item.filePages}</td>
												<td width="110px">
													<div class="input-group">
														<span class="input-group-addon" name="minus-copy">－</span> <input
															class="form-control" name="copy-choice" 
															value="1" id="print-copies"></input> <span
															class="input-group-addon" name="add-copy">＋</span>
													</div>
												</td>
												<td width="150px">
												    <select class="form-control" value="${item.fileName}" val="${item.imageSize}" style="width:110px;" name="print-choice" id="print-choice">
													</select>
												</td>
												<td><span class="per-price">0.00</span></td>
												<td><span class="univalent">0.00</span></td>
												<td><a class="remove-fileid"
													value="${item.id}" href="javascript:void(0);">移除</a></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="6">
												<div style="color:red;text-align:center;margin:0;padding:0;">
													您还没有上传要打印文件，请上传要打印的文件！</div>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="remark" class="col-md-2 control-label">备注</label>
				<div class="col-xs-7 col-sm-8 col-md-8">
					<textarea class="form-control" rows="2" id="order-remark" name="remark" placeholder="如果有什么对打印店老板说的请在此填写！最多100个字符，一个中文两个字符。" autocomplete="off"></textarea>
				</div>
			</div>
			<div class="form-group" id="delivery-choice">
				<label for="pay-choice" class="col-md-2 control-label">配送方式</label>
				<div class="col-md-5">
					<div class="radio">
						<label> <input type="radio" name="delivery-choice" value="1"
							id="delivery-one" checked>到店自取</label>
						<label style ="color: rgb(170, 170, 170);"> <input type="radio" name="delivery-choice" value="2" 
							id="delivery-two" disabled="disabled">加急配送（收取配送费5.00元）</label>
					</div>
				</div>
			</div>
			<div class="form-group hidden" id="delivery-address">
				<label for="address-choice" class="col-md-2 control-label">配送地址</label>
				<div class="col-md-8" id="address-list"></div>
				<div class="col-md-offset-2 col-md-10">
					<span class="btn btn-sm btn-default add-address" id="add-address-btn">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;添加新地址
					</span>
					<div id="add-address-form" class="hidden">
						<div class="form-group">
							<div class="col-xs-12 col-sm-3 col-md-3">
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
							<div class="col-xs-12 col-sm-7 col-md-7">
								<input type="text" class="form-control" id="street" name="street" autocomplete="off"
									placeholder="街道地址（不能包含特殊字符）">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12 col-sm-3 col-md-3">
								<input type="text" class="form-control" id="address-telenumber" autocomplete="off"
									name="address-telenumber" placeholder="联系号码">
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
							<div class="col-xs-12 col-sm-3 col-md-3">
								<input type="text" class="form-control" id="address-username" autocomplete="off"
									name="address-username" placeholder="收件姓名">
							</div>
							<div class="col-md-4">
				             	<div class="alert alert-danger hidden" role="alert" id="alert-address-username">
				             		<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>&nbsp;&nbsp;
				             		<span id="alert-address-username-message"></span>
				             	</div>
				             	<div class="alert hidden" role="alert" id="alert-address-username-ok"></div>
			            	</div>
						</div>
						<div class="form-group" id="save-address-submit">
							<div class="col-md-3">
								<span class="btn btn-sm btn-primary" id="save-address-btn">
									<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;保存
								</span>
								<span class="btn btn-sm btn-default" id="address-cancel-btn1">
									<span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;取消
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group" id="priority-choice">
				<label for="priority-choice" class="col-md-2 control-label">打印方式</label>
				<div class="col-md-5">
					<div class="radio">
						<%
				        HttpSession s = request.getSession();
				        String role = (String) s.getAttribute("role");
				        if(!"4".equals(role)){
				        %>
				        <label> <input type="radio" name="priority-choice" value="1" id="priority-one" checked>即时打印</label>
						<label> <input type="radio" name="priority-choice" value="2" id="priority-two">到店打印（无需在线付款）</label>
				        <%}else{%>
						<label> <input type="radio" name="priority-choice" value="1" id="priority-one" checked>即时打印</label>
						<label> <input type="radio" name="priority-choice" value="2" id="priority-two" disabled><span style="color:#aaa">到店打印（无需在线付款）<span></label>
						<%}%>
					</div>
				</div>
			</div>
			<div class="form-group" id="total-pay">
				<label for="total-pay" class="col-md-2 control-label">实付总额</label>
				<div class="col-md-4">
					<p class="form-control-static">
					<span id="file-money">0.00</span>
					<span class="alert alert-danger hidden" id="discount">(已8折优惠)</span> 
					<span class="hidden" id="if-deliveried">
					+ <span id="delivery-money">5.00</span> 元 = <span id="total-money">
					0.00</span> 元</span></p>
				</div>
			</div>
			<div class="form-group" id="pay-choice">
				<label for="pay-choice" class="col-md-2 control-label">支付方式</label>
				<div class="col-xs-7 col-sm-4 col-md-3">
						<select class="form-control" name="pay-choice">	
						<option value="1">支付宝支付</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-3 col-md-2 form-btn-group">
					<button type="submit" name="submit"
						class="btn btn-primary pull-right button-stripe" id="order-submit">
						提交订单&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-right"></span>
					</button>
				</div>
				<div class="col-xs-12 col-sm-7 col-md-7" style="margin-top: 18px;">
				<span class="print-tip">温情提示：付款成功后，请不要手动关闭支付页面，否则可能导致支付无效！</span>
				</div>
			</div>
		</form>
	</div>
</div>
</div>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=GpDZDSdhW0GYDfDMgV2eYHLO"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<script src="js/map.js"></script>
<script src="js/select2.min.js"></script>
<script src="js/createOrder.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />