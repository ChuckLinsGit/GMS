package gms.DAO.event;

import java.sql.Date;
import java.util.List; 

import gms.entry.event.EventApplication;

public interface IEventApplicationDAO {
	public void insert(EventApplication ep);
	public void delete(Integer applicationID);
	public void deleteByFieldID(Integer fieldID);
	public void deleteByEquipmentID(Integer equipmentID);
	public void update(EventApplication ep);
	public void refuseEventApplication(Integer applicationID);
	public void acceptEventApplication(Integer applicationID);
	public EventApplication selectByID(Integer applicationID);
	public List<EventApplication> selectAll();
	public List<EventApplication> selectByUserID(Integer userID);
	public EventApplication selectByFieldID(Integer fieldID);
	public EventApplication selectByEquipmentID(Integer equipmentID);
	public List<EventApplication> selectWaitingEventApplication();
	public List<Integer> selectOverdueEventApplicationIDs(Date date);
}
