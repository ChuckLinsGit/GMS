package gms.entry.equip;

import java.math.BigDecimal;

public class Equipment {

	
	/*
	 * CREATE TABLE `equipment` (
		`equip_id`  int(4) UNSIGNED NOT NULL AUTO_INCREMENT ,
		`equip_name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
		`equip_value`  decimal(10,0) NOT NULL ,
		`equip_price`  decimal(10,0) NOT NULL ,
		`equip_sum`  int(10) UNSIGNED NOT NULL ,
		`equip_last`  int(10) UNSIGNED NOT NULL ,
		PRIMARY KEY (`equip_id`)
		)
	 */
	private Integer equip_id;
	private String equip_name;
	private BigDecimal equip_price;
	private Integer equip_last;
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
	public BigDecimal getEquip_price() {
		return equip_price;
	}
	public void setEquip_price(BigDecimal equip_price) {
		this.equip_price = equip_price;
	}
	public Integer getEquip_last() {
		return equip_last;
	}
	public void setEquip_last(Integer equip_last) {
		this.equip_last = equip_last;
	}
	@Override
	public String toString() {
		return "{equip_id:" + equip_id + ", equip_name:" + equip_name + ", equip_price:" + equip_price + ", equip_last:"
				+ equip_last + "}";
	}
	
}
