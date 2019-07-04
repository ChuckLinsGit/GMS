package gms.entry.field;

import java.sql.Timestamp;

public class FOrderExtent{


	private String fieldname;
	private Integer orderid;
	private	Integer fieldid;
	private Timestamp rentstart;
	private Timestamp rentend;
	private Integer returnid;
	private String orderstate;
	private String phone;
	private Integer unitprice;
	private Float money;
	private Integer payid;
	
	
	public Integer getPayid() {
		return payid;
	}
	public void setPayid(Integer payid) {
		this.payid = payid;
	}

	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public Integer getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Integer unitprice) {
		this.unitprice = unitprice;
	}

	
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getFieldid() {
		return fieldid;
	}
	public void setFieldid(Integer fieldid) {
		this.fieldid = fieldid;
	}
	public Timestamp getRentstart() {
		return rentstart;
	}
	public void setRentstart(Timestamp rentstart) {
		this.rentstart = rentstart;
	}
	public Timestamp getRentend() {
		return rentend;
	}
	public void setRentend(Timestamp rentend) {
		this.rentend = rentend;
	}
	public Integer getReturnid() {
		return returnid;
	}
	public void setReturnid(Integer returnid) {
		this.returnid = returnid;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "FOrderExtent [fieldname=" + fieldname + ", orderid=" + orderid + ", fieldid=" + fieldid + ", rentstart="
				+ rentstart + ", rentend=" + rentend + ", returnid=" + returnid + ", orderstate=" + orderstate
				+ ", phone=" + phone + ", unitprice=" + unitprice + ", money=" + money + ", payid=" + payid + "]";
	}
	
	

}
