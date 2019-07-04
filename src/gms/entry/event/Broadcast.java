package gms.entry.event;

public class Broadcast {
	private Integer broadcastID;
	private String content;
	private Integer state;//0 ���㲥 1�㲥 2ɾ��
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getBroadcastID() {
		return broadcastID;
	}
	public void setBroadcastID(Integer broadcastID) {
		this.broadcastID = broadcastID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Broadcast() {
		super();
	}
	@Override
	public String toString() {
		return "Broadcast [broadcastID=" + broadcastID + ", content=" + content + ", state=" + state + "]";
	}
}
