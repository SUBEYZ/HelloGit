package cn.bl.dao.carQuery.factory;

import cn.bl.dao.carQuery.dao.CarQueryDao;
import cn.bl.dao.carQuery.impl.CarQueryImpl;

public class CarQueryFactory {
	public static CarQueryDao getInstance() {
		return new CarQueryImpl();
	}
}
