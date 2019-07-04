package gms.entry.field;

public class NoticeWithFieldname {
	private Integer noticefid;
	private String noticename;
	private String noticemessage;
	private Integer fieldid;
	private String fieldname;
	public Integer getNoticefid() {
		return noticefid;
	}
	public void setNoticefid(Integer noticefid) {
		this.noticefid = noticefid;
	}
	public String getNoticename() {
		return noticename;
	}
	public void setNoticename(String noticename) {
		this.noticename = noticename;
	}
	public String getNoticemessage() {
		return noticemessage;
	}
	public void setNoticemessage(String noticemessage) {
		this.noticemessage = noticemessage;
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
	@Override
	public String toString() {
		return "NoticeWithFieldname [noticefid=" + noticefid + ", noticename=" + noticename + ", noticemessage="
				+ noticemessage + ", fieldid=" + fieldid + ", fieldname=" + fieldname + "]";
	}
	
	
}
