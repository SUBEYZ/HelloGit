package cn.bl.dao.guitar.dao;

import java.util.ArrayList;

import cn.bl.bean.Guitar;
/**
 * 接口   操作商品
 */
public interface GuitarDao {
	/**
	 * 根据id查询单个商品
	 * @param id
	 * @return Guitar
	 */
	public abstract Guitar getOneById(int id);
	/**
	 * 查询所有在架商品
	 * @return List<Guitar>
	 */
	public abstract ArrayList<Guitar> getAll();

	/**
	 * 根据类别查询
	 * @param genre
	 * @return
	 */
	public abstract ArrayList<Guitar> getByGenre(String genre);
	
	/**
	 * 根据名字进行模糊查询
	 * @param name
	 * @return
	 */
	public abstract ArrayList<Guitar> getByName(String name);
	
	/**
	 * 获取全部商品的数量
	 * @return
	 */
	public abstract int getTotalCount();
	
	/**
	 * 根据类别获取全部商品的数量
	 * @return
	 */
	public abstract int getTotalCountByGenre(String genre);
	
	/**
	 * 根据当前是第几个，当前页的总个数来获取当前页面的商品列表
	 * @param indx
	 * @param curCount
	 * @return
	 */
	public abstract ArrayList<Guitar>pageBeanList(int index,int curCount,String genre);
	
}
