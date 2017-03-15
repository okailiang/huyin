//功能：通过文件名获得文件后缀名
var getFileType = function(fileName){
	return fileName.substr(fileName.lastIndexOf(".")).toLowerCase();
};

//所有文件计算总价
function getTotal(){
	var total = 0;
	$("#file-list tr").each(function(){
		var id = $(this).attr("id");
		getUnivalentbyId(id);
	});
	$(".univalent").each(function(){
		total += parseFloat($(this).text());
	});
	$("#file-money").text(total.toFixed(2)+"元");
	$("#file-money").removeAttr("class");
	
	$("#pay-choice").removeClass("hidden");
	//苏州市八折优惠
	if(!$("#discount").hasClass("hidden")&&$("#printer-city").find("option:selected").text()=="苏州市"){
		//小于1元免费打印
//		if(total <= 1){
//			$("#file-money").text("免费打印");
//			$("#file-money").attr("class","alert alert-danger");
//			$("#pay-choice").addClass("hidden");
//		}else{
        	total = total*0.8;
			//$("#discount").removeClass("hidden");
			$("#file-money").text(total.toFixed(2)+"元");
	//}
	}

	var fileMoney = parseFloat($("#file-money").text());
	$("#total-money").text(((isNaN(fileMoney)?0:fileMoney)+5).toFixed(2));
}

//每个文件按id计算单价
function getUnivalentbyId(id){
	var univalent = 0;
	var tr = $("#"+id);
	var page = parseInt(tr.find("#print-page").text());
	var copies = parseInt(tr.find("#print-copies").val());
	var choice = tr.find("#print-choice").val();
	var pay = tr.find("span.univalent");
	var perPrice = tr.find("span.per-price");
 	univalent = choice*page*copies;
	univalent = univalent.toFixed(2);
	perPrice.text(parseFloat(choice).toFixed(2));
	pay.text(univalent);
}
var filterFileType = function(fileTypeArr,fileType,fileType1){
	var len = fileTypeArr.length;
	var fileTypeTemp = [];
	for(var i= 0;i < len; i++){
		if(fileTypeArr[i]!=fileType && fileTypeArr[i]!=fileType1){
			fileTypeTemp.push(fileTypeArr[i]);
		}
	}
	return fileTypeTemp;
};
/**
 * 判断该用户啊在该打印店享受折扣是否用完
 */
var isDiscount = function(){
	if($("#printer-city").find("option:selected").text()=="苏州市"){
		var printerAddressId = $("#printer-name").val();
		//判断该用户啊在该打印店享受折扣是否用完
		$.ajax({
			type : 'post',
			url : 'getUserTodayOrders.do',
			dataType:'json',
			data : {
				printerAddressId : printerAddressId
			},
			cache : false,
			success : function(data) {
	            //允许有折扣
				if(data.success){
					if($("#discount").hasClass("hidden")){
						$("#discount").removeClass("hidden");
					}
				}else{
					if(!$("#discount").hasClass("hidden")){
						$("#discount").addClass("hidden");
					}
				}
			},
			error : function(data) {

			}
		});
	}else{
		if(!$("#discount").hasClass("hidden")){
			$("#discount").addClass("hidden");
		}
	}
};
/**
 * 获得对应打印店的打印价格
 */
