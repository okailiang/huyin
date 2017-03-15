
function showDiv() {		
		showMask();
		document.getElementById('pop-div').style.display = 'block';
		
		$(".register-div-descript").text("添加用户信息");
		$("#username").val("");
		$("#email").val("");
		$("#telenumber").val("");
		$("#password").val("");
		$("#repassword").val("");	
		//document.getElementById('bg').style.display='block';
	}
	function closeDiv() {
		hideMask();
		document.getElementById('pop-div').style.display = 'none';
		//document.getElementById('bg').style.display = 'none';
	}

//删除多个用户信息
$("#del-all").click(function() {
	var idList;
	idList = new Array();	
		$("input[type='checkbox']").each(function() {
			if (this.checked) {
				idList.push(this.value);
			}
		});
		if (idList == null || idList.length == 0) {
			alert("请选择要删除的数据！");
			return false;
		} else {
			var delMoreUserInfo=function(){
				$.ajax({
					type : 'POST',
					url : 'removeUserByList.do',
					data : 'checklist=' + idList,
					cache : false,
					success : function(data) {
						if (data) {
							for(var i=0;i<idList.length;i++){
								$("#tr" + idList[i]).remove();
							}
						}else{
							alert(data);
						}
					},
					 error : function(data) {
						 alert("当前删除列表中有用户不存在，请刷新列表！"); 
					}
				});
			};
			confirm("确定删除？", delMoreUserInfo);
		}
});

//删除单个用户
function removeUser(obj) {
	var userId = obj.id;
	var delOneUser=function(){
		$.ajax({
			type : 'post',
			url : 'removeUser.do',
			data : 'userId=' + userId,
			cache : false,
			success : function(data) {
				if (data == "success") {
					var rowNode = obj.parentNode.parentNode;
					var patNode = rowNode.parentNode;
					patNode.removeChild(rowNode);
				} else if (data == "error") {
					
				}
			}
		});
	};
	confirm("确认删除？",delOneUser);
}
var closeEditDiv=function(){
	hideMask();
	document.getElementById('pop-edit-div').style.display = 'none';
};

var initEditDiv=function(){
	var html = "";
	html += '<div id="pop-edit-div" class="register-div">';
	html += ' <div id="pop-close-div" class="register-div-descript">';
	html += '  <p for="userrole">编辑用户信息</p>';
	html += ' </div>';
	html += ' <div id="pop-edit-close" class="register-div-close">';
	html += '  <span class="glyphicon glyphicon-remove" onclick="javascript:closeEditDiv()"></span>';
	html += ' </div>';
	html += '<form id="pop-edit-form" class="form-horizontal" role="form">';
	html += '  <div class="form-group">';
	html += '   <lable for="username"class="col-md-3 control-label ">用户名</lable>';
	html +='      <div class="col-md-9">';
	html += '        <input type="text" class="form-control" id="edit-username" name="username" placeholder="输入用户名" autocomplete="off" disabled>';
	html += '      </div>';
	html += '   </div>';
	html += '  <div class="form-group">';
	html += '  <label for="email" class="col-md-3 control-label">邮箱</label>';
	html +='      <div class="col-md-9">';
	html += '        <input type="text" class="form-control" id="edit-email" name="email"placeholder="输入邮箱地址" autocomplete="off">';
	html += '      </div>';
	html += '   </div>';
	html += '  <div class="form-group">';
	html += '   <label for="telenumber" class="col-md-3 control-label">手机号码</label>';
	html +='      <div class="col-md-9">';
	html += '        <input type="text" class="form-control" id="edit-telenumber"name="telenumber" placeholder="输入手机号码" autocomplete="off">';
	html += '      </div>';
	html += '   </div>';
	html += '  <div class="form-group">';
	html += '   <label for="password" class="col-md-3 control-label">密码</label>';
	html +='      <div class="col-md-9">';
	html += '       <input type="password" class="form-control" id="edit-password"name="password" placeholder="密码长度不小于6位" autocomplete="off">';
	html += '      </div>';
	html += '   </div>';
	html += '  <div class="form-group">';
	html += '   <label for="repassword" class="col-md-3 control-label">确认密码</label>';
	html += '      <div class="col-md-9">';
	html += '        <input type="password" class="form-control" id="edit-repassword"name="repassword" placeholder="密码长度不小于6位" autocomplete="off">';
	html += '      </div>';
	html += '   </div>';
	html += '  <div class="form-group">';
	html += '  <div class="col-md-8">';
	html += '  <button  class="btn btn-primary pull-right" id="edit-userInfo-submit">';
	html += '  	<span class="glyphicon glyphicon-plane"></span>&nbsp;&nbsp;确定</button>';
	html += '  </div>';
	html += '  </div>';
	html +='</form>';
	html +='</div>';
	$("body").append(html);
	
};
initEditDiv();
var showEditDiv = function(){
	showMask();
	$("#pop-edit-div").css("display","block");
};
	
//修改用户信息
function modifyUser(obj){
	var userId = obj.id;
	$.ajax({
		type : "post",
		url : "adminGetUserId.do",
		data : {
			id : userId
		},
		dataType : "json",
		cache : false,
		success : function(data){
			if(data.success){
				showEditDiv();
				$("#edit-username").val(data.result.userName);
				$("#edit-email").val(data.result.email);
				$("#edit-telenumber").val(data.result.teleNumber);
				$("#edit-password").val("");
				$("#edit-repassword").val("");	
				//
				var editUserInfo = function(e){
					    //阻止绑定在body上的点击事件
					    e.preventDefault();
						//当前页
						var currentPage = $(".currentPage").attr("value");
						var pageurl = $("#page-div").attr("value");
						var username = $("#edit-username").val();
						var email = $("#edit-email").val();
						var tele = $("#edit-telenumber").val();
						var psd = $("#edit-password").val();
						var repsd = $("#edit-repassword").val();
						// 校验email
						if (email == "") {
							$("#edit-email").parent().addClass("has-error");
							return false;
						} else {
							$("#edit-email").parent().removeClass("has-error");
						}
						//校验手机
						if (tele == "") {
							$("#edit-telenumber").parent().addClass("has-error");
							return false;
						} else {
							$("#edit-telenumber").parent().removeClass("has-error");
						}
						//校验密码
						if (psd == "") {
							$("#edit-password").parent().addClass("has-error");
							return false;
						} else {
							$("#edit-password").parent().removeClass("has-error");
						}
						//校验确认密码
						if (repsd == "") {
							$("#edit-repassword").parent().addClass("has-error");
							return false;
						} else {
							$("#edit-repassword").parent().removeClass("has-error");
						}
						// 判断两次密码是否相同
						if (psd !==repsd) {
							tip("两次密码输入不相同！");			
							return false;
						}else{
							$.ajax({
								type : "post",
								url : "adminUpdateUser.do",
								data : {
									id : userId,				
									username :username,
									telenumber : tele,	
									email : email,
									password : psd,
									repassword :repsd
								},
								dataType : "json",
								success : function(data){
									if(data.success){
										closeEditDiv();
										window.location.href = pageurl + "currentPage="
										+ currentPage + "";
									}else{
										alert(data.message);
										return false;
									}
								},
								error : function(data){
									
								}
							});
						}
				};
				$("#edit-userInfo-submit").click(editUserInfo);
			}else{
				alert(data.message);
				return false;
			}
			
		}
	});
};
