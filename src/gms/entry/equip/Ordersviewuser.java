package gms.entry.equip;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class Ordersviewuser {

	/*orders.orders_id,
	equipment.equip_id,
	equipment.equip_name,
	equipment.equip_price,
	ordersdetail.equip_num,
	ordersdetail.equip_backtime,
	ordersdetail.equip_renttime,
	`user`.user_name,
	`user`.user_id*/
	private Integer orders_id;
	private Integer equip_id;
	private String equip_name;
	private BigDecimal equip_price;
	private Integer equip_num;
	private Timestamp orders_renttime;
	private Timestamp orders_backtime;
	private Integer user_id;
	private String user_name;
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
	public Integer getEquip_num() {
		return equip_num;
	}
	public void setEquip_num(Integer equip_num) {
		this.equip_num = equip_num;
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getOrders_state() {
		return orders_state;
	}
	public void setOrders_state(Integer orders_state) {
		this.orders_state = orders_state;
	}
	@Override
	public String toString() {
		return "{orders_id:" + orders_id + ", equip_id:" + equip_id + ", equip_name:" + equip_name + ", equip_price:"
				+ equip_price + ", equip_num:" + equip_num + ", orders_renttime:" + orders_renttime
				+ ", orders_backtime:" + orders_backtime + ", user_id:" + user_id + ", user_name:" + user_name
				+ ", orders_state:" + orders_state + "}";
	}
}
