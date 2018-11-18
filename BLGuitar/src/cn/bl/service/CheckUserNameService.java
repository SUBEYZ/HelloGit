package cn.bl.service;

import cn.bl.dao.user.dao.UserDao;
import cn.bl.dao.user.factory.UserFactory;

public class CheckUserNameService {
	private UserDao userDao = UserFactory.getInstance();
	public boolean checkUserName(String username) {
		if(username==null || username.trim().length()==0) {
			return false;
		}
		if(userDao.checkUserName(username.trim())) {
			return true;
		}else {
			return false;
		}
	}
}
