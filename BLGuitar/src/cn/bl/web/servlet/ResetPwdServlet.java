package cn.bl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bl.service.ResetPwdService;

public class ResetPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.收集数据
		String password = request.getParameter("password");
		String username = (String) request.getSession().getAttribute("resetPwd_UserName");
		//System.out.println(password+","+username);
		String sessionCode = (String)request.getSession().getAttribute("findPwdCode");
		String code = request.getParameter("code");
		//System.out.println(sessionCode+","+code);
		
		//2.调用业务逻辑层
		ResetPwdService service = new ResetPwdService();
		String res = service.resetPwd(username, password,sessionCode,code);
		
		//3.返回结果
		response.getWriter().print(res);
		
		//4.将原来的session数据清除
		request.getSession().setAttribute("resetPwd_UserName", null);
	} 

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}