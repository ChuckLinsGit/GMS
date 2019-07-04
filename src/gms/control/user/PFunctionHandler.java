package gms.control.user;

import gms.entry.user.PFunction;
import gms.service.user.PFunctionService;

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


@RequestMapping("/PFunction")
@Controller
public class PFunctionHandler {

	@Resource(name="PFunctionService")
	private PFunctionService pfunctionService;

	public void setPFunctionService(PFunctionService pfunctionService) {
		this.pfunctionService = pfunctionService;
	}

	@ResponseBody
	@RequestMapping("/addPFunction.do")
	public Object addPFunction(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> roleMap1 = new HashMap<String, Object>();
		String PFName = req.getParameter("PFName");
		String PFDetails = req.getParameter("PFDetails");
		String CPFName = new String(PFName.getBytes("ISO8859-1"), "utf-8");
		String CPFDetails = new String(PFDetails.getBytes("ISO8859-1"), "utf-8");
		PFunction pfunction=new PFunction(CPFName,CPFDetails);
		if(pfunction!=null) {
			pfunctionService.addPFunction(pfunction);
			roleMap1.put("error1", "success");
			list.add(roleMap1);
		}
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping("/PFunctionList.do")
	public Object pfunctionList(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> userMap1 = new HashMap<String, Object>();

		List<PFunction> u = pfunctionService.pfunctionList();
		userMap1.put("info", u);
		list.add(userMap1);
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
}