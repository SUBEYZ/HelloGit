package cn.bl.service;

import cn.bl.bean.User;
import cn.bl.dao.user.dao.UserDao;
import cn.bl.dao.user.factory.UserFactory;

public class RegisterService {
	private UserDao userDao = UserFactory.getInstance();
	public String register(String username, String password, String email, String code, String sessionCode) {
		// 1.验证验证码
		if(code==null || code.trim().length()==0) {
			return "code";
		}
		code = code.trim();
		if (sessionCode == null || !code.equals(sessionCode)) {
			return "code";
		}

		// 2.验证数据格式
		if(username==null || username.trim().length()==0) {
			return "user";
		}
		username = username.trim();
		if (username.length() < 3 || username.length() > 18) {
			return "user";
		}
		//密码允许输入空格
		if (password == null || password.length() < 6 || password.length() > 18) {
			return "user";
		}
		
		//验证邮箱
		if(email==null || email.trim().length()==0) {
			return "email";
		}
		email = email.trim();
		String regex = "^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$";
		if (!email.matches(regex)) {
			return "email";
		}

		// 3.封装数据
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		//System.out.println(user);

		// 4.调用userdao并返回结果
		if (userDao.add(user)) {
			return "true";
		}
		return "user";
	}
}
