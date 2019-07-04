package gms.service.field;

import gms.entry.field.Pay;

import gms.entry.field.PayMent;

public interface FieldPayService {

	public void addPay(Pay pay) throws Exception;
	public void paidPay(Integer payid) throws Exception;
	public void addPayMent(PayMent payment) throws Exception;
	public void delPay(Integer payid) throws Exception;
}
