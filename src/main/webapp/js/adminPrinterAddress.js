// 功能：删除打印店的注册信息
var removePrinterInfo = function(userId){
	var callback=function(){
		$.ajax({
			type : "post",
			url : "removePrinterInfo.do",
			async : true,
			dataType: 'json',
			data : {
				userId:userId
			},
			cache : false,
			success : function(data) {
				if(data.success){
					$("#"+userId).remove();
				}else{
					alert(data.message);
				}
			}
		});
	};
	confirm("确认删除吗？",callback);
};

// 页面加载时获取必要信息
$(function() {
	// 异步获取打印店地址信息
	$.ajax({
		type : "post",
		url : "getAllPrinterInfo.do",
		async : true,
		dataType: 'json',
		data : {},
		cache : false,
		success : function(data) {
			var addressList = data.result;
			for(var i=0;i<addressList.length;i++){
				var addressWrap = $("<div class='col-md-6' id='" + addressList[i].userId + "'></div>");
				var addressWrapSection = $("<div class='printer-address-block'></div>");
				var addressImg = $("<div class='printer-cover col-md-2'><img src='"+addressList[i].printShopImage+"'></div>");
				var addressDescribe = $("<div class='printer-describe col-md-10'></div>");
				var addressTitle = $("<h4>"+addressList[i].printShopName+
						"<span class='glyphicon glyphicon-trash delete-address error' aria-hidden='true' " +
						"onclick=\"removePrinterInfo(\'"+addressList[i].userId+"\')\" title='删除'></span>" +
								"<a href='/A4print/jsp/admin/adminEditPrinter.jsp?id="+ addressList[i].id +"'><span class='glyphicon glyphicon-edit edit-address' aria-hidden='true' title='编辑'></span></a></h4>");
				var addressInfo = $("<p><span class='text-overflow' title='"
						+addressList[i].address+"'>地址："+addressList[i].address
						+"</span><br />联系人："+addressList[i].userName+"<br />邮箱："
						+addressList[i].users.email+"<br />电话："+addressList[i].teleNumber+"</p>");
				var clear = $("<div class='clear'></div>");
				addressDescribe.append(addressTitle).append(addressInfo);
				addressWrap.append(addressWrapSection);
				addressWrapSection.append(addressImg).append(addressDescribe).append(clear);
				$("#printer-list").append(addressWrap);
			}
		}
	});
});