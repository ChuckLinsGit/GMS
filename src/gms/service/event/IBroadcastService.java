package gms.service.event;

import java.util.List;

import gms.entry.event.Broadcast;

public interface IBroadcastService {
	public void addBroadcast(Broadcast broadcast);
	public void dropBroadcast(Integer broadcastID);
	public void changeBroadcast(Broadcast broadcast);
	public void lauchBroadcast(Integer broadcastID);
	public void stopBroadcast(Integer broadcastID);
	public List<Broadcast> getBroadcastByID(Integer broadcastID);
	public List<Broadcast> getALLBroadcast();
	public List<Broadcast> getlauchedBroadcast();
}
