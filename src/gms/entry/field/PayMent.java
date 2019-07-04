package gms.entry.field;

public class PayMent {

	private Integer paymentid;
	private Integer payid;
	private Integer returnid;
	private Float money;
	public Integer getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(Integer paymentid) {
		this.paymentid = paymentid;
	}
	public Integer getPayid() {
		return payid;
	}
	public void setPayid(Integer payid) {
		this.payid = payid;
	}
	public Integer getReturnid() {
		return returnid;
	}
	public void setReturnid(Integer returnid) {
		this.returnid = returnid;
	}

	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "PayMent [paymentid=" + paymentid + ", payid=" + payid + ", returnid=" + returnid + ", money=" + money
				+ "]";
	}
	
}
