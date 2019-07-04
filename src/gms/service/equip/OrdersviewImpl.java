package gms.service.equip;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.equip.IOrdersviewDAO;
import gms.entry.equip.Ordersview;

@Component("OrdersviewImpl")
public class OrdersviewImpl implements OrdersviewService{

	@Resource(name="IOrdersviewDAO")
	private IOrdersviewDAO ordersdao;
	
	//管理员获取所有订单列表
	@Transactional
	@Override
	public List<Ordersview> findallOrders(){
		List<Ordersview> list = new ArrayList<>();
		list = ordersdao.findallOrders();
		return list;
	}
	
	//用户获取订单列表
	@Transactional
	@Override
	public List<Ordersview> finduserOrders(Integer user_id){
		List<Ordersview> list = new ArrayList<>();
		list = ordersdao.finduserOrders(user_id);
		return list;
	}
	
	//管理员获取审查列表
	@Transactional
	@Override
	public List<Ordersview> gmgetchecklist(){
		List<Ordersview> list = new ArrayList<Ordersview>();
		list = ordersdao.gmgetchecklist();
		return list;
	}
}
