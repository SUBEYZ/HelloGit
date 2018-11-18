
//页面加载
$(function() {

	//为提交按钮图片添加一个点击事件
	$("#submit").click(function() {
		submit();
	});
	//为页面回车添加一个事件
	//经过测试：ie和火狐都支持，chrom不支持这个回车事件
	$("body").keypress(function(event) {
		if(event.keyCode == 13){//13是回车的ASCII码
			submit();
		}
	});
});
// 更新验证码图片
function changeImg(obj) {
	// 浏览器具有缓存功能，因此如果url一样的话就不会刷新，所以就加一个不断变化的时间，
	obj.src = "/BLGuitar/checkImg?time=" + new Date().getTime();
}
function submit() {
	var username = $("#username").val();
	var password = $("#password").val();
	var code = $("#code").val();
	if (username.length == 0) {
		$("#username").focus();
		/* $("#usernameSpan").css("color", "red"); */
		$("#usernameSpan").html(" × &nbsp;&nbsp;请输入用户名");
		return false;
	}
	if (password.length == 0) {
		$("#password").focus();
		$("#passwordSpan").html(" × &nbsp;&nbsp;请输入密码");
		return false;
	}
	if (code.length == 0) {
		$("#code").focus();
		$("#codeSpan").html(" × &nbsp;&nbsp;请输入验证码");
		return false;
	}

	$.ajax({
		url : "/BLGuitar/login",
		type : "POST",
		data : {
			"username" : username,
			"password" : password,
			"code" : code
		},
		dataType : "text",
		success : function(data) {
			if (data == "user") {
				$("#username").focus();
				$("#usernameSpan").html(" × &nbsp;&nbsp;用户名或密码错误");
				return false;
			} else if (data == "code") {
				$("#code").focus();
				$("#codeSpan").html(" × &nbsp;&nbsp;验证码错误");
				$("#codeimg").attr(
						'src',
						'/BLGuitar/checkImg?time='
								+ new Date().getTime());
				return false;
			} else if (data == "true") {
				window.location.href = "../index.jsp";// 跳转到主页
			}
		},
		error : function() {
			alert("服务器开小差啦，请稍后再试");
		}
	});

}

/*
 * function check(){ return checkUserName() && checkPassword(); } function
 * checkUserName(){ var username = document.getElementById("username").value;
 * if(username.length<3 || username.length>18){
 * document.getElementById("usernameSpan").innerHTML=" ×
 * &nbsp;&nbsp;用户名非法[3~18位字符]"; document.getElementById("username").focus();
 * return false; } document.getElementById("usernameSpan").innerHTML=""; return
 * true; } function checkPassword(){ var password =
 * document.getElementById("password").value; if(password.length<6 ||
 * password.length>18){ document.getElementById("passwordSpan").innerHTML=" ×
 * &nbsp;&nbsp;密码非法[6~18位字符]"; document.getElementById("password").focus();
 * return false; } document.getElementById("passwordSpan").innerHTML=""; return
 * true; }
 */