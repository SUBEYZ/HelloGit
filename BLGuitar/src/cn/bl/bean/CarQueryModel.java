package cn.bl.bean;

public class CarQueryModel extends Guitar{
	private static final long serialVersionUID = 1L;
	private int carid;
	private int count;
	public CarQueryModel() {
		super();
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	@Override
	public String toString() {
		return "CarQueryModel [carid=" + carid + ", count=" + count + ", getId()=" + getId() + ", getGuitarname()="
				+ getGuitarname() + ", getPrice()=" + getPrice() + ", getImgpath()=" + getImgpath() + ", getBimgpath()="
				+ getBimgpath() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + "]";
	}
	
}
