package gms.DAO.field;

import java.util.List;

import gms.entry.field.FOrderExtent;
import gms.entry.field.FieldOrder;
import gms.entry.field.MixFieldOrder;

public interface FieldOrderMapper {

	public void insertOrder(FieldOrder fieldOrder) throws Exception;
	public void deleteOrder(Integer orderid) throws Exception;
	public void updateOrder(FieldOrder fieldOrder) throws Exception;
	
	//特定更改（通过userid和orderid，因为是自增，所以是唯一值）
	public void updatefieldForEvent(FieldOrder fieldOrder) throws Exception;
	
	//Id查找一个
	public FieldOrder selectOrderById(Integer orderid) throws Exception;
	//查找用户本身的ID信息
	public List<FieldOrder> selectOrderByUserId(Integer userid) throws Exception;
	//查找全部,管理员操作，用去撤销order
	public List<FieldOrder> selectAll() throws Exception;
	//高级赛事预约包括撤销场地在内的查询
	public List<FieldOrder> selectByfidAndstate(Integer fieldid) throws Exception;
	
	//通过id查询已经过时的订单
	public List<MixFieldOrder> selectOrderByUidOverT(Integer userid) throws Exception;
	
	//连表所有查询预约中场地
	public List<FOrderExtent> selectEAll() throws Exception;
	//查询已经支付过的场地（仍在预约）
	public List<MixFieldOrder> selectPaidOrderWithUsr(Integer userid) throws Exception;
	//查询所有和特定userid有关的未支付场地预约
	public List<FOrderExtent> selectEAllByUsr(Integer userid) throws Exception;


	

}
