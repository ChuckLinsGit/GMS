package gms.service.event;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.event.IBroadcastDAO;
import gms.entry.event.Broadcast;

@Component("BroadcastService")
public class BroadcastService implements IBroadcastService {

	@Resource(name="IBroadcastDAO")
	private IBroadcastDAO broadcastDAO;
	
	@Transactional
	@Override
	public void addBroadcast(Broadcast broadcast) {
		broadcastDAO.insert(broadcast);
	}

	@Transactional
	@Override
	public void dropBroadcast(Integer broadcastID) {
		broadcastDAO.delete(broadcastID);
	}

	@Transactional
	@Override
	public void changeBroadcast(Broadcast broadcast) {
		broadcastDAO.update(broadcast);
	}

	@Transactional
	@Override
	public void lauchBroadcast(Integer broadcastID) {
		broadcastDAO.lauchBroadcast(broadcastID);
	}

	@Override
	public void stopBroadcast(Integer broadcastID) {
		broadcastDAO.stopBroadcast(broadcastID);
	}

	@Transactional
	@Override
	public List<Broadcast> getBroadcastByID(Integer broadcastID) {
		return broadcastDAO.selectByID(broadcastID);
	}

	@Transactional
	@Override
	public List<Broadcast> getALLBroadcast() {
		return broadcastDAO.selectALL();
	}

	@Override
	public List<Broadcast> getlauchedBroadcast() {
		return broadcastDAO.selectlauchedBroadcast();
	}

}
