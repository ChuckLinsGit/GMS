package gms.service.event;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.event.IEventInformDAO;
import gms.entry.event.EventInform;

@Component("EventInformService")
public class EventInformService implements IEventInformService {
	
	@Resource(name="IEventInformDAO")
	private IEventInformDAO informDAO;

	@Transactional
	@Override
	public void addEventInform(EventInform ei) {
		informDAO.insert(ei);
	}

	@Transactional
	@Override
	public void dropEventInform(Integer eventInformID) {
		informDAO.delete(eventInformID);
	}

	@Transactional
	@Override
	public void changeEventInform(EventInform ei) {
		informDAO.update(ei);
	}

	@Transactional
	@Override
	public void confirmEventInform(Integer informID) {
		informDAO.confirmEventInform(informID);
	}

	@Transactional
	@Override
	public EventInform getEventInformByID(Integer eventInformID) {
		return informDAO.selectByID(eventInformID);
	}

	@Transactional
	@Override
	public List<EventInform> getAllEventInform() {
		return informDAO.selectAll();
	}

	@Override
	public List<EventInform> getMyEventInform(Integer userID) {
		return informDAO.selectByUserID(userID);
	}

}