var getPriterPrice = function(printerUserId){
	var fileArr = $(".order-file-name");
	var len = fileArr.length;
	var fileTypeArr = [];
	var fileName;
	//获得要打印文件的类型
	for(var i = 0;i < len;i++){
		fileName = fileArr[i].title;
		if(fileName!=null){
			fileName = getFileType(fileName);
			if(fileTypeArr.indexOf(fileName)==-1){
				fileTypeArr.push(fileName);
			}
		}
	}
	//根据文件类型加载文件的打印价格
	$.ajax({
		type : 'post',
		url : 'getPricesByParam.do',
		dataType:'json',
		data : {
			printerId : printerUserId,
			fileType : JSON.stringify(fileTypeArr)
		},
		cache : false,
		success : function(data) {
			var priceChoice = 	$("select[name='print-choice']");
			priceChoice.children().remove();
			if(data.success&&data.result.length>0){
                var imagePrintTip = "";
				//给对应要打印的文件添加价格
				priceChoice.each(function(e){
					var selectVal = $(this).attr("value");
					var fileType = getFileType(selectVal);
					var type="";
					var imageSize = "";
					//判断文件类型
					if(fileType==".doc"||fileType==".docx"){
						type ="1";
					}else if(fileType==".ppt"||fileType==".pptx"){
						type ="2";
					}else if(fileType==".pdf"){
						type ="3";
					}else if(fileType==".png"||fileType==".jpg"||fileType==".jpeg"){
						type ="4";
						imageSize = $(this).attr("val");
					}
					var priceArr = data.result;
					var len = priceArr.length;
					var imageType = "";
					//循环查找是否是给文件对应的类型
					for(var i = 0;i < len;i++){
						if(priceArr[i].fileType==type&&type!= 4){
							if(type ==1){
								fileTypeArr = filterFileType(fileTypeArr,".doc",".docx");	
							}else if(type ==2){
								fileTypeArr = filterFileType(fileTypeArr,".ppt",".pptx");	
							}else if(type ==3){
								fileTypeArr = filterFileType(fileTypeArr,".pdf",".pdf");	
							} 
							$(this.parentElement.nextElementSibling).find("span.per-price").text(priceArr[i].price);
							var option = $('<option value="'+priceArr[i].price+'">'+priceArr[i].priceType+'</option>');
							$(this).append(option);
						}else if(type==4){
							fileTypeArr = filterFileType(fileTypeArr,".png",".jpg");
							fileTypeArr = filterFileType(fileTypeArr,".jpeg",".jpeg");
							var priceType = priceArr[i].priceType;
							if(imageSize==1 && priceType.indexOf("一") == 0){
								imageType = priceType;
							}else if(imageSize==2 && priceType.indexOf("两") == 0){
								imageType = priceType;
							}else if(imageSize==5 && priceType.indexOf("五") == 0){
								imageType = priceType;
							}else if(imageSize==7 && priceType.indexOf("七") == 0){
								imageType = priceType;
							}else if(imageSize==10 && priceType.indexOf("十") == 0){
								imageType = priceType;
							}
							if(imageType!= ""){
							  var option = $('<option value="'+priceArr[i].price+'">'+imageType+'</option>');
							  $(this).append(option);
							  $(this).attr("disabled","disabled");
							  break;
							}
						}
					}
					if(type==4&&imageType== ""){
						imagePrintTip = imagePrintTip + imageSize + "寸、";
					}
				});
				//初始化单价和总价
				getTotal();
				var tip ="";
				if(imagePrintTip!=""){
					tip = tip + "<" + imagePrintTip.substring(0,imagePrintTip.length - 1) + ">照片";
			    }
				if(fileTypeArr.length!=0){
					tip = (tip + (imagePrintTip!="" ? "和" : ""));
					tip = tip + "<" + fileTypeArr.toString() + ">格式的文件";
				}
				if(fileTypeArr.length!=0||imagePrintTip!=""){
					alert("该店暂不支持" + tip + "打印！");
				}
			}else {
				alert("该店暂不支持<" + fileTypeArr.toString() + ">格式的文件打印！");
				return false;
			}
		},
		error : function(data) {

		}
	});
};
//选择配送方式
$("#delivery-one").change(function() {
	if($("#delivery-one").is(":checked")){
		$("#delivery-address").addClass("hidden");
		$("#if-deliveried").addClass("hidden");
	}
});
$("#delivery-two").change(function(){
	if($("#delivery-two").is(":checked")){
		$("#delivery-address").removeClass("hidden");
		$("#if-deliveried").removeClass("hidden");
		var fileMoney = parseFloat($("#file-money").text());
		$("#total-money").text(((isNaN(fileMoney)?0:fileMoney)+5).toFixed(2));
		//异步获取用户自己的地址信息
		$.ajax({
			type : 'post',
			url : 'getUserAddress.do',
			data : '',
			cache : false,
			success : function(data) {
				$("#address-list .radio").remove();
				var addressList = JSON.parse(data).result;
				var adddressTip = $("<p class='form-control-static error' id='no-address'>您还没有添加收获地址，请进行添加！</p>");
				//没有保存任何地址时
				if (addressList.length == 0) {
				    $("#address-list").children().remove();
					$("#address-list").append(adddressTip);
				}
				//依次读取地址，写入页面
				else {
					var radioDiv = $("<div class='radio'></div>");
					var addressLabel,addressInput,addressContent,addressTele;
					for (var i=0;i<addressList.length;i++){
						addressLabel = $("<label></label>");
						if(addressList[i].defaultAddress==1)
							addressInput = $("<input type='radio' name='address-choice' id='"+addressList[i].id+"' value='"+addressList[i].id+"' checked>");
						else
							addressInput = $("<input type='radio' name='address-choice' id='"+addressList[i].id+"' value='"+addressList[i].id+"'>");
						addressContent = $("<span>"+addressList[i].address+"－"+addressList[i].userName+"</span>");
						addressTele = $("</span><span class='label label-info address-telenumber'>"+addressList[i].teleNumber+"</span>");
						addressLabel.append(addressInput).append(addressContent).append(addressTele);
						radioDiv.append(addressLabel);
					}
					$("#address-list").append(radioDiv);
				}
				if($("#province").find("option").length==0)
					initAddress();
			},
			error : function(data) {
			}
		});
	}
});
//选择打印方式
$("#priority-one").change(function() {
	if($("#priority-one").is(":checked")){
		$("#pay-choice").removeClass("hidden");
		$("#total-pay").removeClass("hidden");
	//	$("#delivery-two").removeAttr("disabled");
	//	$("#delivery-two").parent().css({color:"#000"});
	}
});
$("#priority-two").change(function() {
	if($("#priority-two").is(":checked")){
		$("#pay-choice").addClass("hidden");
		$("#total-pay").addClass("hidden");
		$("#delivery-one").prop("checked","checked");
		$("#delivery-two").attr("disabled","disabled");
		$("#delivery-two").parent().css({color:"#aaa"});
		$("#delivery-address").addClass("hidden");
		$("#if-deliveried").addClass("hidden");
	}
});
//打印份数加减
$("#file-list tr span[name='minus-copy']").each(function(){
	$(this).click(function(){
		var copies = $(this).next();
		var copiesValue = parseInt(copies.val());
		var id = $(this).parent().parent().parent().attr("id");
		if(isNaN(copiesValue || copiesValue<2))
			copies.val(1);
		else if(copiesValue>=2)
			copies.val(copiesValue-1);
		getUnivalentbyId(id);
		getTotal();
	});
});

