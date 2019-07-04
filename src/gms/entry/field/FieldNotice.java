package gms.entry.field;

public class FieldNotice {

	private Integer noticefid;
	private String noticename;
	private String noticemessage;
	private Integer fieldid;
	private String noticestate;
	
	
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
	public String getNoticestate() {
		return noticestate;
	}
	public void setNoticestate(String noticestate) {
		this.noticestate = noticestate;
	}
	@Override
	public String toString() {
		return "FieldNotice [noticefid=" + noticefid + ", noticename=" + noticename + ", noticemessage=" + noticemessage
				+ ", fieldid=" + fieldid + ", noticestate=" + noticestate + "]";
	}
	
	
	//get set
	
	
	

	
}
