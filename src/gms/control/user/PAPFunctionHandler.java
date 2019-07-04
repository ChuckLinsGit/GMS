package gms.control.user;

import gms.entry.user.PAPFunction;
import gms.service.user.PAPFunctionService;

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

@RequestMapping("/PAPFunction")
@Controller
public class PAPFunctionHandler {

	@Resource(name="PAPFunctionService")
	private PAPFunctionService pAPFunctionService;

	public void setPAPFunctionService(PAPFunctionService pAPFunctionService) {
		this.pAPFunctionService = pAPFunctionService;
	}

	@ResponseBody
	@RequestMapping("/addPAPFunction.do")
	public Object addPAPFunction(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> roleMap1 = new HashMap<String, Object>();
		String PFID = req.getParameter("PFID");
		Integer CPFID = Integer.parseInt(PFID);
		String PID = req.getParameter("PID");
		Integer CPID = Integer.parseInt(PID);
		PAPFunction pAPFunction=new PAPFunction(CPID,CPFID);
		if(pAPFunction!=null) {
			pAPFunctionService.addPAPFunction(pAPFunction);
			roleMap1.put("error1", "success");
			list.add(roleMap1);
		}
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
}