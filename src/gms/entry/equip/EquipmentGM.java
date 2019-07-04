package gms.entry.equip;

import java.math.BigDecimal;

public class EquipmentGM {

	
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
	private BigDecimal equip_value;
	private BigDecimal equip_price;
	private Integer equip_sum;
	private Integer equip_last;
	private Integer equip_state;
	private Integer equip_book;
	private Integer equip_rent;
	private Integer equip_damage;
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
	public BigDecimal getEquip_value() {
		return equip_value;
	}
	public void setEquip_value(BigDecimal equip_value) {
		this.equip_value = equip_value;
	}
	public BigDecimal getEquip_price() {
		return equip_price;
	}
	public void setEquip_price(BigDecimal equip_price) {
		this.equip_price = equip_price;
	}
	public Integer getEquip_sum() {
		return equip_sum;
	}
	public void setEquip_sum(Integer equip_sum) {
		this.equip_sum = equip_sum;
	}
	public Integer getEquip_last() {
		return equip_last;
	}
	public void setEquip_last(Integer equip_last) {
		this.equip_last = equip_last;
	}
	public Integer getEquip_state() {
		return equip_state;
	}
	public void setEquip_state(Integer equip_state) {
		this.equip_state = equip_state;
	}
	public Integer getEquip_book() {
		return equip_book;
	}
	public void setEquip_book(Integer equip_book) {
		this.equip_book = equip_book;
	}
	public Integer getEquip_rent() {
		return equip_rent;
	}
	public void setEquip_rent(Integer equip_rent) {
		this.equip_rent = equip_rent;
	}
	public Integer getEquip_damage() {
		return equip_damage;
	}
	public void setEquip_damage(Integer equip_damage) {
		this.equip_damage = equip_damage;
	}
	@Override
	public String toString() {
		return "{equip_id:" + equip_id + ", equip_name:" + equip_name + ", equip_value:" + equip_value
				+ ", equip_price:" + equip_price + ", equip_sum:" + equip_sum + ", equip_last:" + equip_last
				+ ", equip_state:" + equip_state + ", equip_book:" + equip_book + ", equip_rent:" + equip_rent
				+ ", equip_damage:" + equip_damage + "}";
	}
}
