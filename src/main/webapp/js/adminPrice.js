/**
 * 功能：校验输入价格是否正确 param:_keyword打印文档的价格 return：true 输入的价格正确
 */
var isPriceNumber = function(_keyword) {
	if (_keyword == "0" || _keyword == "0." || _keyword == "0.0"
			|| _keyword == "0.00") {
		_keyword = "0";
		return true;
	} else {
		var index = _keyword.indexOf("0");
		var length = _keyword.length;
		if (index == 0 && length > 1) {/* 0开头的数字串 */
			var reg = /^[0]{1}[.]{1}[0-9]{1,2}$/;
			if (!reg.test(_keyword)) {
				return false;
			} else {
				return true;
			}
		} else {/* 非0开头的数字 */
			var reg = /^[1-9]{1}[0-9]{0,10}[.]{0,1}[0-9]{0,2}$/;
			if (!reg.test(_keyword)) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
};

/**
 * 功能：添加价格时选择打印店
 */
$("#printer-name").click(
		function(e) {
			$.ajax({
				type : "post",
				url : "getAllPrinterInfo.do",
				dataType : "json",
				data : {},
				cache : false,
				success : function(data) {
					var addressList = data.result;
					for ( var i = 0; i < addressList.length; i++) {
						var option = $("<option value ='"
								+ addressList[i].userId + "'>"
								+ addressList[i].printShopName + "</option>");
						$("#printer-name").append(option);
					}
					$('#printer-name').unbind("click");
				}
			});
		});

/**
 * 功能：管理员添加价格,点击确认按钮
 */
$("#admin-add-price").click(function(e) {
	var printerId = $("#printer-name").val();
	var fileType = $("#filetype-choice").val();
	var priceType = $("#pricetype-choice").val();
	var price = $("#price").val();
	if (printerId.length != 32) {
		// alert("请选择正确的打印店信息！");
		$("#printer-name").parent().addClass("has-error");
		return false;
	} else {
		$("#printer-name").parent().removeClass("has-error");

	}
	if (price == "") {
		// alert("请输入打印价格！");
		$("#price").parent().addClass("has-error");
		return false;
	} else {
		$("#price").parent().removeClass("has-error");
	}
	// 判断字符串是否是数字
	if (!isPriceNumber(price)) {
		// alert("请输入符合要求的价格！");
		$("#price").val("");
		return false;
	}
	$.ajax({
		type : "post",
		url : "addPrices.do",
		dataType : "json",
		data : {
			printerId : printerId,
			fileType : fileType,
			priceType : priceType,
			price : price
		},
		cache : false,
		success : function(data) {
			if (data.success) {
				document.getElementById('pop-div').style.display = 'none';
				window.location.href = "getAllPrices.do";
			} else {
				// 提示信息
				tip(data.message);
			}
		}
	});
});

/*
 * 编辑价格信息
 */
function editPrice(priceId) {
	
	// 根据价格id返回其他信息

	$.ajax({
		type : "post",
		url : "getPriceById.do",
		data : {
			id : priceId
		},
		dataType : "json",
		success : function(data) {
			showDiv();
			$("#pop-div-title p").text("编辑打印价格");
			var option = $("<option value='" + data.result.printerId + "'>"
					+ $('#tr' + priceId + ' td:nth-child(2)').text()
					+ "</option>");
			$("#printer-name").append(option);
			$("#printer-name").val(data.result.printerId);
			$("#printer-name").attr("priceId", priceId);			
			$("#printer-name").attr("id", "disabledSelect");
			var filetype = data.result.fileType;
			var pricetype = $("#pricetype-choice");
			//清空下拉框
			pricetype.empty();
			if (filetype == 4) {
				pricetype.append('<option value="一寸">一寸</option>');
				pricetype.append('<option value="两寸">两寸</option>');
				pricetype.append('<option value="五寸">五寸</option>');
				pricetype.append('<option value="十寸">十寸</option>');
			} else {
				pricetype.append('<option value="黑白单面">黑白单面</option>');
				pricetype.append('<option value="黑白双面">黑白双面</option>');
				pricetype.append('<option value="彩色单面">彩色单面</option>');
				pricetype.append('<option value="彩色双面">彩色双面</option>');
			}
			$("#filetype-choice").val(data.result.fileType);
			$("#pricetype-choice").val(data.result.priceType);
			$("#price").val(data.result.price);

		},
		error : function() {
		}
	});
	$("#admin-add-price").unbind("click");
	$("#admin-add-price").attr('id', 'admin-edit-price');
	$("#admin-edit-price").click(adminEditPrice);
}
/*
 * 更新价格
 */
var adminEditPrice = function() {
	
	// 把更新后的信息传给后端
	var priceId = $("[name=printer-name]").attr("priceId");
	var printerId = $("[name=printer-name]").val();
	var fileType = $("#filetype-choice").val();
	var priceType = $("#pricetype-choice").val();
	var price = $("#price").val();
	var file = $("#filetype-choice").text();
	var priceTypeText = $("#pricetype-choice").text();

	// 校验价格
	if (price == "") {
		$("#price").parent().addClass("has-error");
		return false;
	} else {
		$("#price").parent().removeClass("has-error");
	}
	// 判断字符串是否是数字
	if (!isPriceNumber(price)) {
		tip("请输入符合要求的价格！");
		$("#price").val("");
		return false;
	}
	$.ajax({
		type : "post",
		url : "updatePrices.do",
		data : {
			id : priceId,
			printerId : printerId,
			fileType : fileType,
			priceType : priceType,
			price : price
		},
		dataType : "json",
		success : function(data) {
			if (data.success) {
				closeDiv();
				window.location.href = "getAllPrices.do";
			} else {
				alert(data.message);
			}
		}
	});

};

/**
 * 功能：删除单个价格
 */
function removePrice(priceId) {

	var callback = function() {
		$.ajax({
			cache : false,
			type : "post",
			url : "removePrice.do",
			dataType : 'json',
			data : {
				priceId : priceId
			},

			success : function(data) {
				if (data.success) {
					$("#tr" + priceId).remove();
				} else {
					alert(data.message);
					return false;
				}
			},
			error : function(data) {
				alert("删除出现错误，请重试！");
			}
		});
	};

	confirm("确认删除?", callback);
};

/**
 * 功能：删除多个价格
 */
$("#admin-del-multi-price").click(function(e) {
	var priceIds = [];
	$("input[type='checkbox']").each(function() {
		if (this.checked) {
			priceIds.push(this.value);			
		}
	});
	if(priceIds.length==0){
		alert("请选择要删除的数据！");
		return false;
	}else{		
		
			var callback=function(){
				$.ajax({
					cache : false,
					type : "post",
					url : "removePricesByIds.do",
					dataType : 'json',
					data : {
						priceIds : JSON.stringify(priceIds)
					},
					error : function(data) {
						// alert("请选择要删除的数据！");
					},
					success : function(data) {
						if (data.success) {
							for ( var i = 0; i < priceIds.length; i++) {
								$("#tr" + priceIds[i]).remove();
							}
						} else {
							alert(data.message);
						}
					},
					error:function(data){
						alert("哎呦，服务器出现了问题");
					}
				});
			};
			confirm("你真的要删除吗?",callback);	
	}
});

/*
 * 点击添加后弹出遮罩和添加价格窗口
 */
function showDiv() {
	showMask();
	$("#pop-div-title p").text("添加打印价格");
	document.getElementById('pop-div').style.display = 'block';
	$("#filetype-choice").change(function(e) {
		var filetype = e.target.value;
		var pricetype = $("#pricetype-choice");
		// 清空下拉框
		pricetype.empty();
		if (filetype == 4) {
			pricetype.append('<option value="一寸">一寸</option>');
			pricetype.append('<option value="两寸">两寸</option>');
			pricetype.append('<option value="五寸">五寸</option>');
			pricetype.append('<option value="十寸">十寸</option>');
		} else {
			pricetype.append('<option value="黑白单面">黑白单面</option>');
			pricetype.append('<option value="黑白双面">黑白双面</option>');
			pricetype.append('<option value="彩色单面">彩色单面</option>');
			pricetype.append('<option value="彩色双面">彩色双面</option>');
		}
	});
	// document.getElementById('bg').style.display='block';
};
function closeDiv() {
	document.getElementById('pop-div').style.display = 'none';
	$("#filetype-choice").unbind("click");
	hideMask();
};

