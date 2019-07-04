package gms.control.event;

import java.io.UnsupportedEncodingException;  
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import gms.entry.event.Event;
import gms.service.equip.OrdercreateService;
import gms.service.event.IEventService;
import gms.service.field.FieldOrderService;

@RequestMapping("/event")//该handler url访问前缀
@Controller//声明为controller才能响应url请求
public class EventHandler {

	@Resource(name="EventService")//注入EventService代理对象，name必须与对应EventService类声明的name相同
	private IEventService eventService;
	@Resource(name="FieldOrderServiceImpl")
	private FieldOrderService fOrderService;
	@Resource(name="OrdercreateImpl")
	private OrdercreateService ordercreate;
	
	
	/**
	 * 表单请求，需要跳转，无需返回
	 * 更改赛事
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("changeEvent.do")
	public String changeEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean rs = setEncoding(request);
		if(!rs) {
			return "";
		}
		Event event = getEventFromPostForm(request);
		event=eventService.getEventByID(event.getEventID());
		try {
			eventService.dropEvent(event.getEventID());
			ordercreate.onrentorbook(event.getEquipmentID());
			fOrderService.delFOrder(event.getFieldID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/application/lauchEventApplication.do";
	}
	
	/**
	 * 表单请求，需要跳转，无需返回
	 * 发起重大赛事申请：跳往场地选择和器材选择
	 * 不论是新申请还是更改申请，都先跳向此处，在完成场地和器材选择后再跳往写入操作的函数
	 * 新申请跳向addImportantEventApplication
	 * 更改申请跳向changeImportantEventApplication
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("lauchImportantEvent.do")
	public String lauchImportantEvent(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return "";
		}
		Event importantEvent = getEventFromPostForm(request);
		importantEvent=eventService.getEventByID(importantEvent.getEventID());
		HttpSession session = request.getSession();
		session.setAttribute("eventApplication", importantEvent);
		session.setAttribute("important", 1);
		return "redirect:/dist/field.html#/ResolutionCenter";
	}
	
	
	/**
	 * 表单请求，需要跳转，无需返回
	 * 发起重大赛事申请：跳往场地选择和器材选择
	 * 不论是新申请还是更改申请，都先跳向此处，在完成场地和器材选择后再跳往写入操作的函数
	 * 新申请跳向addImportantEventApplication
	 * 更改申请跳向changeImportantEventApplication
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("lauchChangeImportantEvent.do")
	public String lauchChangeImportantEvent(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return "";
		}
		Event importantEvent = getEventFromPostForm(request);
		importantEvent=eventService.getEventByID(importantEvent.getEventID());
		try {
			eventService.dropEvent(importantEvent.getEventID());
			ordercreate.onrentorbook(importantEvent.getEquipmentID());
			fOrderService.delFOrder(importantEvent.getFieldID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("eventApplication", importantEvent);
		session.setAttribute("important", 1);
		return "redirect:/dist/field.html#/ResolutionCenter";
	}
	
	/**
	 * 表单请求，需要跳转，无需返回
	 * 结束重大赛事申请，往数据库写入
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addImportantEvent.do")
	public Object addImportantEvent(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return "";
		}
		HttpSession session = request.getSession();
		Event event = (Event) session.getAttribute("eventApplication");
		event.setState(0);
		eventService.addEvent(event);
		String header = request.getHeader("Referer");
		return "/GMS/event/event_manager_application.html";
	}

	
	/**
	 * 表单请求，需要跳转，无需返回
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("changeImportantEvent.do")
	public String changeImportantEvent(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return "";
		}
		Event event = getEventFromPostForm(request);
		eventService.changeImportatnEvent(event);
		return "redirect:/event/event_manager_application.html";
	}
	
	
	/**
	 * 删除赛事
	 * axios请求，因此需要返回JSON
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("dropEvent.do")
	public Object dropEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean rs = setEncoding(request);
		if(!rs) {
			return errorHandle(response);
		}
		String eventID = request.getParameter("eventID");
		if(eventID!=null&&eventID.length()!=0)
			eventService.dropEvent(Integer.valueOf(eventID));
		return "";
	}
	
	
	/**
	 * 查询“我”的赛事
	 * 响应Vue请求，以JSON封装数据返回
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getMyEvent.do")
	public Object getMyEvent(HttpServletRequest request, HttpServletResponse response) {
		Integer userID = Integer.valueOf (request.getParameter("userID"));
		List<Event> myEvent = eventService.getEventByUserID(userID);
		String jsonStr = JSON.toJSON(myEvent).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	/**
	 * 响应Vue请求，以JSON封装数据返回
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllEvent.do")
	public Object getAllEvent(HttpServletRequest request, HttpServletResponse response) {
		List<Event> allEvent = eventService.getAllEvent();
		String jsonStr = JSON.toJSON(allEvent).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private Event getEventFromPostForm(HttpServletRequest request) {
		Event e=new Event();

		/*request.getAttribute用于接受重定向或者转发请求中使用request.getAttribute的数据，
		 如果用来接收客户端的数据将报404，这是最难以发现的地方，按正常逻辑应该报500错误
		 接收客户端数据应该使用getParameter方法
		 如果要探究原因，需要深入了解servletrequest的原理
		 */
		String eventID = request.getParameter("eventID");
		if(eventID!=null&&eventID.length()!=0)
			e.setEventID(Integer.valueOf(eventID));
		
		String userID = request.getParameter("userID");
		if(userID!=null&&userID.length()!=0)
			e.setUserID(Integer.valueOf(userID));
		
		String equipmentID = request.getParameter("equipmentID");
		if(equipmentID!=null&&equipmentID.length()!=0)
			e.setEquipmentID(Integer.valueOf(equipmentID));
		
		String fieldID = request.getParameter("fieldID");
		if(fieldID!=null&&fieldID.length()!=0)
			e.setFieldID(Integer.valueOf(fieldID));
		
		String date = request.getParameter("date");
		if(date!=null&&date.length()!=0)
			e.setDate(Date.valueOf(date));
		
		String endDate = request.getParameter("endDate");
		if(endDate!=null&&date.length()!=0)
			e.setEndDate(Date.valueOf(endDate));
		
		String content = request.getParameter("content");
		if(content!=null&&content.length()!=0)
			e.setContent(content);
		
		String judgement = request.getParameter("judgement");
		if(judgement!=null&&judgement.length()!=0)
			e.setJudgement(judgement);
		return e;
	}
	private boolean setEncoding(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private Object errorHandle(HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("exception", "服务端出错，已反馈给管理员，请稍后再试");
		String jsonStr = JSON.toJSON(map).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
}
