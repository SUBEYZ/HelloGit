package cn.bl.dao.user.factory;

import cn.bl.dao.user.dao.UserDao;
import cn.bl.dao.user.impl.UserImpl;

public class UserFactory {
	public static UserDao getInstance() {
		return new UserImpl();
	}
}
