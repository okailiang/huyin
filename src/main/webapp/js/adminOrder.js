//显示网上支付提示框
function showPayTip() {
	showMask();
	document.getElementById('pay-tip').style.display = 'block';
};
//关闭网上支付提示
function closePayTip() {
	document.getElementById('pay-tip').style.display = 'none';
	hideMask();
};
/**
 * 将订单状态设置为已付款
 */
var setPaidOrder = function(orderNo){
	var  orderState = "2";
	 var callback = function() {
			$.ajax({
			cache : true,
			type : "post",
			async : true,
			url : "updateOrder.do",
			dataType : 'json',
			data : {
				orderNo : orderNo,
				orderState : orderState
			},
			error : function(data) {
	            alert("连接服务器失败，请重试！！");
			},
			success : function(data) {
				
				if (data.success) {
					var orderState = $("#order-state-" + orderNo);
					var result = data.result;
					if(result.orderState==2){
						orderState.text("状态：未打印");
					}else if(result.orderState==3){
						orderState.text("状态：完成");
					}
				}else{
					alert(data.message);
				}
			}
		});
	  };
	  confirm("确定修改吗？",callback);
};

/**
 * 功能：显示订单详情
 */
var showOrders = function(data) {
	var orderArray = data;
	var orderArrLen = orderArray.length;
	var orderFileRM;
	var orderFileRMLen;
	var payWay = "";
	var orderState = "";
	var deliveryWay = "";
	for ( var i = 0; i < orderArrLen; i++) {
		var orderNo = orderArray[i].orderNo;
		var html = "";
		html += '<div class="order-all clearfix" id="order-' + orderArray[i].id + '">';
		html += '  <div class="order-header">';
		html += '    <input type="checkbox" class="order-checkbox" value="'+orderArray[i].id+'" />';
		html += '    <p class="order-discription">';
		html += '    订单编号：<span class="order-num">'+orderNo+'</span>&nbsp;&nbsp;创建时间：'+stampToDate(orderArray[i].createTime);
		html += '    <div class="order-del-span" onclick=deleteOrderById("'+orderArray[i].id+'")><span class="glyphicon glyphicon-trash"></span>&nbsp;删除</div>';
		html += '    <div class="order-details-span" onclick=getDetailsById("'+orderArray[i].id+'")><span class="glyphicon glyphicon glyphicon-chevron-right"></span>&nbsp;展开</div>';
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
		if (orderArray[i].payWay == "1") {
			payWay = "支付宝";
		} else if (orderArray[i].payWay == "2") {
			payWay = "微信支付";
		} else if (orderArray[i].payWay == "3") {
			payWay = "网银支付";
		}
		if (orderArray[i].orderState == "1") {
			orderState = "未支付";
		} else if (orderArray[i].orderState == "2") {
			orderState = "已支付";
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
		html += '</table>';
		html += '</div>';
		html += '<div class="order-footer">';
		html += '<p><strong>打印地址：</strong>'
				+ orderArray[i].userAddressRM.address + " "
				+ orderArray[i].userAddressRM.printShopName
				+ "&nbsp;&nbsp;<span class='label label-info'>"
				+ orderArray[i].userAddressRM.teleNumber + '</span></p>';
		if (orderArray[i].deliveryWay == "1") {
			html += '<p><strong>配送方式：</strong>' + deliveryWay + '</p>';
		} else if (orderArray[i].deliveryWay == "2") {
			html += '<p><strong>配送地址：</strong>' + orderArray[i].address.address
					+ " " + orderArray[i].address.userName
					+ "&nbsp;&nbsp;<span class='label label-info'>"
					+ orderArray[i].address.teleNumber + '</span></p>';
		}
		if(orderArray[i].directPrint==1){
			html += '<p><strong>支付方式：</strong>' + payWay
					+ '<span class="order-state" id= "order-state-'+orderArray[i].orderNo+'"><strong>状态：</strong>' + orderState+ '';
			html += orderArray[i].orderState == "1" ? '&nbsp;&nbsp;&nbsp;&nbsp;<span class="order-pay-span" id="'+orderNo+'" value="'
					+ orderArray[i].totalPrice
					+ '"><span class="glyphicon glyphicon-usd"></span>前往付款</span>': '';
			html += '&nbsp;&nbsp;&nbsp;&nbsp;</span><strong>总计：</strong>'+ orderArray[i].totalPrice;
			html += orderArray[i].orderState == "1"?'<button id="set-paid-order" style="float:right;" onclick="setPaidOrder(\''+orderArray[i].orderNo+'\')" value= "'+orderArray[i].orderNo+'" class="btn btn-info" >置为已付款</button>':'';
			html += '</p>';
		}
		else{
			html += '<p><strong>支付方式：</strong>到店修改文档后支付</p>';
		}
		html += '</div>';
		
		$("#admin-order-table").append(html);
		//绑定点击付款按钮
		$('#'+orderNo).click(function() {
			var param = "WIDout_trade_no=" + orderNo
					+ "&WIDsubject=打印文件" + "&WIDtotal_fee="
					+ $(this).attr("value") + "&WIDbody=打印文件";
			var a = $("<a href='" + "/A4print/jsp/alipay/alipayapi.jsp?"+param+"' target='_blank'></a>").get(0);
			var e = document.createEvent('MouseEvents');
			e.initEvent('click', true, true);
			a.dispatchEvent(e);
			showPayTip();
			$("#pay-success-tip").click(function() {
				closePayTip();
			});
			$("#pay-error-tip").click(function() {
				closePayTip();
			});
		});
	}
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
 * 功能：根据订单id删除订单信息
 */
var deleteOrderById = function(id){debugger
	var orderId = id;
	
	  var callback = function() {
		$.ajax({
		cache : true,
		type : "post",
		async : true,
		url : "deleteOrderById.do",
		dataType : 'json',
		data : {
			orderId : orderId
		},
		error : function(data) {
            alert("连接服务器失败，请重试！！");
		},
		success : function(data) {
			if (data.success) {
				$("#order-"+orderId).remove();
			}else{
				if(data.message==""){
					alert("删除错误，请联系系统管理员！");
				}else{
					alert(data.message);
				}
				 
			}
		}
	});
  };
  confirm("确定删除？",callback);
};
/**
 * 功能：根据多个订单id删除多个订单
 */
var deleteOrdersByIds = function(e){
	
    	var orderIds = "";
		var idList;
	    //将id拼接为字符串方便传到后台
		idList = new Array();
		$("input[type='checkbox']").each(function() {
			if (this.checked) {
				idList.push(this.value);
				orderIds = orderIds +this.value + ",";
			}
		});
		if (orderIds.length > 0) {
			orderIds = orderIds.substr(0, orderIds.length - 1);
	    }
		
        if(idList==null || idList.length==0){
        	alert("请选择要删除的数据！");
        	return false;
        }else{ 
        	
        	var callback=function(){
        		$.ajax({
        			type : 'post',
        			url : 'deleteOrdersByIds.do',
        			dataType : 'json',
        			data : {
        				orderIds : orderIds
        			},
        			cache:false,
    	    		success : function(data) {
    	    			if (data.success) {
    	    				window.location.href = "/A4print/jsp/admin/adminUserOrder.jsp";
    	        			//window.location.href = url + "currentPage="+currentPage+"";
    					}else{
    						if(data.message==""){
    							alert("连接服务器失败，请重试！");
    						}else{
    							alert(data.message);
    						}
    						 
    					}

    	    		},
        	        error: function(data) {
    	            		alert("连接服务器失败，请重试！");
    	    	    }
    	    		});
        	};
        	confirm("确定删除？",callback);
        }
};

/**
 * 功能：admin点击订单状态加载后请求数据
 */
function getOrdersByStates(orderState,orderNo,preTime,aftTime){
	$.ajax({
		cache : true,
		type : "post",
		async : true,
		url : "admin_searchOrders.do",
		dataType : 'json',
		data : {
			orderState:orderState,
			orderNo:orderNo,
			preTime:preTime,
			aftTime:aftTime
		},
		error : function(data) {
			alert("连接服务器失败，请重试！");
		},
		success : function(data) {
		if (data.success) {
			var orderArray = data.result;
			showOrders(orderArray);	
			}else{
				if(data.message==""){
					alert("连接服务器失败，请重试！");
				}else{
					alert(data.message);
				}
				 
			}
		}
	});
};

/**
 * 功能：将时间戳转成日期
 */
var stampToDate = function(stamp){
	var date = new Date(stamp);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = (date.getDate() < 10 ? "0" + date.getDate() + ' ':date.getDate()  + ' ');
	h = (date.getHours() < 10 ? "0" + date.getHours() + ':':date.getHours()  + ':');
	m = (date.getMinutes() < 10 ? "0" + date.getMinutes() + ':':date.getMinutes()  + ':');
	s = (date.getSeconds() < 10 ? "0" + date.getSeconds():date.getSeconds()); 
	return (Y+M+D+h+m+s); 
};

/**
 * 功能：通过文件名获得文件后缀名
 */
var getFileType = function(fileName){
	return fileName.substr(fileName.lastIndexOf(".")+1).toLowerCase();
};

$(function() {
	
	/**
	 * 功能：adminOrder.jsp页面加载后请求数据
	 */
	var getAllOrderData_admin = function(e){
		$.ajax({
			cache : true,
			type : "post",
			async : true,
			url : "admin_getAllOrders.do",
			dataType : 'json',
			data : {},
			error : function(data) {
				alert("连接服务器失败，请重试！");
			},
			success : function(data) {
			if (data.success) {
			   var orderArray = data.result;
			   showOrders(orderArray);	
				}else{
					if(data.message==""){
						alert("连接服务器失败，请重试！");
					}else{
						alert(data.message);
					}
					 
				}
			}
		});
	};

	/**
	 * 功能：adminOrder.jsp页面加载后请求数据
	 */
	$("#admin-order-table").ready(function(e){
		getAllOrderData_admin(e);
	});
	 //通过选中的多个订单id删除多个订单
    $("#admin-del-select-orders").click(function(e) {
    	deleteOrdersByIds(e);
    });
    //Tab导航选择订单查询方式
    $("li[role='presentation']").each(function(){
    	$(this).click(function(){
    		$("li[role='presentation']").each(function(){
        		$(this).removeClass("active");
        	});
    		$("#admin-order-table div.order-all").each(function(){
        		$(this).remove();
        	});
    		$(this).addClass("active");
    	});
    });
    /**
     * 功能：用户搜索订单
     */
    $("#admin-search-order").click(function(e) {
    	var state = $("li[role='presentation'][class='active'] a").attr("id");
    	state = state.substr(state.length-1,1);
    	var befTime = $("#admin-search-befTime").val();
    	var afterTime = $("#admin-search-aftTime").val();
    	var orderNo = $("#admin-search-orderNo").val();
    	$("#admin-order-table div.order-all").remove();
    	
    	getOrdersByStates(state,orderNo,befTime,afterTime);
    });
});