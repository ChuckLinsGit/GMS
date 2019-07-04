package gms.control.user;

import gms.entry.user.Permission;
import gms.service.user.PermissionService;

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


@RequestMapping("/Permission")
@Controller
public class PermissionHandler {

	@Resource(name="PermissionService")
	private PermissionService permissionService;

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	@ResponseBody
	@RequestMapping("/addPermission.do")
	public Object addPermission(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> roleMap1 = new HashMap<String, Object>();
		String pleveld = req.getParameter("PLevelD");
		Integer PLevelD = Integer.parseInt(pleveld);
		String PName = req.getParameter("PName");
		String CPName = new String(PName.getBytes("ISO8859-1"), "utf-8");
		Permission permission=new Permission(PLevelD,CPName);
		if(permission!=null) {
			permissionService.addPermission(permission);
			roleMap1.put("error1", "success");
			list.add(roleMap1);
		}
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping("/PermissionList.do")
	public Object permissionList(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> userMap1 = new HashMap<String, Object>();

		List<Permission> u = permissionService.permissionList();
		userMap1.put("info", u);
		list.add(userMap1);
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping("/selectPermission.do")
	public Object electPermission(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> userMap1 = new HashMap<String, Object>();
		String UID=req.getParameter("UID");
		Integer CUID = Integer.parseInt(UID);
		
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
}