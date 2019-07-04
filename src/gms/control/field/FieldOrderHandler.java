package gms.control.field;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import gms.entry.event.Event;
import gms.entry.event.EventApplication;
import gms.entry.field.FOrderExtent;
import gms.entry.field.FieldOrder;
import gms.entry.field.MixFieldOrder;
import gms.entry.field.Pay;
import gms.entry.field.PayMent;
import gms.service.event.IEventApplicationService;
import gms.service.event.IEventService;
import gms.service.field.FieldOrderService;
import gms.service.field.FieldPayService;
import gms.service.field.FieldService;

@RequestMapping("/FieldOrder")
@Controller
public class FieldOrderHandler {
	
	//注入FieldOrderServiceImpl代理对象，name必须与对应FieldOrderServiceImpl类声明的name相同
	@Resource(name="FieldOrderServiceImpl")
	private FieldOrderService fOrderService;
	
	@Resource(name="FieldPayServiceImpl")
	private FieldPayService fieldPayService;
	
	@Resource(name="FieldServiceImpl")
	private FieldService fieldService;
	
	@Resource(name="EventApplicationService")
	private IEventApplicationService applicationService;
	
	@Resource(name="EventService")//注入EventService代理对象，name必须与对应EventService类声明的name相同
	private IEventService eventService;
	
	/**
	 * @throws Exception 
	 * @Title: orderField  
	 * @Description: 新增订单 
	 * @param req
	 * @param resp
	 * @return ModelAndView
	 * @throws
	 */
	//URL:/FieldOrder/orderField
	@CrossOrigin
	@ResponseBody
	@RequestMapping("/orderField.do")
	public String orderField(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String tempReturn;
		FieldOrder fieldOrder = getFieldOrderFromPostForm(req);
		Pay pay = getPayFromPostForm(req);
		fieldPayService.addPay(pay);
		
		//获取到插入主键，当做外键写入order表
		//System.out.println(pay.getPayid());
		if(pay.getPayid().toString()!=null&&pay.getPayid().toString().length()>0) {
			fieldOrder.setPayid(pay.getPayid());
			//更新order表
			fOrderService.addFOrder(fieldOrder);
			//更新场地表
			fieldService.setRent(fieldOrder.getFieldid());
			//更新payment表
			PayMent payment = new PayMent();
			
			String moneyStr = req.getParameter("money");
			if(moneyStr!=null&&moneyStr.length()>0) {
				Float money = Float.valueOf(moneyStr);
				payment.setMoney(money);
				payment.setPayid(pay.getPayid());
				payment.setReturnid(fieldOrder.getReturnid());
				fieldPayService.addPayMent(payment);
				HttpSession session = req.getSession();
				Integer ifImportant = (Integer) session.getAttribute("important");
				if(ifImportant!=null) {
					EventApplication ep = (EventApplication) session.getAttribute("eventApplication");
					ep.setFieldID(fieldOrder.getOrderid());
					System.out.println(ep);
					System.out.println(fieldOrder.getOrderid());
				}
				tempReturn = "Success to Order";
				
			}else {
				tempReturn = "SQL ERROR";
			}

		}else {
			tempReturn = null;
		}
		
		//System.out.println(fieldOrder);
		//System.out.println(pay);
		return tempReturn;	
	}
	
