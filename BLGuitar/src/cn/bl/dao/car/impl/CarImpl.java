package cn.bl.dao.car.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.bl.bean.Car;
import cn.bl.dao.car.dao.CarDao;
import cn.bl.util.JDBCUtils;

/**
 * 购物车实现类
 */
public class CarImpl implements CarDao {

	private Connection connection;

	@Override
	public boolean add(int userid, int guitarid) {
		
		connection = JDBCUtils.getConnection();
		PreparedStatement pst = null;
		// 先将原来的商品个数查出来，如果没查到该商品信息就添加一条记录
		// 如果查找到了，就将count的值加一
		String findsql = "select * from car where userid=? and guitarid=?";
		boolean boo = false;
		try {
			pst = connection.prepareStatement(findsql);
			pst.setInt(1, userid);
			pst.setInt(2, guitarid);
			ResultSet set = pst.executeQuery();
			if(set.next()) {
				int count = set.getInt("count");
				boo = update(userid,guitarid,count);
			}else {
				boo = insert2car(userid, guitarid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	@Override
	public boolean delete(int userid, int guitarid) {
		//先从数据库中查找有没该条记录，没有的话就返回false，
		//有的话看看是不是大于1的，如果大于1就将count减一，如果count等于1就直接移除该条记录
		connection = JDBCUtils.getConnection();
		PreparedStatement pst = null;
		String findsql = "select * from car where userid=? and guitarid=?";
		boolean boo = false;
		try {
			pst = connection.prepareStatement(findsql);
			pst.setInt(1, userid);
			pst.setInt(2, guitarid);
			ResultSet set = pst.executeQuery();
			if(set.next()) {
				int count = set.getInt("count");
				if(count>1) {
					boo = deleteOne(userid,guitarid,count);
				}else {
					boo = deleteRecord(userid,guitarid);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}

	@Override
	public boolean remove(int userid) {
		connection = JDBCUtils.getConnection();
		String sql = "delete from car where userid=?";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, userid);
			return pst.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Car> getAll(int userid) {
		connection = JDBCUtils.getConnection();
		String sql = "select * from car where userid=?";
		PreparedStatement pst = null;
		ArrayList<Car>list = new ArrayList<>();
		try {
			//查找所有，然后封装为一个list
			pst =  connection.prepareStatement(sql);
			pst.setInt(1, userid);
			ResultSet set = pst.executeQuery();
			while(set.next()) {
				Car car = new Car();
				car.setId(set.getInt("id"));
				car.setUserid(set.getInt("userid"));
				car.setGuitarid(set.getInt("guitarid"));
				car.setCount(set.getInt("count"));
				list.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 添加商品的时候，如果商品已经在该用户的购物车了，就使得count加一
	 * @param userid
	 * @param guitarid
	 * @param count
	 * @return
	 */
	private boolean update(int userid,int guitarid,int count) {
		String updatesql = "update car set count=? where userid=? and guitarid=?";
		PreparedStatement pst = null;
		
		try {
			pst = connection.prepareStatement(updatesql);
			pst.setInt(1, count+1);
			pst.setInt(2, userid);
			pst.setInt(3, guitarid);
			return pst.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 添加商品的时候，如果商品不再该用户的购物车中，就添加一条记录
	 * @param userid
	 * @param guitarid
	 * @return
	 */
	private boolean insert2car(int userid,int guitarid) {
		String inertsql = "insert into car(userid,guitarid,count) values(?,?,?)";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(inertsql);
			pst.setInt(1, userid);
			pst.setInt(2, guitarid);
			pst.setInt(3, 1);
			return pst.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 购物车中该商品数量大于1，点击移除时商品数量减一
	 * @param userid
	 * @param guitarid
	 * @param count
	 * @return
	 */
	private boolean deleteOne(int userid, int guitarid, int count) {
		String updatesql = "update car set count=? where userid=? and guitarid=?";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(updatesql);
			pst.setInt(1, count-1);
			pst.setInt(2, userid);
			pst.setInt(3, guitarid);
			return pst.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 购物车中该商品数量不大于1时，点击移除就是从删除该条记录
	 * @param userid
	 * @param guitarid
	 * @return
	 */
	private boolean deleteRecord(int userid, int guitarid) {
		String sql = "delete from car where userid=? and guitarid=?";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, userid);
			pst.setInt(2, guitarid);
			return pst.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteById(int carid) {
		connection = JDBCUtils.getConnection();
		String sql = "delete from car where id=?";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, carid);
			return pst.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
