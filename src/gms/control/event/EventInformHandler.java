package gms.control.event;

import java.io.UnsupportedEncodingException;  
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import gms.entry.event.EventInform;
import gms.service.event.IEventInformService;

@RequestMapping("/inform")
@Controller
public class EventInformHandler {
	@Resource(name="EventInformService")
	private IEventInformService informService;

	
	@ResponseBody
	@RequestMapping(value="confirmEventInform.do")
	public Object confirmEventInform(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return errorHandle(response);
		}
		Integer informID = Integer.valueOf(request.getParameter("informID"));
		informService.confirmEventInform(informID);
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value="dropEventInform.do")
	public Object dropEventInform(HttpServletRequest request,HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return errorHandle(response);
		}
		Integer informID = Integer.valueOf(request.getParameter("informID"));
		informService.dropEventInform(informID);
		return "";
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getMyEventInform.do")
	public Object getMyEventInform(HttpServletRequest request, HttpServletResponse response) {
		boolean rs = setEncoding(request);
		if(!rs) {
			return errorHandle(response);
		}
		Integer userID = Integer.valueOf(request.getParameter("userID"));
		List<EventInform> allEventInform = informService.getMyEventInform(userID);
		String jsonStr = JSON.toJSON(allEventInform).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllEventInform.do")
	public Object getAllEventInform(HttpServletRequest request, HttpServletResponse response) {
		List<EventInform> allEventInform = informService.getAllEventInform();
//		粗略看来一下JSON的源码，其原理主要是使用反射机制和对象序列化将对象转为字符串
		String jsonStr = JSON.toJSON(allEventInform).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
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
