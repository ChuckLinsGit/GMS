package gms.control.supreme;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import gms.entry.event.Event;
import gms.entry.event.EventApplication;

public class Logger implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Date startTime;
		private Date endTime;
		private List<Integer> handledEvents;
		private List<Integer> handledApplications;
		public Date getStartTime() {
			return startTime;
		}
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		public Date getEndTime() {
			return endTime;
		}
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		public List<Integer> getHandledEvents() {
			return handledEvents;
		}
		public void setHandledEvents(List<Integer> overdueEventIDs) {
			this.handledEvents = overdueEventIDs;
		}
		public List<Integer> getHandledApplications() {
			return handledApplications;
		}
		public void setHandledApplications(List<Integer> overdueEventApplicationIDs) {
			this.handledApplications = overdueEventApplicationIDs;
		}
}
