package cn.bl.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bl.bean.Guitar;
import cn.bl.service.QueryService;

public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户输入要查询的商品名
		String name = request.getParameter("name");
		//调用业务逻辑层
		System.out.println(name);
		QueryService service = new QueryService();
		ArrayList<Guitar>list = service.query(name);
		
		//设置request域
		request.setAttribute("queryList", list);
		System.out.println(list.size());
		//跳转页面
		request.getRequestDispatcher("/jsps/queryList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
