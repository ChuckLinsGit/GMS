package gms.control.equip;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import gms.DAO.event.IEventApplicationDAO;
import gms.entry.equip.Ordersdetail;
import gms.entry.event.Event;
import gms.entry.event.EventApplication;
import gms.service.equip.OrdercreateService;
import gms.service.event.IEventApplicationService;

/**
 * @Title:OrdercreateHandler.java
 * @author:耶路·马伦
 * @Description:对于订单的操作
 * @date:2019年6月25日
 */
@RequestMapping("/ordercreate")
@Controller
public class OrdersHandler {

	@Resource(name="OrdercreateImpl")
	private OrdercreateService ordercreate;
	@Resource(name="EventApplicationService")
	private IEventApplicationService applicationService;
	
	/**
	 * 创建订单
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/createorder.do")
	public String createorder(/*@ResponseBody String Strjson) {*/
			HttpServletRequest request,HttpServletResponse response) {
		Ordersdetail ordersdetail = getcreateordersdatafromrequest(request);
		Integer order_id = ordercreate.ceateorders(ordersdetail);
		HttpSession session = request.getSession();
		EventApplication ep = (EventApplication) session.getAttribute("eventApplication");
		if(ep!=null) {
			ep.setEquipmentID(order_id);
			applicationService.addEventApplication(ep);
			System.out.println(ep);
			return "1";
		}
		System.out.println(ordersdetail);
		return "创建成功！";
	}
	
	//用户申请归还或取消预约
	@ResponseBody
	@RequestMapping("/norentorbook.do")
	public String norentorbook(
			HttpServletRequest request,HttpServletResponse response) {
		Ordersdetail ordersdetail = new Ordersdetail();
		ordersdetail.setOrders_id(Integer.parseInt(request.getParameter("orders_id")));
		System.out.println(ordersdetail.getOrders_id());
		return ordercreate.onrentorbook(ordersdetail.getOrders_id());
		
	}
	
	//管理员审核订单
	@ResponseBody
	@RequestMapping("/gmcheckorders.do")
	public String gmcheckorders(
			HttpServletRequest request,HttpServletResponse response) {
		System.out.println("startcheck");
		Ordersdetail orders = new Ordersdetail();
		Integer orders_id = Integer.parseInt(request.getParameter("orders_id"));
		Integer orders_state = Integer.parseInt(request.getParameter("orders_state"));
		System.out.println(orders_id+" "+orders_state);
		orders.setOrders_id(orders_id);
		orders.setOrders_state(orders_state);
		/*Ordersdetail orders = getcreateordersdatafromrequest(request);*/
		return ordercreate.gmcheckequip(orders);
	}
	
	//赛事创建订单
	@RequestMapping("/bgcreateorder.do")
	public String bgcreateorder(
			HttpServletRequest request,HttpServletResponse responseBody) {
		System.out.println("重大赛事");
		Ordersdetail orders = getcreateordersdatafromrequest(request);
		System.out.println("获取参数");
		List<Integer> droporderid = ordercreate.bgcreateorder(orders);
		HttpSession session = request.getSession();
		System.out.println("");
		Event ep = (Event) session.getAttribute("eventApplication");
		ep.setEquipmentID(orders.getEquip_id());
		System.out.println(ep);
		Integer ifImportant = (Integer) session.getAttribute("important");
		if(ifImportant==0) {
			return "redirect:/application/addEventApplication.do";
		}
		else {
			return "redirect:/event/addImportantEvent.do";
		}
	}
	
	
	
	public Ordersdetail getcreateordersdatafromrequest(HttpServletRequest request) {
		Ordersdetail ordersdetail = new Ordersdetail();
		Integer equip_id = Integer.parseInt(request.getParameter("equip_id"));
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		Timestamp orders_renttime = Timestamp.valueOf((request.getParameter("renttime")));
		Timestamp orders_backtime = Timestamp.valueOf(request.getParameter("backtime"));
		Integer equip_num = Integer.parseInt(request.getParameter("num"));
		Integer orders_state = Integer.parseInt(request.getParameter("orders_state"));
		if(equip_id != null) {
			ordersdetail.setEquip_id(equip_id);
		}
		if(user_id!=null) {
			ordersdetail.setUser_id(user_id);
		}
		if(orders_renttime != null) {
			ordersdetail.setOrders_renttime(orders_renttime);
		}
		if(orders_backtime != null) {
			ordersdetail.setOrders_backtime(orders_backtime);
		}
		if(equip_num != null) {
			ordersdetail.setEquip_num(equip_num);
		}
		if(orders_state != null) {
			ordersdetail.setOrders_state(orders_state);
		}
		System.out.println(ordersdetail);
		return ordersdetail;
	}
}
