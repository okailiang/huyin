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

//添加地址弹出表单
$("#add-address-btn").click(function() {
	$("#add-address-div").addClass("hidden");
	$("#add-address-form").removeClass("hidden");
	$("#address-title").text("添加新地址");
	$("#save-address-submit").removeClass("hidden");
	$("#modify-address-submit").addClass("hidden");
});
//取消保存隐藏表单
$("#address-cancel-btn1").click(function() {
	$("#add-address-div").removeClass("hidden");
	$("#add-address-form").addClass("hidden");
	clearAddress();
});
$("#address-cancel-btn2").click(function() {
	$("#add-address-div").removeClass("hidden");
	$("#add-address-form").addClass("hidden");
	$("#address-list tr").css("background-color","#fff");
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

//删除用户地址
function deleteUserAddress(addressId) {
	var addressTarget = $("#" +addressId);
	 if(confirm("确认删除?")){
		 $.ajax({	
			cache : true,
			type : "post",
			async : true,
			url : "removeAddressById.do",
			dataType : 'json',
			data : {
				addressId : addressId
			},
			error : function(data) {
	             alert("删除出现错误，请重试！");
			},
			success : function(data) {
				if (data.success) {
					if($("#address-list").find("tr").length>1){
						addressTarget.remove();
					}
					else{
						$("#address-list table").remove();
						var addressListWrap = $("<p class='form-control-static error'>您还没有添加收件地址，请进行添加！</p>");
						$("#address-list").append(addressListWrap);
					}
				}else{
					alert(data.message);
					if($("#address-list").find("tr").length>1) {
						addressTarget.remove();
					}
					else {
						$("#address-list table").remove();
						var addressListWrap = $("<p class='form-control-static error'>您还没有添加收件地址，请进行添加！</p>");
						$("#address-list").append(addressListWrap);
					}
				}
			}
		});
	 }
};

//修改用户默认地址
function setDefaultAddress(addressId) {
	var defaultAddressId = $(".default-address-row").prop("id");
	var defaultAddress = $("#"+defaultAddressId);
	var addressTarget = $("#" +addressId);
	var addressSetTd;
	if(confirm("确认修改默认地址?")){
		 $.ajax({	
			cache : false,
			type : "post",
			async : true,
			url : "setDefaultAddress.do",
			dataType : 'json',
			data : {
				addressId : addressId
			},
			error : function(data) {
	             alert("修改出现错误，请重试！");
			},
			success : function(data) {
				if (data.success) {
					// 处理新的默认地址
					addressTarget.removeClass("other-address-row");
					addressTarget.addClass("default-address-row");
					addressTarget.find(".set-default").remove();
					addressSetTd = $("<td class='set-default'><span class='label label-warning default-address'>默认地址</span></td>");
					addressTarget.append(addressSetTd);
					// 处理旧的默认地址
					defaultAddress.removeClass("default-address-row");
					defaultAddress.addClass("other-address-row");
					defaultAddress.find(".set-default").remove();
					addressSetTd = $("<td class='set-default'><a class='label other-address hidden' href='javascript:void(0);' onclick='setDefaultAddress(\""+defaultAddressId+"\")'>设为默认</a></td>");
					defaultAddress.append(addressSetTd);
					defaultAddress.mouseover(function(){
						$(this).find(".other-address").removeClass("hidden");
					});
					defaultAddress.mouseout(function(){
						$(this).find(".other-address").addClass("hidden");
					});
				}else{
					alert(data.message);
					addressTarget.remove();
				}
			}
		});
	 }
};

//弹出修改框进行修改
function editUserAddress(addressId) {
	$("#add-address-div").addClass("hidden");
	$("#add-address-form").removeClass("hidden");
	$("#address-title").text("修改旧地址");
	$("#save-address-submit").addClass("hidden");
	$("#modify-address-submit").removeClass("hidden");
	$("#address-list tr").css("background-color","#fff");
	$("#"+addressId).css("background-color","#f4fad2");
	$.ajax({
		cache : true,
		type : "post",
		async : true,
		url : "getAddressById.do",
		dataType : 'json',
		data : {
			addressId : addressId
		},
		success : function(data) {
			//获取最底层地址号
			var cityAreaId = $("#"+addressId).attr("value");
			//更新得到市区列表
			var areaList = data.result.area;
			var cityList = data.result.city;
			//按级联等级来设定
			if(areaList){
				//三重级联
				clearAddress();
				var cityId=-1,provinceId=-1;
				for(var i=0;i<areaList.length;i++){
					option = $("<option value ='"+areaList[i].id+"'>"+areaList[i].name+"</option>");
					if(areaList[i].id==cityAreaId)
						cityId = areaList[i].pid;
					$("#area").append(option);
				}
				$("#area").val(cityAreaId);
				$("#area").removeClass("hidden");
				for(var i=0;i<cityList.length;i++){
					option = $("<option value ='"+cityList[i].id+"'>"+cityList[i].name+"</option>");
					if(cityList[i].id==cityId)
						provinceId = cityList[i].pid;
					$("#city").append(option);
				}
				$("#city").val(cityId);
				$("#province").val(provinceId);
				$("#street").val($("#"+addressId+" .address-content").text().split("／")[3]);
			}
			else {
				//两重级联
				clearAddress();
				var cityId=cityAreaId,provinceId=-1;
				for(var i=0;i<cityList.length;i++){
					option = $("<option value ='"+cityList[i].id+"'>"+cityList[i].name+"</option>");
					if(cityList[i].id==cityId)
						provinceId = cityList[i].pid;
					$("#city").append(option);
				}
				$("#city").val(cityId);
				$("#province").val(provinceId);
				$("#street").val($("#"+addressId+" .address-content").text().split("／")[2]);
			}
			//更新其他信息
			$("#address-telenumber").val($("#"+addressId+" .address-tele").text());
			$("#address-username").val($("#"+addressId+" .address-name").text());
			$("#address-id").attr("value",addressId);
		}
	});
};

//修改用户地址
$("#modify-address-btn").click(function(e) {
	
	addressStreetValidation();
	addressTelenumberValidation();
	usernameValidation();
	provinceCityValidation();
	
	if(validateFlag(addressFlag)){
		var address,addressTelenumber,cityAreaId,userName;
		var addressId = $("#address-id").val();
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
			url : "updateAddress.do",
			dataType : 'json',
			data : {
				addressId: addressId,
				address: address,
				telenumber: addressTelenumber,
				cityAreaId: cityAreaId,
				userName: userName
			},
			error : function(data) {
				alert("地址修改失败！");
			},
			success : function(data) {
				if (data.success) {
					$("#"+addressId).attr("value",data.result.cityAreaId);
					$("#"+addressId+" .address-content").text(data.result.address);
					$("#"+addressId+" .address-name").text(data.result.userName);
					$("#"+addressId+" .address-tele").text(data.result.teleNumber);
				}
			}
		});
		$("#add-address-div").removeClass("hidden");
		$("#add-address-form").addClass("hidden");
		$("#address-list-ul li").css("background-color","#fff");
	}
	else{
		e.preventDefault();
	}
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
				var id = data.result.id;
				if (data.success) {
					if($("#address-list").find("tr").length>0){
						var addressRow = $("<tr id='"+id+"' value='"+cityAreaId+"' class='address-row other-address-row'></tr>");
						var addressMessage = $("<td class='address-content'>"+address+"</td>");
						var addressUsername = $("<td class='address-name'>"+userName+"</td>");
						var addressTele = $("<td><span class='label label-info address-tele'>"+addressTelenumber+"</span></td>");
						var addressModify = $("<a href='javascript:void(0);' onclick='editUserAddress(\""+id+"\")'> 修改 </a>");
						var addressApart = $("<span> | </span>");
						var addressDelete = $("<a href='javascript:void(0);' onclick='deleteUserAddress(\""+id +"\",this)'> 删除 </a>");
						var addressOperate = $("<td></td>").append(addressDelete).append(addressApart).append(addressModify);
						var addressDeafault = $("<td class='set-default'><a class='label other-address hidden' href='javascript:void(0);' onclick='setDefaultAddress(\""+id+"\")'>设为默认</a></td>");
						addressRow.append(addressMessage).append(addressUsername).append(addressTele).append(addressOperate).append(addressDeafault);
						$("#address-list").find("tbody").append(addressRow);
						// 浮动默认地址选择
						addressRow.mouseover(function(){
							$(this).find(".other-address").removeClass("hidden");
						});
						addressRow.mouseout(function(){
							$(this).find(".other-address").addClass("hidden");
						});
						clearAddress();
					}
					else{
						$("#address-list p").remove();
						var addressListWrap = $("<table class='table table-hover'><tbody></tbody></table>");
						var addressRow = $("<tr id='"+id+"' value='"+cityAreaId+"' class='address-row other-address-row'></tr>");
						var addressMessage = $("<td class='address-content'>"+address+"</td>");
						var addressUsername = $("<td class='address-name'>"+userName+"</td>");
						var addressTele = $("<td><span class='label label-info address-tele'>"+addressTelenumber+"</span></td>");
						var addressModify = $("<a href='javascript:void(0);' onclick='editUserAddress(\""+id+"\")'> 修改 </a>");
						var addressApart = $("<span> | </span>");
						var addressDelete = $("<a href='javascript:void(0);' onclick='deleteUserAddress(\""+id +"\",this)'> 删除 </a>");
						var addressOperate = $("<td></td>").append(addressDelete).append(addressApart).append(addressModify);
						var addressDeafault = $("<td class='set-default'><span class='label label-warning default-address'>默认地址</span></td>");
						addressRow.append(addressMessage).append(addressUsername).append(addressTele).append(addressOperate).append(addressDeafault);
						addressListWrap.append(addressRow);
						$("#address-list").append(addressListWrap);
						clearAddress();
					}
				}else{
					alert("地址保存失败！");
				}
			}
		});
		$("#add-address-div").removeClass("hidden");
		$("#add-address-form").addClass("hidden");
	}
	else{
		e.preventDefault();
	}
});

