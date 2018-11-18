package cn.bl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bl.service.CheckCodeService;

public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取数据
		String sessionWord = (String) request.getSession().getAttribute("checkcode_session");
		String code = request.getParameter("code");
		//调用业务层
		CheckCodeService service = new CheckCodeService();
		boolean res = service.checkCode(code, sessionWord);
		//返回结果
		response.getWriter().print(res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}