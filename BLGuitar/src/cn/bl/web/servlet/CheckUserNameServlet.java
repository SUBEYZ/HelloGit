package cn.bl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bl.service.CheckUserNameService;
/**
 * 配合Ajax检验用户名是否存在
 */
public class CheckUserNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.收集数据
		String username = request.getParameter("username");
		//2.调用业务层
		CheckUserNameService service = new CheckUserNameService();
		boolean res = service.checkUserName(username);
		response.getWriter().print(res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}