//地址管理页面加载时触发
$(function() {
	//查询已经保存的地址
	$.ajax({
		type : "post",
		url : "getUserAddress.do",
		cache : false,
		success : function(data) {
			var obj = JSON.parse(data);
			var addressList = obj.result;
			var addressListWrap;
			if (addressList.length == 0) {
				addressListWrap = $("<p class='form-control-static error'>您还没有添加收件地址，请进行添加！</p>");
			} else {
				addressListWrap = $("<table class='table table-hover'><tbody></tbody></table>");
				for (var i = 0; i < addressList.length; i++) {
					for (var i = 0; i < addressList.length; i++) {
						var addressRow = $("<tr id='"+addressList[i].id+"' class='address-row' value='"+addressList[i].cityAreaId+"'></tr>");
						var addressMessage = $("<td class='address-content'>"+addressList[i].address+"</td>");
						var addressUsername = $("<td class='address-name'>"+addressList[i].userName+"</td>");
						var addressTele = $("<td><span class='label label-info address-tele'>"+addressList[i].teleNumber+"</span></td>");
						var addressOperate = $("<td><a href='javascript:void(0);' onclick=deleteUserAddress('"
								+ addressList[i].id + "',this)> 删除 </a><span> | </span>"
								+ "<a href='javascript:void(0);'  onclick=editUserAddress('"
								+ addressList[i].id + "')> 修改 </a></td>");
						var addressDefault;
						if(addressList[i].defaultAddress==1){
							addressDefault = $("<td class='set-default'><span class='label label-warning default-address'>默认地址</span></td>");
							addressRow.addClass("default-address-row");
						}
						else{
							addressDefault = $("<td class='set-default'><a class='label other-address hidden' href='javascript:void(0);' onclick='setDefaultAddress(\""+addressList[i].id+"\")'>设为默认</a></td>");
							addressRow.addClass("other-address-row");
						}
						addressRow.append(addressMessage).append(addressUsername).append(addressTele).append(addressOperate).append(addressDefault);
						addressListWrap.append(addressRow);
					}
				}
			}
			$("#address-list").append(addressListWrap);
			//浮动默认地址选择
			$(".other-address-row").mouseover(function(){
				$(this).find(".other-address").removeClass("hidden");
			});
			$(".other-address-row").mouseout(function(){
				$(this).find(".other-address").addClass("hidden");
			});
		},
		error : function(data) {
		}
	});
	initAddress();
});