package cn.bl.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import cn.bl.domain.User;
import cn.bl.mapper.UserMapper;

@Repository(value="userDAO")
public class UserImpl implements UserDAO{
	@Resource(name="factory")
	private SqlSessionFactory factory;
	@Override
	public User login(User user) {
		SqlSession session = factory.openSession();
		//System.out.println("username:"+user.getUsername()+",password:"+user.getPassword());
		User res = session.getMapper(UserMapper.class).login(user);
		//System.out.println(res);
		return res;
	}
}
