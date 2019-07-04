package gms.service.equip;

import java.util.List;

import gms.entry.equip.Ordersview;

public interface OrdersviewService {

	public List<Ordersview> findallOrders();
	public List<Ordersview> finduserOrders(Integer user_id);
	public List<Ordersview> gmgetchecklist();//管理员获取审查列表

}
