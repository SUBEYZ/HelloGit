package cn.bl.bean;

import java.io.Serializable;
/**
 * 商品信息
 * equals by id
 */
public class Guitar implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;//guitarid
	private String guitarname;//吉他名称
	private double price;//吉他售价
	private String imgpath;//图片位置
	private String bimgpath;//详情的图片位置
	private String genre;//商品类别
	public Guitar() {
		super();
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGuitarname() {
		return guitarname;
	}
	public void setGuitarname(String guitarname) {
		this.guitarname = guitarname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getBimgpath() {
		return bimgpath;
	}
	public void setBimgpath(String bimgpath) {
		this.bimgpath = bimgpath;
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
		Guitar other = (Guitar) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Guitar [id=" + id + ", guitarname=" + guitarname + ", price=" + price + ", imgpath=" + imgpath
				+ ", bimgpath=" + bimgpath + ", genre=" + genre + "]";
	}
	
}
