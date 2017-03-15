$(function(){
	
	var registerFlag = [false,false,false,false,false,false]; //注册
	var loginFlag = [false,false,false]; //用户登录
	var adminLoginFlag = [false,false,false]; //管理员登录
	var passwordFlag = [false,false,false]; //用户修改密码
	var modifyFlag = [false,false]; //用户更改信息
	
	//公用函数
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
	
	//表单可提交状态
	//注册验证
	function checkRegisterFlag(){
		return validateFlag(registerFlag);
	}
	//用户登录验证
	function checkLoginFlag(){
		return validateFlag(loginFlag);
	}
	//管理员登录验证
	function checkAdminLoginFlag(){
		return validateFlag(adminLoginFlag);
	}
	//用户修改密码验证
	function checkPasswordFlag(){
		return validateFlag(passwordFlag);
	}
	//用户更改信息
	function checkModifyFlag(){
		return validateFlag(modifyFlag);
	}
	
	//检查每一项输入是否合法
	//注册校验
	//用户名校验
	function usernameValidation() {
		var username = $("#username").val();
		var usernamereg = /^([\u4e00-\u9fa50-9a-zA-Z_]+)$/;
		if(username==""){
			$("#alert-username-message").text("用户名不能为空");
			showandhide("#alert-username","#alert-username-ok");
			registerFlag[0]=false;
		}
		else if(username.length<2 || username.length>8){
			$("#alert-username-message").text("用户名长度在2到8字符");
			showandhide("#alert-username","#alert-username-ok");
			registerFlag[0]=false;
		}
		else if(!usernamereg.test(username)){
 			$("#alert-username-message").text("不能包含除“_”外的其他字符");
 			showandhide("#alert-username","#alert-username-ok");
 			registerFlag[0]=false;
 		}
		else $.ajax({
	         type:'post',      
	         url:'checkusername.do',  
	         data:'username='+$("#username").val(),  
	         cache:false,   
	         success:function(data){
	        	if(data=="success")
		        {
					$("#alert-username-message").text("用户名已存在");
					showandhide("#alert-username","#alert-username-ok");
					registerFlag[0]=false;
				}
				else if(data=="error")
				{
					showandhide("#alert-username-ok","#alert-username");
					registerFlag[0]=true;
				}
	         }
	     });
		checkRegisterFlag();
	}
	//注册邮箱校验
	function emailValidation(){
		var email = $("#email").val();
		var emailreg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(email==""){
 			$("#alert-email-message").text("邮箱不能为空");
 			showandhide("#alert-email","#alert-email-ok");
 			registerFlag[1]=false;
 		}
 		else if(email.length>50){
 			$("#alert-email-message").text("邮箱长度小于50个字符");
 			showandhide("#alert-email","#alert-email-ok");
 			registerFlag[1]=false;
 		}
 		else if(!emailreg.test(email)){
 			$("#alert-email-message").text("邮箱格式不正确");
 			showandhide("#alert-email","#alert-email-ok");
 			registerFlag[1]=false;
 		}
 		else $.ajax({
	         type:'post',      
	         url:'checkemail.do',  
	         data:'email='+$("#email").val(),
	         cache:false,   
	         success:function(data){
	        	if(data=="success")
		        {
	     			$("#alert-email-message").text("邮箱已经被注册");
	     			showandhide("#alert-email","#alert-email-ok");
	     			registerFlag[1]=false;
				}
				else if(data=="error")
				{
					showandhide("#alert-email-ok","#alert-email");
					registerFlag[1]=true;
				}
	         }
	    });
		checkRegisterFlag();
	}
	//注册手机校验
	function telenumberValidation(){
		var telenumber = $("#telenumber").val();
		var telenumberreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(telenumber==""){
			$("#alert-telenumber-message").text("手机号码不能为空");
			showandhide("#alert-telenumber","#alert-telenumber-ok");
			registerFlag[2]=false;
		}
		else if(telenumber.length!=11){
			$("#alert-telenumber-message").text("手机号码长度应为11位数字");
			showandhide("#alert-telenumber","#alert-telenumber-ok");
			registerFlag[2]=false;
		}
		else if(!telenumberreg.test(telenumber)){
			$("#alert-telenumber-message").text("手机号码格式不正确");
			showandhide("#alert-telenumber","#alert-telenumber-ok");
			registerFlag[2]=false;
		}
		else $.ajax({
	         type:'post',      
	         url:'checktelenumber.do',  
	         data:'telenumber='+$("#telenumber").val(),
	         cache:false,   
	         success:function(data){
	        	if(data=="success")
		        {
	     			$("#alert-telenumber-message").text("手机号码已经被注册");
	     			showandhide("#alert-telenumber","#alert-telenumber-ok");
	     			registerFlag[2]=false;
				}
				else if(data=="error")
				{
					showandhide("#alert-telenumber-ok","#alert-telenumber");
					registerFlag[2]=true;
				}
	         }
	    });
		checkRegisterFlag();
	}
	//注册密码验证
	function passwordValidation(){
		var password = $("#password").val();
		var passwordreg = /^[a-zA-Z0-9_]+$/;
		if(password==""){
			$("#alert-password-message").text("密码不能为空");
			showandhide("#alert-password","#alert-password-ok");
			registerFlag[3]=false;
		}
		else if(password.length<6 || password.length>20){
			$("#alert-password-message").text("密码长度在6到20个字符");
			showandhide("#alert-password","#alert-password-ok");
			registerFlag[3]=false;
		}
		else if(!passwordreg.test(password)){
			$("#alert-password-message").text("密码只能包含字母、数字、下划线");
			showandhide("#alert-password","#alert-password-ok");
			registerFlag[3]=false;
		}
		else if(password!=$("#repassword").val()){
			$("#alert-repassword-message").text("两次输入的密码需完全相同");
			showandhide("#alert-repassword","#alert-repassword-ok");
			showandhide("#alert-password-ok","#alert-password");
			registerFlag[4]=false;
			registerFlag[3]=true;
		}
		else{
			showandhide("#alert-password-ok","#alert-password");
			registerFlag[3]=true;
		}
		checkRegisterFlag();
	}
	//注册重复密码验证
	function repasswordValidation(){
		var repassword = $("#repassword").val();
		var repasswordreg = /^[a-zA-Z0-9_]+$/;
		if(repassword==""){
			$("#alert-repassword-message").text("密码不能为空");
			showandhide("#alert-repassword","#alert-repassword-ok");
			registerFlag[4]=false;
		}
		else if(repassword.length<6 || repassword.length>20){
			$("#alert-repassword-message").text("密码长度在6到20个字符");
			showandhide("#alert-repassword","#alert-repassword-ok");
			registerFlag[4]=false;
		}
		else if(!repasswordreg.test(repassword)){
			$("#alert-repassword-message").text("密码只能包含字母、数字、下划线");
			showandhide("#alert-repassword","#alert-repassword-ok");
			registerFlag[4]=false;
		}
		else if(repassword!=$("#password").val()){
			$("#alert-repassword-message").text("两次输入的密码需完全相同");
			showandhide("#alert-repassword","#alert-repassword-ok");
			registerFlag[4]=false;
		}
		else{
			showandhide("#alert-repassword-ok","#alert-repassword");
			registerFlag[4]=true;
		}
		checkRegisterFlag();
	}
	
	//登录验证
	//登录邮箱校验
	function loginEmailValidation(){
		var email = $("#login-email").val();
		var emailreg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(email==""){
 			$("#alert-login-email-message").text("邮箱不能为空");
 			showandhide("#alert-login-email","#alert-login-email-ok");
 			loginFlag[0]=false;
 			adminLoginFlag[0]=false;
 		}
 		else if(email.length>50){
 			$("#alert-login-email-message").text("邮箱长度小于50个字符");
 			showandhide("#alert-login-email","#alert-login-email-ok");
 			loginFlag[0]=false;
 			adminLoginFlag[0]=false;
 		}
 		else if(!emailreg.test(email)){
 			$("#alert-login-email-message").text("邮箱格式不正确");
 			showandhide("#alert-login-email","#alert-login-email-ok");
 			loginFlag[0]=false;
 			adminLoginFlag[0]=false;
 		}
 		else{
 			showandhide("#alert-login-email-ok","#alert-login-email");
 			loginFlag[0]=true;
 			adminLoginFlag[0]=true;
 		}
		checkLoginFlag();
		checkAdminLoginFlag();
	}
	//登录密码校验
	function loginPasswordValidation(){
		var password = $("#login-password").val();
		var passwordreg = /^[a-zA-Z0-9_]+$/;
		if(password==""){
			$("#alert-login-password-message").text("密码不能为空");
			showandhide("#alert-login-password","#alert-login-password-ok");
			loginFlag[1]=false;
			adminLoginFlag[1]=false;
		}
		else if(password.length<6 || password.length>20){
			$("#alert-login-password-message").text("密码长度在6到20个字符");
			showandhide("#alert-login-password","#alert-login-password-ok");
			loginFlag[1]=false;
			adminLoginFlag[1]=false;
		}
		else if(!passwordreg.test(password)){
			$("#alert-login-password-message").text("密码只能包含字母、数字、下划线");
			showandhide("#alert-login-password","#alert-login-password-ok");
			loginFlag[1]=false;
			adminLoginFlag[1]=false;
		}
		else{
			showandhide("#alert-login-password-ok","#alert-login-password");
			loginFlag[1]=true;
			adminLoginFlag[1]=true;
		}
		checkLoginFlag();
		checkAdminLoginFlag();
	}
	
	//修改密码校验
	//旧密码校验
	function changePwdValidation(){
		var password = $("#pwd").val();
		var passwordreg = /^[a-zA-Z0-9_]+$/;
		if(password==""){
			$("#alert-pwd-message").text("密码不能为空");
			showandhide("#alert-pwd","#alert-pwd-ok");
			passwordFlag[0]=false;
		}
		else if(password.length<6 || password.length>20){
			$("#alert-pwd-message").text("密码长度在6到20个字符");
			showandhide("#alert-pwd","#alert-pwd-ok");
			passwordFlag[0]=false;
		}
		else if(!passwordreg.test(password)){
			$("#alert-pwd-message").text("密码只能包含字母、数字、下划线");
			showandhide("#alert-pwd","#alert-pwd-ok");
			passwordFlag[0]=false;
		}
		else{
			showandhide("#alert-pwd-ok","#alert-pwd");
			passwordFlag[0]=true;
		}
		checkPasswordFlag();
	}
	//新密码校验
	function changeNewPwdValidation(){
		var password = $("#newpwd").val();
		var passwordreg = /^[a-zA-Z0-9_]+$/;
		if(password==""){
			$("#alert-newpwd-message").text("密码不能为空");
			showandhide("#alert-newpwd","#alert-newpwd-ok");
			passwordFlag[1]=false;
		}
		else if(password.length<6 || password.length>20){
			$("#alert-newpwd-message").text("密码长度在6到20个字符");
			showandhide("#alert-newpwd","#alert-newpwd-ok");
			passwordFlag[1]=false;
		}
		else if(!passwordreg.test(password)){
			$("#alert-newpwd-message").text("密码只能包含字母、数字、下划线");
			showandhide("#alert-newpwd","#alert-newpwd-ok");
			passwordFlag[1]=false;
		}
		else if(password!=$("#repwd").val()){
			$("#alert-repwd-message").text("两次输入的密码需完全相同");
			showandhide("#alert-repwd","#alert-repwd-ok");
			showandhide("#alert-newpwd-ok","#alert-newpwd");
			passwordFlag[2]=false;
			passwordFlag[1]=true;
		}
		else{
			showandhide("#alert-newpwd-ok","#alert-newpwd");
			passwordFlag[1]=true;
		}
		checkPasswordFlag();
	}
	//重复密码校验
	function changeRePwdValidation(){
		var repassword = $("#repwd").val();
		var repasswordreg = /^[a-zA-Z0-9_]+$/;
		if(repassword==""){
			$("#alert-repwd-message").text("密码不能为空");
			showandhide("#alert-repwd","#alert-repwd-ok");
			passwordFlag[2]=false;
		}
		else if(repassword.length<6 || repassword.length>20){
			$("#alert-repwd-message").text("密码长度在6到20个字符");
			showandhide("#alert-repwd","#alert-repwd-ok");
			passwordFlag[2]=false;
		}
		else if(!repasswordreg.test(repassword)){
			$("#alert-repwd-message").text("密码只能包含字母、数字、下划线");
			showandhide("#alert-repwd","#alert-repwd-ok");
			passwordFlag[2]=false;
		}
		else if(repassword!=$("#newpwd").val()){
			$("#alert-repwd-message").text("两次输入的密码需完全相同");
			showandhide("#alert-repwd","#alert-repwd-ok");
			passwordFlag[2]=false;
		}
		else{
			showandhide("#alert-repwd-ok","#alert-repwd");
			passwordFlag[2]=true;
		}
		checkPasswordFlag();
	}
	
	//修改个人信息校验
	//邮箱校验
	function modifyEmailValidation(){
		var email = $("#modify-email").val();
		var emailreg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(email==""){
 			$("#alert-modify-email-message").text("邮箱不能为空");
 			showandhide("#alert-modify-email","#alert-modify-email-ok");
 			modifyFlag[0]=false;
 		}
 		else if(email.length>50){
 			$("#alert-modify-email-message").text("邮箱长度小于50个字符");
 			showandhide("#alert-modify-email","#alert-modify-email-ok");
 			modifyFlag[0]=false;
 		}
 		else if(!emailreg.test(email)){
 			$("#alert-modify-email-message").text("邮箱格式不正确");
 			showandhide("#alert-modify-email","#alert-modify-email-ok");
 			modifyFlag[0]=false;
 		}
 		else{
 			showandhide("#alert-modify-email-ok","#alert-modify-email");
 			modifyFlag[0]=true;
 		}
		checkModifyFlag();
	}
	//手机校验
	function modifyTelenumberValidation(){
		var telenumber = $("#modify-telenumber").val();
		var telenumberreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		if(telenumber==""){
			$("#alert-modify-telenumber-message").text("手机号码不能为空");
			showandhide("#alert-modify-telenumber","#alert-modify-telenumber-ok");
			modifyFlag[1]=false;
		}
		else if(telenumber.length!=11){
			$("#alert-modify-telenumber-message").text("手机号码长度应为11位数字");
			showandhide("#alert-modify-telenumber","#alert-modify-telenumber-ok");
			modifyFlag[1]=false;
		}
		else if(!telenumberreg.test(telenumber)){
			$("#alert-modify-telenumber-message").text("手机号码格式不正确");
			showandhide("#alert-modify-telenumber","#alert-modify-telenumber-ok");
			modifyFlag[1]=false;
		}
		else {
			showandhide("#alert-modify-telenumber-ok","#alert-modify-telenumber");
 			modifyFlag[1]=true;
	    }
		checkModifyFlag();
	}
	
	//验证码校验
	function vcodeValidation() {
		var vcode = $("#random-code").val();
		if(vcode==""){
			$("#alert-vcode-message").text("验证码不能为空");
			showandhide("#alert-vcode","#alert-vcode-ok");
			registerFlag[5]=false;
			loginFlag[2]=false;
			loginFlag[2]=false;
		}
		else{
			showandhide("#alert-vcode-ok","#alert-vcode");
			registerFlag[5]=true;
			loginFlag[2]=true;
			adminLoginFlag[2]=true;
			checkRegisterFlag();
			checkLoginFlag();
			checkAdminLoginFlag();
			//$.ajax({
		     //    type:'post',
		     //    url:'checkcode.do',
		     //    data:'mycode='+$("#random-code").val(),
		     //    cache:false,
		     //    success:function(data){
		     //   	 if(data=="success")
		     //   	 {
		     //   		showandhide("#alert-vcode-ok","#alert-vcode");
		     //   		registerFlag[5]=true;
		     //   		loginFlag[2]=true;
		     //   		adminLoginFlag[2]=true;
		     //   		checkRegisterFlag();
		     //   		checkLoginFlag();
		     //   		checkAdminLoginFlag();
		     //   	 }
		     //   	 else
		     //   	 {
		     //   		$("#alert-vcode-message").text("验证码错误");
			//			showandhide("#alert-vcode","#alert-vcode-ok");
			//			registerFlag[5]=false;
			//			loginFlag[2]=false;
			//			loginFlag[2]=false;
			//			checkRegisterFlag();
			//			checkLoginFlag();
			//			checkAdminLoginFlag();
		     //   	 }
		     //    }
			//});
		}
	}
	
	// 绑定事件
	// 验证码点击事件
	$("#vimg").click(function() {    
		var imgNode = document.getElementById("vimg");                    
		imgNode.src = "servlet/AuthImageServlet?t=" + Math.random(); 
	});
	//验证码校验事件
	$("#random-code").keyup(function() {
		vcodeValidation();
	});
	// 注册验证事件
	$("#username").keyup(function(){
		showandhide("#alert-username-ok","#alert-username");
	});
	$("#email").keyup(function(){
		showandhide("#alert-email-ok","#alert-email");
	});
	$("#telenumber").keyup(function(){
		showandhide("#alert-telenumber-ok","#alert-telenumber");
	});
	$("#password").keyup(function(){
		showandhide("#alert-password-ok","#alert-password");
	});
	$("#repassword").keyup(function(){
		showandhide("#alert-repassword-ok","#alert-repassword");
	});
	$("#username").keyup(function(){
		usernameValidation();
	});
	$("#email").keyup(function(){
		//emailValidation();
	});
	$("#telenumber").keyup(function(){
		telenumberValidation();
	});
	$("#password").keyup(function(){
		passwordValidation();
	});
	$("#repassword").keyup(function(){
		repasswordValidation();
	});
	$("#register-form").submit(function(e){
		usernameValidation();
		usernameValidation();
		telenumberValidation();
		emailValidation();
		passwordValidation();
		repasswordValidation();
		vcodeValidation();
		if(!checkRegisterFlag()){
			e.preventDefault();
		}
	});
	//登录校验事件
	$("#login-email").focus(function(){
		showandhide("#alert-login-email-ok","#alert-login-email");
	});
	$("#login-password").focus(function(){
		showandhide("#alert-login-password-ok","#alert-login-password");
	});
	$("#login-form").submit(function(e){
		//loginEmailValidation();
		//loginPasswordValidation();
		//vcodeValidation();
		if(!checkLoginFlag()){
		//	e.preventDefault();
		}
	});
	$("#admin-login-form").submit(function(e){
		//loginEmailValidation();
		//loginPasswordValidation();
		//vcodeValidation();
		//if(!checkAdminLoginFlag()){
		//	e.preventDefault();
		//}
	});
	//修改密码校验
	$("#pwd").keyup(function(){
		changePwdValidation();
	});
	$("#newpwd").keyup(function(){
		changeNewPwdValidation();
	});
	$("#repwd").keyup(function(){
		changeRePwdValidation();
	});
	$("#change-password-form").submit(function(e){
		changePwdValidation();
		changeNewPwdValidation();
		changeRePwdValidation();
		if(!checkPasswordFlag()){
			e.preventDefault();
		}
	});
	//修改个人信息校验
	$("#modify-email").keyup(function(){
		modifyEmailValidation();
	});
	$("#modify-telenumber").keyup(function(){
		modifyTelenumberValidation();
	});
	$("#modify-form").submit(function(e){
		modifyEmailValidation();
		modifyTelenumberValidation();
		if(!checkModifyFlag()){
			e.preventDefault();
		}
	});
});