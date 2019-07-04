package gms.DAO.event;

import java.sql.Date;
import java.util.List;

import gms.entry.event.Event;
import gms.entry.event.EventApplication;
/**
 * DAO�࣬Ϊʵ��Mybaits����DAO������󣬱��봴��һ����ӿ���һ�µ�xmlӳ���ļ����ڸ��ļ���
 * ������������Ӧ��sql���
 * @author www25
 *
 */
public interface IEventDAO {
	public void insert(Event event);
	public void update(Event event);
	public void overdueEvent(Integer eventID);
	public void deleteByID(Integer eventId);
	public void deleteByFieldID(Integer fieldID);
	public void deleteByEquipmentID(Integer equipmentID);
	public Event selectByID(Integer eventID);
	public List<Event> selectAll();
	public List<Event> selectByUserID(Integer userID);
	public Event selectByFieldID(Integer fieldID);
	public Event selectByEquipmentID(Integer equipmentID);
	public List<Integer> getOverdueEventIDs();
}
