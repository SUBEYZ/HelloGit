package cn.bl.dao.guitar.factory;

import cn.bl.dao.guitar.dao.GuitarDao;
import cn.bl.dao.guitar.impl.GuitarImpl;

public class GuitarFactory {
	public static GuitarDao getInstance() {
		return new GuitarImpl();
	}
}
