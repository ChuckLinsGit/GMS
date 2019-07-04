package gms.service.field;

import java.util.List;

import gms.entry.field.FOrderExtent;
import gms.entry.field.FieldOrder;
import gms.entry.field.MixFieldOrder;

public interface FieldOrderService {

	public void addFOrder(FieldOrder fieldOrder) throws Exception;
	public void delFOrder(Integer orderid) throws Exception;
	public void changeFOrder(FieldOrder fieldOrder) throws Exception;
	
	//业务层通过用户本身的id查找订单
	//模块交互
	public List<FieldOrder> getOrderByUsrId(Integer userid) throws Exception;
	
	//接收管理员操作，用于撤销order
	public List<FieldOrder> getOrderAll() throws Exception;
	
	//赛事
	public List<FieldOrder> selectByfidAndstate(Integer fieldid) throws Exception;
	
	//历史连表查询业务
	public List<MixFieldOrder> getHistoryByUsrid(Integer userid) throws Exception;
	
	//支付订单连表查询业务
	public List<MixFieldOrder> getPaidByUsrid(Integer userid) throws Exception;
	
	//获取扩展信息连表查询减轻前台多次请求导致后台负担
	public List<FOrderExtent> getOrderEAll() throws Exception;
	
	//通过userid查找未支付订单
	public List<FOrderExtent> getListEAll(Integer userid) throws Exception;
	
	//userid+orderid实现赛事高一级预约
	public void setForEvent(FieldOrder fieldOrder) throws Exception;
	
}
