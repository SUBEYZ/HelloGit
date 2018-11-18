package cn.bl.bean;

import java.io.Serializable;
/**
 * 用户的购物车
 * equals by id
 */
public class Car implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;//id
	private int userid;//用户id
	private int guitarid;//商品id
	private int count;//商品数量
	public Car() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getGuitarid() {
		return guitarid;
	}
	public void setGuitarid(int guitarid) {
		this.guitarid = guitarid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", userid=" + userid + ", guitarid=" + guitarid + ", count=" + count + "]";
	}
}
