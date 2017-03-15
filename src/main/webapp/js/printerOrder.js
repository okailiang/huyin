/**
 * 功能：根据订单id删除订单信息
 */
var deleteOrderById = function(orderNo,orderId) {
	var deleteOneOrder = function() {
		$.ajax({
			cache : true,
			type : "post",
			async : true,
			url : "updateOrderIsDeleted.do",
			dataType : 'json',
			data : {
				orderNo : orderNo,
				isDeleted : 1
			},
			error : function(data) {
				alert("连接服务器失败，请重试！！");
			},
			success : function(data) {
				if (data.success) {
					$("#order-" + orderId).remove();
				} else {
					if (data.message == "") {
						alert("删除错误，请联系系统管理员！");
					} else {
						alert(data.message);
					}
				}
			}
		});
	};
	confirm("确定删除？", deleteOneOrder);
};
/**
 * 功能：根据订单id展开或者收起
 */
var getDetailsById = function(id) {
	var order = $("#order-"+id);
	if(order.find(".order-details").hasClass("hidden")){
		order.find(".order-details").removeClass("hidden");
		order.find(".order-details-span")[0].innerHTML = '<span class="glyphicon glyphicon glyphicon-chevron-down"></span>&nbsp;收起';
	}
	else {
		order.find(".order-details").addClass("hidden");
		order.find(".order-details-span")[0].innerHTML = '<span class="glyphicon glyphicon glyphicon-chevron-right"></span>&nbsp;展开';
	}
};
/**
 * 功能：展开或者收起全部订单详情
 */
$("#all-order-details").click(function(id) {
	var orderDetails = $(".order-details");
	if(orderDetails.hasClass("hidden")){
		orderDetails.removeClass("hidden");
		$(".all-order-details")[0].innerHTML = '<span class="glyphicon glyphicon glyphicon-chevron-down"></span>&nbsp;全收起';
		$(".order-details-span").each(function(){
			this.innerHTML = '<span class="glyphicon glyphicon glyphicon-chevron-down"></span>&nbsp;收起';
		});
	}else {
		orderDetails.addClass("hidden");
		$(".all-order-details")[0].innerHTML = '<span class="glyphicon glyphicon glyphicon-chevron-right"></span>&nbsp;全展开';
		$(".order-details-span").each(function(){
			this.innerHTML = '<span class="glyphicon glyphicon glyphicon-chevron-right"></span>&nbsp;展开';
		});
	}
});
/**
 * 功能：根据多个订单id删除多个订单
 */
var deleteOrdersByIds = function(e) {
	var orderIds = "";
	var idList;
	//将id拼接为字符串方便传到后台
	idList = new Array();
	var deleteMoreOrder = function() {
		$.ajax({
			type : 'post',
			url : 'updateOrdersIsDeleted.do',
			dataType : 'json',
			data : {
				orderIds : orderIds,
				isDeleted : 1
			},
			cache : false,
			success : function(data) {
				if (data.success) {
					var idArr = orderIds.split(',');
					var len = idArr.length;
					for(var i = 0; i < len; i++){
						$("#order-" + idArr[i]).remove();
					}
				} else {
					if (data.message == "") {
						alert("连接服务器失败，请重试！");
					} else {
						alert(data.message);
					}
				}
			},
			error : function(data) {
				alert("连接服务器失败，请重试！");
			}
		});
	};
	$("input[type='checkbox']").each(function() {
		if (this.checked) {
			idList.push(this.value);
			orderIds = orderIds + this.value + ",";
		}
	});
	if (orderIds.length > 0) {
		orderIds = orderIds.substr(0, orderIds.length - 1);
	}
	if (idList == null || idList.length == 0) {
		alert("请选择要删除的数据！");
		return false;
	} else {
		confirm("确定删除？", deleteMoreOrder);
	}
};

