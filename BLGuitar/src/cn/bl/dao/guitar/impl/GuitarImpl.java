package cn.bl.dao.guitar.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import cn.bl.bean.Guitar;
import cn.bl.dao.guitar.dao.GuitarDao;
import cn.bl.util.JDBCUtils;

/**
 * 商品实现类
 */
public class GuitarImpl implements GuitarDao{

	@Override
	public Guitar getOneById(int id) {
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from guitar where id=?";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet set = pst.executeQuery();//never null
			set.next();
			Guitar guitar = new Guitar();
			guitar.setId(set.getInt("id"));
			guitar.setGuitarname(set.getString("guitarname"));
			guitar.setPrice(set.getDouble("price"));
			guitar.setImgpath(set.getString("imgpath"));
			guitar.setBimgpath(set.getString("bimgpath"));
			return guitar;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Guitar> getAll() {
		ArrayList<Guitar> list = null;
		Connection connection = JDBCUtils.getConnection();
		try {
			//1.链接数据库
			Statement statement = connection.createStatement();
			String sql = "select * from guitar";
			
			//2.执行SQL语句获得结果集
			ResultSet set = statement.executeQuery(sql);
			
			//3.将结果集ResultSet转换为ArrayList，用于封装Json
			//(JSONObject放进去的必须是一个list才能进行正确的转换)
			list = new ArrayList<>();
			while (set.next()) {
				Guitar guitar = new Guitar();
				guitar.setId(set.getInt(1));
				guitar.setGuitarname(set.getString(2));
				guitar.setPrice(set.getDouble(3));
				guitar.setImgpath(set.getString(4));
				guitar.setBimgpath(set.getString(5));
				list.add(guitar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Guitar> getByGenre(String genre) {
		ArrayList<Guitar>list = new ArrayList<>();
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from guitar where genre=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, genre);
			ResultSet set = pst.executeQuery();
			while(set.next()) {
				Guitar guitar = new Guitar();
				guitar.setBimgpath(set.getString("bimgpath"));
				guitar.setGuitarname(set.getString("guitarname"));
				guitar.setId(set.getInt("id"));
				guitar.setImgpath(set.getString("imgpath"));
				guitar.setPrice(set.getDouble("price"));
				list.add(guitar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<Guitar> getByName(String name) {
		ArrayList<Guitar>list = new ArrayList<>();
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from guitar where guitarname like ?";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, "%"+name+"%");
			ResultSet set = pst.executeQuery();
			while(set.next()) {
				Guitar guitar = new Guitar();
				guitar.setBimgpath(set.getString("bimgpath"));
				guitar.setGuitarname(set.getString("guitarname"));
				guitar.setId(set.getInt("id"));
				guitar.setImgpath(set.getString("imgpath"));
				guitar.setPrice(set.getDouble("price"));
				guitar.setGenre(set.getString("genre"));
				list.add(guitar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getTotalCount() {
		Connection connection = JDBCUtils.getConnection();
		String sql = "select count(*) from guitar";
		try {
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(sql);
			set.next();
			return set.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<Guitar> pageBeanList(int index, int curCount,String genre) {
		ArrayList<Guitar>list = new ArrayList<>();
		Connection connection = JDBCUtils.getConnection();
		String sql = "select * from guitar where genre=? limit ?, ?";
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, genre);
			pst.setInt(2, index);
			pst.setInt(3, curCount);
			ResultSet set = pst.executeQuery();
			while(set.next()) {
				Guitar guitar = new Guitar();
				guitar.setBimgpath(set.getString("bimgpath"));
				guitar.setGuitarname(set.getString("guitarname"));
				guitar.setId(set.getInt("id"));
				guitar.setImgpath(set.getString("imgpath"));
				guitar.setPrice(set.getDouble("price"));
				list.add(guitar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getTotalCountByGenre(String genre) {
		Connection connection = JDBCUtils.getConnection();
		String sql = "select count(*) from guitar where genre = ? ";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, genre);
			ResultSet set = pst.executeQuery();
			set.next();
			return set.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
