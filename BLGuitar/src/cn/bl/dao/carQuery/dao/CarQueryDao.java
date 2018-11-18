package cn.bl.dao.carQuery.dao;

import java.util.ArrayList;

import cn.bl.bean.CarQueryModel;

public interface CarQueryDao {
	/**
	 * 根据用户id返回  他的购物车中的商品
	 * @param id
	 * @return ArrayList<Guitar>
	 */
	public abstract ArrayList<CarQueryModel> getAllByUserId(int userid);
}
