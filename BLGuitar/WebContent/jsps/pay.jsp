<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 
	支付页面
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
	
	<title>BL吉他商城 - 支付</title>
	
	<!-- 引入自己写的css,js -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pay.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/pay.js"></script>
	
</head>
<body>
<div class="container">
<jsp:include page="header.jsp"></jsp:include> 

	<!-- 表单 -->
	<form action="${pageContext.request.contextPath}/pay" method="post">
		<!-- 填写收货人信息 -->
		<div class="row" id="msg">
				填写收货人信息
		</div>
		<table>
			<tr>
				<td><input type="text" placeholder="请输入收货人姓名" class="input"></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="请输入收货人地址" class="input"></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="请输入收货人手机号" class="input"></td>
			</tr>
		</table>
		
		<!-- 选择银行 -->
		<div class="row" id="bank">
				选择银行
		</div>
		<!-- 工商银行 中国银行  农业银行 交通银行 平安银行 建设银行  -->
		<div id="bankRP">
			<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked" />
				<img src="${pageContext.request.contextPath}/bank_img/icbc.bmp" align="middle" />
				&nbsp;&nbsp;&nbsp;&nbsp; 
			<input type="radio" name="pd_FrpId" value="BOC-NET-B2C" />
				<img src="${pageContext.request.contextPath}/bank_img/bc.bmp" align="middle" />
				&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="pd_FrpId" value="ABC-NET-B2C" />
				<img src="${pageContext.request.contextPath}/bank_img/abc.bmp" align="middle" />
			<br /> <br /> 
			<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C" />
				<img src="${pageContext.request.contextPath}/bank_img/bcc.bmp" align="middle" />
				&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="pd_FrpId" value="PINGANBANK-NET" />
				<img src="${pageContext.request.contextPath}/bank_img/pingan.bmp" align="middle" />
				&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="pd_FrpId" value="CCB-NET-B2C" />
				<img src="${pageContext.request.contextPath}/bank_img/ccb.bmp" align="middle" />
		</div>
		
		<!-- 显示总价 -->
		<div id="textsum">
			<span>商品总价：</span> 
			<span id="sum">100</span>
			<!-- 确认支付按钮 -->
			<button id="btnPay" onclick="fun_pay()">确认支付</button>
		</div>
	</form>
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>