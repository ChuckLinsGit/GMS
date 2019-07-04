package gms.control.user;

import gms.entry.user.Role;
import gms.service.user.RoleService;

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


@RequestMapping("/Role")
@Controller
public class RoleHandler {

	@Resource(name="RoleService")
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@ResponseBody
	@RequestMapping("/addRole.do")
	public Object addRole(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> roleMap1 = new HashMap<String, Object>();
		String RType = req.getParameter("RType");
		String RName = req.getParameter("RName");
		String CRName = new String(RName.getBytes("ISO8859-1"), "utf-8");
		String CRType = new String(RType.getBytes("ISO8859-1"), "utf-8");
		Role role=new Role(CRType,CRName);
		if(role!=null) {
			roleService.addRole(role);
			roleMap1.put("error1", "success");
			list.add(roleMap1);
		}
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping("/RoleList.do")
	public Object roleList(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> userMap1 = new HashMap<String, Object>();

		List<Role> u = roleService.roleList();
		userMap1.put("info", u);
		list.add(userMap1);
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
}