package cn.bl.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bl.service.SendEmailService;
/**
 * 获取邮箱验证码，发送验证码
 */
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取用户名、接收者邮箱
		String username = request.getParameter("username");
		String receiveEmail = request.getParameter("email");
		
		//2.调用业务逻辑层
		SendEmailService service = new SendEmailService();//调用这句需要mail.jar,否则就一致报错
		String res = service.sendEmail(username, receiveEmail);//发送邮件
		if(res.length()>5) {//长度大于4，也就是发送成功了，将emailCode设置到session
			String[]strs = res.split(":");//true:12345
			//System.out.println(strs[0]+","+strs[1]);
			response.getWriter().write(strs[0]);
			request.getSession().setAttribute("findPwdCode", strs[1]);
			request.getSession().setAttribute("resetPwd_UserName", username);
			return ;
		}
		response.getWriter().write(res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}