$("#file-list tr span[name='add-copy']").each(function(){
	$(this).click(function(){
		var copies = $(this).prev();
		var copiesValue = parseInt(copies.val());
		var id = $(this).parent().parent().parent().attr("id");
		if(isNaN(copiesValue) || copiesValue<1)
			copies.val(1);
		else if(copiesValue>=1)
			copies.val(copiesValue+1);
		getUnivalentbyId(id);
		getTotal();
	});
});

$("#print-copies").keyup(function(){
	var copies = $(this);
	var copiesValue = copies.val();
	if(isNaN(copiesValue) || copiesValue<1)
		copies.val(1);
});

$("#file-list tr input[name='copy-choice']").each(function(){
	$(this).keyup(function(){
		var copies = $(this);
		var copiesValue = parseInt(copies.val());
		var id = $(this).parent().parent().parent().attr("id");
		if(isNaN(copiesValue) || copiesValue<1)
			copies.val(1);
		getUnivalentbyId(id);
		getTotal();
	});
});

//打印类型改变
$("select[name='print-choice']").each(function(){
	$(this).change(function(){
		var id = $(this).parent().parent().attr("id");
		getUnivalentbyId(id);
		getTotal();
	});
});

//删除文件
$(".remove-fileid").each(function(){
	$(this).click(function(){
		$(this).parent().parent().remove();
		getTotal();
		if($(".remove-fileid").length==0){
			window.location.href="/A4print/jsp/users/printFile.jsp";
		}
	});
});

