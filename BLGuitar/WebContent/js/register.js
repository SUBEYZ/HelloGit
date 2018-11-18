// 页面加载
$(function() {
	//ajax技术检验用户名是否存在
	// 为用户名输入框引入一个事件
	$("#username").blur(function() {
		// 失去焦点时获取用户名
		var name = $(this).val();
		if (name.length == 0){
			//$("#usernameSpan").css("color", "red");
			$("#usernameSpanWA").html(" × &nbsp;&nbsp;用户名不能为空");
			$("#usernameSpanAC").html("");
			return;
		}
		if (name.length<3 || name.length>18){
			$("#usernameSpanWA").html(" × &nbsp;&nbsp;用户名由3~18位字符组成");
			$("#usernameSpanAC").html("");
			return;
		}
		// 使用ajax技术到服务器校验该用户名是否存在
		$.ajax({
			url : "/BLGuitar/checkUserName", // 请求地址
			type : "POST", // 请求方式
			data : {
				"username" : name
			}, // 传递到服务器的参数，使用json方式传递
			success : function(data) { // 请求成功时的回调函数
				if ("true" === data) {
					$("#usernameSpanWA").html(" × &nbsp;&nbsp;用户名已经存在");
					$("#usernameSpanAC").html("");
				} else {
					$("#usernameSpanAC").html(" √ &nbsp;&nbsp;用户名可用");
					$("#usernameSpanWA").html("");
				}
			},
			error : function(data) {// 请求失败时的回调函数
				alert("服务器开小差啦，请稍后再试");
			},
			dataType : "text" // 返回数据类型
		});
	});
	
	
	//为页面按回车添加一个事件
	$("body").keypress(function(event){
		if(event.keyCode==13){//13是回车的ASCII码
			$("#submit").click();//触发点击事件
		}
	});
	//为注册图标绑定一个点击事件
	$("#submit").click(function(){
		if(!check()){//检查通过不了就不让提交
			return false;
		}
		var username = $("#username").val();
		var password = $("#password").val();
		var email = $("#email").val();
		var code = $("#code").val();
		$.ajax({
			url : "/BLGuitar/register", // 请求地址
			type : "POST", // 请求方式
			data : {
				"username" : username,
				"password":password,
				"email":email,
				"code":code
			}, // 传递到服务器的参数，使用json方式传递
			dataType : "text", // 返回数据类型
			success : function(data) { // 请求成功时的回调函数
				if ("true" == data) {
					var choose = confirm("注册成功，是否立马登陆？");
					if(choose==true){
						window.location.href="login.jsp";
					}else{
						window.location.href="../index.jsp";
					}
				} else if("user"==data){
					$("#username").focus();
					$("#usernameSpanAC").html("");
					$("#usernameSpanWA").html(" × &nbsp;&nbsp;用户名或密码错误,注册失败");
				} else if("email"==data){
					$("#email").focus();
					$("#emailSpanAC").html("");
					$("#emailSpanWA").html(" × &nbsp;&nbsp;邮箱格式错误,注册失败");
				} else if("code"==data){
					$("#code").focus();
					$("#codeSpanAC").html("");
					$("#codeSpanWA").html(" × &nbsp;&nbsp;验证码错误,请重新输入");
					/*给一个新的验证码*/
					$("#codeimg").attr('src','/BLGuitar/checkImg?time='+new Date().getTime());
				}
			},
			error : function(data) {// 请求失败时的回调函数
				alert("服务器开小差啦，请稍后再试");
			}
		});
	});
});

//点击注册 -> 检查所有元素是否符合注册要求
function check(){
	return checkName() && checkPwd() && checkRePwd() && checkEmail();
}

//校验用户名是否为空
function checkName(){
	var name = document.getElementById("username").value;
	if(name.length<3 || name.length>18){
		document.getElementById("username").focus();
		return false;
	}
	return true;
}

//校验密码
function checkPwd(){
	var pwd = document.getElementById("password").value;
	if(pwd.length<6 || pwd.length>18){
		document.getElementById("passwordSpanWA").innerHTML=" × &nbsp;&nbsp;密码由6~18位字符组成";
		document.getElementById("passwordSpanAC").innerHTML="";
		document.getElementById("password").focus();
		return false;
	}
	document.getElementById("passwordSpanAC").innerHTML=" √ &nbsp;&nbsp;密码合法";
	document.getElementById("passwordSpanWA").innerHTML="";
	return true;
}

//校验确认密码
function checkRePwd(){
	var pwd = document.getElementById("password").value;
	var repwd = document.getElementById("repassword").value;
	if(repwd.length==0){
		document.getElementById("repasswordSpanWA").innerHTML=" × &nbsp;&nbsp;请输入确认密码";
		document.getElementById("repasswordSpanAC").innerHTML="";
		document.getElementById("repassword").focus();
		return false;
	}
	if(pwd!==repwd){
		document.getElementById("repasswordSpanWA").innerHTML=" × &nbsp;&nbsp;密码与确认密码不一致";
		document.getElementById("repasswordSpanAC").innerHTML="";
		document.getElementById("repassword").focus();
		return false;
	}
	document.getElementById("repasswordSpanWA").innerHTML="";
	document.getElementById("repasswordSpanAC").innerHTML=" √ &nbsp;&nbsp;确认密码正确";
	return true;
}

//正则表达式校验邮箱地址
function checkEmail(){
	var email = document.getElementById("email").value;
	var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.length==0){
		//alert("len");
		document.getElementById("emailSpanWA").innerHTML=" × &nbsp;&nbsp;请输入邮箱";
		document.getElementById("emailSpanAC").innerHTML="";
		document.getElementById("email").focus();
		return false;
	}
	if(!regex.test(email)){
		//alert("regex");
		document.getElementById("emailSpanWA").innerHTML=" × &nbsp;&nbsp;请输入正确的邮箱格式";
		document.getElementById("emailSpanAC").innerHTML="";
		document.getElementById("email").focus();
		return false;
	}
	//alert("right");
	document.getElementById("emailSpanWA").innerHTML="";
	document.getElementById("emailSpanAC").innerHTML=" √ &nbsp;&nbsp;邮箱可用";
	return true;
}

//验证 验证码 是否输入正确
function checkCode(){
	var code = document.getElementById("code").value;
	$.ajax({
		url:"/BLGuitar/checkCode",
		type : "POST", 
		data:{"code":code},
		dataType:"json",
		success:function(data){
			if(data==true){
				$("#codeSpanWA").html("");
				$("#codeSpanAC").html(" √ &nbsp;&nbsp;验证码正确");
				return true;
			}else{
				$("#code").focus();
				$("#codeSpanWA").html(" × &nbsp;&nbsp;验证码错误");
				$("#codeSpanAC").html("");
				$("#codeimg").attr('src','/BLGuitar/checkImg?time='+new Date().getTime());
				return false;
			}
		},
		error:function(){
			alert("服务器开小差啦，请稍后再试");
		}
	});
}

//更新验证码图片
function changeImg(obj){
	//浏览器具有缓存功能，因此如果url一样的话就不会刷新，所以就加一个不断变化的时间，
	obj.src="/BLGuitar/checkImg?time="+new Date().getTime();
}

