package cn.bl.dao.carQuery.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.bl.bean.CarQueryModel;
import cn.bl.dao.carQuery.dao.CarQueryDao;
import cn.bl.util.JDBCUtils;

public class CarQueryImpl implements CarQueryDao {
	@Override
	public ArrayList<CarQueryModel> getAllByUserId(int userid) {
		/*
		 * 先从car表中查询所有userid的商品--guitarid 
		 * 然后根据guitarid到guitar表中将所有商品信息查找出来 
		 * 封装到List
		 */
		ArrayList<CarQueryModel> list = new ArrayList<>();
		// Map<Integer, Integer>map = new HashMap<>();
		ArrayList<Integer> guitarids = new ArrayList<>();
		ArrayList<Integer> counts = new ArrayList<>();
		ArrayList<Integer> carids = new ArrayList<>();
		Connection connection = JDBCUtils.getConnection();
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		String findIdsql = "select * from car where userid=?";
		String findGuitarSql = "select * from guitar where id=?";
		try {
			// 查找购物车中该用户的所有的商品
			pst1 = connection.prepareStatement(findIdsql);
			pst1.setInt(1, userid);
			ResultSet set = pst1.executeQuery();
			while (set.next()) {
				carids.add(set.getInt("id"));
				guitarids.add(set.getInt("guitarid"));
				counts.add(set.getInt("count"));
			}
			pst2 = connection.prepareStatement(findGuitarSql);
			// 查找所有商品的信息，将其封装
			for (int i = 0; i < guitarids.size(); i++) {
				// 封装数据--guitarid,carid,count
				int guitarid = guitarids.get(i);
				int carid = carids.get(i);
				int count = counts.get(i);
				CarQueryModel model = new CarQueryModel();
				model.setId(guitarid);
				model.setCarid(carid);
				model.setCount(count);
				pst2.setInt(1, guitarid);
				ResultSet gs = pst2.executeQuery();
				if (gs.next()) {
					// 封装数据--bimgpath,guitarname,imgpath,price
					model.setBimgpath(gs.getString("bimgpath"));
					model.setGuitarname(gs.getString("guitarname"));
					model.setImgpath(gs.getString("imgpath"));
					model.setPrice(gs.getDouble("price"));
					list.add(model);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
