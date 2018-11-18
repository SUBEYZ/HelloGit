package cn.bl.service;

import cn.bl.dao.car.dao.CarDao;
import cn.bl.dao.car.factory.CarFactory;

public class RemoveService {
	private CarDao carDao = CarFactory.getInstance();
	public boolean remove(String strId) {
		if(strId==null || strId.trim().length()==0) {
			return false;
		}
		strId = strId.trim();
		int carid;
		try {
			carid = Integer.parseInt(strId);
			if(carDao.deleteById(carid)) {
				return true;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return false;
	}
}