//判断订单状态是否改变
function isOrderPaid(orderNo) {
	$.ajax({
		type : "post",
		url : "getOrderInfoByNo.do",
		async : true,
		dataType : "json",
		data : {
			orderNo : orderNo
		},
		cache : false,
		success : function(data) {
			if (data.success && (data.result.orderState == 2)) {
			} else if (data.success) {

			}
		}
	});
};
/**
 * 功能：显示订单详情
 */
var showOrders = function(data) {
	var orderArray = data.result;
	var orderArrLen = orderArray.length;
	var orderFileRM;
	var orderFileRMLen;
	var payWay = "";
	var orderState;
	var deliveryWay = "";
	//显示全部收缩和展开
	var allOrderDetails = $("#all-order-details");
	if(orderArrLen <= 0){
		allOrderDetails.addClass("hidden");
	}else if(allOrderDetails.hasClass("hidden")){
		allOrderDetails.removeClass("hidden");
	}
	for ( var i = 0; i < orderArrLen; i++) {
		orderState = "未打印";
		var orderNo = orderArray[i].orderNo;
		var html = "";
		if (orderArray[i].payWay == "1") {
			payWay = "支付宝";
		} else if (orderArray[i].payWay == "2") {
			payWay = "微信支付";
		} else if (orderArray[i].payWay == "3") {
			payWay = "网银支付";
		}
		if (orderArray[i].orderState == "1"&&orderArray[i].directPrint==1) {
			orderState = "未打印";
		} else if (orderArray[i].orderState == "2") {
			orderState = "未打印";
		} else if (orderArray[i].orderState == "3") {
			orderState = "已完成";
		} else if (orderArray[i].orderState == "4") {
			orderState = "已取消";
		}
		if (orderArray[i].deliveryWay == "1") {
			deliveryWay = "自取";
		} else if (orderArray[i].deliveryWay == "2") {
			deliveryWay = "配送";
		}
		html += '<div class="order-all clearfix" id="order-' + orderArray[i].id + '">';
		html += '  <div class="order-header">';
		//已完成的可以选中
		if (orderArray[i].orderState == "3"){
		    html += '    <input type="checkbox" class="order-checkbox" value="'+orderArray[i].id+'"/>';
		}else{
			html += '    <input type="checkbox" class="order-checkbox" value="'+orderArray[i].id+'" disabled="disabled"/>';
		}
		html += '    <p class="order-discription">';
		html += '    订单编号：<span class="order-num">'+orderNo+'</span>&nbsp;&nbsp;创建时间：'+stampToDate(orderArray[i].createTime);
		//已完成的可以删除
		if (orderArray[i].orderState == "3"){
			html += '    <div class="order-del-span" onclick=deleteOrderById("'+orderArray[i].orderNo+'","'+orderArray[i].id+'")><span class="glyphicon glyphicon-trash"></span>&nbsp;删除</div>';
		}
		html += '    <div class="order-details-span" onclick=getDetailsById("'+orderArray[i].id+'")><span class="glyphicon glyphicon glyphicon-chevron-down"></span>&nbsp;收起</div>';
		html += '    </p>';
		html += '  </div>';
		html += '<div class="order-details"><div class="table-responsive">';
		html += ' <table class="table table-striped">';
		html += '  <thead>';
		html += '    <tr>';
		html += '      <th width="50px"></th>';
		html += '      <th>文件名</th>';
		html += '      <th>页数</th>';
		html += '      <th>打印份数</th>';
		html += '      <th>打印要求</th>';
		html += '      <th>结算金额</th>';
		html += '    </tr>';
		html += '  </thead>';
		orderFileRM = orderArray[i].orderFileRManageList;
		orderFileRMLen = orderFileRM.length;
		for ( var j = 0; j < orderFileRMLen; j++) {
			html += '  <tbody>';
			html += '    <tr>';
			html += '    <td><img src = "images/fileType/'
					+ getFileType(orderFileRM[j].fileName) + '.png"></td>';
			html += '      <td title="' + orderFileRM[j].fileName + '">'
					+ orderFileRM[j].fileName + '</td>';
			html += '      <td>' + orderFileRM[j].filePages + '</td>';
			html += '      <td>' + orderFileRM[j].perFileCopies + '</td>';
			html += '      <td>' + orderFileRM[j].printType + '</td>';
			html += '      <td>' + orderFileRM[j].perFilePrice + '</td>';
			html += '    </tr>';
			html += '  </tbody>';
		}
		html += '</table>';
		html += '</div>';
		html += '<div class="order-footer">';
//		html += '<p><strong>打印地址：</strong>'
//				+ orderArray[i].userAddressRM.address + " "
//				+ orderArray[i].userAddressRM.printShopName
//				+ "&nbsp;&nbsp;<span class='label label-info'>"
//				+ orderArray[i].userAddressRM.teleNumber + '</span></p>';
		if (orderArray[i].deliveryWay == "1") {
			html += '<p><strong>配送方式：</strong>' + deliveryWay + '</p>';
		} else if (orderArray[i].deliveryWay == "2") {
			html += '<p><strong>配送地址：</strong>' + orderArray[i].address.address
					+ " " + orderArray[i].address.userName
					+ "&nbsp;&nbsp;<span class='label label-info'>"
					+ orderArray[i].address.teleNumber + '</span></p>';
		}
		//显示支付方式
		html += '<p>';
		if(orderArray[i].directPrint==1){
			html += '<strong>支付方式：</strong>' + payWay
					+ '<span class="order-state"><strong>状态：</strong>' + orderState+ '';
			html += orderArray[i].orderState == "1" ? '&nbsp;&nbsp;&nbsp;&nbsp;<span class="order-pay-span" id="'+orderNo+'" value="'
					+ orderArray[i].totalPrice
					+ '"><span class="glyphicon glyphicon-usd"></span>前往付款</span>': '';
			html += '&nbsp;&nbsp;&nbsp;&nbsp;</span><strong>总计：</strong>'+ orderArray[i].totalPrice +'元';
		}
		else{
			html += '<strong>支付方式：</strong>到店修改文档后支付';
			html +='<span class="order-state"><strong>状态：</strong>' + orderState+ '';
		}
		html += '</p>';
		html += '<p>';
		//显示备注信息
		if(orderArray[i].remark!=""){
			html += '<span class="text-overflow" title="'+ orderArray[i].remark +'"><strong>备注：</strong>'+ orderArray[i].remark +'</span>';
		}
		html += '</p>';

		if(!(orderArray[i].orderState==1&&orderArray[i].directPrint==1)){
			html += '<div class="order-qrcode">';
			html += '	<span class="printer-qrcode-thumbnail btn btn-sm btn-default" id="qrcode-thumbnail-'
				+orderNo
				+'"><span class="glyphicon glyphicon-qrcode" aria-hidden="true"></span>&nbsp;&nbsp;取件码</span>';
			html += '	<div class="qrcode-details hidden" id="qrcode-'+orderNo+'"></div>';
			html += '</div></div>';
		}
		html += '</div>';
		
		$("#user-order-table").append(html);
	}
	$(".printer-qrcode-thumbnail").mouseover(function() {
		var orderNo = $(this).attr("id").split("-")[2];
		$("#qrcode-"+orderNo).removeClass("hidden");
		if($("#qrcode-"+orderNo).find("canvas").length==0){
			$.ajax({
				cache : true,
				type : "post",
				async : true,
				url : "getEncodeOrderInfoByOderNo.do",
				dataType : 'json',
				data : {
					orderNo : orderNo
				},
				error : function(data) {
					alert("连接服务器失败，请重试！");
				},
				success : function(data) {
					$("#qrcode-"+orderNo).qrcode({
						width : 240,
						height : 240,
						text : data.result,
						src : "images/logo.png"
					});
				}
			});
		}
	});
	$(".printer-qrcode-thumbnail").mouseout(function() {
		var orderNo = $(this).attr("id").split("-")[2];
		$("#qrcode-"+orderNo).addClass("hidden");
	});
	$(".printer-qrcode-thumbnail").click(function() {
		var orderNo = $(this).attr("id").split("-")[2];
		var strDataURI = $("#qrcode-"+orderNo).find("canvas")[0].toDataURL("image/png").replace("image/png","image/octet-stream");
		window.location.href = strDataURI;
	});
};
/**
 * 功能：order.jsp页面加载后请求数据
 */
