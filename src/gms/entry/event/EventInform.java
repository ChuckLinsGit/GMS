package gms.entry.event;

import java.sql.Date;

public class EventInform {
	private Integer informID;
	private Integer userID;
	private Date date;
	private Integer state;//0 Î´²é¿´ 1ÒÑ¶Á 2É¾³ý
	private String content;
	public Integer getInformID() {
		return informID;
	}
	public void setInformID(Integer informID) {
		this.informID = informID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public EventInform() {
		super();
	}
	@Override
	public String toString() {
		return "EventInform [informID=" + informID + ", userID=" + userID + ", date=" + date + ", state=" + state
				+ ", content=" + content + "]";
	}
}
