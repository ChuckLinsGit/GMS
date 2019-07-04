package gms.control.equip;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import gms.entry.equip.Equipment;
import gms.entry.equip.EquipmentBG;
import gms.entry.equip.EquipmentGM;
import gms.service.equip.EquipmentService;
/**
 * @Title:EquipmentHandler.java
 * @author:耶路·马伦
 * @Description:用户对于器材列表的操作都在这里
 * @date:2019年6月25日
 */
@RequestMapping("/equipment")
@Controller
public class EquipmentHandler {

	@Resource(name="EquipmentImpl")
	private EquipmentService equipservice;
	
	//用户查询所有器材*
	@ResponseBody
	@RequestMapping("/findallequipment.do")
	public String findallequipment(
			HttpServletRequest request,HttpServletResponse response) {
		List<Equipment> list = new ArrayList<Equipment>();
		list = equipservice.findallequipment();
		System.out.println(list);
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	//通过id或器材名字查找器材*
	@ResponseBody
	@RequestMapping("/findequipbyidorname.do")
	public Object findequipbyidorname(
			HttpServletRequest request,HttpServletResponse response){
		Integer equip_id = 0;
		String equip_name = "";
		Equipment equipment = new Equipment();
		List<Equipment> list = new ArrayList<Equipment>();
//		System.out.println((Pattern.compile("[0-9]*")).matcher(String.valueOf(request.getParameter("equip_id"))).matches()==true);
		if((Pattern.compile("[0-9]*")).matcher(String.valueOf(request.getParameter("equip_id"))).matches()==true){
			equip_id = Integer.parseInt(request.getParameter("equip_id"));
			equipment.setEquip_id(equip_id);
			System.out.println(equip_id);
		}else {
			equip_name = (String) request.getParameter("equip_name");
			equipment.setEquip_name(equip_name);
			System.out.println(equip_name);		
		}
		list = equipservice.findequipbynameorid(equipment);
		System.out.println(list);
		String jsonStr = JSON.toJSON(list).toString();
		response.setHeader("Access-Control-Allow-Origin", "*");
		return jsonStr;
	}
	
	//管理员获取器材列表
	@ResponseBody
	@RequestMapping("/gmfindallequipment.do")
	public String gmfindallequipment(
			HttpServletRequest request,HttpServletResponse response) {
		List<EquipmentGM> list = new ArrayList<EquipmentGM>();
		list = equipservice.gmfindallequipment();
		String jsonStr = JSON.toJSON(list).toString();
		return jsonStr;	
	}
	
	//管理员增加器材
	@ResponseBody
	@RequestMapping("/gmaddequipment.do")
	public String gmaddequipment(
			HttpServletRequest request,HttpServletResponse response) {
		EquipmentGM equip = new EquipmentGM();
		String eq_name = request.getParameter(("equip_name"));
		equip.setEquip_name(eq_name);
		BigDecimal eq_price = new BigDecimal(request.getParameter("equip_price"));
		equip.setEquip_price(eq_price);
		BigDecimal eq_value = new BigDecimal(request.getParameter("equip_value"));
		equip.setEquip_value(eq_value);
		Integer eq_sum = Integer.parseInt(request.getParameter("equip_sum"));
		equip.setEquip_sum(eq_sum);
//		equip = gmgetEquipmentFromRquest(request);
		equipservice.addequipment(equip);
		return "添加成功！";
	}
	
	//管理员修改器材
	@ResponseBody
	@RequestMapping("/gmupdateequipment.do")
	public String gmupdateequipment(
			HttpServletRequest request,HttpServletResponse response) {
		/*EquipmentGM equip = new EquipmentGM();
		equip = this.gmgetEquipmentFromRquest(request);*/
		EquipmentGM equip = gmgetEquipmentFromRquest(request);
		Integer eq_id = Integer.parseInt(request.getParameter("equip_id"));
		System.out.println(eq_id);
		System.out.println(equip);
		equipservice.updateequipbyid(equip);
		return "修改成功";	
	}
	
	//管理员删除器材
	@ResponseBody
	@RequestMapping("/gmdeleteequipment.do")
	public String gmdeleteequipment(
			HttpServletRequest request,HttpServletResponse response) {
		EquipmentGM equip = gmgetEquipmentFromRquest(request);
		equipservice.gmdeleteequipbyid(equip);
		return "删除成功";
	}
	
	//重大赛事获取器材列表
	@ResponseBody
	@RequestMapping("/bggetallequip.do")
	public String bggetallequip(
			HttpServletRequest request,HttpServletResponse response) {
		List<EquipmentBG> list = new ArrayList<EquipmentBG>();
		list = equipservice.bggetallequip();
		String jsonStr = JSON.toJSON(list).toString();
		return jsonStr;
	}
	
	public Equipment getEquipmentFromRquest(HttpServletRequest request) {
		Equipment equip = new Equipment();
		
		Integer eq_id = Integer.parseInt(request.getParameter("equip_id"));
		if(eq_id != null) {
			equip.setEquip_id(eq_id);
		}
		
		String eq_name = request.getParameter(("equip_name"));
		if(eq_name != null) {
			equip.setEquip_name(eq_name);
		}
		
		
		BigDecimal eq_price = new BigDecimal(request.getParameter("equip_price"));
		if(eq_price != null) {
			equip.setEquip_price(eq_price);
		}
		
		Integer eq_last = Integer.parseInt(request.getParameter("equip_last"));
		if(eq_last != null) {
			equip.setEquip_id(eq_last);
		}
		return equip;
	}
	
	public EquipmentGM gmgetEquipmentFromRquest(HttpServletRequest request) {
		EquipmentGM equip = new EquipmentGM();
		Integer eq_id = Integer.parseInt(request.getParameter("equip_id"));
		if(eq_id != null) {
			equip.setEquip_id(eq_id);
		}
		
		String eq_name = request.getParameter("equip_name");
		if(eq_name != null) {
			equip.setEquip_name(eq_name);
		}
		
		BigDecimal eq_value = new BigDecimal(request.getParameter("equip_value"));
		if(eq_value != null) {
			equip.setEquip_value(eq_value);
		}
		
		BigDecimal eq_price = new BigDecimal(request.getParameter("equip_price"));
		if(eq_price != null) {
			equip.setEquip_price(eq_price);
		}
		
		Integer eq_sum = Integer.parseInt(request.getParameter("equip_sum"));
		if(eq_sum != null) {
			equip.setEquip_sum(eq_sum);
		}
		
		Integer eq_last = Integer.parseInt(request.getParameter("equip_last"));
		if(eq_last != null) {
			equip.setEquip_last(eq_last);
		}
		
		Integer eq_state = Integer.parseInt(request.getParameter("equip_state"));
		if(eq_state != null) {
			equip.setEquip_state(eq_state);
		}
		
		Integer eq_rent = Integer.parseInt(request.getParameter("equip_rent"));
		if(eq_rent != null) {
			equip.setEquip_rent(eq_rent);
		}
		
		Integer eq_book = Integer.parseInt(request.getParameter("equip_book"));
		if(eq_book != null) {
			equip.setEquip_book(eq_book);
		}
		
		Integer eq_damage = Integer.parseInt(request.getParameter("equip_damage"));
		if(eq_damage != null) {
			equip.setEquip_damage(eq_damage);
		}
		System.out.println(equip);
		return equip;
	}
}
