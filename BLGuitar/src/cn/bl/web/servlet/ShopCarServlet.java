package cn.bl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bl.bean.User;
import cn.bl.service.ShopCarService;
/**
 * 查询指定用户的购物车中所有商品的信息，返回数据：json格式字符串
 */
public class ShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前登陆的用户
		User user = (User)request.getSession().getAttribute("loginuser");
		//调用逻辑业务层
		ShopCarService service = new ShopCarService();
		String res = service.shopCar(user);
		
		//返回结果
		response.getWriter().print(res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
/*
{"guitars":[
{"bimgpath":"goodsimgs/bguitar03.jpg","carid":7,"count":1,"guitarname":"TYMA2018全新单板杰作","id":3,"imgpath":"goodsimgs/guitar03.jpg","price":12000},
{"bimgpath":"goodsimgs/bguitar02.jpg","carid":11,"count":1,"guitarname":"LAVA全桃花芯木面单","id":2,"imgpath":"goodsimgs/guitar02.jpg","price":23000}
]}
*/
