/**
 * 
 */

//页面加载
$(function(){
	$.ajax({
		url:"/BLGuitar/index",
		type:"POST",
		dataType:"json",
		success:function(data){
			/*服务器返回的数据是JSON格式的数组，现在将其解析出来，然后对index.jsp的页面进行相关设置*/
			//首页的八个商品
			for(var i = 0; i< 8; i++){//首页就写八个
				//获取数据--路径、名称、价格
				var guitarname = data.guitars[i].guitarname;
				var imgpath = data.guitars[i].imgpath;
				var price = data.guitars[i].price;
				var id = data.guitars[i].id;
				
				//生成id,并且设置到首页
				var in_img = (i+1) +"-img";//生成的第一个是这样的  -->  1-img,,,但是这里不能带‘#’,因为下面的必须有‘#’
				var in_name = (i+1) + "-name";
				var in_price = (i+1) + "-price";
				$("#"+in_img).html("<img src='"+imgpath+"'></img>");
				$("#"+in_name).html(guitarname);
				$("#"+in_price).html("￥"+price);
				//修改点击图片的链接,并且将商品的id也放进去  
				$("#"+(i+1)+"-img").attr('href',"jsps/product.jsp?id="+id);
			}
			//轮播图部分
			for(var i = 8; i< 11;i++){
				var guitarname = data.guitars[i].guitarname;
				var imgpath = data.guitars[i].imgpath;
				var price = data.guitars[i].price;
				var id = data.guitars[i].id;
				
				$("#rou"+i).html("<img src='"+imgpath+"'></img>");
				//修改点击图片的链接,并且将商品的id也放进去  
				$("#rou"+i).attr('href',"jsps/product.jsp?id="+id);
			}
		},
		error:function(){
			alert("服务器开小差啦，请稍后再试");
		}
	});
//${pageContext.request.contextPath}/goodsimgs/rou1.jpg
});
/*$.ajax({
url:"/BLGuitar/index",
type:"POST",
dataType:"text",/////////可以直接返回json格式数据//////////////////
success:function(data){
	服务器返回的数据是JSON格式的数组，现在将其解析出来，然后对index.jsp的页面进行相关设置
	var res = JSON.parse(data);//$.parseJSON(data);->新版本不推荐使用
	alert(data);
	alert(res.guitars[0].guitarname);
	for(var i = 0; i<4;i++){
		alert(res[0][i].guitarname);
	}
},
error:function(){
	alert("服务器开小差啦，请稍后再试");
}
});*/