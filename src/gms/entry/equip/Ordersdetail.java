package gms.entry.equip;

import java.sql.Timestamp;
import java.util.Date;

public class Ordersdetail {


	/*
	 `orders_id`  int(8) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT ,
	`equip_id`  int(4) UNSIGNED NULL DEFAULT NULL ,
	`user_id`  int(8) UNSIGNED NULL DEFAULT NULL ,
	`orders_renttime`  datetime NOT NULL ,
	`orders_backtime`  datetime NOT NULL ,
	`equip_num`  int(2) NOT NULL ,
	`orders_createtime`  datetime NOT NULL ,
	`orders_state`  int(1) UNSIGNED NOT NULL ,
	 */
	private Integer orders_id;
	private Integer equip_id;
	private Integer user_id;
	private Timestamp orders_renttime;
	private Timestamp orders_backtime;
	private Integer equip_num;
	private Timestamp orders_createtime;
	private Integer orders_state;
	public Integer getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(Integer orders_id) {
		this.orders_id = orders_id;
	}
	public Integer getEquip_id() {
		return equip_id;
	}
	public void setEquip_id(Integer equip_id) {
		this.equip_id = equip_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Timestamp getOrders_renttime() {
		return orders_renttime;
	}
	public void setOrders_renttime(Timestamp orders_renttime) {
		this.orders_renttime = orders_renttime;
	}
	public Timestamp getOrders_backtime() {
		return orders_backtime;
	}
	public void setOrders_backtime(Timestamp orders_backtime) {
		this.orders_backtime = orders_backtime;
	}
	public Integer getEquip_num() {
		return equip_num;
	}
	public void setEquip_num(Integer equip_num) {
		this.equip_num = equip_num;
	}
	public Timestamp getOrders_createtime() {
		return orders_createtime;
	}
	public void setOrders_createtime(Timestamp orders_createtime) {
		this.orders_createtime = orders_createtime;
	}
	public Integer getOrders_state() {
		return orders_state;
	}
	public void setOrders_state(Integer orders_state) {
		this.orders_state = orders_state;
	}
	@Override
	public String toString() {
		return "{orders_id:" + orders_id + ", equip_id:" + equip_id + ", user_id:" + user_id + ", orders_renttime:"
				+ orders_renttime + ", orders_backtime:" + orders_backtime + ", equip_num:" + equip_num
				+ ", orders_createtime:" + orders_createtime + ", orders_state:" + orders_state + "}";
	}
}
