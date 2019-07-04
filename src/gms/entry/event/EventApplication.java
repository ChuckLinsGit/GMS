package gms.entry.event;

import java.sql.Date;

public class EventApplication {
	private Integer applicationID;
	private Integer userID;
	private Integer fieldID;
	private Integer equipmentID;
	private Date date;
	private Date endDate;
	private String content;
	private String judgement;
	private Integer state;//0 ÉêÇë 1Í¬Òâ 2¾Ü¾ø 3É¾³ý
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public EventApplication() {
		super();
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
	@Override
	public String toString() {
		return "EventApplication [applicationID=" + applicationID + ", userID=" + userID + ", fieldID=" + fieldID
				+ ", equipmentID=" + equipmentID + ", date=" + date + ", endDate=" + endDate + ", content=" + content
				+ ", judgement=" + judgement + ", state=" + state + "]";
	}
}
