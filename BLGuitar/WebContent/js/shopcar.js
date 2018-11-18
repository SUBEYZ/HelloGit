/**
 * 显示指定用户的购物车中所有的商品信息
 */
$(function(){
	$.ajax({
		url:"/BLGuitar/shopCar",
		dataType:"json",
		type:"POST",
		success:function(data){
			var sumMoney = 0;//商品总价
			//表头部分
			var str = "<tr><th>商品图片</th><th>商品名</th><th>单价</th><th>数量</th><th>小计</th><th>操作</th></tr>";
			
			//表的每一列
			for(var i = 0; i< data.guitars.length;i++){
				var id = data.guitars[i].id;//商品id
				var carid = data.guitars[i].carid;//购物车id
				var imgpath = data.guitars[i].imgpath;//商品图片路径
				var guitarname = data.guitars[i].guitarname;//商品名
				var price = data.guitars[i].price;//商品单价
				var count = data.guitars[i].count;//单样商品个数
				var money = price*count;//单样商品小计
				sumMoney = sumMoney+money;//商品总价
				str = str+("<tr><td><a href='product.jsp?id="+id+"'><img class='img' alt='商品图片' src='../"+imgpath+"' ></a></td><td>"+guitarname+"</td><td>￥"+price+"</td><td>"+count+"</td><td>￥"+money+"</td><td><a id='remove' onclick='remove("+carid+")'>移除</a></td></tr>");
			}
			//插入HTML到table
			$("#table").html(str);
			//设置商品总价
			$("#sum").html("￥"+sumMoney);
		},
		error:function(){
			alert("服务器开小差啦，请稍后再试");
		}
	});
});
/*$("#remove").click(function(){
	alert(111);
});*/
/*这个方法不能放在页面加载方法里面（$(function(){），
因为这个方法是必须在页面加载完之后才调用的
如果放在里面，就会调用失败*/
function remove(id){
	$.ajax({
		url:"/BLGuitar/remove",
		data:{"carid":id},
		dataType:"json",
		success:function(data){
			if(data==false){
				alert("请求错误，请稍后再试");
			}else{
				window.location.href = "/BLGuitar/jsps/shopcar.jsp";
			}
		},
		error:function(){
			alert("服务器开小差啦，请稍后再试");
		}
	});
}








