<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
	<meta name="BLGuitar" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	
	<!-- 引入header.js --遇到问题，引入这个文件的时候就不能正常加载loginuser，因此就直接放这里了---->
	<!-- <script type="text/javascript" src="js/header.js"></script> -->
	
</head>
<body>

<!-- 登录 注册  购物车 -->
<div class="container-fluid" style="margin-bottom: 10px ;margin-top: 10px;">
	<div class="col-lg-4 col-md-4 col-sm-6">
		<img src="${pageContext.request.contextPath }/imgs/logo.jpg" />
	</div>

	<div class="col-lg-5 col-md-4 hidden-sm hidden-xs" style="padding-top: 10px">
		<img src="${pageContext.request.contextPath }/imgs/header.png" />
	</div>
	
	<!-- 还没登陆的时候显示的div -->
	<c:if test="${empty loginuser }"><!-- page,request,session,application -->
		<div id="not-login" class="col-lg-3 col-md-4 col-sm-6" style="padding-top: 35px;">
			<a href="${pageContext.request.contextPath}/jsps/login.jsp">登录</a> &nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/jsps/register.jsp">注册</a> &nbsp;&nbsp;&nbsp;
		</div>
	</c:if>
	<!-- 已经登陆的时候显示的div -->
	<c:if test="${!empty loginuser }">
		<div id="logged-in" class="col-lg-3 col-md-4 col-sm-6" style="padding-top: 35px;">
			<div style="color: #7B68EE;padding-left:15px; padding-bottom: 5px;">
				欢迎：${loginuser.username}
			</div> &nbsp;&nbsp;&nbsp;
			<!-- <span id="usernameSpan" style="color: #7B68EE;"></span> &nbsp;&nbsp;&nbsp; -->
			<a href="${pageContext.request.contextPath}/index.jsp" onclick="exit()">退出</a> &nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/jsps/shopcar.jsp">购物车</a>
		</div>
	</c:if>
	
</div>
<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- 品牌和切换分组以获得更好的移动展示效果 -->
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath }/index.jsp">BL首页</a>
			</div>

			<!-- 收集导航链接，表单和其他内容以进行切换 -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					
					<li><a href="${pageContext.request.contextPath}/queryPagingProduct?genre=ballad">民谣吉他</a></li>
					<li><a href="${pageContext.request.contextPath}/queryPagingProduct?genre=yucli">尤克里里</a></li>
					<li><a href="${pageContext.request.contextPath}/queryPagingProduct?genre=classic">古典吉他</a></li>
					<li><a href="${pageContext.request.contextPath}/queryPagingProduct?genre=electric">电吉他</a></li>
					<li><a href="javascript:void(0)">关于BL吉他商城</a></li>
					</ul>
					
				<!-- 搜索栏 -->
				<form class="navbar-form navbar-right">
					<div class="form-group">
						<input id="queryText" type="text" class="form-control" placeholder="搜索商品">
					</div>
					<input id="queryBtn" type="button" class="btn btn-default" value="搜索">
					<!-- <button id="queryBtn" class="btn btn-default">搜索</button> -->
				</form>
			</div>
		</div>
	</nav>
</div>
</body>
<script type="text/javascript">
	function exit(){
		$.ajax({
			url:"${pageContext.request.contextPath}/logout",
			error:function(){
				alert("服务器开小差啦，请稍后再试");
			}
		});
	}
	/* $("body").keydown(function(event){
		if(event.keyCode==13){
			$("#queryBtn").click();
		}
	}); */
	//为搜索按钮添加点击事件
	$("#queryBtn").click(function(){
		var name = $("#queryText").val();
		if(name.length==0){
			return false;
		}
		window.location.href = "${pageContext.request.contextPath}/query?name="+name;
	});
	/* 
		ajax是用来点对点通讯，是传输数据的
	 	$.ajax({
			url:"${pageContext.request.contextPath}/query",
			type:"POST",
			data:{"name":name},
			dataType:"json",
			success:function(data){
				alert(data+"请求成功");
			},
			error:function(){
				alert("服务器开小差啦，请稍后再试");
			}
		}); 
	*/
</script>
</html>
