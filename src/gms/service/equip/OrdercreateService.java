package gms.service.equip;

import java.util.List;

import gms.entry.equip.Ordersdetail;

public interface OrdercreateService {

	public Integer ceateorders(Ordersdetail ordersdetail);

	public String onrentorbook(Integer orders_id);

	public String gmcheckequip(Ordersdetail ordersdetail);


/*	public List<Integer> bguserseeifmassage(Integer user_id);

	public void bguserseemassage(Integer user_id);
*/
	public void systemcheck();

	public List<Integer> bgcreateorder(Ordersdetail order);

}
