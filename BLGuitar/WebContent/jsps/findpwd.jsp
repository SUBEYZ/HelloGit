<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 
	用于找回密码
 -->

<!DOCTYPE html>
<html>
<head>
<title>BL吉他商城 - 找回密码</title>
<!-- 引入css -->
<link href="${pageContext.request.contextPath}/css/findpwd.css" type="text/css" rel="stylesheet">

<!-- 引入js -->
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/findpwd.js" type="text/javascript"></script>

</head>
<body>
<!-- 背景图片 -->
<div id="background">
<img src="${pageContext.request.contextPath }/imgs/findpwd_bg.jpg" width="100%" height="100%"/></div>

<!-- 首页logo -->
<a href="${pageContext.request.contextPath }/index.jsp" id="logo"><img src="${pageContext.request.contextPath }/imgs/logo.jpg"></img></a>

<!-- 表单 -->
<form method="post"> 

<table class="container">
	<tr><td id="findText">找回密码</td></tr>
	
	<!-- 用户名 -->
	<tr><td><input type="text" name="username" id="username" placeholder="请输入用户名"></td> </tr>
	<tr><td class="span" id="usernameSpan"> </td> </tr>
	
	<!-- 邮箱 -->
	<tr><td><input type="email" name="email" id="email" placeholder="请输入邮箱获取验证码"></td> </tr>
	<tr><td class="span" id="emailSpan"> </td> </tr>

	<!-- 验证码 -->
	<tr><td><input type="text" id="code" name="code" placeholder="请输入验证码">
		<img id="codeimg" alt="获取验证码按钮图片"src="${pageContext.request.contextPath}/imgs/btngetcode.png"></td></tr>
	<tr><td class="span"  id="codeSpan"> </td></tr>
	
	<!-- 新密码 -->
	<tr><td><input type="password" id="password" placeholder="请输入新密码[6~18位字符]"></td></tr>
	<tr><td class="span" id="passwordSpan"></td></tr>
	
	<!-- 确认密码 -->
	<tr><td><input type="password" id="repassword" placeholder="请输入确认密码"></td></tr>
	<tr><td class="span" id="repasswordSpan"></td></tr>
	
	<!-- 提交按钮图片 -->
	<tr><td>
		<img src="${pageContext.request.contextPath}/imgs/btnresetpwd.png" id="submit">
	</td></tr>
</table>
</form>
</body>
</html>