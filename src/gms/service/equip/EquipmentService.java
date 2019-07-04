package gms.service.equip;

import java.util.List;

import gms.entry.equip.Equipment;
import gms.entry.equip.EquipmentBG;
import gms.entry.equip.EquipmentGM;

public interface EquipmentService {

//	public void addequipment(Equipment equip);
//	public void updateequipbyid(Equipment equipment);
//	public void deleteequipbyid(Equipment equipment);
//	public List<Equipment> finddeleteequip(Equipment equipment);
//	public void returndeleteequip(Equipment equipment);
	public List<Equipment> findallequipment();
	public List<Equipment> findequipbynameorid(Equipment equipment);
	public List<EquipmentGM> gmfindallequipment();
	public void addequipment(EquipmentGM equip);
	public void updateequipbyid(EquipmentGM equipment);
	public void gmdeleteequipbyid(EquipmentGM equipment);
	public List<EquipmentBG> bggetallequip();



}
