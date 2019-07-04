package gms.control.field;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import gms.entry.field.Field;
import gms.service.field.FieldService;
//import gms.service.field.FieldServiceImpl;
/*
@RequestMapping(“/field”) 标记在Controller 方法上，
表示当请求/请求方法名.do 的时候访问的是MyController 的showView 方法，
该方法返回了一个包括Model 和View 的ModelAndView 对象。

	HttpSession session = req.getSession();
*/
@RequestMapping("/field")
@Controller
public class FieldHandler {
	
	@Resource(name="FieldServiceImpl")
	private FieldService fieldService;
	
	/**
	 * @Title: addField  
	 * @Description: 新增场地 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws
	 */
	//xml配置：*.do,请求为addField时候进行添加
	//响应体以及post的跨域允许
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/addField.do")
	public String addField(HttpServletRequest request,HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView("");
//		boolean encoding  = isEncoding(request, modelAndView);
//		if(!encoding) {
//			modelAndView.setViewName("");
//			modelAndView.addObject("message","服务端错误，请稍候重试");
//			return modelAndView;
//		}
//		读取封装的request请求
		Field field = getFieldFromPostForm(request);
		fieldService.addField(field);	
		//System.out.println(field);
		
		return "Success to AddField";
		
	}
	
	/**
	 * @Title: delField  
	 * @Description: 删除场地 
	 * @param req
	 * @param resp
	 * @return String
	 * @throws
	 */
	@CrossOrigin
	@ResponseBody
	@RequestMapping("/delField.do")
	public String delField(HttpServletRequest req, HttpServletResponse resp) {
		String needToReturn;
		String fieldtemp = req.getParameter("fieldid");
		if(fieldtemp!=null&&fieldtemp.length()>0) {
			Integer fieldid = Integer.parseInt(fieldtemp);
			fieldService.delField(fieldid);
			//System.out.println(fieldid+"is Del");
			needToReturn="Success to DeleteField";
		}else {
			needToReturn="Fail to DeleteField";
		}
			
		
		return needToReturn;
		
		
	}
	/**
	 * @Title: changeField  
	 * @Description: 更改场地 
	 * @param req
	 * @param resp
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/changeField.do")
	public String changeField(HttpServletRequest req, HttpServletResponse resp) {
		//获取更改表单信息
		Field field = getFieldFromPostForm(req);
		//System.out.println(field);
		fieldService.changeField(field);
		return "Success to ChangeField";
		
	}
	
	
	/**
	 *响应请求，以JSON形式封装数据并通过接口返回到前端
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * 
	 * */

	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getAllField.do")
	public Object getAllField(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Field> allField = fieldService.getFieldAll();
		//封装json
		String jsonStr = JSON.toJSON(allField).toString();
//		System.out.println(allField);
		//get方法需要该请求头
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	/**
	 * @Title: getVisibleField  
	 * @Description: 查找场地状态，是否可以预约 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception 
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getVisibleField.do")
	public Object getVisibleField(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		//获取请求中的状态
		String fieldstate = req.getParameter("fieldState");
		List<Field> visibleList = fieldService.getFieldIfVisible(fieldstate);
		//包装成json返回
		String jsonStr = JSON.toJSON(visibleList).toString();
		return jsonStr;
		
	}
	
	/**
	 * @Title: getInOrOutSide  
	 * @Description: 获取室内外的值，需要注意问题，路由跳转以及请求内参数的设置
	 * 				  其次，要注意有多少条 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception 
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getInOrOutSide.do")
	public Object getInOrOutSide(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取请求参数
		String fieldinout = req.getParameter("fieldInOut");
		System.out.println(fieldinout);
		List<Field> inOrOut = fieldService.getFieldByInOut(fieldinout);
		//封装json
		/*
		 * 因为数据量少，通过前台获取数据长度进行分页，无需后台进行操作
		 * 
		 * */
		String jsonStr = JSON.toJSON(inOrOut).toString();
		return jsonStr;
		
	}
	
	/**
	 * @Title: getOneField  
	 * @Description: 查询一个，通过查看订单等的时候可能用到 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception 
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getOneField.do")
	public Object getOneField(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Integer fieldid = Integer.parseInt(req.getParameter("fieldid"));
		Field field = fieldService.getFieldById(fieldid);
		String jsonStr = JSON.toJSON(field).toString();
		return jsonStr;
		
	}
	
	/**
	 * @Title: getEventField  
	 * @Description: 重大赛事 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getEventField.do")
	public Object getEventField(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String fieldstate = req.getParameter("fieldState");
		List<Field> list = fieldService.getFieldIfEvent(fieldstate);
		String jsonStr = JSON.toJSON(list).toString();
		return jsonStr;
	}
	
	/**
	 * @Title: getAllwDel  
	 * @Description: 获取“删除”的场地 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getAllwDel.do")
	public Object getAllwDel(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		List<Field> allFieldwD = fieldService.getFieldAllwDel();
		//封装json
		String jsonStr = JSON.toJSON(allFieldwD).toString();
//		System.out.println(allField);
		//get方法需要该请求头
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/recycleFieldFromDel.do")
	public String recycleFieldFromDel(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String needToReturn;
		String fieldtemp = req.getParameter("fieldid");
		if(fieldtemp!=null&&fieldtemp.length()>0) {
			Integer fieldid = Integer.parseInt(fieldtemp);
			fieldService.recycleField(fieldid);
			//System.out.println(fieldid+"is Del");
			needToReturn="Success to RecycleField";
		}else {
			needToReturn="Fail to RecycleField";
		}
			
		
		return needToReturn;
	}
	
	
	private Field getFieldFromPostForm(HttpServletRequest request) {
		
		Field field = new Field();
		
		String fieldid = request.getParameter("fieldid");
		if(fieldid!=null&&fieldid.length()!=0)
			field.setFieldid(Integer.valueOf(fieldid));
		
		String fieldname = (String) request.getParameter("fieldname");
		if(fieldname!=null)
			field.setFieldname(fieldname);
		
		String fieldtype = (String) request.getParameter("fieldtype");
		if(fieldtype!=null)
			field.setFieldtype(fieldtype);
		
		String fieldinout = (String) request.getParameter("fieldinout");
		if(fieldinout!=null)
			field.setFieldinout(fieldinout);
		
		String address = (String) request.getParameter("address");
		if(address!=null)
			field.setAddress(address);
		
		String fieldstate = (String) request.getParameter("fieldstate");
		if(fieldstate!=null)
			field.setFieldstate(fieldstate);;
		
		Integer fieldlong = Integer.parseInt(request.getParameter("fieldlong"));
		if(fieldlong!=null)
			field.setFieldlong(fieldlong);
		
		Integer fieldwidth = Integer.parseInt(request.getParameter("fieldwidth"));
		if(fieldwidth!=null)
			field.setFieldwidth(fieldwidth);
		
		String fieldimg = request.getParameter("fieldimg");
		if(fieldimg!=null)
			field.setFieldimg(fieldimg);
		
		Integer unitprice = Integer.parseInt(request.getParameter("unitprice"));
		if(unitprice!=null)
			field.setUnitprice(unitprice);
		return field;
		
	}
	
	private boolean isEncoding(HttpServletRequest req, ModelAndView modelAndView) {
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
