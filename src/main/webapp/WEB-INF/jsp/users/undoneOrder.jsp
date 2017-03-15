<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="/jsp/template/header.jsp"/>
<jsp:include page="/jsp/template/sidebar.jsp" />
    <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10 content">
        <div class="page-header">
            <h1>打印文件</h1>
            <p><span> 上传文件</span> → <span></span><span>提交订单</span> → <span class="label label-success">在线支付</span> → <span>完成订单</span></p>
        </div>
        ${error}
        <div id="print">
            <form class="form-horizontal" role="form" method="post" action="jsp/users/doneOrder.jsp">
            		<div class="form-group">
	            		<div class="col-xs-12 col-sm-6 col-md-6">
							<div class="alert alert-warning alert-dismissible" role="alert">
							<span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;&nbsp;
							恭喜你！订单已经提交成功！<strong>请前往付款！</strong></div>
				        </div>
            		</div>
            		<div class="order-header">
            			<p>订单编号：<span class="order-num">201510110001</span>&nbsp;&nbsp;&nbsp;&nbsp;提交时间：2015/10/11 12:31</p>
            		</div>
            		<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>文件名</th>
									<th>开始页码</th>
									<th>结束页码</th>
									<th>打印份数</th>
									<th>结算金额</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td id="file.name">Javascript精粹.pdf</td>
									<td id="start-page">1</td>
									<td id="end-page">12</td>
									<td id="copy">2</td>
									<td id="total">12元</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="order-footer">
            			<p>配送地址：江苏省／苏州市／工业园区／中国科技大学苏州研究院思贤楼5楼</p>
            			<p>支付方式：支付宝</p>
            		</div>
                    <div class="form-group">
                        <div class="col-md-6 form-btn-group">
                        <button type="submit" name="submit" class="btn btn-primary pull-right button-stripe" id="submit">
                        前往支付&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></button>
                        </div>
                    </div>
                </form>
        </div>
    </div>
</div>
<jsp:include page="/jsp/template/footer.jsp"/>