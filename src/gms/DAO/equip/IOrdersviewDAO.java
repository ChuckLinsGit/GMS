package gms.DAO.equip;

import java.util.List;

import gms.entry.equip.Ordersview;

/**
 * @Title:IOrdersviewDAO.java
 * @author:耶路·马伦
 * @Description:关于订单列表和审查列表的操作
 * @date:2019年6月27日
 */
public interface IOrdersviewDAO {

	public List<Ordersview> findallOrders();//查找所有订单
	public List<Ordersview> gmgetchecklist();//管理员获得审查列表
	public void addequiptochecklist(Integer orders_id);//将器材订单加到审查列表中
	public Integer getcheckidfromcheck(Integer orders_id);//用于检测审查列表中是否有该订单
	public List<Ordersview> finduserOrders(Integer user_id);//用户查找个人订单
}