//地址校验标志位
var addressFlag = [false,false,false,false];
//显示隐藏提示
function showandhide(show, hide) {
	$(show).removeClass("hidden");
	$(hide).addClass("hidden");
}
//依次校验标志数组
function validateFlag(flag) {
	var statu = true;
	for(var i=0;i<flag.length;i++){
		statu = statu && flag[i];
	}
	return statu;
}
//街道地址校验
function addressStreetValidation(){
	var street = $("#street").val();
	var streetreg = /^([\u4e00-\u9fa50-9a-zA-Z_]+)$/;
	if(street==""){
		$("#street").parent().addClass("has-error");
		addressFlag[0]=false;
	}
	else if(!streetreg.test(street)){
		$("#street").parent().addClass("has-error");
		addressFlag[0]=false;
		}
	else{
		$("#street").parent().removeClass("has-error");
		addressFlag[0]=true;
	}
	validateFlag(addressFlag);
}
//手机校验
function addressTelenumberValidation(){
	var telenumber = $("#address-telenumber").val();
	var telenumberreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(telenumber==""){
		$("#alert-address-telenumber-message").text("手机号码不能为空");
		showandhide("#alert-address-telenumber","#alert-address-telenumber-ok");
		$("#address-telenumber").parent().addClass("has-error");
		addressFlag[1]=false;
	}
	else if(telenumber.length!=11){
		$("#alert-address-telenumber-message").text("手机号码长度应为11位数字");
		showandhide("#alert-address-telenumber","#alert-address-telenumber-ok");
		$("#address-telenumber").parent().addClass("has-error");
		addressFlag[1]=false;
	}
	else if(!telenumberreg.test(telenumber)){
		$("#alert-address-telenumber-message").text("手机号码格式不正确");
		showandhide("#alert-address-telenumber","#alert-address-telenumber-ok");
		$("#address-telenumber").parent().addClass("has-error");
		addressFlag[1]=false;
	}
	else {
		showandhide("#alert-address-telenumber-ok","#alert-address-telenumber");
		$("#address-telenumber").parent().removeClass("has-error");
		addressFlag[1]=true;
    }
	validateFlag(addressFlag);
}
//收件姓名校验
function usernameValidation(){
	var username = $("#address-username").val();
	var usernamereg = /^([\u4e00-\u9fa5]+)$/;
	if(username==""){
		$("#alert-address-username-message").text("收件姓名不能为空");
		showandhide("#alert-address-username","#alert-address-username-ok");
		$("#address-username").parent().addClass("has-error");
		addressFlag[2]=false;
	}
	else if(username.length<2 || username.length>6){
		$("#alert-address-username-message").text("收件姓名在2到6个字符");
		showandhide("#alert-address-username","#alert-address-username-ok");
		$("#address-username").parent().addClass("has-error");
		addressFlag[2]=false;
	}
	else if(!usernamereg.test(username)){
		$("#alert-address-username-message").text("收件姓名应该全部为汉字");
		showandhide("#alert-address-username","#alert-address-username-ok");
		$("#address-username").parent().addClass("has-error");
		addressFlag[2]=false;
	} else{
		showandhide("#alert-address-username-ok","#alert-address-username");
		$("#address-username").parent().removeClass("has-error");
		addressFlag[2]=true;
	}
	validateFlag(addressFlag);
}
//省市校验
function provinceCityValidation(){
	var province = $("#province").val();
	var city = $("#city").val();
	if(province==-1||city==-1){
		$("#province").parent().addClass("has-error");
		$("#city").parent().addClass("has-error");
		$("#area").parent().addClass("has-error");
		addressFlag[3]=false;
	} else{
		$("#province").parent().removeClass("has-error");
		$("#city").parent().removeClass("has-error");
		$("#area").parent().removeClass("has-error");
		addressFlag[3]=true;
	}
	validateFlag(addressFlag);
}

//各种回调校验和表单提交
$("#street").keyup(function(){
	addressStreetValidation();
});
$("#address-telenumber").keyup(function(){
	addressTelenumberValidation();
});
$("#address-username").keyup(function(){
	usernameValidation();
});
$("#address-form").submit(function(e){
	addressStreetValidation();
	addressTelenumberValidation();
	usernameValidation();
	provinceCityValidation();
	if(!validateFlag(addressFlag)){
		e.preventDefault();
	}
});

//清空表单
function clearAddress(){
	//初始化级联选择列表
	var option2 = $("<option value ='-1'>请选择市</option>");
	//清空省
	$("#province").val("-1");
	//清空市
	$("#city").find("option").remove();
	$("#city").append(option2);
	//清空区
	$("#area").find("option").remove();
	$("#area").addClass("hidden");
	//清空其他内容
	$("#street").val("");
	$("#address-telenumber").val("");
	$("#address-username").val("");
}
function clearPrinterAddress(){
	//初始化级联选择列表
	var option2 = $("<option value ='-1'>请选择市</option>");
	//清空省
	$("#printer-province").val("-1");
	//清空市
	$("#printer-city").find("option").remove();
	$("#printer-city").append(option2);
	//清空区
	$("#printer-name").find("option").remove();
	$(".select2").addClass("hidden");
	$("#alert-printer-address").addClass("hidden");
}

//弹出收起地图
$("#address-map-btn").click(function(){
	if($("#l-map").hasClass("hidden")){
		$("#l-map").removeClass("hidden");
	}
	else{
		$("#l-map").addClass("hidden");
	}
});

//添加地址弹出表单
$("#add-address-btn").click(function() {
	$("#add-address-btn").addClass("hidden");
	$("#add-address-form").removeClass("hidden");
});
//取消保存隐藏表单
$("#address-cancel-btn1").click(function() {
	$("#add-address-btn").removeClass("hidden");
	$("#add-address-form").addClass("hidden");
	clearAddress();
});

//初始化级联选择列表
function initAddress(){
	//查询省，添加默认选项
	$.ajax({
		type : "post",
		url : "getBaseAddress.do",
		async : true,
		dataType: 'json',
		data : {addressPid: 0},
		cache : false,
		success : function(data) {
			var addressList = data.result;
			var option;
			var option1 = $("<option value ='-1'>请选择省</option>");
			var option2 = $("<option value ='-1'>请选择市</option>");
			$("#province").append(option1);
			$("#city").append(option2);
			for(var i=1;i<addressList.length;i++){
				option = $("<option value ='"+addressList[i].id+"'>"+addressList[i].name+"</option>");
				$("#province").append(option);
			}
		}
	});
}

