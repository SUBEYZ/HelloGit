package cn.bl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bl.service.LoginService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求数据
		String username = request.getParameter("username");//用户名
		String password = request.getParameter("password");//密码
		String code = request.getParameter("code");//用户输入的验证码
		//放在session中的验证码
		String sessionWord = (String) request.getSession().getAttribute("checkcode_session");
		
		//调用业务逻辑层
		LoginService service = new LoginService();
		String res = service.login(username,password,code,sessionWord,request);
		
		//返回结果
		response.getWriter().write(res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}