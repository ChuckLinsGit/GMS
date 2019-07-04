package gms.service.event;

import java.util.List;

import gms.entry.event.EventInform;

public interface IEventInformService {
	public void addEventInform(EventInform ei);
	public void dropEventInform(Integer eventInformID);
	public void changeEventInform(EventInform ei);
	public void confirmEventInform(Integer informID);
	public EventInform getEventInformByID(Integer eventInformID);
	public List<EventInform> getAllEventInform();
	public List<EventInform> getMyEventInform(Integer userID);
}
