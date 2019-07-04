package gms.service.field;

import java.util.List;

//import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.field.FieldOrderMapper;
import gms.entry.field.FOrderExtent;
import gms.entry.field.FieldOrder;
import gms.entry.field.MixFieldOrder;

@Component("FieldOrderServiceImpl")
public class FieldOrderServiceImpl implements FieldOrderService {

//	@Resource(name="FieldOrderMapper")
	@Autowired
	private FieldOrderMapper fieldOrderDAO;
	
	@Transactional
	@Override
	public void addFOrder(FieldOrder fieldOrder) throws Exception {
		fieldOrderDAO.insertOrder(fieldOrder);
	}

	@Transactional
	@Override
	public void delFOrder(Integer orderid) throws Exception {
		fieldOrderDAO.deleteOrder(orderid);
		
	}

	@Transactional
	@Override
	public void changeFOrder(FieldOrder fieldOrder) throws Exception {
		fieldOrderDAO.updateOrder(fieldOrder);
		
	}

	@Transactional
	@Override
	public List<FieldOrder> getOrderByUsrId(Integer userid) throws Exception {
		
		return fieldOrderDAO.selectOrderByUserId(userid);
	}

	@Transactional
	@Override
	public List<FieldOrder> getOrderAll() throws Exception {
		
		return fieldOrderDAO.selectAll();
	}

	@Transactional
	@Override
	public List<FieldOrder> selectByfidAndstate(Integer fieldid) throws Exception {
		return fieldOrderDAO.selectByfidAndstate(fieldid);
	}

	@Transactional
	@Override
	public List<MixFieldOrder> getHistoryByUsrid(Integer userid) throws Exception {
		
		return fieldOrderDAO.selectOrderByUidOverT(userid);
	}

	@Transactional
	@Override
	public List<FOrderExtent> getOrderEAll() throws Exception {
		return fieldOrderDAO.selectEAll();
	}

	@Transactional
	@Override
	public List<MixFieldOrder> getPaidByUsrid(Integer userid) throws Exception {
		return fieldOrderDAO.selectPaidOrderWithUsr(userid);
	}

	@Transactional
	@Override
	public List<FOrderExtent> getListEAll(Integer userid) throws Exception {
		return fieldOrderDAO.selectEAllByUsr(userid);
	}

	@Transactional
	@Override
	public void setForEvent(FieldOrder fieldOrder) throws Exception {
		fieldOrderDAO.updatefieldForEvent(fieldOrder);
	}

}
