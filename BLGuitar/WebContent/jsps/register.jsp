<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BL吉他商城 - 注册</title>

<!-- 引入样式 --><!-- Stylesheet -- 定义一个外部加载的样式表  -->
<link type="text/css" href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet">

<!-- js校验表单 -->
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/register.js"></script>
<!--  style="width: 100%; background: url('imgs/register_bg.jpg'); margin-top: 28px;" -->
<script type="text/javascript">

</script>
</head>
<body>
<!-- 背景图片 -->
<div id="background" style="position:absolute;z-index:-1;width:100%;height:100%;top:0px;left:0px;">
<img src="${pageContext.request.contextPath}/imgs/register_bg.jpg" width="100%" height="100%"/></div>

<!-- 首页logo -->
<a href="${pageContext.request.contextPath}/index.jsp"><img id="logo" src="${pageContext.request.contextPath}/imgs/logo.jpg"></img></a>

<!-- 表单 -->
<table class="container">
	<tr><td id="registerText">会员注册</td></tr>
	
	<!-- 用户名 -->
	<tr><td><input type="text" id="username" name="username" onchange="checkName()" placeholder="请输入用户名 [3~18位]" > </td></tr>
	<tr><td class="span">
		<span class="AC" id="usernameSpanAC"></span>
		<span class="WA" id="usernameSpanWA"></span>
	</td></tr>
	
	<!-- 密码 -->
	<tr><td><input type="password" id="password" name="password" onchange="checkPwd()" placeholder="请输入密码 [6~18位]" ></td></tr>
	<tr><td class="span">
		<span class="AC" id="passwordSpanAC"></span>
		<span class="WA" id="passwordSpanWA"></span>
	</td></tr>
	
	<!-- 确认密码 -->
	<tr><td><input type="password" id="repassword" name="repassword" onchange="checkRePwd()" placeholder="请输入确认密码" ></td></tr>
	<tr><td class="span">
		<span class="AC" id="repasswordSpanAC"></span>
		<span class="WA" id="repasswordSpanWA"></span>
	</td></tr>
	
	<!-- 邮箱 -->
	<tr><td><input type="email" id="email" name="email" onchange="checkEmail()" placeholder="请输入邮箱 [用于找回密码]" ></td></tr>
	<tr><td class="span">
		<span class="WA" id="emailSpanWA"></span>
		<span class="AC" id="emailSpanAC"></span>
	</td></tr>
	
	<!-- 验证码 -->
	<tr><td><input type="text" id="code" name="code" onchange="checkCode()" placeholder="请输入验证码">
		<img id="codeimg" alt="验证码图片" onclick="changeImg(this)" src="/BLGuitar/checkImg"></td></tr>
	<tr><td class="span">
		<span class="AC" id="codeSpanAC"></span>
		<span class="WA" id="codeSpanWA"></span>
	</td></tr>
	
	<!-- 提交按钮 -->
	<tr><td>
		<img src="${pageContext.request.contextPath}/imgs/btnregister.png" id="submit">
	</td></tr>
	
	<!-- 跳转到登陆页面 -->
	<tr><td>
		<a href="login.jsp" id="loginNow">已有账号？点此立即登陆</a>
	</td></tr>
</table>
</body>
</html>