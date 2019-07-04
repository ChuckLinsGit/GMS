package gms.DAO.event;

import java.util.List;

import gms.entry.event.Broadcast;

public interface IBroadcastDAO {
	public void insert(Broadcast broadcast);
	public void delete(Integer broadcastID);
	public void update(Broadcast broadcast);
	public void lauchBroadcast(Integer broadcastID);
	public void stopBroadcast(Integer broadcastID);
	public List<Broadcast> selectByID(Integer broadcastID);
	public List<Broadcast> selectALL();
	public List<Broadcast> selectlauchedBroadcast();
}
