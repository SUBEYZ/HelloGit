/**
 * 
 */
$(function(){
	//为点击获取验证码图片绑定一个事件
	$("#codeimg").click(function(){
		//获取用户名、邮箱地址
		var username = $("#username").val();
		var email = $("#email").val();
		//校验用户名是否为空
		if(username.length==0){
			//先将全部span清空，然后设置usernamespan,然后返回
			$(".span").html("");
			$("#usernameSpan").html(" × &nbsp;&nbsp;用户名不能为空");
			$("#username").focus();
			return;
		}
		//校验邮箱是或为空
		if(email.length==0){
			$(".span").html("");
			$("#emailSpan").html(" × &nbsp;&nbsp;邮箱不能为空");
			$("#email").focus();
			return;
		}
		//校验邮箱格式
		var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(!regex.test(email)){
			$(".span").html("");
			$("#emailSpan").html(" × &nbsp;&nbsp;邮箱格式错误");
			$("#email").focus();
			return;
		}
		//Ajax请求发送邮件
		$.ajax({
			url:"/BLGuitar/sendEmail",
			/*url:"${pageContext.request.contextPath}/sendEmail",*/
			type:"POST",
			data:{
				"username":username,
				"email":email
			},
			dataType:"text",
			success:function(data){
				$(".span").html("");
				if("uname"==data){
					$("#usernameSpan").html(" × &nbsp;&nbsp;用户名错误");
					$("#username").focus();
				}else if("email"==data){
					$("#emailSpan").html(" × &nbsp;&nbsp;邮箱错误");
					$("#email").focus();
				}else if("umail"==data){
					$("#usernameSpan").html(" × &nbsp;&nbsp;用户名和邮箱不匹配");
					$("#username").focus();
				}else if("true"==data){//值得注意的是：当用户的邮箱符合格式，不存在也会发送成功，只是没人接收而已
					$("#codeSpan").html(" √ &nbsp;&nbsp;邮件已经发送，请注意查收");
					$("#password").focus();
				}
			},
			error:function(){
				alert("服务器开小差啦，请稍后再试");
			}
		});	
	});
	
	//为重置密码按钮绑定一个事件
	$("#submit").click(function(){
		//获取密码
		var pwd = $("#password").val();
		var repwd = $("#repassword").val();
		//获取用户输入的验证码
		var code = $("#code").val();
		
		if(code.length==0){
			$(".span").html("");
			$("#codeSpan").html(" × &nbsp;&nbsp;请输入验证码");
			$("#code").focus();
			return;
		}
		if(pwd.length<6 || pwd.length>18){
			$(".span").html("");
			$("#passwordSpan").html(" × &nbsp;&nbsp;密码由6~18位字符组成");
			$("#password").focus();
			return;
		}
		if(pwd!=repwd){
			$(".span").html("");
			$("#repasswordSpan").html(" × &nbsp;&nbsp;两次密码不一致");
			$("#repassword").focus();
			return;
		}
		$.ajax({
			url:"/BLGuitar/resetPwd",
			data:{
				"password":pwd,
				"code":code
			},
			type:"POST",
			dataType:"text",
			success:function(data){
				if("true"==data){
					//如果修改密码成功，弹出选择框，询问是否登陆
					//选择确定--到登陆页面
					//选择取消--到商城首页
					var choose = confirm("修改密码成功，是否立马登陆？");
					if(choose==true){
						window.location.href="login.jsp";
					}else{
						window.location.href="../index.jsp";
					}
				}else if("code"==data){
					$(".span").html("");
					$("#codeSpan").html(" × &nbsp;&nbsp;验证码错误，请重新获取并输入");
					$("#code").focus();
				}else if("pwd"==data){
					$(".span").html("");
					$("#passwordSpan").html(" × &nbsp;&nbsp;密码由6~18位字符组成，请重新获取验证码");
					$("#password").focus();
				}else if("username"){
					$(".span").html("");
					$("#usernameSpan").html(" × &nbsp;&nbsp;请先输入用户名并重新获取验证码");
					$("#username").focus();
				}else{//false
					alert("请求失败，请重新输入");
				}
			},
			error:function(){
				alert("服务器开小差啦，请稍后再试");
			}
		});
	});
});