package gms.control.field;


import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import gms.entry.field.FieldNotice;
import gms.entry.field.NoticeWithFieldname;
import gms.service.field.FieldNoticeService;

@RequestMapping("/FieldNotice")
@Controller
public class FieldNoticeHandler {

	//注入
	@Resource(name="FieldNoticeServiceImpl")
	private FieldNoticeService fieldNoticeService;
	
	/**
	 * @Title: addNotice  
	 * @Description: 新增公告 
	 * @param req
	 * @param resp
	 * @return String
	 * @throws Exception
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/addNotice.do")
	public String addNotice(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		FieldNotice fieldNotice = getFieldNoticeFromPostForm(req);
		fieldNoticeService.addFNotice(fieldNotice);

		return "Success to AddNotice";
		
	}

	/**
	 * @Title: delNotice  
	 * @Description: 删除公告 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception ModelAndView
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/delNotice.do")
	public String delNotice(HttpServletRequest req, HttpServletRequest resp) throws Exception {
		String flagDel = null;
		String noticefidStr = req.getParameter("noticefid");
		if(noticefidStr!=null&&noticefidStr.length()>0) {
			Integer noticefid = Integer.parseInt(noticefidStr);
			fieldNoticeService.delFNotice(noticefid);
			System.out.println(noticefid);
			flagDel = "Success to DelNotice";
		}else {
			flagDel = "Fail to DelNotice";
		}
		
		
		return flagDel;	
	}
	
	/**
	 * @Title: changeNotice  
	 * @Description: 修改公告 
	 * @param req
	 * @param resp
	 * @return ModelAndView
	 * @throws Exception
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/changeNotice.do")
	public String changeNotice(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		//获取更改表单信息
		FieldNotice fn = getFieldNoticeFromPostForm(req);
		//System.out.println(fn);
		fieldNoticeService.changeFNotice(fn);
		return "Success to ChangeNotice";
		
	}
	
	/**
	 * @Title: getAllNotice  
	 * @Description: 获取所有公告 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getAllNotice.do")
	public Object getAllNotice(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		List<FieldNotice> allNotice = fieldNoticeService.getAll();
		String jsonStr = JSON.toJSON(allNotice).toString();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
		
	}
	/**
	 * @Title: getNoticeDim  
	 * @Description: 模糊查询 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws Exception
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getNoticeDim.do")
	public Object getNoticeDim(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String noticename = req.getParameter("noticename");
		List<FieldNotice> noticeDim = fieldNoticeService.selectNoticeByNameDim(noticename);
		String jsonStr = JSON.toJSON(noticeDim).toString();
		return jsonStr;
	}
	
	/*
	 * 轮播使用
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getFiveNotice.do")
	public Object getFiveNotice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		List<FieldNotice> fiveNotice = fieldNoticeService.selectFive();
		String jsonStr = JSON.toJSON(fiveNotice).toString();
		return jsonStr;
	}
	
	/*
	 * 获取未被“删除数据”
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getDelAll.do")
	public Object getDelAll(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		List<NoticeWithFieldname> delAllList = fieldNoticeService.selectAllWfieldname();
		String jsonStr = JSON.toJSONString(delAllList);
		return jsonStr;
	}
	
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getDelAllwDel.do")
	public Object getDelAllwDel(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		List<NoticeWithFieldname> delAllList = fieldNoticeService.selectAllWfDeleted();
		String jsonStr = JSON.toJSONString(delAllList);
		return jsonStr;
	}
	
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/recycleNotice.do")
	public String recycleNotice(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String needToReturn;
		String temp = req.getParameter("noticefid");
		if(temp!=null&&temp.length()>0) {
			Integer noticefid = Integer.parseInt(temp);
			fieldNoticeService.recycleNoticeDel(noticefid);
			//System.out.println(fieldid+"is Del");
			needToReturn="Success to RecycleNotice";
		}else {
			needToReturn="Fail to RecycleNotice";
		}
			
		
		return needToReturn;
	}
	/**
	 *响应axios请求，json数据的封装和返回
	 *@param req
	 *@param resp
	 *@return 
	 */
	private FieldNotice getFieldNoticeFromPostForm(HttpServletRequest req) {
		FieldNotice fieldNotice = new FieldNotice();
		
		String noticefidStr = req.getParameter("noticefid");
		if(noticefidStr!=null&&noticefidStr.length()>0)
			fieldNotice.setNoticefid(Integer.valueOf(noticefidStr));
		String fieldidStr = req.getParameter("fieldid");
		if(fieldidStr!=null&&fieldidStr.length()>0)
			fieldNotice.setFieldid(Integer.parseInt(fieldidStr));
		String noticemessage = (String) req.getParameter("noticemessage");
		if(noticemessage!=null)
			fieldNotice.setNoticemessage(noticemessage);
		String noticename = (String)req.getParameter("noticename");
		if(noticename!=null)
			fieldNotice.setNoticename(noticename);
		
		return fieldNotice;
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
