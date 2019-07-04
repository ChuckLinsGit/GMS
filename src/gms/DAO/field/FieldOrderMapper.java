package gms.DAO.field;

import java.util.List;

import gms.entry.field.FOrderExtent;
import gms.entry.field.FieldOrder;
import gms.entry.field.MixFieldOrder;

public interface FieldOrderMapper {

	public void insertOrder(FieldOrder fieldOrder) throws Exception;
	public void deleteOrder(Integer orderid) throws Exception;
	public void updateOrder(FieldOrder fieldOrder) throws Exception;
	
	//�ض����ģ�ͨ��userid��orderid����Ϊ��������������Ψһֵ��
	public void updatefieldForEvent(FieldOrder fieldOrder) throws Exception;
	
	//Id����һ��
	public FieldOrder selectOrderById(Integer orderid) throws Exception;
	//�����û������ID��Ϣ
	public List<FieldOrder> selectOrderByUserId(Integer userid) throws Exception;
	//����ȫ��,����Ա��������ȥ����order
	public List<FieldOrder> selectAll() throws Exception;
	//�߼�����ԤԼ���������������ڵĲ�ѯ
	public List<FieldOrder> selectByfidAndstate(Integer fieldid) throws Exception;
	
	//ͨ��id��ѯ�Ѿ���ʱ�Ķ���
	public List<MixFieldOrder> selectOrderByUidOverT(Integer userid) throws Exception;
	
	//�������в�ѯԤԼ�г���
	public List<FOrderExtent> selectEAll() throws Exception;
	//��ѯ�Ѿ�֧�����ĳ��أ�����ԤԼ��
	public List<MixFieldOrder> selectPaidOrderWithUsr(Integer userid) throws Exception;
	//��ѯ���к��ض�userid�йص�δ֧������ԤԼ
	public List<FOrderExtent> selectEAllByUsr(Integer userid) throws Exception;


	

}
