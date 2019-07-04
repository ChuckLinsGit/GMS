package gms.control.test;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;

@RequestMapping("/api")
@Controller
public class TestDemoHandler {
	@ResponseBody
	@RequestMapping(value="/check.do")
	@CrossOrigin
	public Object check(HttpServletRequest req, HttpServletResponse resp) {
		Map<Integer,Object> map1 = new HashMap<Integer,Object>();
		String str = "ÐÖµÜ";
			map1.put(1, str);
			map1.put(2, "b");
			map1.put(3, "c");
		
		List<Object> list = new ArrayList<Object>();
		list.add(map1);
		String jsonStr = JSON.toJSON(list).toString();
		System.out.println(jsonStr);
		System.out.println(req.getParameter("name")+req.getParameter("pass"));
//		resp.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
}
