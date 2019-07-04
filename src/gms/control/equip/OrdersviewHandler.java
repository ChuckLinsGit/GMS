package gms.control.equip;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import gms.entry.equip.Ordersview;
import gms.service.equip.OrdersviewService;

/**
 * @Title:OrdersviewHandler.java
 * @author:耶路·马伦
 * @Description:获取订单列表、审查列表相关
 * @date:2019年6月28日
 */
@RequestMapping("/ordersview")
@Controller
public class OrdersviewHandler {

	@Resource(name="OrdersviewImpl")
	private OrdersviewService ordersservice;
	
	//用户获取自己订单
	@ResponseBody
	@RequestMapping("/finduserOrders.do")
	public Object finduserOrders(
			HttpServletRequest request,HttpServletResponse response) {
		List<Ordersview> list = new ArrayList<>();
		list = ordersservice.finduserOrders(Integer.parseInt(request.getParameter("userid")));
		System.out.println(list);
		String jsonStr = JSON.toJSON(list).toString();
		response.addHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;	
	}
	
	//管理员获取所有订单
		@ResponseBody
		@RequestMapping("/findallOrders.do")
		public Object findallOrders(
				HttpServletRequest request,HttpServletResponse response) {
			List<Ordersview> list = new ArrayList<>();
			list = ordersservice.findallOrders();
			System.out.println(list);
			String jsonStr = JSON.toJSON(list).toString();
			response.addHeader("Access-Control-Allow-Origin", "*");
			return jsonStr;	
		}
		
	//管理员获取审查列表
		@ResponseBody
		@RequestMapping("/gmgetchecklist.do")
		public Object gmgetchecklist(
				HttpServletRequest request,HttpServletResponse response) {
			List<Ordersview> list = ordersservice.gmgetchecklist();
			String jsonStr = JSON.toJSON(list).toString();
			response.addHeader("Access-Control-Allow-Origin", "*");
			return jsonStr;
		}	
}
