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

@RequestMapping("/event")//��handler url����ǰ׺
@Controller//����Ϊcontroller������Ӧurl����
public class EventHandler {

	@Resource(name="EventService")//ע��EventService�������name�������ӦEventService��������name��ͬ
	private IEventService eventService;
	@Resource(name="FieldOrderServiceImpl")
	private FieldOrderService fOrderService;
	@Resource(name="OrdercreateImpl")
	private OrdercreateService ordercreate;
	
	
	/**
	 * ��������Ҫ��ת�����践��
	 * ��������
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
	 * ��������Ҫ��ת�����践��
	 * �����ش��������룺��������ѡ�������ѡ��
	 * �����������뻹�Ǹ������룬��������˴�������ɳ��غ�����ѡ���������д������ĺ���
	 * ����������addImportantEventApplication
	 * ������������changeImportantEventApplication
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
	 * ��������Ҫ��ת�����践��
	 * �����ش��������룺��������ѡ�������ѡ��
	 * �����������뻹�Ǹ������룬��������˴�������ɳ��غ�����ѡ���������д������ĺ���
	 * ����������addImportantEventApplication
	 * ������������changeImportantEventApplication
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
	 * ��������Ҫ��ת�����践��
	 * �����ش��������룬�����ݿ�д��
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
	 * ��������Ҫ��ת�����践��
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
	 * ɾ������
	 * axios���������Ҫ����JSON
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
	 * ��ѯ���ҡ�������
	 * ��ӦVue������JSON��װ���ݷ���
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
	 * ��ӦVue������JSON��װ���ݷ���
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

		/*request.getAttribute���ڽ����ض������ת��������ʹ��request.getAttribute�����ݣ�
		 ����������տͻ��˵����ݽ���404�����������Է��ֵĵط����������߼�Ӧ�ñ�500����
		 ���տͻ�������Ӧ��ʹ��getParameter����
		 ���Ҫ̽��ԭ����Ҫ�����˽�servletrequest��ԭ��
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
		map.put("exception", "����˳����ѷ���������Ա�����Ժ�����");
		String jsonStr = JSON.toJSON(map).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
}