//级联选择省市
$("#province").change(function(){
	var pid = $("#province").val();
	//判断省的选择是否有意义
	if(pid!=-1){
		$.ajax({
			type : "post",
			url : "getBaseAddress.do",
			async : true,
			dataType: "json",
			data : {addressPid: pid},
			cache : false,
			success : function(data) {
				var addressList = data.result;
				var option;
				var option2 = $("<option value ='-1'>请选择市</option>");
				//重新选择市
				$("#city").find("option").remove();
				$("#city").append(option2);
				//重新选择区
				$("#area").find("option").remove();
				//第一次选择省时，查询第一个市与区
				for(var i=0;i<addressList.length;i++){
					option = $("<option value ='"+addressList[i].id+"'>"+addressList[i].name+"</option>");
					$("#city").append(option);
				}
				$("#city").val(addressList[0].id);
				$.ajax({
					type : "post",
					url : "getBaseAddress.do",
					async : true,
					dataType: "json",
					data : {addressPid: addressList[0].id},
					cache : false,
					success : function(data) {
						var areaList = data.result;
						if(areaList.length>0){
							for(var i=0;i<areaList.length;i++){
								option = $("<option value ='"+areaList[i].id+"'>"+areaList[i].name+"</option>");
								$("#area").append(option);
							}
							$("#area").val(areaList[0].id);
							$("#area").removeClass("hidden");
						}
						else{
							$("#area").addClass("hidden");
						}
					}
				});
				provinceCityValidation();
			}
		});
	}
	else{
		clearAddress();
		provinceCityValidation();
	}
});

//级联选择市区
$("#city").change(function(){
	var cid = $("#city").val();
	//判断市的选择是否有意义
	if(cid!=-1){
		$.ajax({
			type : "post",
			url : "getBaseAddress.do",
			async : true,
			dataType: "json",
			data : {addressPid: cid},
			cache : false,
			success : function(data) {
				//重新选择区
				var addressList = data.result;
				$("#area").find("option").remove();
				if(addressList.length>0){
					for(var i=0;i<addressList.length;i++){
						var option = $("<option value='"+addressList[i].id+"'>"+addressList[i].name+"</option>");
						$("#area").append(option);
					}
					$("#area").val(addressList[0].id);
					$("#area").removeClass("hidden");
				}
				provinceCityValidation();
			}
		});
	}
	else{
		//重新选择区
		$("#area").find("option").remove();
		$("#area").addClass("hidden");
		provinceCityValidation();
	}
});

//判断市区下面是否存在打印店
function printerValidation(printers){
	$("#select2-printer-name-container").remove()
	$("#printer-name").empty();
	var data = [];
	var option = {};
	if(printers.length>0){
		for(var i=0;i<printers.length;i++){
			option = {id: printers[i].id, text: printers[i].printShopName};
			data.push(option);
			option = {id: printers[i].id, userId: printers[i].userId, longitude: printers[i].longitude, latitude: printers[i].latitude};
			printShops.push(option);
		}
		$("#printer-name").select2({
		  data: data,
		});
		$("#alert-printer-address").addClass("hidden");
		$(".select2").removeClass("hidden");
	}
	else{
		$(".select2").addClass("hidden");
		$("#alert-printer-address").removeClass("hidden");
	}
}

//全局数组存取市区下的所有打印店
var printShops=[];

//级联选择省市－打印机
$("#printer-province").change(function(){
	var priceChoice = 	$("select[name='print-choice']");
	priceChoice.children().remove();
	var pid = $("#printer-province").val();
	printShops=[];
	//判断省的选择是否有意义
	if(pid!=-1){
		$.ajax({
			type : "post",
			url : "getBaseAddress.do",
			async : true,
			dataType: "json",
			data : {addressPid: pid},
			cache : false,
			success : function(data) {
				var addressList = data.result;
				var option;
				var option2 = $("<option value ='-1'>请选择市</option>");
				//重新选择市
				$("#printer-city").find("option").remove();
				$("#printer-city").append(option2);
				//重新选择区
				$("#printer-name").find("option").remove();
				//第一次选择省时，查询第一个市区
				for(var i=0;i<addressList.length;i++){
					option = $("<option value ='"+addressList[i].id+"'>"+addressList[i].name+"</option>");
					$("#printer-city").append(option);
				}
				$("#printer-city").val(addressList[0].id);
				$.ajax({
					type : "post",
					url : "getPrinterAddressesByCity.do",
					async : true,
					dataType: "json",
					data : {
						cityAreaId: addressList[0].id,
						address: ""
					},
					cache : false,
					success : function(data) {
						var areaList = data.result;
						printerValidation(areaList);
						
						if(areaList.length!=0){
							if(!notPrinter(areaList[0].printShopName)){
									return false;
							};
							isDiscount();
							getPriterPrice(areaList[0].userId);
						}
						getBaseData(areaList);
					}
				});
			}
		});
	}
	else{
		clearPrinterAddress();
	}
});

