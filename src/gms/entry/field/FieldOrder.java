package gms.entry.field;

import java.sql.Timestamp;

public class FieldOrder {

	private Integer orderid;
	private	Integer fieldid;
	private Timestamp rentstart;
	private Timestamp rentend;
	private Integer returnid;
	private String orderstate;
	private String phone;
	private Integer payid;
	//	用户id传过来的值
	private Integer userid;
	
public Integer getPayid() {
		return payid;
	}
	public void setPayid(Integer payid) {
		this.payid = payid;
	}

	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	//get set
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
	public void setRentstart(Timestamp rentstart2) {
		this.rentstart = rentstart2;
	}
	public Timestamp getRentend() {
		return rentend;
	}
	public void setRentend(Timestamp rentend2) {
		this.rentend = rentend2;
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
	
	@Override
	public String toString() {
		return "FieldOrder [orderid=" + orderid + ", fieldid=" + fieldid + ", rentstart=" + rentstart + ", rentend="
				+ rentend + ", returnid=" + returnid + ", orderstate=" + orderstate + ", phone=" + phone + ", payid="
				+ payid + ", userid=" + userid + "]";
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
