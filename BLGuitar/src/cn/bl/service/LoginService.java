package cn.bl.service;

import javax.servlet.http.HttpServletRequest;

import cn.bl.bean.User;
import cn.bl.dao.user.dao.UserDao;
import cn.bl.dao.user.factory.UserFactory;

public class LoginService {
	private UserDao userDao = UserFactory.getInstance();
	public String login(String username,String password,String code,String sessionWord, HttpServletRequest request) {
		// 验证码错误返回"code"
		// 用户不存在或者密码错误返回"user"
		// 可以登陆返回"true"

		//先将 用户名 和 验证码 空格去掉
		if(username==null || username.trim().length()==0) {
			return "user";
		}
		username = username.trim();
		if(code==null || code.trim().length()==0) {
			return "code";
		}
		code = code.trim();
		
		// 先验证验证码是否为空或者一致
		// System.out.println(sessionWord+","+code);
		if (sessionWord == null || !sessionWord.equals(code)) {
			return "code";
		}

		// 然后验证用户名密码
		User user = userDao.getOneByNamePwd(username, password);
		if (user != null) {
			request.getSession().setAttribute("loginuser", user);
			return "true";
		} else {
			request.getSession().setAttribute("loginuser", null);
			return "user";
		}
	}
}
