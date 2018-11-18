<%@page import="cn.bl.bean.Guitar"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 
	分页显示各个种类的商品
 -->

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
	<title>BL吉他商城 - ${genreGBK}</title>
</head>
<body>
<div class="container">
<jsp:include page="header.jsp"></jsp:include>
	
	<!-- 商品 -->
	<div class="row">
		<c:forEach items="${pageBean.guitarList}" var="guitar" >
			<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" align="center">
				<!-- 1.图片展示，2.品牌描述，3.价格 -->
				<a href="${pageContext.request.contextPath}/jsps/product.jsp?id=${guitar.id}" >
					<img src="${pageContext.request.contextPath}/${guitar.imgpath}"
				></img></a>
				<p><font color="gray">${guitar.guitarname}</font></p>
				<p><font color="red">${guitar.price}</font></p>
			</div>
		</c:forEach>
		<%-- <%
		ArrayList<Guitar> list = new ArrayList<Guitar>();
		for (Guitar guitar : list) {
			
		}
		for(int i = 0;i < 10;i++){
			
		}
		%> --%>
	</div>
	<!-- 分页 -->
	<div style="width: 160px; margin: 0 auto; margin-top: 50px;">
	<nav aria-label="Page navigation">
	  <ul class="pagination">
	  	
	  	<!-- 上一页 -->
	    <li>
	    	<!-- 如果是第一页就不让点 -->
	    	<c:if test="${pageBean.curPage==1}">
	    		<a href="javascript:void(0);" aria-label="Previous">
		        	<span aria-hidden="true">&laquo;</span>
		     	</a>
	    	</c:if>
	      	<!-- 如果不是第一页就可以到上一页 -->
	    	<c:if test="${pageBean.curPage!=1}">
	    		<a href="${pageContext.request.contextPath}/queryPagingProduct?curPage=${pageBean.curPage-1}&genre=${genre}" aria-label="Previous">
		        	<span aria-hidden="true">&laquo;</span>
		     	</a>
	    	</c:if>
	    </li>
	    
	    <!-- 是否为当前页 -->
	    <c:forEach begin="1" end="${pageBean.totalPage}" var = "page">
	    	<!-- 不是当前页 -->
	    	<c:if test="${pageBean.curPage!=page}">
		    	<li>
		    		<a href="${pageContext.request.contextPath}/queryPagingProduct?curPage=${page}&genre=${genre}">${page}</a>
		    	</li>
	    	</c:if>
	    	<!-- 是当前页 -->
	    	<c:if test="${pageBean.curPage==page}">
		    	<li class="active">
		    		<a href="javascript:void(0)">${page}</a>
		    	</li>
	    	</c:if>
	    </c:forEach>
	   
	    <!-- 下一页 -->
	    <li>
	    	<!-- 如果是最后一页就不让点 -->
	    	<c:if test="${pageBean.curPage==pageBean.totalPage}">
	    		<a href="javascript:void(0);" aria-label="Previous">
		        	<span aria-hidden="true">&raquo;</span>
		     	</a>
	    	</c:if>
	      	<!-- 如果不是最后一页就可以到下一页 -->
	    	<c:if test="${pageBean.curPage!=pageBean.totalPage}">
	    		<a href="${pageContext.request.contextPath}/queryPagingProduct?curPage=${pageBean.curPage+1}&genre=${genre}" aria-label="Previous">
		        	<span aria-hidden="true">&raquo;</span>
		     	</a>
	    	</c:if>
	    </li>
	    
	  </ul>
	</nav>
	</div>
	
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>