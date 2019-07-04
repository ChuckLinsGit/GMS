package gms.service.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.field.PayMapper;
import gms.entry.field.Pay;
import gms.entry.field.PayMent;

@Component("FieldPayServiceImpl")
public class FieldPayServiceImpl implements FieldPayService {

	//×¢Èë
	@Autowired
	private PayMapper payDAO;
	
	@Transactional
	@Override
	public void addPay(Pay pay) throws Exception {
		payDAO.insertPay(pay);
	}

	@Transactional
	@Override
	public void paidPay(Integer payid) throws Exception {
		payDAO.upstatePay(payid);
	}

	@Transactional
	@Override
	public void addPayMent(PayMent payment) throws Exception {
		payDAO.insertPayMent(payment);
	}

	@Transactional
	@Override
	public void delPay(Integer payid) throws Exception {
		payDAO.delPay(payid);
	}

}