var getOrdersByStates = function(orderState, directPrint, orderNo, preTime, aftTime) {
	$.ajax({
		cache : true,
		type : "post",
		async : true,
		url : "printerSearchOrders.do",
		dataType : 'json',
		data : {
			orderState : orderState,
			directPrint : directPrint,
			orderNo : orderNo,
			preTime : preTime,
			aftTime : aftTime
		},
		error : function(data) {
			alert("连接服务器失败，请重试！");
		},
		success : function(data) {
			if (data.success) {
				//显示订单详情
				showOrders(data);
			} else {
				if (data.message == "") {
					alert("连接服务器失败，请重试！");
				} else {
					alert(data.message);
				}
			}
		}
	});
};

/**
 * 功能：将时间戳转成日期
 */
var stampToDate = function(stamp) {
	var date = new Date(stamp);
	Y = date.getFullYear() + '-';
	M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date
			.getMonth() + 1)
			+ '-';
	D = (date.getDate() < 10 ? "0" + date.getDate() + ' ' : date.getDate()
			+ ' ');
	h = (date.getHours() < 10 ? "0" + date.getHours() + ':' : date.getHours()
			+ ':');
	m = (date.getMinutes() < 10 ? "0" + date.getMinutes() + ':' : date
			.getMinutes()
			+ ':');
	s = (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
	return (Y + M + D + h + m + s);
};

