package cn.bl.bean;

import java.util.ArrayList;

/**
 * 封装分页信息
 * @param <T>
 */
public class PageBean<T> {
	private int curPage;//当前页
	private int curCount;//当前页显示的条数
	private int totalPage;//总页数
	private int totalCount;//总条数
	private ArrayList<Guitar>guitarList = new ArrayList<>();//当前页的所有商品信息
	public PageBean() {
		super();
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getCurCount() {
		return curCount;
	}
	public void setCurCount(int curCount) {
		this.curCount = curCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public ArrayList<Guitar> getGuitarList() {
		return guitarList;
	}
	public void setGuitarList(ArrayList<Guitar> guitarList) {
		this.guitarList = guitarList;
	}
	@Override
	public String toString() {
		return "PageBean [curPage=" + curPage + ", curCount=" + curCount + ", totalPage=" + totalPage + ", totalCount="
				+ totalCount + ", guitarList=" + guitarList + "]";
	}
	
}
