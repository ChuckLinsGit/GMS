package gms.control.user;

import gms.entry.user.User;
import gms.service.user.UserService;

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


@RequestMapping("/User")
@Controller
public class UserHandler {

	@Resource(name="UserService")
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@ResponseBody
	@RequestMapping("/register.do")
	public Object register(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> userMap1 = new HashMap<String, Object>();
		String UPassword = req.getParameter("UPassword");
		String UAccountID = req.getParameter("UAccountID");
		String UName = req.getParameter("UName");
		String UPhone = req.getParameter("UPhone");
		String UEmail = req.getParameter("UEmail");
		String CUPassword = new String(UPassword.getBytes("ISO8859-1"), "utf-8");
		String CUAccountID = new String(UAccountID.getBytes("ISO8859-1"), "utf-8");
		String CUName = new String(UName.getBytes("ISO8859-1"), "utf-8");
		String CUPhone = new String(UPhone.getBytes("ISO8859-1"), "utf-8");
		String CUEmail = new String(UEmail.getBytes("ISO8859-1"), "utf-8");

		User user1=new User(CUName,CUAccountID,CUPassword,CUEmail,CUPhone);
		User user2=new User(UAccountID,UEmail,UPhone);
		
		if(user1!=null) {
			if(userService.check(user2)==null){
				userService.addUser(user1);
			}else{
				if(user2.getUAccountID()!=null && user2.getUAccountID()==UAccountID){
					userMap1.put("error1", "�˺��Ѵ���");
				}
                if(user2.getUPhone()!=null && user2.getUPhone()==UPhone){
					userMap1.put("error2", "������������Ѿ�ע�����");
				}
                if(user2.getUEmail()!=null && user2.getUEmail()==UEmail){
					userMap1.put("error3", "����ֻ������Ѿ�ע�����");
				}
				list.add(userMap1);
				String jsonStr = JSON.toJSON(list).toString();
				response.setHeader("Access-Control-Allow-Origin", "*");					
				return jsonStr;
			}
		}
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping("/login.do")
	public Object login(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> userMap1 = new HashMap<String, Object>();
		String UPassword = req.getParameter("UPassword");
		String UAccountID = req.getParameter("UAccountID");
		User user = new User(UAccountID,UPassword);
		User u = userService.login(user);
		if(u !=null){
			userMap1.put("UID", u.getUID());
			userMap1.put("UName",u.getUName());
			list.add(userMap1);
			String jsonStr = JSON.toJSON(list).toString();
			response.setHeader("Access-Control-Allow-Origin", "*");					
			return jsonStr;
		}
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");	
		return jsonStr;
	}

	@ResponseBody
	@RequestMapping("/UserList.do")
	public Object userList(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> userMap1 = new HashMap<String, Object>();

		List<User> user = userService.userList();
		System.out.println(user.get(0));
		userMap1.put("info", user);
		list.add(userMap1);
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
	
	@ResponseBody
	@RequestMapping("/sePID.do")
	public Object sePID(HttpServletRequest req,HttpServletResponse response) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> userMap1 = new HashMap<String, Object>();
		String UID = req.getParameter("UID");
		Integer CUID = Integer.parseInt(UID);
		User u =new User(CUID);
		User user = userService.sePID(u);
		System.out.println(user);
		userMap1.put("PID", user);
		list.add(userMap1);
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");					
		return jsonStr;
	}
}