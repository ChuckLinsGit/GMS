package gms.service.equip;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.equip.IEquipmentDAO;
import gms.entry.equip.Equipment;
import gms.entry.equip.EquipmentBG;
import gms.entry.equip.EquipmentGM;

@Component("EquipmentImpl")
public class EquipmentImpl implements EquipmentService {
	
	
	@Resource(name="IEquipmentDAO")
	private IEquipmentDAO equipdao;
	
	
	
	//查找所有器材
	@Transactional
	@Override
	public List<Equipment> findallequipment(){
		List<Equipment> list = new ArrayList<Equipment>();
		list = equipdao.findallequipment();
		return list;
	}
	
	//通过名称或id查询器材
	@Transactional
	@Override
	public List<Equipment> findequipbynameorid(Equipment equipment){
		List<Equipment> list = new ArrayList<Equipment>();
		list = equipdao.findequipbynameorid(equipment);
		return list;	
	}
	
	//管理员查找所有器材
	@Transactional
	@Override
	public List<EquipmentGM> gmfindallequipment(){
		List<EquipmentGM> list = new ArrayList<EquipmentGM>();
		list = equipdao.gmfindallequipment();
		return list;
	}
		
	//添加一个器材
	@Transactional
	@Override
	public void addequipment(EquipmentGM equip) {
		equipdao.gmaddequipment(equip);
	};
		
	//根据id修改器材
	@Transactional
	@Override
	public void updateequipbyid(EquipmentGM equipment) {
		equipdao.gmupdateequipbyid(equipment);
	}
	
	//删除器材（并不是真正的消失）
	@Transactional
	@Override
	public void gmdeleteequipbyid(EquipmentGM equipment) {
		equipdao.gmdeleteequipbyid(equipment.getEquip_id());
	}
	
	@Transactional
	@Override
	public List<EquipmentBG> bggetallequip(){
		List<EquipmentBG> list = new ArrayList<EquipmentBG>();
		list = equipdao.bggetallequip();
		return list;
	}
	
	//查看删除器材
	/*@Transactional
	@Override
	public List<Equipment> finddeleteequip(Equipment equipment){
		List<Equipment> list = new ArrayList<Equipment>(); 
		list = equipdao.finddeleteequip();
		return list;		
	}*/
	
	//恢复删除器材
	/*@Transactional
	@Override
	public void returndeleteequip(Equipment equipment) {
		equipdao.returndeleteequip(equipment.getEquip_id());
	}*/
	
}
