package cn.bl.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.bl.bean.CarQueryModel;
import cn.bl.dao.carQuery.impl.CarQueryImpl;

public class TestCarQueryImpl {
	CarQueryImpl car = null;
	@Before
	public void before() {
		car = new CarQueryImpl();
	}
	@Test
	public void testGetAllByUserId() {// AC
		int userid = 6;
		List<CarQueryModel> list = car.getAllByUserId(userid);
		for (CarQueryModel guitar : list) {
			System.out.println(guitar);
		}
/*
CarQueryModel [carid=8, count=1, getId()=7, getGuitarname()=吉他平方越南Ayers爱乐诗, getPrice()=24319.0, getImgpath()=goodsimgs/guitar07.jpg, getBimgpath()=goodsimgs/bguitar07.jpg, hashCode()=38, toString()=Guitar [id=7, guitarname=吉他平方越南Ayers爱乐诗, price=24319.0, imgpath=goodsimgs/guitar07.jpg, bimgpath=goodsimgs/bguitar07.jpg], getClass()=class cn.bl.bean.CarQueryModel]
CarQueryModel [carid=9, count=2, getId()=3, getGuitarname()=TYMA2018全新单板杰作, getPrice()=12000.0, getImgpath()=goodsimgs/guitar03.jpg, getBimgpath()=goodsimgs/bguitar03.jpg, hashCode()=34, toString()=Guitar [id=3, guitarname=TYMA2018全新单板杰作, price=12000.0, imgpath=goodsimgs/guitar03.jpg, bimgpath=goodsimgs/bguitar03.jpg], getClass()=class cn.bl.bean.CarQueryModel]
CarQueryModel [carid=10, count=2, getId()=1, getGuitarname()=泰勒量产高端系列, getPrice()=34999.0, getImgpath()=goodsimgs/guitar01.jpg, getBimgpath()=goodsimgs/bguitar01.jpg, hashCode()=32, toString()=Guitar [id=1, guitarname=泰勒量产高端系列, price=34999.0, imgpath=goodsimgs/guitar01.jpg, bimgpath=goodsimgs/bguitar01.jpg], getClass()=class cn.bl.bean.CarQueryModel]
CarQueryModel [carid=12, count=1, getId()=8, getGuitarname()=John Petrucci JP6 变色龙, getPrice()=34399.0, getImgpath()=goodsimgs/guitar08.jpg, getBimgpath()=goodsimgs/bguitar08.jpg, hashCode()=39, toString()=Guitar [id=8, guitarname=John Petrucci JP6 变色龙, price=34399.0, imgpath=goodsimgs/guitar08.jpg, bimgpath=goodsimgs/bguitar08.jpg], getClass()=class cn.bl.bean.CarQueryModel]
 */
	}
}
