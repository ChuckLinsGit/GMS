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

import gms.entry.event.Broadcast;
import gms.service.event.IBroadcastService;

@RequestMapping("/broadcast")
@Controller
public class BroadcastHandler {
	@Resource(name="BroadcastService")
	private IBroadcastService broadcastService;
	
	/**
	 * 添加新的公告
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addBroadcast.do")
	public String addBroadcast(HttpServletRequest request, HttpServletResponse response)  {
		boolean rs = setEncoding(request);
		if(!rs) {
			return "";
		}
		Broadcast broadcast = getBroadcastFromPostForm(request);
		broadcastService.addBroadcast(broadcast);
		String header = request.getHeader("Referer");
		return "redirect:/"+header.substring(header.indexOf("GMS")+4, header.length());
	}
	
	/**
	 * 删除公告
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/dropBroadcast.do")
	public Object dropBroadcast(HttpServletRequest request, HttpServletResponse response)  {
		boolean rs = setEncoding(request);
		if(!rs) {
			return errorHandle(response);
		}
		Integer broadcastID = Integer.valueOf(request.getParameter("broadcastID"));
		broadcastService.dropBroadcast(broadcastID);
		return "";
	}
	
	/**
	 * 更改公告
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/changeBroadcast.do")
	public Object changeBroadcast(HttpServletRequest request, HttpServletResponse response)  {
		boolean rs = setEncoding(request);
		if(!rs) {
			return "";
		}
		Broadcast broadcast = getBroadcastFromPostForm(request);
		broadcastService.changeBroadcast(broadcast);
		String header = request.getHeader("Referer");
		return "redirect:/"+header.substring(header.indexOf("GMS")+4, header.length());
	}
	
	/**
	 * 设置公告轮播
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/lauchBroadcast.do")
	public Object lauchBroadcast(HttpServletRequest request, HttpServletResponse response)  {
		boolean rs = setEncoding(request);
		if(!rs) {
			return errorHandle(response);
		}
		Integer broadcastID = Integer.valueOf(request.getParameter("broadcastID"));
		broadcastService.lauchBroadcast(broadcastID);
		return "";
	}
	
	/**
	 * 停止公告轮播
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/stopBroadcast.do")
	public Object stopBroadcast(HttpServletRequest request, HttpServletResponse response)  {
		boolean rs = setEncoding(request);
		if(!rs) {
			return errorHandle(response);
		}
		Integer broadcastID = Integer.valueOf(request.getParameter("broadcastID"));
		broadcastService.stopBroadcast(broadcastID);
		return "";
	}

	/**
	 * 响应Vue请求，以JSON封装数据返回
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllBroadcast.do")
	public Object getAllBroadcast(HttpServletRequest request, HttpServletResponse response) {
		List<Broadcast> allBroadcast = broadcastService.getALLBroadcast();
		String jsonStr = JSON.toJSON(allBroadcast).toString();
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
	@RequestMapping("/getlauchedBroadcast.do")
	public Object getlauchedBroadcast(HttpServletRequest request, HttpServletResponse response) {
		List<Broadcast> lauchedBroadcast = broadcastService.getlauchedBroadcast();
		String jsonStr = JSON.toJSON(lauchedBroadcast).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	private Broadcast getBroadcastFromPostForm(HttpServletRequest request) {
		Broadcast broadcast = new Broadcast();
		
		String broadcastID = request.getParameter("broadcastID");
		if(broadcastID!=null&&broadcastID.length()!=0)
			broadcast.setBroadcastID(Integer.valueOf(broadcastID));
		
		String content = request.getParameter("content");
		if(content!=null&&content.length()!=0)
			broadcast.setContent(content);
		
		String state = request.getParameter("state");
		if(state!=null&&state.length()!=0&&(state.equals("on")||state.equals("1")))
			broadcast.setState(1);
		else
			broadcast.setState(0);
			
		
		return broadcast;
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
