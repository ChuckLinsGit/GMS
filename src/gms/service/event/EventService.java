package gms.service.event;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.event.IEventApplicationDAO;
import gms.DAO.event.IEventDAO;
import gms.DAO.field.FieldMapper;
import gms.DAO.field.FieldOrderMapper;
import gms.entry.event.Event;
import gms.entry.event.EventApplication;
import gms.entry.event.EventInform;

/**
 * Service�࣬����㴦���࣬��������@Component("EventService")�ұ���ͨ��@Resourceע��
 * ע��DAO����
 * @author www25
 *
 */
@Component("EventService")
public class EventService implements IEventService {
	@Resource(name="IEventDAO")
	private IEventDAO eventDAO;
	@Resource(name="IEventApplicationDAO")
	private IEventApplicationDAO applicationDAO;
	@Resource(name="EventInformService")
	private IEventInformService informService;
	@Autowired
	private FieldOrderMapper fiedlOrderDAO;
	@Autowired
	private FieldMapper fiedlDAO;
	

	@Transactional//ʹ��Ĭ������
	@Override
	public void addEvent(Event event) {
		event.setState(0);
		eventDAO.insert(event);
	}

	@Transactional
	@Override
	public void dropEvent(Integer eventId) {
		eventDAO.deleteByID(eventId);
	}  
	
	@Transactional
	@Override
	public void cancelEventByFieldID(Integer fieldID) {
		//��Ϣ֪ͨ����
		Event ep=eventDAO.selectByFieldID(fieldID);
		if(ep!=null) {
			
			EventInform inform=new EventInform();
			inform.setDate(new Date(System.currentTimeMillis()));
			inform.setContent(ep.getContent()+"����ȡ�����ش�����������Դ��");
			inform.setUserID(ep.getUserID());
			inform.setState(0);
			informService.addEventInform(inform);
			eventDAO.deleteByFieldID(fieldID);
		}
	}
	
	@Transactional
	@Override
	public void cancelEventByEquipmentID(Integer equipmentID) {
		//��Ϣ֪ͨ����
		Event ep=eventDAO.selectByEquipmentID(equipmentID);
		if(ep!=null) {
			
			EventInform inform=new EventInform();
			inform.setDate(new Date(System.currentTimeMillis()));
			inform.setContent(ep.getContent()+"����ȡ�����ش�����������Դ��");
			inform.setUserID(ep.getUserID());
			inform.setState(0);
			informService.addEventInform(inform);
			eventDAO.deleteByEquipmentID(equipmentID);
		}
	}
	
	@Transactional
	@Override
	public void overdueEvent(Integer eventID) {
		eventDAO.overdueEvent(eventID);
	}

	/**
	 * ������ͨ���½�ɾ��ԭ���������ύ�µ�����
	 */
	@Transactional
	@Override
	public void changeEvent(Event event) {
		eventDAO.deleteByID(event.getEventID());
		EventApplication ep = new EventApplication();
		ep.setUserID(event.getUserID());
		ep.setEquipmentID(event.getEquipmentID());
		ep.setFieldID(event.getFieldID());
		ep.setDate(event.getDate());
		ep.setEndDate(event.getEndDate());
		ep.setContent(event.getContent());
		ep.setJudgement(event.getJudgement());
		ep.setState(0);
		applicationDAO.insert(ep);
	}

	/**
	 * �����ش�����ֱ���޸ļ�¼����
	 */
	@Transactional
	@Override
	public void changeImportatnEvent(Event event) {
		event.checkTime();
		eventDAO.update(event);
	}

	@Transactional
	@Override
	public Event getEventByID(Integer eventID) {
		return eventDAO.selectByID(eventID);
	}

	@Transactional
	@Override
	public List<Event> getAllEvent() {
		List<Event> rsList = eventDAO.selectAll();
		Iterator<Event> iterator = rsList.iterator();
		while(iterator.hasNext()) {
			Event next = iterator.next();
			Integer fieldid;
			try {
				System.out.println(next.getFieldID());
				System.out.println(fiedlOrderDAO.selectOrderById(next.getFieldID()));
				System.out.println(fiedlOrderDAO.selectOrderById(next.getFieldID()).getFieldid());
				fieldid = fiedlOrderDAO.selectOrderById(next.getFieldID()).getFieldid();
				System.out.println(fiedlDAO.selectFieldById(fieldid));
				System.out.println(fiedlDAO.selectFieldById(fieldid).getFieldname());
				next.setFiled(fiedlDAO.selectFieldById(fieldid).getFieldname());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rsList;
	}

	@Override
	public List<Event> getEventByUserID(Integer userID) {
		return eventDAO.selectByUserID(userID);
	}

	@Override
	public List<Integer> getOverdueEventIDs() {
		List<Integer> overdueEventIDs = eventDAO.getOverdueEventIDs();
		System.out.println("DAO:"+overdueEventIDs==null);
		return overdueEventIDs;
	}

}
