package cn.bl.test;

import java.util.ArrayList;

import org.junit.Test;

import cn.bl.bean.Guitar;
import cn.bl.dao.guitar.dao.GuitarDao;
import cn.bl.dao.guitar.factory.GuitarFactory;

public class TestGuitar {
	private GuitarDao guitarDao = GuitarFactory.getInstance();
	@Test
	public void testGetByName() {//AC,数据库缘故--不区分大小写
		ArrayList<Guitar>list = guitarDao.getByName("A");
		for (Guitar guitar : list) {
			System.out.println(guitar);
		}
	}
	@Test
	public void testGetCount() {//AC
		System.out.println(guitarDao.getTotalCount());//20
	}
	
	@Test
	public void testLimit() {//AC
		ArrayList<Guitar>list = guitarDao.pageBeanList(1, 3,"ballad");
		for (Guitar guitar : list) {
			System.out.println(guitar);
		}
	}
	@Test
	public void testGetTotalCountByGenre() {
		System.out.println(guitarDao.getTotalCountByGenre("ballad"));
	}
}
