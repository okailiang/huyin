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
/**
 * 功能：转换 UTF8 编码
 */
var toUtf8 = function(str) {    
    var out, i, len, c;    
    out = "";    
    len = str.length;    
    for(i = 0; i < len; i++) {    
        c = str.charCodeAt(i);    
        if ((c >= 0x0001) && (c <= 0x007F)) {    
            out += str.charAt(i);    
        } else if (c > 0x07FF) {    
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));    
            out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));    
            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
        } else {    
            out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));    
            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
        }    
    }    
    return out;    
};
/**
 * 功能：显示订单详情
 */
var showOrder = function(data){
	   var orderArray = data.result[0];
	   var orderArrLen = orderArray.length;
	   var orderFileRM;
	   var orderFileRMLen;
	   var printType = "";
	   var payWay = "";
	   var orderState = "";
	   var deliveryWay = "";
	   for(var i = 0;i < orderArrLen; i++){
		   var html = "";
		   html += '<div class="order-all" id="order-'+orderArray[i].id+'">';
		   html += '  <div class="order-header">';
		   html += '    <p class="order-discription">';
		   html += '    订单编号：<span class="order-num">'+ orderArray[i].orderNo;
		   html += '    </span>&nbsp;&nbsp;创建时间：'+ stampToDate(orderArray[i].createTime);
		   html += '    </p>';
		   html += '  </div>';
		   html += '<div class="table-responsive">';
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
		   for(var j= 0;j < orderFileRMLen;j++){
			   html += '  <tbody>';
			   html += '    <tr>';
			   html += '    <td><img src = "images/fileType/'+getFileType(orderFileRM[j].fileName)+'.png"></td>';
			   html += '      <td title="'+orderFileRM[j].fileName+'">'+ orderFileRM[j].fileName +'</td>';
			   html += '      <td>'+ orderFileRM[j].filePages +'</td>';
			   html += '      <td>'+ orderFileRM[j].perFileCopies+'</td>';
			   html += '      <td>'+ orderFileRM[j].printType + '</td>';
			   html += '      <td>'+ orderFileRM[j].perFilePrice +'</td>';
			   html += '    </tr>';
		       html += '  </tbody>';
		  }
		  if(orderArray[i].payWay == "1"){
			   payWay = "支付宝";
		   }else if(orderArray[i].payWay == "2" ){
			   payWay = "微信支付";
		   }else if(orderArray[i].payWay == "3" ){
			   payWay = "网银支付";
		   }
		   if(orderArray[i].orderState == "1"){
			   orderState = "未支付";
		   }else if(orderArray[i].orderState == "2" ){
			   orderState = "未打印";
		   }else if(orderArray[i].orderState == "3" ){
			   orderState = "完成";
		   }else if(orderArray[i].orderState == "4" ){
			   orderState = "已取消";
		   }
		   if(orderArray[i].deliveryWay == "1"){
			   deliveryWay = "自取";
		   }else if(orderArray[i].deliveryWay == "2" ){
			   deliveryWay = "配送";
		   }
		   html += ' </table>';
		   html += '</div>';
		   html += '<div class="order-footer">';
		   html += '   <p><strong>打印地址：</strong>'+orderArray[i].userAddressRM.printShopName+"      " + orderArray[i].userAddressRM.address+"&nbsp;&nbsp;<span class='label label-info'>" + orderArray[i].userAddressRM.teleNumber +'</span></p>';
		   if(orderArray[i].deliveryWay == "1"){
			   html += '     <p><strong>配送方式：</strong>'+ deliveryWay +'</p>';
		   }else if(orderArray[i].deliveryWay == "2" ){
			   html += '   <p><strong>配送地址：</strong>'+ orderArray[i].address.address+"      " 
			           + orderArray[i].address.userName+"&nbsp;&nbsp;<span class='label label-info'>" + orderArray[i].address.teleNumber +'</span></p>';
		   }
		   html += '   <p>';
		   html += '     <strong>支付方式：</strong>'+ payWay +'<span class="order-state"><strong>状态：</strong>' + orderState +'';
		   html += '     </span>&nbsp;&nbsp;&nbsp;&nbsp;<strong>总计：</strong>'+ orderArray[i].totalPrice;
		   html += '   </p>';
		   html += '<p>';
			//显示备注信息
			if(orderArray[i].remark!=""){
				html += '<span class="text-overflow" title="'+ orderArray[i].remark +'"><strong>备注：</strong>'+ orderArray[i].remark +'</span>';
			}
			html += '</p>';
		   html += '</div>';
		   html += '</div>';
		   $("#order-paid-table").after(html);
	    }
	   //显示领取打印文件的确认信息二维码
	   $("#qrcode").qrcode({width: 240, height: 240, text:data.result[1], src:"images/logo.png"});
};

$(function(e) {
	//order.jsp页面加载后请求数据
    var orderNo = $("#done-order-number").attr('value');
	$.ajax({
		cache : false,
		type : "post",
		async : true,
		url : "getOrderByOderNo.do",
		dataType : 'json',
		data : {
			orderNo:orderNo
		},
		error : function(data) {
			alert("连接服务器失败，请重试！");
		},
		success : function(data) {
		if (data.success) {
			//显示订单详情
			showOrder(data);	
			}else{
				if(data.message==""){
					alert("连接服务器失败，请重试！");
				}else{
					alert(data.message);
				}
			}
		}
	});
});