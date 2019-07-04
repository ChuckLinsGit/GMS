package gms.entry.field;

public class Pay {

	private Integer payid;
	private Integer fieldid;
	private Integer allTime;
	private String paystate;
	public Integer getPayid() {
		return payid;
	}
	public void setPayid(Integer payid) {
		this.payid = payid;
	}
	public Integer getFieldid() {
		return fieldid;
	}
	public void setFieldid(Integer fieldid) {
		this.fieldid = fieldid;
	}
	public Integer getAllTime() {
		return allTime;
	}
	public void setAllTime(Integer allTime) {
		this.allTime = allTime;
	}
	public String getPaystate() {
		return paystate;
	}
	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}
	@Override
	public String toString() {
		return "Pay [payid=" + payid + ", fieldid=" + fieldid + ", allTime=" + allTime + ", paystate=" + paystate + "]";
	}
	
}
