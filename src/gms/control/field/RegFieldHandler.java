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

import gms.entry.field.Regulation;
import gms.service.field.RegFieldService;


@RequestMapping("/Regulation")
@Controller
public class RegFieldHandler {

	//注入,对应声明的Component
	@Resource(name="RegFieldServiceImpl")
	private RegFieldService regFieldService;
	
	/**
	 * @Title: addRegulation  
	 * @Description: 添加条例 
	 * @param req
	 * @param resp
	 * @return ModelAndView
	 * @throws Exception
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/addRegulation.do")
	public String addRegulation(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		Regulation regulation = FieldRegFrom(req);
		//System.out.println(regulation);
		regFieldService.addFieldReg(regulation);
		return "Success to AddReg";
	}
	
	/**
	 * @Title: delFieldReg  
	 * @Description: 删除公告 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception ModelAndView
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/delFieldReg.do")
	public String delFieldReg(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String tempReturn;
		String regulationidStr = req.getParameter("regulationid");
		if(regulationidStr!=null&&regulationidStr.length()>0) {
			regFieldService.delFieldReg(Integer.parseInt(regulationidStr));
			tempReturn="Success to DelReg";
		}else {
			tempReturn="Fail to DelReg";
		}
			
		return tempReturn;
		
	}
	
	/**
	 * @Title: changeFieldReg  
	 * @Description: 更改公告 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception ModelAndView
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/changeFieldReg.do")
	public String changeFieldReg(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String tempReturn;
			Regulation regulation = FieldRegFrom(req);
			//System.out.println(regulation);
			regFieldService.changeFieldReg(regulation);
			tempReturn="Success to ChangeReg";
			
		return tempReturn;
	}
	
	/**
	 * @Title: getRegByName  
	 * @Description: 通过名字搜索条例 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception Object
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getRegByNamel.do")
	public Object getRegByName(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		String regname = (String) req.getParameter("regname");
		List<Regulation> regList = regFieldService.getRegByName(regname);
		
		String jsonStr = JSON.toJSON(regList).toString();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	/**
	 * @Title: getRegById  
	 * @Description: 通过id获取一个 
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception Object
	 * @throws
	 */
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getRegById.do")
	public Object getRegById(HttpServletRequest req, HttpServletResponse resp) throws Exception{

		Integer regulationid =  Integer.parseInt(req.getParameter("regulationID"));
		Regulation reg = regFieldService.getOneRegById(regulationid);
		
		String jsonStr = JSON.toJSON(reg).toString();
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getAllReg.do")
	public Object getAllReg(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		List<Regulation> regAll = regFieldService.getAll();
		String jsonStr = JSON.toJSON(regAll).toString();
		//resp.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	//获取废弃条例
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/getAllRegD.do")
	public Object getAllRegD(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		List<Regulation> regAllD = regFieldService.getAllD();
		String jsonStr = JSON.toJSON(regAllD).toString();
		//resp.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	//回收
	@ResponseBody
	@CrossOrigin
	@RequestMapping("/recycleDelReg.do")
	public String recycleDelReg(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		String tempReturn;
		String regulationidStr = req.getParameter("regulationid");
		if(regulationidStr!=null&&regulationidStr.length()>0) {
			regFieldService.RecycleDelReg(Integer.parseInt(regulationidStr));
			tempReturn="Success to RecycleReg";
		}else {
			tempReturn="Fail to RecycleReg";
		}
			
		return tempReturn;
	}
	
	
	/**
	 *响应axios请求，json数据的封装和返回
	 *
	 *@param req
	 *@param resp
	 *@return 
	 */
	private Regulation FieldRegFrom(HttpServletRequest req) {
		Regulation regulation = new Regulation();
		
		String regulationid =req.getParameter("regulationid");
		if(regulationid!=null&&regulationid.length()>0)
			regulation.setRegulationid(Integer.parseInt(regulationid));
		
		String viodispose =req.getParameter("viodispose");
		if(viodispose!=null&&viodispose.length()>0)
		regulation.setViodispose(viodispose);
		
		String regcontent = req.getParameter("regcontent");
		if(regcontent!=null)
			regulation.setRegcontent(regcontent);
		
		String regname = req.getParameter("regname");
		if(regname!=null)
			regulation.setRegname(regname);
		return regulation;
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
