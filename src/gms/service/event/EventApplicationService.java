package gms.service.event;
 
import java.sql.Date;
import java.util.List; 

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.event.IEventApplicationDAO;
import gms.DAO.event.IEventDAO;
import gms.entry.event.Event;
import gms.entry.event.EventApplication;
import gms.entry.event.EventInform;

@Component("EventApplicationService")
public class EventApplicationService implements IEventApplicationService {
	@Resource(name="IEventApplicationDAO")
	private IEventApplicationDAO applicationDAO;
	@Resource(name="IEventDAO")
	private IEventDAO eventDAO;
	@Resource(name="EventInformService")
	private IEventInformService informService;
	

	@Transactional
	@Override
	public void addEventApplication(EventApplication ep) {
		ep.setState(0);
		applicationDAO.insert(ep);
	}
	
	@Transactional
	@Override
	public void dropEventApplication(Integer applicationID) {
		applicationDAO.delete(applicationID);
	}

	@Transactional
	@Override
	public void dropEventApplicationByFieldID(Integer fieldID) {
		//消息通知处理
		EventApplication ep=applicationDAO.selectByFieldID(fieldID);
		if(ep!=null) {
			
			EventInform inform=new EventInform();
			inform.setDate(new Date(System.currentTimeMillis()));
			inform.setContent(ep.getContent()+"申请失败！重大赛事征用资源。");
			inform.setUserID(ep.getUserID());
			inform.setState(0);
			informService.addEventInform(inform);
			applicationDAO.deleteByFieldID(fieldID);
		}
	} 
	
	@Transactional
	@Override
	public void dropEventApplicationByEquipmentID(Integer equipmentID) {
		//消息通知处理
		EventApplication ep=applicationDAO.selectByEquipmentID(equipmentID);
		if(ep!=null) {
			
			EventInform inform=new EventInform();
			inform.setDate(new Date(System.currentTimeMillis()));
			inform.setContent(ep.getContent()+"申请失败！重大赛事征用资源。");
			inform.setUserID(ep.getUserID());
			inform.setState(0);
			informService.addEventInform(inform);
			applicationDAO.deleteByEquipmentID(equipmentID);
		}
	}
	

	@Transactional
	@Override
	public void changeEventApplication(EventApplication ep) {
		ep.setState(0);
		applicationDAO.update(ep);
	}

	@Transactional
	@Override
	public EventApplication refuseEventApplication(Integer applicationID) {
		applicationDAO.refuseEventApplication(applicationID);
		return applicationDAO.selectByID(applicationID);
	}

	/**
	 * 申请成功，申请表单标志位为1，并插入新的赛事
	 */
	@Override
	public EventApplication acceptEventApplication(Integer applicationID) {
		applicationDAO.acceptEventApplication(applicationID);
		EventApplication ep = applicationDAO.selectByID(applicationID);
		Event e = new Event();
		e.setUserID(ep.getUserID());
		e.setEquipmentID(ep.getEquipmentID());
		e.setFieldID(ep.getFieldID());
		e.setDate(ep.getDate());
		e.setEndDate(ep.getEndDate());
		e.setContent(ep.getContent());
		e.setJudgement(ep.getJudgement());
		e.checkTime();
		eventDAO.insert(e);
		return ep;
	}

	@Transactional
	@Override
	public EventApplication getEventApplicationByID(Integer applicationID) {
		return applicationDAO.selectByID(applicationID);
	}

	@Transactional
	@Override
	public List<EventApplication> getAllEventApplication() {
		return applicationDAO.selectAll();
	}

	@Override
	public List<EventApplication> getMyEventApplication(Integer userID) {
		return applicationDAO.selectByUserID(userID);
	}

	@Override
	public List<EventApplication> getWaitingEventApplication() {
		return applicationDAO.selectWaitingEventApplication();
	}

	@Override
	public List<Integer> getOverdueEventApplicationIDs() {
		return applicationDAO.selectOverdueEventApplicationIDs(new Date(System.currentTimeMillis()));
	}
}
