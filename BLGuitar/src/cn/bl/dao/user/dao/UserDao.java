package cn.bl.dao.user.dao;

import java.util.ArrayList;

import cn.bl.bean.User;
/**
 * 接口  操作用户 
 */
public interface UserDao {
	/**
	 * 添加一个用户到数据库，可使用于注册操作
	 * @param user
	 * @return boolean
	 */
	public abstract boolean add(User user);
	/**
	 * 根据id查询一个用户
	 * @param id
	 * @return User 没查找到的时候返回null
	 */
	public abstract User getOneByID(int id);
	/**
	 * 根据用户名、密码查询是否存在该用户
	 * 登陆时可以使用
	 * @param username
	 * @param password
	 * @return boolean
	 */
	public abstract User getOneByNamePwd(String username,String password);
	/**
	 * 查询所有用户
	 * @return List<User>
	 */
	public abstract ArrayList<User> getAll();
	/**
	 * 查询用户名是否注册过
	 * @param name
	 * @return
	 */
	public abstract boolean checkUserName(String username);
	/**
	 * 根据用户名和邮箱地址查找用户，用于查找密码时使用
	 * @param username
	 * @param receiveEmail
	 * @return
	 */
	public abstract boolean getOneByNameEmail(String username, String receiveEmail);
	/**
	 * 根据用户名更改密码
	 * @param username
	 * @param password
	 * @return
	 */
	public abstract boolean updatePwd(String username,String password);
}
