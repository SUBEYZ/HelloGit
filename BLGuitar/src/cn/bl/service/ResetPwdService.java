package cn.bl.service;

import cn.bl.dao.user.dao.UserDao;
import cn.bl.dao.user.factory.UserFactory;

public class ResetPwdService {
	private UserDao userDao = UserFactory.getInstance();
	public String resetPwd(String username,String password, String sessionCode, String code) {
		
		//1.验证用户名密码格式,验证验证码
		if(username==null || username.trim().length()==0) {
			return "username";
		}
		username = username.trim();
		if(code==null || code.length()==0) {
			return "code";
		}
		code = code.trim();
		if(sessionCode==null || !sessionCode.equals(code)) {
			return "code";
		}
		if(password==null || password.length()<6 || password.length()>18){
			return "pwd";
		}
		
		//2.调用dao层
		boolean res = userDao.updatePwd(username, password);
		
		//3.返回结果
		return ""+res;
	}
}
