<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title>BL吉他商城 - 查询结果</title>
</head>
<body>
<div class="container">
<jsp:include page="header.jsp"></jsp:include>
	<div class="row">
	<c:forEach items="${queryList}" var="guitar"><!-- 栅格系统 -->
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" align="center">
			<!-- 1.图片展示，2.品牌描述，3.价格 -->
			<a href="${pageContext.request.contextPath}/jsps/product.jsp?id=${guitar.id}" >
				<img src="${pageContext.request.contextPath}/${guitar.imgpath}"
			></img></a>
			<p><font color="gray">${guitar.guitarname}</font></p>
			<p><font color="red">${guitar.price}</font></p>
		</div>
	</c:forEach>
	</div>
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>