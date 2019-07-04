package gms.entry.field;

import java.sql.Timestamp;

public class MixFieldOrder {
	private Integer orderid;
	private Timestamp rentstart;
	private Timestamp rentend;
	private String orderstate;
	private Integer fieldid;
	private String fieldname;
	private String phone;
//	private Integer returnid;
	private Integer payid; 
	private Integer paymentid;
	private Float money;
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
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
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
	public Integer getFieldid() {
		return fieldid;
	}
	public void setFieldid(Integer fieldid) {
		this.fieldid = fieldid;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
//	public Integer getReturnid() {
//		return returnid;
//	}
//	public void setReturnid(Integer returnid) {
//		this.returnid = returnid;
//	}
	public Integer getPayid() {
		return payid;
	}
	public void setPayid(Integer payid) {
		this.payid = payid;
	}
	public Integer getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(Integer paymentid) {
		this.paymentid = paymentid;
	}

	
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "MixFieldOrder [orderid=" + orderid + ", rentstart=" + rentstart + ", rentend=" + rentend
				+ ", orderstate=" + orderstate + ", fieldid=" + fieldid + ", fieldname=" + fieldname + ", phone="
				+ phone + ", payid=" + payid + ", paymentid=" + paymentid + ", money=" + money + "]";
	}
	
}
