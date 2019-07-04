package gms.control.equip;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

@RequestMapping("/test")
@Controller
public class TestHandler {

	@ResponseBody
	@RequestMapping("/Testjson.do")
	public String Testjson(
			HttpServletRequest request,HttpServletResponse response) {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> userMap1 = new HashMap<String, Object>();
		userMap1.put("conpany", "河南电力");
		userMap1.put("section", "郑州分公司");
		userMap1.put("admin", "123123");
		userMap1.put("name", "张华凤");
		userMap1.put("tel", "954127004");
		userMap1.put("phone", "15056993012");
		list.add(userMap1);
		String rs = JSON.toJSON(list).toString();
		System.out.println(rs);
/*		byte[] jsonStr = JSON.toJSON(list).toString().getBytes();
		String rs=null;
		try {
			rs = new String(jsonStr,"UTF-8");
			System.out.println(rs);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		/*response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");*/
		response.setHeader("Access-Control-Allow-Origin", "*");
		/*response.setContentType("text/html;charset=UTF-8");*/
		return rs;
	}
	
	@ResponseBody
	@RequestMapping("/Testmodelandview.do")
	public String Testmodelandview(
			HttpServletRequest request,HttpServletResponse response) {
		String jsonStr = JSON.toJSON("finish").toString();
		String username = (String) request.getParameter("username");
//		Integer password = Integer.valueOf( request.getParameter("password"));
		Integer password = Integer.parseInt(request.getParameter("password"));
		System.out.println("用户名："+username+"密码："+password);
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping("/Testsreach.do")
	public String Testsreach(
			HttpServletRequest request,HttpServletResponse response) {
		String jsonStr = JSON.toJSON("finish").toString();
		Integer password = null;
		String username = null;
		System.out.println((Pattern.compile("[0-9]*")).matcher(String.valueOf(request.getParameter("password"))).matches()==true);
		if((Pattern.compile("[0-9]*")).matcher(String.valueOf(request.getParameter("password"))).matches()==true){
		password = Integer.parseInt(request.getParameter("password"));
		}else {
		username = (String) request.getParameter("username");
		}
		System.out.println("用户名："+username+"密码："+password);
		return jsonStr;
	}
}
