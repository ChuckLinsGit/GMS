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
@RequestMapping(��/field��) �����Controller �����ϣ�
��ʾ������/���󷽷���.do ��ʱ����ʵ���MyController ��showView ������
�÷���������һ������Model ��View ��ModelAndView ����

	HttpSession session = req.getSession();
*/
@RequestMapping("/field")
@Controller
public class FieldHandler {
	
	@Resource(name="FieldServiceImpl")
	private FieldService fieldService;
	
	/**
	 * @Title: addField  
	 * @Description: �������� 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws
	 */
	//xml���ã�*.do,����ΪaddFieldʱ��������
	//��Ӧ���Լ�post�Ŀ�������
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/addField.do")
	public String addField(HttpServletRequest request,HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView("");
//		boolean encoding  = isEncoding(request, modelAndView);
//		if(!encoding) {
//			modelAndView.setViewName("");
//			modelAndView.addObject("message","����˴������Ժ�����");
//			return modelAndView;
//		}
//		��ȡ��װ��request����
		Field field = getFieldFromPostForm(request);
		fieldService.addField(field);	
		//System.out.println(field);
		
		return "Success to AddField";
		
	}
	
	/**
	 * @Title: delField  
	 * @Description: ɾ������ 
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
	 * @Description: ���ĳ��� 
	 * @param req
	 * @param resp
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/changeField.do")
	public String changeField(HttpServletRequest req, HttpServletResponse resp) {
		//��ȡ���ı���Ϣ
		Field field = getFieldFromPostForm(req);
		//System.out.println(field);
		fieldService.changeField(field);
		return "Success to ChangeField";
		
	}
	
	
	/**
	 *��Ӧ������JSON��ʽ��װ���ݲ�ͨ���ӿڷ��ص�ǰ��
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
		//��װjson
		String jsonStr = JSON.toJSON(allField).toString();
//		System.out.println(allField);
		//get������Ҫ������ͷ
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	/**
	 * @Title: getVisibleField  
	 * @Description: ���ҳ���״̬���Ƿ����ԤԼ 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception 
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getVisibleField.do")
	public Object getVisibleField(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		//��ȡ�����е�״̬
		String fieldstate = req.getParameter("fieldState");
		List<Field> visibleList = fieldService.getFieldIfVisible(fieldstate);
		//��װ��json����
		String jsonStr = JSON.toJSON(visibleList).toString();
		return jsonStr;
		
	}
	
	/**
	 * @Title: getInOrOutSide  
	 * @Description: ��ȡ�������ֵ����Ҫע�����⣬·����ת�Լ������ڲ���������
	 * 				  ��Σ�Ҫע���ж����� 
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
		//��ȡ�������
		String fieldinout = req.getParameter("fieldInOut");
		System.out.println(fieldinout);
		List<Field> inOrOut = fieldService.getFieldByInOut(fieldinout);
		//��װjson
		/*
		 * ��Ϊ�������٣�ͨ��ǰ̨��ȡ���ݳ��Ƚ��з�ҳ�������̨���в���
		 * 
		 * */
		String jsonStr = JSON.toJSON(inOrOut).toString();
		return jsonStr;
		
	}
	
	/**
	 * @Title: getOneField  
	 * @Description: ��ѯһ����ͨ���鿴�����ȵ�ʱ������õ� 
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
	 * @Description: �ش����� 
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
	 * @Description: ��ȡ��ɾ�����ĳ��� 
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
		//��װjson
		String jsonStr = JSON.toJSON(allFieldwD).toString();
//		System.out.println(allField);
		//get������Ҫ������ͷ
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
