package gms.service.event;

import java.util.List;

import gms.entry.event.Event;

public interface IEventService {
	public void addEvent(Event event);
	public void dropEvent(Integer eventID);
	public void overdueEvent(Integer eventID);
	public void cancelEventByFieldID(Integer fieldID);
	public void cancelEventByEquipmentID(Integer equipmentID);
	public void changeEvent(Event event);
	public void changeImportatnEvent(Event event);
	public Event getEventByID(Integer eventID);
	public List<Event> getAllEvent();
	public List<Event> getEventByUserID(Integer userID);
	public List<Integer> getOverdueEventIDs();
}
