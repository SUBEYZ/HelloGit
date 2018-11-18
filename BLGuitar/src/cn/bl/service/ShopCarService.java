package cn.bl.service;

import java.util.ArrayList;

import cn.bl.bean.CarQueryModel;
import cn.bl.bean.User;
import cn.bl.dao.carQuery.dao.CarQueryDao;
import cn.bl.dao.carQuery.factory.CarQueryFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ShopCarService {
	private CarQueryDao car = CarQueryFactory.getInstance();
	public String shopCar(User user) {
		if(user==null) {//用户没登陆却来到这里页面的时候user为空，应当防范一下
			return "";
		}
		//获取该用户所有购物车中的商品对象
		ArrayList<CarQueryModel> list = car.getAllByUserId(user.getId());
		//将其转换为json格式字符串
		JSONObject object = new JSONObject();
		object.put("guitars", list);
		JSONArray json = new JSONArray();
		json.add(object);
		String js = json.toString();
		String res = js.substring(1,js.length()-1);
		//System.out.println(res);
		return res;
	}
}
/*
{"guitars":[
{"bimgpath":"goodsimgs/bguitar03.jpg","carid":7,"count":2,"guitarname":"TYMA2018全新单板杰作","id":3,"imgpath":"goodsimgs/guitar03.jpg","price":12000},
{"bimgpath":"goodsimgs/bguitar02.jpg","carid":11,"count":1,"guitarname":"LAVA全桃花芯木面单","id":2,"imgpath":"goodsimgs/guitar02.jpg","price":23000},
{"bimgpath":"goodsimgs/bguitar04.jpg","carid":22,"count":1,"guitarname":"泰勒700/800/900","id":4,"imgpath":"goodsimgs/guitar04.jpg","price":79000},
{"bimgpath":"goodsimgs/big_rou1.jpg","carid":50,"count":2,"guitarname":"德国手工 Lakewood","id":31,"imgpath":"goodsimgs/rou1.jpg","price":32800},
{"bimgpath":"goodsimgs/big_rou2.jpg","carid":51,"count":1,"guitarname":"GibsonJ-45全单板民谣","id":32,"imgpath":"goodsimgs/rou2.jpg","price":35600},
{"bimgpath":"goodsimgs/big_rou3.jpg","carid":52,"count":1,"guitarname":"美产马丁D35云杉玫瑰木","id":33,"imgpath":"goodsimgs/rou3.jpg","price":37899}
]}
*/