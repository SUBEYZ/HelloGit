package cn.bl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bl.bean.User;
import cn.bl.service.Add2CarService;
/**
 * 添加商品到购物车
 */
public class Add2CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取数据：guitarid,user
		String strId = request.getParameter("id");
		User user = null;
		try {
			user = (User)request.getSession().getAttribute("loginuser");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//调用业务层
		Add2CarService service = new Add2CarService();
		String res = service.add2car(user, strId);
		
		//返回结果
		response.getWriter().print(res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}