package gms.DAO.equip;

import java.util.List;

import gms.entry.equip.Equipment;
import gms.entry.equip.EquipmentBG;
import gms.entry.equip.EquipmentGM;
import gms.entry.equip.Ordersdetail;

public interface IEquipmentDAO {

	public List<Equipment> findallequipment();//查询所有器材(用户）+
	public void rentequip(Ordersdetail ordersdetail);//租借器材修改器材余量和租借数量+
	public void bookequip(Ordersdetail ordersdetail);//预约器材修改器材余量和预约数量+
	public void nodobook(Ordersdetail ordersdetail);//取消预约+
	public List<Equipment> findequipbynameorid(Equipment equipment);//根据器材ID或名称模糊查询+
	
	public List<EquipmentBG> bggetallequip();//重大赛事申请×
	public EquipmentBG bggetequipbyid(Integer equip_id);//重大赛事根据id查询器材×
	
	public void gmaddequipment(EquipmentGM equipment);//添加一个器材（管理员）*
	public void gmupdateequipbyid(EquipmentGM equipment);//修改一个器材（管理员）*
	public void gmdeleteequipbyid(Integer equip_id);//按照ID删除器材*
	public List<EquipmentGM> gmfinddeleteequip();//查看删除器材*
	public void gmreturndeleteequip(Integer equip_id);//按照ID恢复删除器材*
	public void gmreturnequip(Ordersdetail ordersdetail);//正常器材归还（管理员）*
	public void gmreturndamageequip(Ordersdetail ordersdetail);//损坏器材归还（管理员）*
	public List<EquipmentGM> gmfindallequipment();//管理员查看所有器材
	public List<EquipmentGM> gmfindequipbynameorid(EquipmentGM equipment);//管理员根据器材ID或名称模糊查询*
}
