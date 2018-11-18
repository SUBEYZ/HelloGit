<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	
	<title>BL吉他商城 - 购物车</title>
	
	<script src="${pageContext.request.contextPath }/js/shopcar.js" type="text/javascript"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/shopcar.css" />

</head>
<body>
<div class="container">
<jsp:include page="header.jsp"></jsp:include>
	<table class="table table-striped" id="table">
		<!-- 这个展示购物车中商品的表格在js中嵌入HTML代码 -->
	</table>
	
	<!-- 显示总价 -->
	<div id="textsum"><span>商品总价：</span> <span id="sum"></span></div>
	<!-- 显示继续购物和去结账的两个按钮 -->
	<div id="btn">
		<a href="${pageContext.request.contextPath}/index.jsp"><img alt="继续购物按钮" src="${pageContext.request.contextPath}/imgs/btntoshop.png"></a>
		<a href="${pageContext.request.contextPath }/jsps/pay.jsp"><img alt="结账按钮" src="${pageContext.request.contextPath}/imgs/btnaccount.png"></a>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>

<!-- 
 共六列： 商品图片、商品名、单价、数量、小计、操作(移除) 
	<tr><th>商品图片</th><th>商品名</th><th>单价</th><th>数量</th><th>小计</th><th>操作</th></tr>
	<tr>	
		<td><img alt="商品图片" src="goodsimgs/guitar01.jpg" ></td>
		<td>商品名</td>
		<td>单价</td>
		<td>数量</td>
		<td>小计</td>
		<td>移除</td>
	</tr>
	
	<tr><td><img alt="商品图片" src="goodsimgs/guitar01.jpg" ></td><td>商品名</td><td>单价</td><td>数量</td><td>小计</td><td>移除</td></tr>
 -->
