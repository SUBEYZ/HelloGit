package cn.bl.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.bl.dao.UserDAO;
import cn.bl.domain.User;

@Service(value="userService")
public class UserService implements IUserService{
	@Resource(name="userDAO")
	private UserDAO dao;
	@Override
	public User login(User user) {
		return dao.login(user);
	}
}
