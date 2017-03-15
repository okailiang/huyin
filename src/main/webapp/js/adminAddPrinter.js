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
							//把完整地址写入隐藏输入框
							var province = $("#province").find("option:selected").text();
							var city = $("#city").find("option:selected").text();
							var area = $("#area").find("option:selected").text();
							$("#cascade-address").val(province+"／"+city+"／"+area+"／");
							$("#city-area-id").val($("#area").val());
						}
						else{
							$("#area").addClass("hidden");
							//把完整地址写入隐藏输入框
							var province = $("#province").find("option:selected").text();
							var city = $("#city").find("option:selected").text();
							$("#cascade-address").val(province+"／"+city+"／");
							$("#city-area-id").val($("#city").val());
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
					//把完整地址写入隐藏输入框
					var province = $("#province").find("option:selected").text();
					var city = $("#city").find("option:selected").text();
					var area = $("#area").find("option:selected").text();
					$("#cascade-address").val(province+"／"+city+"／"+area+"／");
					$("#city-area-id").val($("#area").val());
				}
				else{
					//把完整地址写入隐藏输入框
					var province = $("#province").find("option:selected").text();
					var city = $("#city").find("option:selected").text();
					$("#cascade-address").val(province+"／"+city+"／");
					$("#city-area-id").val($("#city").val());
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

//级联选择市区
$("#area").change(function(){
	//把完整地址写入隐藏输入框
	var province = $("#province").find("option:selected").text();
	var city = $("#city").find("option:selected").text();
	var area = $("#area").find("option:selected").text();
	$("#cascade-address").val(province+"／"+city+"／"+area+"／");
	$("#city-area-id").val($("#area").val());
});

//上传照片
$("#shop-image").change(function(){
	file = this.files[0];
    reader = new FileReader();
    reader.onload = function(){
        url = reader.result;
        image = new Image();
        image.src = url;
        $(".avatar img").prop("src",url);
    };
    reader.readAsDataURL(file);
    var name = $(this).val().split("\\");
    $("#shop-image-name").text("照片名："+name[name.length-1]);
});

//页面加载时获取必要信息
$(function() {
	initAddress();
});

/*//弹出收起地图
$("#address-map-btn").click(function(){
	if($("#l-map").hasClass("hidden")){
		$("#l-map").removeClass("hidden");
		cityName=$("#city option:selected").text();		
		map.centerAndZoom(cityName, 12);
	}
	else{
		$("#l-map").addClass("hidden");
	}
});*/

//管理员添加打印店地址拾取经纬度
$("#address-map-btn").click(function(){
	if($("#l-map").hasClass("hidden")){
		$("#l-map").removeClass("hidden");
		cityName=$("#city option:selected").text();		
		map.centerAndZoom(cityName, 12);
		map.addEventListener("click",function(e){
			//清楚覆盖物
			map.clearOverlays(); 
			var point = new BMap.Point(e.point.lng,e.point.lat);
			var marker = new BMap.Marker(point);
			map.addOverlay(marker);
			$("input#longitude").val(e.point.lng);
			$("input#latitude").val(e.point.lat);
		   //alert(e.point.lng + "," + e.point.lat);
		});
	}
	else{
		$("#l-map").addClass("hidden");
	}
});