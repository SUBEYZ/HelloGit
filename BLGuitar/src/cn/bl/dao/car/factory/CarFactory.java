package cn.bl.dao.car.factory;

import cn.bl.dao.car.dao.CarDao;
import cn.bl.dao.car.impl.CarImpl;

public class CarFactory {
	public static CarDao getInstance() {
		return new CarImpl();
	}
}
