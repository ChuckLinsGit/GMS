package gms.DAO.event;

import java.util.List;

import gms.entry.event.EventInform;

public interface IEventInformDAO {
	public void insert(EventInform ei);
	public void delete(Integer informID);
	public void update(EventInform ei);
	public void confirmEventInform(Integer informID);
	public EventInform selectByID(Integer informID);
	public List<EventInform> selectAll();
	public List<EventInform> selectByUserID(Integer userID);
}
