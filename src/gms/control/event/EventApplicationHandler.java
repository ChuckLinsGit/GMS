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
	 * �����������룺��������ѡ�������ѡ��
	 * �����������뻹�Ǹ������룬��������˴�������ɳ��غ�����ѡ���������д������ĺ���
	 * ����������addEventApplication
	 * ������������changeEventApplication
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
		//�˴�����ʹ��redict������ǰ��ҳ����css��js��ʹ�����·�������ý�ʧЧ
		return "redirect:/dist/field.html#/ResolutionCenter";
	}
	
	/**
	 * �������¸������룺��������ѡ�������ѡ��
	 * �����������뻹�Ǹ������룬��������˴�������ɳ��غ�����ѡ���������д������ĺ���
	 * ����������addEventApplication
	 * ������������changeEventApplication
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
		//�˴�����ʹ��redict������ǰ��ҳ����css��js��ʹ�����·�������ý�ʧЧ
		return "redirect:/dist/field.html#/ResolutionCenter";
	}
	
	/**
	 * ������ͨ�������룬�����ݿ�д��
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
		//�˴�����ʹ��redict������ǰ��ҳ����css��js��ʹ�����·�������ý�ʧЧ
		return "redirect:/event/event_visitor_apply.html";
	}
	
	
	/**
	 * ɾ����������
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
			map.put("exception", "����");
			err = JSON.toJSON(map).toString();
			return "err";
		}
	}
	
	/**
	 * �ؾ���������
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
		//��Ϣ֪ͨ����
		EventInform inform=new EventInform();
		inform.setDate(new Date(System.currentTimeMillis()));
		inform.setContent(ep.getContent()+"����ʧ�ܣ�����Ա�����"+request.getParameter("reason"));
		inform.setUserID(ep.getUserID());
		inform.setState(0);
		informService.addEventInform(inform);
		return "redirect:/event/event_manager_application_manage.html";
	}

	/**
	 * ������������
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
		//��Ϣ֪ͨ����
		EventInform inform=new EventInform();
		inform.setDate(new Date(System.currentTimeMillis()));
		inform.setContent(ep.getContent()+"����ɹ���");
		inform.setUserID(ep.getUserID());
		inform.setState(0);
		informService.addEventInform(inform);
		return "";
	}
	
	/**
	 * ��ѯ������������
	 * ��ӦVue������JSON��װ���ݷ���
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
	 * ��ѯ������������
	 * ��ӦVue������JSON��װ���ݷ���
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
	 * ��ѯ���ҡ�����������
	 * ��ӦVue������JSON��װ���ݷ���
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
		map.put("exception", "����˳����ѷ���������Ա�����Ժ�����");
		String jsonStr = JSON.toJSON(map).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
}
