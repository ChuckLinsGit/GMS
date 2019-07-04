package gms.entry.event;

import java.sql.Date;

/**
 * 这是一个bean类，必须声明setter和getter方法，无参构造器
 * @author www25
 *
 */
public class Event {
	private Integer eventID;
	private Integer userID;
	private	Integer fieldID;
	private String  filed;
	private Integer equipmentID;
	private Date date;
	private Date endDate;
	private	String content;
	private String judgement;
	private Integer state;//0 即将开始 1进行中 2结束 3删除
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getFiled() {
		return filed;
	}
	public void setFiled(String filed) {
		this.filed = filed;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getEventID() {
		return eventID;
	}
	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getFieldID() {
		return fieldID;
	}
	public void setFieldID(Integer fieldID) {
		this.fieldID = fieldID;
	}
	public Integer getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(Integer equipmentID) {
		this.equipmentID = equipmentID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getJudgement() {
		return judgement;
	}
	public void setJudgement(String judgement) {
		this.judgement = judgement;
	}
	public Event() {
	}
	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", userID=" + userID + ", fieldID=" + fieldID + ", filed=" + filed
				+ ", equipmentID=" + equipmentID + ", date=" + date + ", endDate=" + endDate + ", content=" + content
				+ ", judgement=" + judgement + ", state=" + state + "]";
	}
	public void checkTime() {
		long currentTimeMillis = System.currentTimeMillis();
		if(date.getTime()>currentTimeMillis)
			this.setState(0);
		if(date.getTime()<=currentTimeMillis&&endDate.getTime()>=currentTimeMillis)
			this.setState(1);
		if(endDate.getTime()<currentTimeMillis)
			this.setState(2);
	}
}
