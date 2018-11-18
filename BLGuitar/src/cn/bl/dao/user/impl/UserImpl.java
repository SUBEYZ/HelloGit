package cn.bl.dao.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.bl.bean.User;
import cn.bl.dao.user.dao.UserDao;
import cn.bl.util.JDBCUtils;

/**
 * 用户  实现类
 */
public class UserImpl implements UserDao {

	@Override
	public boolean add(User user) {
		//1.收集数据
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		// 2.卫条件校验数据
		// 校验用户名
		if (username == null || username.length() < 3 || username.length() > 18) {
			return false;
		}
		// 校验密码
		if (password == null || password.length() < 6 || password.length() > 18) {
			return false;
		}
		// 校验邮箱
		String regex = "^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$";
		if (email == null || !email.matches(regex)) {
			return false;
		}
		// 3.连接数据库并存入数据库
		Connection connection = JDBCUtils.getConnection();
		String sql = "insert into user(username,password,email) values(?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, email);
			return pst.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User getOneByID(int id) {
		Connection connection = JDBCUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "select * from user where id=?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet set = pst.executeQuery();
			if(set.next()) {
				User user = new User();
				user.setId(set.getInt("id"));
				user.setUsername(set.getString("username"));
				user.setPassword(set.getString("password"));
				user.setEmail(set.getString("email"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getOneByNamePwd(String username, String password) {
		User user = new User();
		//校验数据
		if(username==null || username.length()<3 || username.length()>18) {
			return null;
		}
		if(password==null || password.length()<6 || password.length()>18) {
			return null;
		}
		
		//链接数据库
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from user where username=? and password=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet set = pst.executeQuery();
			if(set.next()) {
				user.setId(set.getInt("id"));
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(set.getString("email"));
				return user;
			}else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<User> getAll() {
		ArrayList<User> list = new ArrayList<>();
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from user";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql);
			ResultSet set = pst.executeQuery();
			while(set.next()) {
				User user = new User();
				user.setId(set.getInt("id"));
				user.setUsername(set.getString("username"));
				user.setPassword(set.getString("password"));
				user.setEmail(set.getString("email"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean checkUserName(String username) {
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from user where username=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			return pst.executeQuery().first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean getOneByNameEmail(String username, String receiveEmail) {
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from user where username=? and email=?";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, receiveEmail);
			System.out.println(username+","+receiveEmail);
			if(pst.executeQuery().next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePwd(String username, String password) {
		Connection connection = JDBCUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "update user set password=? where username=?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, username);
			return pst.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
