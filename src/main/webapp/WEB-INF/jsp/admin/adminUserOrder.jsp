<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/adminHeader.jsp" />
<jsp:include page="/jsp/template/adminSidebar.jsp" />
<div id="pay-tip" class="register-div">
    <div id="pop-close-div" class="register-div-descript">
	   <p for="userrole">网上支付提示</p>
	</div>
	<div class="alert alert-danger" role="alert">
          支付完成前，请不要关闭此支付验证窗口。</br>
          支付完成后，请根据您支付的情况点击下面按钮。
    </div>
    </br>
	<div class="row-md-8" >
		<button type="button" name="button" id="pay-error-tip"
			class="btn btn-danger pull-left">支付遇到问题
		</button>
	</div>
	<div class="row-md-8">
		<button type="button" name="button" id="pay-success-tip"
			class="btn btn-success pull-right">支付完成
		</button>
	</div>
</div>
<div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content" name="top">
	<div class="page-header tab-header">
		<h1>用户订单管理</h1>
		<ul class="nav nav-tabs">
		<li role="presentation" class="active"><a href="javascript:void(0);" onclick="getOrdersByStates(0,'','','')" id="orderState0">所有</a>
		</li>
		<li role="presentation"><a href="javascript:void(0);" onclick="getOrdersByStates(1,'','','')" id="orderState1">未支付</a>
		</li>
		<li role="presentation"><a href="javascript:void(0);" onclick="getOrdersByStates(2,'','','')" id="orderState2">已支付</a>
		</li>
		<li role="presentation"><a href="javascript:void(0);" onclick="getOrdersByStates(3,'','','')" id="orderState3">已完成</a>
		</li>
		<li role="presentation"><a href="javascript:void(0);" onclick="getOrdersByStates(4,'','','')" id="orderState4">已取消</a>
		</li>
		</ul>
	</div>
	<div class="order-responsive-group" id = "admin-order-table">
		<div class="order-btn-group">
		    <button id="check-all" class="btn btn-warning">
				<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;全选
			</button>
			<button id="admin-del-select-orders" class="btn btn-danger">
				<span class="glyphicon glyphicon-ok"></span>&nbsp;&nbsp;删除
			</button>
			<form class="form-horizontal" role="form" method="post"
				id="searchFile-form">
				<input type="date" class="form-control" id="admin-search-befTime" placeholder="date"
					name="beforeTime" > <input
					type="date" class="form-control" id="admin-search-aftTime" placeholder="date"
					name="afterTime" > <input type="text"
					class="form-control" id ="admin-search-orderNo" placeholder="请入订单号搜索" name="orderNo"
					> <input type="hidden"
					name="currentPage" value="1">
				<button type="button" id="admin-search-order" class="btn btn-info">
					<span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;搜索
				</button>
			</form>
		</div>
  <%--      <div class="order-all">
          		<div class="order-header">
          		    <input class="order-checkbox" type="checkbox" value="${item.id}" />
          			<p class="order-discription">订单编号：<span class="order-num">201510110001</span>&nbsp;&nbsp;&nbsp;&nbsp;提交时间：2015/10/11 12:31</p>
          			<p class="order-detail">详情</p>
          			<span id="order-del-span" class="glyphicon glyphicon-trash"></span>
          		</div>
          		<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>文件名</th>									
							<th>打印份数</th>
							<th>结算金额</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="file.name">Javascript精粹.pdf</td>
							<div>
							<td  class="order-info" id="copy">2</td>
							</div>
							<td id="total">12元</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="order-footer">
       			<p>支付方式：支付宝<span class="order-state" >状态：未完成<a class="order-state-zf" herf="">&nbsp;&nbsp;支付</a></span></p>
       		</div>
         </div>
         <div class="order-all">
          		<div class="order-header">
          		    <input class="order-checkbox" type="checkbox" value="${item.id}" />
          			<p class="order-discription">订单编号：<span class="order-num">201510110001</span>&nbsp;&nbsp;&nbsp;&nbsp;提交时间：2015/10/11 12:31</p>
          			<p class="order-detail">详情</p>
          			<span id="order-del-span" class="glyphicon glyphicon-trash"></span>
          		</div>
          		<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>文件名</th>									
							<th>打印份数</th>
							<th>结算金额</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="file.name">PHP精粹.pdf</td>
							<div>
							<td  class="order-info" id="copy">2</td>
							</div>
							<td id="total">12元</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="order-footer">
       			<p>支付方式：支付宝<span class="order-state" >状态：未完成<a class="order-state-zf" herf="">&nbsp;&nbsp;支付</a></span></p>
       		</div>
         </div>
         <div class="order-all">
       		<div class="order-header">
       		    <input class="order-checkbox" type="checkbox" value="${item.id}" />
       			<p class="order-discription">订单编号：<span class="order-num">201510110001</span>&nbsp;&nbsp;&nbsp;&nbsp;提交时间：2015/10/11 12:31</p>
       			<p class="order-detail">详情</p>
       			<span id="order-del-span" class="glyphicon glyphicon-trash"></span>
       		</div>
       		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>文件名</th>									
						<th>打印份数</th>
						<th>结算金额</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td id="file.name">Javascript精粹.pdf</td>
						
						<td  class="order-info" id="copy">2</td>
						<span></span>
						<td id="total">12元</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="order-footer">
			<p>支付方式：支付宝<span class="order-state" >状态：未完成<a class="order-state-zf" herf="">&nbsp;&nbsp;支付</a></span></p>
		</div>
    </div>
    --%>
    <div class="table-responsive">
		<!-- 分页 -->
		<div id="page-div" style="text-align:center;margin:0;padding:0;" value="${url}">
			<c:if test="${!empty fileList}">
				<p:pager page="${page}" url="${url}" />
			</c:if>
		</div>
	</div>
	</div>
</div>
<div id="back-top">
    	<a href="#" class="b_t"><span class="glyphicon glyphicon-menu-up" ></span></a>
</div>
</div>
<script src="js/adminOrder.js"></script>
<jsp:include page="/jsp/template/footer.jsp" />