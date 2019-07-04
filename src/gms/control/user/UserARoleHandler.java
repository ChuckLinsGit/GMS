package gms.control.user;

import gms.entry.user.UserARole;
import gms.service.user.UserARoleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/UserARole")
@Controller
public class UserARoleHandler {

	@Resource(name="UserARoleService")
	private UserARoleService useraroleService;

	public void setUserARoleService(UserARoleService useraroleService) {
		this.useraroleService = useraroleService;
	}

	@ResponseBody
	@RequestMapping("/addUserARole.do")
	public Object addUserARole(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> roleMap1 = new HashMap<String, Object>();
		String UID = req.getParameter("UID");
		String RID = req.getParameter("RID");
		Integer CUID = Integer.parseInt(UID);
		Integer CRID = Integer.parseInt(RID);
		UserARole role=new UserARole(CUID,CRID);
		if(role!=null) {
			useraroleService.addUserARole(role);
			roleMap1.put("error1", "success");
			list.add(roleMap1);
		}
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping("/selectRID.do")
	public Object selectRID(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> roleMap1 = new HashMap<String, Object>();
		String UID = req.getParameter("UID");
		String RID = req.getParameter("RID");
		Integer CUID = Integer.parseInt(UID);
		Integer CRID = Integer.parseInt(RID);
		UserARole role=new UserARole(CUID,CRID);
		if(role!=null) {
			useraroleService.addUserARole(role);
			roleMap1.put("error1", "success");
			list.add(roleMap1);
		}
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
}