package gms.service.event;

import java.util.List; 
import gms.entry.event.EventApplication;

public interface IEventApplicationService {
	public void addEventApplication(EventApplication ep);
	public void dropEventApplication(Integer applicationID);
	public void dropEventApplicationByFieldID(Integer fieldID);
	public void dropEventApplicationByEquipmentID(Integer equipmentID);
	public void changeEventApplication(EventApplication ep);
	public EventApplication refuseEventApplication(Integer applicationID);
	public EventApplication acceptEventApplication(Integer applicationID);
	public EventApplication getEventApplicationByID(Integer applicationID);
	public List<EventApplication> getAllEventApplication();
	public List<EventApplication> getMyEventApplication(Integer userID);
	public List<EventApplication> getWaitingEventApplication();
	public List<Integer> getOverdueEventApplicationIDs();
}