	/**
	 * @Title: delOrder  
	 * @Description: 删除订单 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception ModelAndView
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/delOrder.do")
	public String delOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String tempReturn;
		String orderid = req.getParameter("orderid");
		String fieldid = req.getParameter("fieldid");
		if(orderid!=null&&orderid.length()>0&&fieldid!=null&&fieldid.length()>0) {
			fieldService.overRent(Integer.valueOf(fieldid));
			fOrderService.delFOrder(Integer.valueOf(orderid));
			tempReturn = "Success to CancelOrder";
		}else {
			tempReturn = "Fail to CancelOrder";
		}
				
		return tempReturn;
	}
	
	/**
	 * @Title: changeOrder  
	 * @Description: 修改 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping("/changeOrder")
	public String changeOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		return "DO Somethings";
	}
	
	/**
	 * @Title: getOrderAll  
	 * @Description: 场地管理员获取所有订单 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getOrderAll.do")
	public Object getOrderAll(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		List<FieldOrder> allorder = fOrderService.getOrderAll();
		
		//System.out.println("数据库时间戳"+allorder.get(0).getRentstart());
		String jsonStr = JSON.toJSON(allorder).toString();
		//resp.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}

	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getOrderEAll.do")
	public Object getOrderEAll(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		List<FOrderExtent> etorder = fOrderService.getOrderEAll();
		
		String jsonStr = JSON.toJSON(etorder).toString();
		return jsonStr;
	}
	
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getListEAll.do")
	public Object getListEAll(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String useridStr = req.getParameter("userid");
		//System.out.println("uid"+useridStr);
		if(useridStr!=null&&useridStr.length()>0) {
			Integer userid = Integer.parseInt(useridStr);
			List<FOrderExtent> etorder = fOrderService.getListEAll(userid);
			//System.out.println(etorder);
			String jsonStr = JSON.toJSON(etorder).toString();
			return jsonStr;
		}else {
			return null;
		}
		
		

	}
	
	
	/**
	 * @Title: getOrderByUsrId  
	 * @Description: 获取Session中用户的参数，并进行对应用户的订单查询 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception
	 * @throws
	 */
	@RequestMapping("/getOrderByUsrId.do")
	public Object getOrderByUsrId(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		Integer userid = Integer.valueOf((String) session.getAttribute("userID"));
		List<FieldOrder> usrOrder = fOrderService.getOrderByUsrId(userid);
		String jsonStr = JSON.toJSON(usrOrder).toString();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	/**
	 * @Title: getOrderByfidAndState  
	 * @Description: 高一级预约获取旧订单信息 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getOrderByfidAndState.do")
	public Object getOrderByfidAndState(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Integer fieldid = Integer.parseInt(req.getParameter("fieldid"));
		List<FieldOrder> fidStatelist = fOrderService.selectByfidAndstate(fieldid);
		String jsonStr = JSON.toJSON(fidStatelist).toString();
		return jsonStr;
	}
	
	/**
	 * @Title: getHistoryByUsrid  
	 * @Description: 获取user的历史订单 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getHistoryByUsrid.do")
	public Object getHistoryByUsrid(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Integer userid = Integer.parseInt(req.getParameter("userid"));
		List<MixFieldOrder> historyOrder = fOrderService.getHistoryByUsrid(userid);
		String jsonStr = JSON.toJSON(historyOrder).toString();
		return jsonStr;
	}
	
	/**
	 * @Title: getPaidOrderByUsrid  
	 * @Description: 已支付订单数据 
	 * @param req
	 * @param resp
	 * @return Objec
	 * @throws Exceptiont
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getPaidOrderByUsrid.do")
	public Object getPaidOrderByUsrid(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String usrid = req.getParameter("userid");
		String jsonStr;
		if(usrid!=null&&usrid.length()>0) {
			 Integer userid = Integer.parseInt(usrid);
			 List<MixFieldOrder> paidOrder = fOrderService.getPaidByUsrid(userid);
			 jsonStr = JSON.toJSON(paidOrder).toString();
			 
		}else {
			jsonStr = null;
		}
		return jsonStr;
		
		
	}
	
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/orderFieldForEvent.do")
	public String orderFieldForEvent(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String tempReturn;
		
		FieldOrder fieldOrder = getFieldOrderFromPostForm(req);
		Pay pay = getPayFromPostForm(req);
		String oorderid = req.getParameter("oorderid");
		String ouserid = req.getParameter("ouserid");
		if(ouserid!=null&&ouserid.length()>0&&oorderid!=null&&oorderid.length()>0) {
			FieldOrder oldfieldOrder = new FieldOrder();
			oldfieldOrder.setOrderid(Integer.valueOf(oorderid));
			oldfieldOrder.setUserid(Integer.valueOf(ouserid));
			
			//System.out.println(oldfieldOrder.getOrderid());
			
			//普通用户订单置位为过时，如果有赛事，则拒绝对应的赛事
			fOrderService.setForEvent(oldfieldOrder);
			System.out.println(oorderid);
			applicationService.dropEventApplicationByFieldID(Integer.valueOf(oorderid));
			eventService.cancelEventByFieldID(Integer.valueOf(oorderid));
			//加入（本人）订单
			fieldPayService.addPay(pay);
			
			//获取到插入主键，当做外键写入order表
			//System.out.println(pay.getPayid());
			if(pay.getPayid().toString()!=null&&pay.getPayid().toString().length()>0) {
				fieldOrder.setPayid(pay.getPayid());
				//创建order表
				fOrderService.addFOrder(fieldOrder);
				if(fieldOrder.getOrderid().toString()!=null&&fieldOrder.getOrderid().toString().length()>0) {

					

				//fOrderService.addFOrder(fieldOrder);
				
				//更新场地表 无需进行该操作，因为场地状态都为租赁
				//fieldService.setRent(fieldOrder.getFieldid());
				
				//更新payment表
				PayMent payment = new PayMent();
				String moneyStr = req.getParameter("money");
				if(moneyStr!=null&&moneyStr.length()>0) {
					Float money = Float.valueOf(moneyStr);
					payment.setMoney(money);
					payment.setPayid(pay.getPayid());
					payment.setReturnid(fieldOrder.getReturnid());
					//fieldPayService.addPayMent(payment);
					
					HttpSession session = req.getSession();
					Event ep = (Event) session.getAttribute("eventApplication");
					ep.setFieldID(fieldOrder.getOrderid());
					tempReturn = "Success to OrderH";
					
				}else {
					tempReturn = "SQL ERROR";
				}
					
				}else {
					tempReturn = "SQL ERROR";
				}

			}else {
				tempReturn = null;
			}
		}else {
			tempReturn = null;
		}
		
		
		//System.out.println(fieldOrder);
		//System.out.println(pay);
		return tempReturn;	
		
	}
	
	
	
	/**
	 *响应axios请求，json数据的封装和返回
	 *@param req
	 *@param resp
	 *@return 
	 */
	private FieldOrder getFieldOrderFromPostForm(HttpServletRequest req) {
		FieldOrder fieldOrder = new FieldOrder();
		
		String userid = req.getParameter("userid");
		if(userid!=null&&userid.length()>0)
			fieldOrder.setUserid(Integer.valueOf(userid));
		String orderid = req.getParameter("orderid");
		if(orderid!=null&&orderid.length()>0)
			fieldOrder.setOrderid(Integer.parseInt(orderid));
		
		String fieldid = req.getParameter("fieldid");
		if(fieldid!=null&&fieldid.length()>0)
			fieldOrder.setFieldid(Integer.parseInt(fieldid));
		
		String rentstart = req.getParameter("rentstart");
		if(rentstart!=null&&rentstart.length()>0)
			fieldOrder.setRentstart(java.sql.Timestamp.valueOf(rentstart));
		String rentend = req.getParameter("rentend");
		if(rentend!=null&&rentend.length()>0)
			fieldOrder.setRentend(java.sql.Timestamp.valueOf(rentend));
		String returnid = req.getParameter("returnid");
		if(returnid!=null&&returnid.length()>0)
			fieldOrder.setReturnid(Integer.parseInt(returnid));
		
		String phone = req.getParameter("phone");
		if(phone!=null&&phone.length()>0)
			fieldOrder.setPhone(phone);
		
		return fieldOrder;
		

	}
	
	private Pay getPayFromPostForm(HttpServletRequest req) {
		Pay pay = new Pay();
		
		String payid = req.getParameter("payid");
		if(payid!=null&&payid.length()>0)
			pay.setPayid(Integer.valueOf(payid));
		String fieldid = req.getParameter("fieldid");
		if(fieldid!=null&&fieldid.length()>0)
			pay.setFieldid(Integer.valueOf(fieldid));
		String allTime = req.getParameter("allTime");
		if(allTime!=null&&allTime.length()>0)
			pay.setAllTime(Integer.valueOf(allTime));
		//String paystate = req.getParameter("");
		//if(allTime!=null&&allTime.length()>0)
			//pay.setPaystate(paystate);
		
		return pay;
	}
	

	
	private boolean isEncoding(HttpServletRequest req, ModelAndView modelAndView) {
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
