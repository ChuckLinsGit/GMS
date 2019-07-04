package gms.DAO.field;

import gms.entry.field.Pay;
import gms.entry.field.PayMent;

public interface PayMapper {

	//增加订单
	public void insertPay(Pay pay) throws Exception;
	
	//修改订单支付状态
	public void upstatePay(Integer payid) throws Exception;
	//增加金额表
	public void insertPayMent(PayMent payment) throws Exception;
	
	//删除订单（实际是更改订单的状态）
	public void delPay(Integer payid) throws Exception;
}
