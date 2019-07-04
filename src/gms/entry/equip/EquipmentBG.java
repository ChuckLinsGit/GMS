package gms.entry.equip;

public class EquipmentBG {

	private Integer equip_id;
	private String equip_name;
	private Integer equip_last;
	private Integer equip_book;
	public Integer getEquip_id() {
		return equip_id;
	}
	public void setEquip_id(Integer equip_id) {
		this.equip_id = equip_id;
	}
	public String getEquip_name() {
		return equip_name;
	}
	public void setEquip_name(String equip_name) {
		this.equip_name = equip_name;
	}
	public Integer getEquip_last() {
		return equip_last;
	}
	public void setEquip_last(Integer equip_last) {
		this.equip_last = equip_last;
	}
	public Integer getEquip_book() {
		return equip_book;
	}
	public void setEquip_book(Integer equip_book) {
		this.equip_book = equip_book;
	}
	@Override
	public String toString() {
		return "{equip_id:" + equip_id + ", equip_name:" + equip_name + ", equip_last:" + equip_last + ", equip_book:"
				+ equip_book + "}";
	}
	
}
