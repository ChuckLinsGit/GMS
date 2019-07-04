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
import gms.entry.event.EventApplication;
import gms.entry.event.EventInform;
import gms.service.equip.OrdercreateService;
import gms.service.event.IEventApplicationService;
import gms.service.event.IEventInformService;
import gms.service.field.FieldOrderService;

@RequestMapping("/application")
@Controller
public class EventApplicationHandler {
	@Resource(name="EventApplicationService")
	private IEventApplicationService applicationService;
	
	@Resource(name="EventInformService")
	private IEventInformService informService;
	
	@Resource(name="FieldOrderServiceImpl")
	private FieldOrderService fOrderService;
	@Resource(name="OrdercreateImpl")
	private OrdercreateService ordercreate;

	/**
	 * 发起赛事申请：跳往场地选择和器材选择
	 * 不论是新申请还是更改申请，都先跳向此处，在完成场地和器材选择后再跳往写入操作的函数
	 * 新申请跳向addEventApplication
	 * 更改申请跳向changeEventApplication
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("lauchEventApplication.do")
	public String lauchEventApplication(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return "";
		}
		EventApplication ep = getApplicationFromPostForm(request);
		HttpSession session = request.getSession();
		session.setAttribute("eventApplication", ep);
		session.setAttribute("important", 0);
		String header = request.getHeader("Referer");
		//此处必须使用redict，否则前端页面中css和js等使用相对路径的引用将失效
		return "redirect:/dist/field.html#/ResolutionCenter";
	}
	
	/**
	 * 发起赛事更改申请：跳往场地选择和器材选择
	 * 不论是新申请还是更改申请，都先跳向此处，在完成场地和器材选择后再跳往写入操作的函数
	 * 新申请跳向addEventApplication
	 * 更改申请跳向changeEventApplication
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("lauchChangeEventApplication.do")
	public String lauchChangeEventApplication(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return "";
		}
		EventApplication ep = getApplicationFromPostForm(request);
		ep=applicationService.getEventApplicationByID(ep.getApplicationID());
		try {
			applicationService.dropEventApplication(ep.getApplicationID());
			ordercreate.onrentorbook(ep.getEquipmentID());
			fOrderService.delFOrder(ep.getFieldID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		applicationService.dropEventApplication(ep.getApplicationID());
		HttpSession session = request.getSession();
		session.setAttribute("eventApplication", ep);
		session.setAttribute("important", 0);
		String header = request.getHeader("Referer");
		//此处必须使用redict，否则前端页面中css和js等使用相对路径的引用将失效
		return "redirect:/dist/field.html#/ResolutionCenter";
	}
	
	/**
	 * 结束普通赛事申请，往数据库写入
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addEventApplication.do")
	public Object addEventApplication(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return "";
		}
		HttpSession session = request.getSession();
		EventApplication ep = (EventApplication) session.getAttribute("eventApplication");
		ep.setState(0);
		applicationService.addEventApplication(ep);
		//此处必须使用redict，否则前端页面中css和js等使用相对路径的引用将失效
		return "redirect:/event/event_visitor_apply.html";
	}
	
	
	/**
	 * 删除赛事申请
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("dropEventApplication.do")
	public Object dropEventApplication(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return errorHandle(response);
		}
		String applicationID = request.getParameter("applicationID");
		@SuppressWarnings("unused")
		String err=null;
		if(applicationID!=null&&applicationID.length()!=0) {
			applicationService.dropEventApplication(Integer.valueOf(applicationID));
			return "";
		}
		else {
			HashMap<String, String> map = new HashMap<>();
			map.put("exception", "错误！");
			err = JSON.toJSON(map).toString();
			return "err";
		}
	}
	
	/**
	 * 回绝赛事申请
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("refuseEventApplication.do")
	public String refuseEventApplication(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return "";
		}
		EventApplication ep =getApplicationFromPostForm(request);
		ep=applicationService.refuseEventApplication(ep.getApplicationID());
		//消息通知处理
		EventInform inform=new EventInform();
		inform.setDate(new Date(System.currentTimeMillis()));
		inform.setContent(ep.getContent()+"申请失败！管理员意见："+request.getParameter("reason"));
		inform.setUserID(ep.getUserID());
		inform.setState(0);
		informService.addEventInform(inform);
		return "redirect:/event/event_manager_application_manage.html";
	}

	/**
	 * 接收赛事申请
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("acceptEventApplication.do")
	public Object acceptEventApplication(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return errorHandle(response);
		}
		EventApplication ep =getApplicationFromPostForm(request);
		ep=applicationService.acceptEventApplication(ep.getApplicationID());
		//消息通知处理
		EventInform inform=new EventInform();
		inform.setDate(new Date(System.currentTimeMillis()));
		inform.setContent(ep.getContent()+"申请成功！");
		inform.setUserID(ep.getUserID());
		inform.setState(0);
		informService.addEventInform(inform);
		return "";
	}
	
	/**
	 * 查询所有赛事申请
	 * 响应Vue请求，以JSON封装数据返回
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getWaitingEventApplication.do")
	public Object getWaitingEventApplication(HttpServletRequest request, HttpServletResponse response) {
		List<EventApplication> eP = applicationService.getWaitingEventApplication();
		String jsonStr = JSON.toJSON(eP).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	/**
	 * 查询所有赛事申请
	 * 响应Vue请求，以JSON封装数据返回
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllEventApplication.do")
	public Object getAllEventApplication(HttpServletRequest request, HttpServletResponse response) {
		List<EventApplication> allEP = applicationService.getAllEventApplication();
		String jsonStr = JSON.toJSON(allEP).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	/**
	 * 查询“我”的赛事申请
	 * 响应Vue请求，以JSON封装数据返回
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getMyEventApplication.do")
	public Object getMyEventApplication(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		Integer userID = (Integer) session.getAttribute("userID");
		String userID = request.getParameter("userID");
		List<EventApplication> allEP=null;
		if(userID!=null&&userID.length()!=0)
			allEP= applicationService.getMyEventApplication(Integer.valueOf(userID));
		String jsonStr = JSON.toJSON(allEP).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	private EventApplication getApplicationFromPostForm(HttpServletRequest request){
		EventApplication ep = new EventApplication();
		
		String applicationID = request.getParameter("applicationID");
		if(applicationID!=null&&applicationID.length()!=0)
			ep.setApplicationID(Integer.valueOf(applicationID));
		
		String fieldID = request.getParameter("fieldID");
		if(fieldID!=null&&fieldID.length()!=0)
			ep.setFieldID(Integer.valueOf(fieldID));
		
		String equipmentID = request.getParameter("equipmentID");
		if(equipmentID!=null&&equipmentID.length()!=0)
			ep.setEquipmentID(Integer.valueOf(equipmentID));
		
		String userID = request.getParameter("userID");
		if(userID!=null&&userID.length()!=0)
			ep.setUserID(Integer.valueOf(userID));
		
		String date = request.getParameter("date");
		if(date!=null&&date.length()!=0)
			ep.setDate(Date.valueOf(date));
		
		String endDate = request.getParameter("endDate");
		if(endDate!=null&&endDate.length()!=0)
			ep.setEndDate(Date.valueOf(endDate));
		

		String content = request.getParameter("content");
		if(content!=null&&content.length()!=0)
			ep.setContent(content);
		
		String judgement = request.getParameter("judgement");
		if(judgement!=null&&judgement.length()!=0)
			ep.setJudgement(judgement);
		
		String state = request.getParameter("state");
		if(state!=null&&state.length()!=0)
			ep.setState(Integer.valueOf(state));
		
		return ep;
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