//级联选择市区－打印机
$("#printer-city").change(function(){
	var priceChoice = 	$("select[name='print-choice']");
	priceChoice.children().remove();
	var cid = $("#printer-city").val();
	printShops=[];
	//判断市的选择是否有意义
	if(cid!=-1){
		$.ajax({
			type : "post",
			url : "getPrinterAddressesByCity.do",
			async : true,
			dataType: "json",
			data : {       
				cityAreaId: cid,
				address: ""
			},
			cache : false,
			success : function(data) {
				//重新选择区
				var addressList = data.result;
				printerValidation(addressList);
				
				if(addressList.length!=0){
					if(!notPrinter(addressList[0].printShopName)){
							return false;
					};
					isDiscount();
					getPriterPrice(addressList[0].userId);
				}
				getBaseData(addressList);
			}
		});
	}
	else{
		//重新选择区
		$("#printer-name").find("option").remove();
		$(".select2").addClass("hidden");
		$("#alert-printer-address").addClass("hidden");
	}
});

//选择打印机
$("#printer-name").change(function(){
	var printerId = $("#printer-name").val();
	var printerUserId;
	for(var i=0;i<printShops.length;i++){
		if(printerId==printShops[i].id)
			printerUserId = printShops[i].userId;
	}

	printerSelected(printShops,printerId);
	if(!notPrinter($("#printer-name option:selected").text())){
		return false;
	};
	isDiscount();
	//获得对应打印店的打印价格
	getPriterPrice(printerUserId);
});

//增加用户地址
//确定保存隐藏表单，回写数据
$("#save-address-btn").click(function(e) {
	
	addressStreetValidation();
	addressTelenumberValidation();
	usernameValidation();
	provinceCityValidation();
	
	if(validateFlag(addressFlag)){
		var address,addressTelenumber,cityAreaId,userName;
		var province = $("#province").find("option:selected").text();
		var city = $("#city").find("option:selected").text();
		var area = $("#area").find("option:selected").text();
		if(area!=""){
			address = province+"／"+city+"／"+area+"／"+$("#street").val();
			cityAreaId = $("#area").val();
			addressTelenumber = $("#address-telenumber").val();
			userName = $("#address-username").val();
		}
		else{
			address = province+"／"+city+"／"+$("#street").val();
			cityAreaId = $("#city").val();
			addressTelenumber = $("#address-telenumber").val();
			userName = $("#address-username").val();
		}
		$.ajax({
			cache : true,
			type : "post",
			async : true,
			url : "saveAddress.do",
			dataType : 'json',
			data : {
				address: address,
				telenumber: addressTelenumber,
				cityAreaId: cityAreaId,
				userName: userName
			},
			error : function(data) {
              alert("地址保存失败！");
			},
			success : function(data) {
				var addressInfo = data.result;
				if (data.success) {
					if($("#address-list").find("label").length>0){
						$("#address-list label input").removeAttr("checked");
						var addressLabel = $("<label></label>");
						var addressInput = $("<input type='radio' name='address-choice' id='"+addressInfo.id+"' value='"+addressInfo.id+"' checked>");
						var addressContent = $("<span>"+addressInfo.address+"－"+addressInfo.userName+"</span>");
						var addressTele = $("</span><span class='label label-info address-telenumber'>"+addressInfo.teleNumber+"</span>");
						addressLabel.append(addressInput).append(addressContent).append(addressTele);
						$("#address-list").find(".radio").append(addressLabel);
						clearAddress();
					}
					else{
						$("#address-list p").remove();
						var radioDiv = $("<div class='radio'></div>");
						var addressLabel = $("<label></label>");
						var addressInput = $("<input type='radio' name='address-choice' id='"+addressInfo.id+"' value='"+addressInfo.id+"' checked>");
						var addressContent = $("<span>"+addressInfo.address+"－"+addressInfo.userName+"</span>");
						var addressTele = $("</span><span class='label label-info address-telenumber'>"+addressInfo.teleNumber+"</span>");
						addressLabel.append(addressInput).append(addressContent).append(addressTele);
						radioDiv.append(addressLabel);
						$("#address-list").append(radioDiv);
						clearAddress();
					}
				}else{
					alert("地址保存失败！");
				}
			}
		});
		$("#add-address-btn").removeClass("hidden");
		$("#add-address-form").addClass("hidden");
	}
	else{
		e.preventDefault();
	}
});
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
//判断订单状态是否改变
function isOrderPaid(orderNo){
	$.ajax({
		type : "post",
		url : "getOrderInfoByNo.do",
		async : true,
		dataType: "json",
		data : {
			orderNo:orderNo
		},
		cache : false,
		success : function(data) {
			if(data.success&&(data.result.orderState!=1)){
				paySuccessTip("支付成功");
				setTimeout("window.location.href='jsp/users/doneOrder.jsp?orderNo=" + orderNo+"'",3000);
				
			}else{
				//if(data.result.users.role==1){
					payFalseTip("支付失败");	
				 	setTimeout("window.location.href='jsp/users/order.jsp'",3000);
					
//				}else if(data.result.users.role==4){
//					payFalseTip("支付失败");	
//					setTimeout("window.location.href='jsp/users/printFile.jsp'",3000);
//					
//				}
				
			}
		}
	});
};