/**
 * 功能：通过文件名获得文件后缀名
 */
var getFileType = function(fileName) {
	return fileName.substr(fileName.lastIndexOf(".") + 1).toLowerCase();
};

$(function() {

	/**
	 * 功能：order.jsp页面加载后请求数据
	 */
	var getAllOrderData = function(e) {
		$.ajax({
			cache : false,
			type : "post",
			async : true,
			url : "printerSearchOrders.do",
			dataType : 'json',
			data : {
				directPrint:1,
				orderState:2
			},
			error : function(data) {
				alert("连接服务器失败，请重试！");
			},
			success : function(data) {
				if (data.success) {
					//显示订单详情
					showOrders(data);
				} else {
					if (data.message == "") {
						alert("连接服务器失败，请重试！");
					} else {
						alert(data.message);
					}

				}
			}
		});
	};

	/**
	 * 功能：order.jsp页面加载后请求数据
	 */
	$("#user-order-table").ready(function(e) {
		getAllOrderData(e);
	});
	//通过选中的多个订单id删除多个订单
	$("#del-select-orders").click(function(e) {
		deleteOrdersByIds(e);
	});
	//Tab导航选择订单查询方式
	$("li[role='presentation']").each(function() {
		$(this).click(function() {
			$("li[role='presentation']").each(function() {
				$(this).removeClass("active");
			});
			$("#user-order-table div.order-all").each(function() {
				$(this).remove();
			});
			$(this).addClass("active");
		});
	});
	/**
	 * 功能：用户搜索订单
	 */
	$("#user-search-order").click(function(e) {
		var state = $("li[role='presentation'][class='active'] a").attr("id");
		state = state.substr(state.length - 1, 1);
		var befTime = $("#user-search-befTime").val();
		var afterTime = $("#user-search-aftTime").val();
		var orderNo = $("#user-search-orderNo").val();
		$("#user-order-table div.order-all").remove();
		getOrdersByStates(state, orderNo, befTime, afterTime);
	});
});