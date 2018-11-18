package cn.bl.service;

import java.util.ArrayList;

import cn.bl.bean.Guitar;
import cn.bl.dao.guitar.dao.GuitarDao;
import cn.bl.dao.guitar.factory.GuitarFactory;
/**
 * 查找商品
 */
public class QueryService {
	public ArrayList<Guitar> query(String name){
		ArrayList<Guitar>list = new ArrayList<>();
		if(name==null || name.trim().length()==0) {
			return list;
		}
		GuitarDao guitarDao = GuitarFactory.getInstance();
		list = guitarDao.getByName(name);
		
		//将首页的轮播图的吉他去掉--不然搜索出来界面太丑
		/*
		 * 首页的轮播图部分genre=rou
		 * 将genre!=rou的添加到另一个List并返回
		 */
		ArrayList<Guitar> list2 = new ArrayList<>();
		for (Guitar guitar : list) {
			if(!"rou".equals(guitar.getGenre())) {
				list2.add(guitar);
			}
		}
		return list2;
	}
}