//提交要保存的订单数据
function submitSaveOrder(e){
	e.stopPropagation();
	e.preventDefault();
	var fileIdArray = new Array();
	var fileNameArray = new Array();
	var perFilePagesArray = new Array();
	var perFileCopiesArray = new Array();
	var perTotalPriceArray = new Array();
	var perPrintTypeArray = new Array();
	var perPriceTypeArray = new Array();
	var directPrint = "";
	var addressId ="";
	$("input[name='address-choice']").each(function(){
		if($(this).is(":checked"))
			addressId = $(this).val();
	});
	$("input[name='priority-choice']").each(function(){
		if($(this).is(":checked"))
			directPrint = $(this).val();
	});
	var printerAddressId = $("#printer-name").val();
	if(printerAddressId==null){
		alert("请选择有效的打印店！");
		return false;
	}
	var deliveryWay = $("#delivery-one").is(":checked")?1:2;
	if(deliveryWay==2&&addressId==""){
		alert("请添加收货地址！");
		return false;
	}
	var payWay = $("select[name='pay-choice']").val();
	var totalPrice = $("#delivery-one").is(":checked")?$("#file-money").text():$("#total-money").text();
	var printType="";
	var perPrice ="";
	$("#file-list tr").each(function(){
		fileIdArray.push($(this).attr("id"));
		fileNameArray.push($(this).find("#file-name").text());
		perFilePagesArray.push($(this).find("#print-page").text());
		perFileCopiesArray.push($(this).find("#print-copies").val());
		perTotalPriceArray.push($(this).find("span.univalent").text());
		//选中的价格
		perPrice = $(this).find("#print-choice").val();
		//选中的文本
		printType = $(this).find("#print-choice option:selected").text();
		if(perPrice==null){
			alert("该打印店暂不能打印<"+$(this).find("#file-name").text()+">文件,请移除重新选择！");
			return false;
		}
		perPriceTypeArray.push(perPrice);
		perPrintTypeArray.push(printType);
	});
	if(printType==null||perPrice==null){
		return false;
	}
	if(fileIdArray.length==0||fileIdArray.length==0
			||fileIdArray.length==0||fileIdArray.length==0||fileIdArray.length==0){
		alert("请添加文件！");
		return false;
	}
	if($("#order-remark").parent().hasClass("has-error")){
		return false;
	}
	//获得备注信息
	var remark = $("#order-remark").val();
	var orderNo='';
	$.ajax({
		cache : false,
		type : "post",
		async : true,
		url : "saveOrder.do",
		dataType : 'json',
		data : {
			userAddressId : addressId,
			directPrint : directPrint,
			printerAddressId : printerAddressId,
			deliveryWay : deliveryWay,
			payWay : payWay,
			totalPrice : isNaN(parseFloat(totalPrice))?totalPrice:parseFloat(totalPrice),
			discount:$("#discount").hasClass("hidden")?0:1,
			fileIdArray : JSON.stringify(fileIdArray),
			fileNameArray : JSON.stringify(fileNameArray),
			perFilePagesArray : JSON.stringify(perFilePagesArray),
			perFileCopiesArray : JSON.stringify(perFileCopiesArray),
			perTotalPriceArray : JSON.stringify(perTotalPriceArray),
			perPrintTypeArray : JSON.stringify(perPrintTypeArray),
			perPriceTypeArray : JSON.stringify(perPriceTypeArray),
			remark : remark
		},
		error : function(data) {
            alert("连接服务器失败，请重试！");
		}, 
		success : function(data,result,xhr) {
			if (data.success) {
				var orderId;
				var totalPrice;
				if((data.result) instanceof Array){
				   orderNo = data.result[0].orderNo;
				   orderId = data.result[0].id;
				   totalPrice = data.result[0].totalPrice;
				}else{
				   orderNo = data.result.orderNo;
				   orderId = data.result.id;
				   totalPrice = data.result.totalPrice;
				}
				if(data.result.directPrint==1){
					if(data.result.totalPrice=="0.00"){
						$("#pay-tip").hide();
						$("#mask").hide();
						isOrderPaid(data.result.orderNo);
					}else{
						var param = "WIDout_trade_no=" + orderNo
						+ "&WIDsubject=打印文件" + "&WIDtotal_fee="
						+ totalPrice + "&WIDbody=打印文件";
			            var a = $("<a href='"+"/A4print/jsp/alipay/alipayapi.jsp?" + param+"' target='_blank'></a>").get(0);      
			            var e = document.createEvent('MouseEvents');
			            e.initEvent('click', true, true);
			            a.dispatchEvent(e);
					}
				}
				else
					window.location.href = "/A4print/jsp/users/order.jsp";
			}else{
				if(data.message==""){
					alert("连接服务器失败，请重试！");
				}else{
					alert(data.message);
				}
			}
		}
	});
	if(directPrint==1&&totalPrice!="免费打印"){
		showPayTip();
		$("#pay-success-tip").click(function() {
			closePayTip();
			isOrderPaid(orderNo);
		});
		$("#pay-error-tip").click(function() {
			closePayTip();
			isOrderPaid(orderNo);
		});
	}
};

