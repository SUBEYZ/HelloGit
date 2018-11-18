<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 导入jstl标准库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 登陆页面 -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BL吉他商城 - 登陆</title>
<!-- 引入css -->
<link href="${pageContext.request.contextPath}/css/login.css" type="text/css" rel="stylesheet">

<!-- 引入js -->
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>

</head>
<body>
<!-- 背景图片 -->
<div id="background" style="position:absolute;z-index:-1;width:100%;height:100%;top:0px;left:0px;">
<img src="${pageContext.request.contextPath}/imgs/login_bg.jpg" width="100%" height="100%"/></div>

<!-- 首页logo -->
<a href="${pageContext.request.contextPath}/index.jsp" id="logo"><img src="${pageContext.request.contextPath}/imgs/logo.jpg"></img></a>

<!-- 表单 -->
<form method="post">  <!-- action="login"   onsubmit="return check()" -->

<table class="container">
	<tr><td id="loginText">用户登录</td></tr>
	
	<!-- 用户名 -->
	<tr><td><input type="text" name="username" id="username" onchange="checkUserName()" placeholder="请输入用户名"></td> </tr>
	<tr><td id="usernameSpan"> </td> </tr>
	
	<!-- 用户密码 -->
	<tr><td><input type="password" name="password" id="password" onchange="checkPassword()" placeholder="请输入密码"></td> </tr>
	<tr><td id="passwordSpan"> </td> </tr>
	
	<!-- 验证码 -->
	<tr><td><input type="text" id="code" name="code" placeholder="请输入验证码">
		<img id="codeimg" alt="验证码图片" onclick="changeImg(this)" src="/BLGuitar/checkImg"></td></tr>
	<tr><td id="codeSpan"> </td></tr>
	
	<!-- 登陆按钮图片 -->
	<tr><td>
		<img src="${pageContext.request.contextPath }/imgs/btnlogin.png" id="submit">
		<!-- <input type="image" src="imgs/btnlogin.png" id="submit"> -->
	</td></tr>
	
	<!-- 跳转到注册页面 -->
	<tr><td>
		<a href="${pageContext.request.contextPath}/jsps/register.jsp" id="registerNow" >立即注册</a>
		<a href="${pageContext.request.contextPath}/jsps/findpwd.jsp" id="findpwd">忘记密码</a>
	</td></tr>
	<!-- 此处遇到的一个问题：按钮为submit的时候，执行JS的时候会自动为我提交，提示数据昙花一现，修改为button就不会了 -->
	<!-- 然后又改为图片了…… -->
</table>
</form>
</body>
</html>