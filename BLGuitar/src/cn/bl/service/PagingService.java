package cn.bl.service;

import java.util.ArrayList;

import cn.bl.bean.Guitar;
import cn.bl.bean.PageBean;
import cn.bl.dao.guitar.dao.GuitarDao;
import cn.bl.dao.guitar.factory.GuitarFactory;

/**
 * 分页--业务逻辑层
 */
public class PagingService {
	private GuitarDao guitarDao = GuitarFactory.getInstance();
	
	/**
	 * 查找所有商品
	 * @param genre
	 * @return
	 */
	public ArrayList<Guitar> queryAllGuitar(String genre){
		if(genre==null || genre.length()==0){		
			return null;
		}
		return guitarDao.getByGenre(genre);
	}
	
	/**
	 * 获取指定页面 的pageBean( 分页  )
	 * @param curPage-当前是第几页
	 * @param curCount-当前页面显示几条
	 * @return
	 */
	public PageBean<Guitar> getPageBean(int curPage,int curCount,String genre){
		PageBean<Guitar>pageBean = new PageBean<>();
		//1. int curPage;//当前页
		pageBean.setCurPage(curPage);
		
		//2. int curCount;//当前页显示的条数
		pageBean.setCurCount(curCount);
		
		//3. int totalCount;//总条数
		int totalCount = guitarDao.getTotalCountByGenre(genre);
		pageBean.setTotalCount(totalCount);
		
		//4. int totalPage;//总页数
		int totalPage = (int)Math.ceil(1.0*totalCount/curCount);//总页数=Math.ceil(总条数/当前显示的条数)
		pageBean.setTotalPage(totalPage);
		
		//5. ArrayList<T>guitarList = new ArrayList<>();//当前页的所有商品信息
		int index = (curPage-1)*curCount;//索引index = (当前页数-1)*每页显示的条数
		ArrayList<Guitar> list = guitarDao.pageBeanList(index, curCount, genre);
		pageBean.setGuitarList(list);
		
		return pageBean;
	}
	
}
