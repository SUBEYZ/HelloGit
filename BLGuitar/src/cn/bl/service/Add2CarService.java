package cn.bl.service;

import cn.bl.bean.User;
import cn.bl.dao.car.dao.CarDao;
import cn.bl.dao.car.factory.CarFactory;

public class Add2CarService {
	private CarDao carDao = CarFactory.getInstance();
	public String add2car(User user,String strid) {
		if(strid==null || strid.length()==0) {
			return "出错啦";
		}
		int guitarid = -1;
		try {
			guitarid = Integer.parseInt(strid);
		} catch (Exception e) {
			return "出错啦";
		}
		if(user==null) {//用户没登陆
			return "请先登录";
		}
		int userid = user.getId();
		if(carDao.add(userid, guitarid)) {
			return "true";//加入购物车成功
		}else {
			return "添加失败，请稍后再试";
		}
	}
}
