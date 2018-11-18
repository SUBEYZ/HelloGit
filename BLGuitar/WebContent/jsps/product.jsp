<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 
	商品详情页面
 -->

<!DOCTYPE html>
<html>
<head>
	<title>BL吉他商城 - 商品详情</title>
                                                      	
	<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
	<meta name="BLGuitar" content="width=device-width, initial-scale=1">
	
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css" type="text/css" />
<!--  相当于   '/BLGuitar'
			el表达式
			用来获取当前项目的根目录
			一般在项目中会使用绝对路径，而项目的名称可能会有变化，所以使用el表达式防止需要更改源代码-->
	<!-- <script src="../js/product.js" type="text/javascript"></script> -->
	<%String id= request.getParameter("id"); %>
	<script type="text/javascript">
		$(function() {
			var id = <%=id%>;
			// 获取商品详情 
			$.ajax({
				url:"/BLGuitar/product",
				type:"POST",
				data:{"id":id},    
				dataType:"json",
				success:function(data){
					$("#name").html(data.guitarname);
					$("#price").html("狂欢价：￥"+data.price);
					var bimgpath = data.bimgpath;
					$("#img").html("<img alt='加入购物车图片' src='../"+bimgpath+"'>");
				},
				error:function(){
					alert("服务器开小差啦，请稍后再试");
				}
			});
			//为点击加入购物车添加一个事件
			$("#add").click(function(){
				$.ajax({
					url:"/BLGuitar/add2car",
					type:"POST",
					data:{"id":id},
					dataType:"text",
					success:function(data){
						if(data=="true"){
							window.location.href = "shopcar.jsp";
						}else{
							alert(data);
						}
					},
					error:function(){
						alert("服务器开小差啦，请稍后再试");
					}
				});
			});
		});
	</script>

</head>
<body>
<div class="container">
	<!-- 引入header.jsp  ->  首页logo... 、 导航栏 -->
	<jsp:include page="header.jsp"></jsp:include>
	
	<!-- 
		中间商品介绍部分
		左边一个图片，右边是商品名、价格、加入购物车按钮
		再下面就是商品详情四个大字以及商品详细信息的图片
	-->
	<div class="row">
		<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12" align="center">
			<a id="img"></a>	
			<!--style="padding-left: 20%; padding-bottom: 15%;" goodsimgs/bguitar01.jpg -->
		</div>
		<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12" align="center">
			<h1 class="black" id="name"></h1>
			<h2 class="black" id="price"></h2>
			<h4 class="black">颜色分类：原木色</h4>
			<h5 class="black">数量：1 &nbsp;&nbsp;&nbsp;&nbsp;库存：22</h5>
			<img id="add" src="${pageContext.request.contextPath }/imgs/btnshopcar.png"></img>
		</div>
	</div>
	<div class="row" id="pdetail" >
			商品详情
	</div>
	<!-- 商品详情图片--这里就是没有写数据库，写死了的 -->
	<div align="center"><img alt="图片1" src="${pageContext.request.contextPath}/goodsimgs/pd1.jpg"></div>
	<div align="center"><img alt="图片2" src="${pageContext.request.contextPath}/goodsimgs/pd2.jpg"></div>
	<div align="center"><img alt="图片3" src="${pageContext.request.contextPath}/goodsimgs/pd3.jpg"></div>
	<div align="center"><img alt="图片4" src="${pageContext.request.contextPath}/goodsimgs/pd4.jpg"></div>
	<div align="center"><img alt="图片5" src="${pageContext.request.contextPath}/goodsimgs/pd5.jpg"></div>
	<div align="center"><img alt="图片6" src="${pageContext.request.contextPath}/goodsimgs/pd6.jpg"></div>
	<!-- 引入尾部分--友情链接和版权声明 -->
	<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>