//保存订单信息
$("#order-submit").click(function(e) {
	submitSaveOrder(e);
});
var notPrinter = function(printShopName){
	if(printShopName.indexOf("思贤楼312")!=-1){
				alert("维护中！思贤楼312暂停打印，请耐心等待！");
				$("#order-submit").attr("disabled","");
	         	//$(".dialog-console").hide();
		        $(".dialog-close").hide();	
				return false;
	}else{
		$("#order-submit").removeAttr("disabled");
	}
	return true;
};
/** 
 * 返回字符的字节长度（汉字算2个字节） 
 * @param {string} 
 * @returns {number} 
 */
var getByteLen = function (val) { 
    var len = 0; 
    for (var i = 0; i < val.length; i++) { 
        if (val[i].match(/[^x00-xff]/ig) != null) //全角 
            len += 2; 
        else
            len += 1; 
    }; 
    return len; 
};
/**
 * 监听订单备注的输入字符个数
 */
$("#order-remark").change(function(){
	var remark  = $(this).val();
	if(getByteLen(remark)>100){
		$(this).parent().addClass("has-error");
	}else{
		$(this).parent().removeClass("has-error");
	}
});
//页面加载时获取必要信息
$(function() {
	//异步获取打印机管理员地址信息
	$.ajax({
		type : "post",
		url : "locatePrinterAddress.do",
		async : true,
		dataType: 'json',
		data : {addressPid: 0},
		cache : false,
		success : function(data) {
			var province = $("#printer-province");
	        var city = $("#printer-city");
	        province.append($("<option value ='-1'>请选择省</option>"));
	        city.append($("<option value ='-1'>请选择市</option>"));
			
	        var addressList = data.result;
		    var proviceArr = addressList.province;
		    var cityArr = addressList.city;
		    var currentCity = addressList.currentCity;
		    proviceArr = addressList.province;
		    cityArr = addressList.city;
		    var option;
		    for(var i = 0;i < proviceArr.length; i++){
		    	option = $("<option value ='"+proviceArr[i].id+"'>"+proviceArr[i].name+"</option>");
		    	province.append(option);
		    }
			if(cityArr==null){
				return false;
			}
		    for(var i = 0;i < cityArr.length; i++){
		    	option = $("<option value ='"+cityArr[i].id+"'>"+cityArr[i].name+"</option>");
		    	city.append(option);
		    }
		    //选中默认
		    province.val(currentCity.pid);
		    city.val(currentCity.id);
			$("#printer-name").select2({placeholder: "请选择一个打印店"});
			$.ajax({
				type : "post",
				url : "getPrinterAddressesByCity.do",
				async : true,
				dataType: "json",
				data : {
					cityAreaId: currentCity.id,
					address: ""
				},
				cache : false,
				success : function(data) {
					var areaList = data.result;
					printerValidation(areaList);
					
					if(areaList.length!=0){
						if(!notPrinter(areaList[0].printShopName)){
							return false;
						};
						isDiscount();
					    getPriterPrice(areaList[0].userId);
					}
					getBaseData(areaList);
				}
			});
			
		}
	});
});