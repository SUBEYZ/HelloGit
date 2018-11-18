package cn.bl.dao.car.dao;

import java.util.ArrayList;

import cn.bl.bean.Car;
public interface CarDao {
	
	/**
	 * 根据用户id和商品id添加一件该商品到数据库
	 * @param userid
	 * @param guitarid
	 * @return boolean
	 */
	public abstract boolean add(int userid,int guitarid);
	
	/**
	 * 根据car表的id将count减一
	 * @param carid
	 * @return
	 */
	public abstract boolean deleteById(int carid);
	
	/**
	 * 根据用户id和商品id从数据库移除一件该商品
	 * @param userid
	 * @param guitarid
	 * @return boolean
	 */
	public abstract boolean delete(int userid,int guitarid);
	
	/**
	 * 根据用户id从数据库移除该用户所有商品
	 * @param userid
	 * @return boolean
	 */
	public abstract boolean remove(int userid);
	
	/**
	 * 根据用户id从数据库查找所有他的购物车中的商品
	 * @param userid
	 * @return List<Car>
	 */
	public abstract ArrayList<Car> getAll(int userid);
	
}
