package gms.control.user;

import gms.entry.user.RoleAPermission;
import gms.service.user.RoleAPermissionService;

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


@RequestMapping("/RoleAPermission")
@Controller
public class RoleAPermissionHandler {

	@Resource(name="RoleAPermissionService")
	private RoleAPermissionService roleAPermissionService;

	public void setRoleAPermissionService(RoleAPermissionService roleAPermissionService) {
		this.roleAPermissionService = roleAPermissionService;
	}

	@ResponseBody
	@RequestMapping("/addRoleAPermission.do")
	public Object addRoleAPermission(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> roleMap1 = new HashMap<String, Object>();
		String PID = req.getParameter("PID");
		Integer CPID = Integer.parseInt(PID);
		String RID = req.getParameter("RID");
		Integer CRID = Integer.parseInt(RID);
		RoleAPermission roleAPermission=new RoleAPermission(CRID,CPID);
		if(roleAPermission!=null) {
			roleAPermissionService.addRoleAPermission(roleAPermission);
			roleMap1.put("error1", "success");
			list.add(roleMap1);
		}
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
}