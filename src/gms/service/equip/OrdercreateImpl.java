package gms.service.equip;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.equip.IEquipmentDAO;
import gms.DAO.equip.IOrdercreateDAO;
import gms.DAO.equip.IOrdersviewDAO;
import gms.entry.equip.Equipment;
import gms.entry.equip.EquipmentBG;
import gms.entry.equip.Ordersdetail;
import gms.service.event.IEventApplicationService;
import gms.service.event.IEventService;

/**
 * @Title:OrdercreateImpl.java
 * @author:耶路·马伦
 * @Description:管理员和用户订单的操作相关
 * @date:2019年6月29日
 */
@Component("OrdercreateImpl")
public class OrdercreateImpl implements OrdercreateService {

	@Resource(name="IOrdercreateDAO")
	private IOrdercreateDAO ordercreate;
	@Resource(name="IEquipmentDAO")
	private IEquipmentDAO equipment;
	@Resource(name="IOrdersviewDAO")
	private IOrdersviewDAO ordersview;
	@Resource(name="EventApplicationService")
	private IEventApplicationService applicationService;
	
	@Resource(name="EventService")//注入EventService代理对象，name必须与对应EventService类声明的name相同
	private IEventService eventService;
	
	/**
	 * 租借器材或预订器材
	 */
	@Transactional
	@Override
	public Integer ceateorders(Ordersdetail ordersdetail) {
		 ordercreate.createorders(ordersdetail);
		if(ordersdetail.getOrders_state() == 1) {
			equipment.rentequip(ordersdetail);

		}else {
			equipment.bookequip(ordersdetail);
		}
		return ordersdetail.getOrders_id();
	}
	
	/**
	 * 归还器材或取消预约
	 * 标志位(orders_state)：1为租借器材，2为预约器材，3为已取消预约，4为审核中，5为正常归还，6为破损归还
	 * @param orders_id
	 */
	@Transactional
	@Override
	public String onrentorbook(Integer orders_id) {
		Ordersdetail ordersdetail = ordercreate.findordersbyid(orders_id);
		//当归还器材时
		if(ordersdetail.getOrders_state() == 1) {
			ordersdetail.setOrders_state(4);
			ordercreate.currstatebyid(ordersdetail);
			if(ordersview.getcheckidfromcheck(ordersdetail.getOrders_id())==null) {
				ordersview.addequiptochecklist(ordersdetail.getOrders_id());
			}
			return "等待审核中";
		}
		//当取消预约时
		else if(ordersdetail.getOrders_state() == 2) {
			ordersdetail.setOrders_state(3);
			ordercreate.currstatebyid(ordersdetail);
			equipment.nodobook(ordersdetail);
			return "取消成功";
		}else {
			return "操作失败";
		}
	}
	
	/**
	 * 管理员审核器材
	 */
	@Transactional
	@Override
	public String gmcheckequip(Ordersdetail ordersdetail) {
		if(ordersdetail.getOrders_state() == 5) {
			ordercreate.currstatebyid(ordersdetail);
			ordersdetail = ordercreate.findordersbyid(ordersdetail.getOrders_id());
			ordersdetail.setOrders_state(5);
			equipment.gmreturnequip(ordersdetail);
			return "正常归还";
		}else if(ordersdetail.getOrders_state() == 6) {
			ordercreate.currstatebyid(ordersdetail);
			ordersdetail = ordercreate.findordersbyid(ordersdetail.getOrders_id());
			ordersdetail.setOrders_state(6);
			equipment.gmreturndamageequip(ordersdetail);
			return "破损归还";
		}else {
			return "审核失败";
		}
	}
	
	/**
	 * 生成重大赛事订单
	 */
	@Transactional
	@Override
	public List<Integer> bgcreateorder(Ordersdetail order) {
		EquipmentBG equip = equipment.bggetequipbyid(order.getEquip_id());
		List<Integer> droporderid = new ArrayList<Integer>();
		if(equip.getEquip_book()+equip.getEquip_last() < order.getEquip_num()) {
			return droporderid;
		}else if(equip.getEquip_last() < order.getEquip_num()) {
			ordercreate.createorders(order);
			List<Ordersdetail> list = ordercreate.bggetcandroporder();
			Integer sum = order.getEquip_num() - equip.getEquip_last();
			Integer drop = 0;
			int cout = 0;
			while(sum > drop) {
				drop += list.get(cout).getEquip_num();
				cout ++;
				order.setEquip_num(list.get(cout).getEquip_num());
				order.setOrders_state(3);
				order.setOrders_id(list.get(cout).getOrders_id());
				order.setUser_id(list.get(cout).getUser_id());
				droporderid.add(list.get(cout).getOrders_id());
				ordercreate.currstatebyid(order);
				equipment.nodobook(order);
				ordercreate.bgadddropmassage(order);
				applicationService.dropEventApplicationByFieldID(list.get(cout).getOrders_id());
				eventService.cancelEventByFieldID(list.get(cout).getOrders_id());
			}
		}else if(equip.getEquip_last() >= order.getEquip_num()) {
			ordercreate.createorders(order);
			equipment.bookequip(order);
			return droporderid;
		}
		return droporderid;
	}
	
	/**
	 * 用户查看有无消息
	 */
	/*@Transactional
	@Override
	public List<Integer> bguserseeifmassage(Integer user_id){
		List<Integer> list = ordercreate.bguserseeifmassage(user_id);
		return list;
	}*/
	/**
	 * 用户已经查看消息
	 */
	/*@Transactional
	@Override
	public void bguserseemassage(Integer user_id) {
		ordercreate.bguserseemassage(user_id);
	}*/
	/**
	 * 系统检测订单过期
	 */
	@Transactional
	@Override
	public void systemcheck() {
		List<Ordersdetail> list = ordercreate.nobookontime();
		Ordersdetail orders = new Ordersdetail();
		for(int i = 0;i<list.size();i++) {
		 	orders.setOrders_id(list.get(i).getOrders_id());
		 	orders.setOrders_state(3);
		 	orders.setEquip_num(list.get(i).getEquip_num());
		 	ordercreate.currstatebyid(orders);
		 	equipment.nodobook(orders);
		}
	}
}
