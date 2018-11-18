package cn.bl.test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cn.bl.bean.Car;
import cn.bl.dao.car.impl.CarImpl;
/**
 * 测试CarImpl
 * @author BarryLee
 * @2018年5月31日@上午9:22:59
 */
public class TestCarImpl {
	private CarImpl carImpl = null;
	@Before
	public void init() {
		 carImpl = new CarImpl();
	}
	@Test 
	public void testAdd() {//AC
		System.out.println(carImpl.add(3, 3));
	}
	
	@Test
	public void testDelete() {//AC
		System.out.println(carImpl.delete(1, 1));
	}
	@Test
	public void testRemove() {//AC
		System.out.println(carImpl.remove(1));
	}
	@Test
	public void testGetAll() {//AC
		ArrayList<Car> all = carImpl.getAll(2);
		for (Car car : all) {
			System.out.println(car);
		}
	}
	@Test
	public void testXXX() {
		
	}
	
	
	
	
	
	
	
	
}
