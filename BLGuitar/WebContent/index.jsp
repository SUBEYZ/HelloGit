<%@page import="net.sf.json.JSON"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 上面一句用于引入jstl核心标签库 -->
<!-- 
	商场首页
 -->

<!DOCTYPE html>
<html>
<head>
	<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
	<meta name="BLGuitar" content="width=device-width, initial-scale=1">
	
	<title>BL吉他商城 - 首页</title>
	<meta name="description" content="BL吉他商城，最专业的网上吉他商城" />
	<meta name="Keywords" content="网上购物,网上商城,吉他,民谣,尤克里里,电吉他,古典" />
	
	<!-- 
		${pageContext.request.contextPath}
			相当于   '/BLGuitar'
			el表达式
			用来获取当前项目的根目录
			一般在项目中会使用绝对路径，而项目的名称可能会有变化，所以使用el表达式防止需要更改源代码
	 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	
	<script src="${pageContext.request.contextPath}/js/index.js" type="text/javascript"></script>
</head>
<body>
<!-- 
	1.class="container"：整个页面放在这个container里面，bootstrap会自动调整整体左右边距15px
	2.class="row"：这里div里面的div都是放在同一行
	3.class="col-lg-3 col-md-3 col-sm-6 col-xs-12"--栅格系统
		lg：超大屏(比如台式电脑的显示屏一般比较大)
		md：大屏(比如笔记本电脑)
		sm：小屏(比如平板)
		xs：超小屏(手机)
 -->
<div class="container">
	<!-- 引入header.jsp  ->  首页logo... 、 导航栏 -->
	<jsp:include page="jsps/header.jsp"></jsp:include>
	
	<!-- 轮播图部分 -->
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
		<!-- 轮播图中的 小点 -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>

		<!-- 轮播图中的 图片 -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<!-- <a href=""><img id="rou8" src="" alt="轮播图1"></a> -->
				<a href="" id="rou8"></a>
			</div>
			<div class="item">
				<!-- <a href=""><img id="rou9" src="" alt="轮播图2"></a> -->
				<a href="" id="rou9"></a>
			</div>
			<div class="item">
				<!-- <a href=""><img id="rou10" src="" alt="轮播图3"></a> -->
				<a href="" id="rou10"></a>
			</div>
		</div>

		<!-- 轮播图终点 上一张 下一张按钮 -->
		<a class="left carousel-control" href="#carousel-example-generic"role="button" data-slide="prev"> 
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			<span class="sr-only">Previous</span>
		</a> 
		<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> 
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	
	<!-- 店长推荐部分 -->
	<div class="container-fluid">
		<div class="row" style="font-size: 32px;
				padding-left: 46%; padding-top: 15px;padding-bottom: 15px;">
			店长推荐
		</div>
		<div class="container-fluid">
			<div class="row">
			<!-- 使用jstl+el表达式大大简化了代码 （首页固定8个商品）-->
			<c:forEach begin="1" end="8" var="cur">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" align="center">
					<!-- 1.图片展示，2.品牌描述，3.价格 -->
					<a href="" id="${cur}-img"></a><!-- 里面放图片<img src=""></img>  -->
					<p><font id="${cur}-name" color="gray"></font></p>
					<p><font id="${cur}-price" color="red"></font></p>
				</div>
			</c:forEach>
			</div>
		</div>
	</div>
	<!-- 引入尾部分--友情链接和版权声明 -->
	<jsp:include page="jsps/footer.jsp"></jsp:include>
	
</div>
</body>
</html>


<!-- 店长推荐的第二行 -->
<!-- <div class="row">
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" align="center">
		<a href="" id="5-img"></a>
		<p><font id="5-name" color="gray"></font></p>
		<p><font id="5-price" color="red"></font></p>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" align="center">
		<a href="" id="6-img"></a>
		<p><font id="6-name" color="gray"></font></p>
		<p><font id="6-price" color="red"></font></p>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" align="center">
		<a href="" id="7-img"></a>
		<p><font id="7-name" color="gray"></font></p>
		<p><font id="7-price" color="red"></font></p>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" align="center">
		<a href="" id="8-img"></a>
		<p><font id="8-name" color="gray"></font></p>
		<p><font id="8-price" color="red"></font></p>
	</div>
</div> -->