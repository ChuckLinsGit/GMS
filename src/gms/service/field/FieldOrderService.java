package gms.service.field;

import java.util.List;

import gms.entry.field.FOrderExtent;
import gms.entry.field.FieldOrder;
import gms.entry.field.MixFieldOrder;

public interface FieldOrderService {

	public void addFOrder(FieldOrder fieldOrder) throws Exception;
	public void delFOrder(Integer orderid) throws Exception;
	public void changeFOrder(FieldOrder fieldOrder) throws Exception;
	
	//ҵ���ͨ���û������id���Ҷ���
	//ģ�齻��
	public List<FieldOrder> getOrderByUsrId(Integer userid) throws Exception;
	
	//���չ���Ա���������ڳ���order
	public List<FieldOrder> getOrderAll() throws Exception;
	
	//����
	public List<FieldOrder> selectByfidAndstate(Integer fieldid) throws Exception;
	
	//��ʷ�����ѯҵ��
	public List<MixFieldOrder> getHistoryByUsrid(Integer userid) throws Exception;
	
	//֧�����������ѯҵ��
	public List<MixFieldOrder> getPaidByUsrid(Integer userid) throws Exception;
	
	//��ȡ��չ��Ϣ�����ѯ����ǰ̨��������º�̨����
	public List<FOrderExtent> getOrderEAll() throws Exception;
	
	//ͨ��userid����δ֧������
	public List<FOrderExtent> getListEAll(Integer userid) throws Exception;
	
	//userid+orderidʵ�����¸�һ��ԤԼ
	public void setForEvent(FieldOrder fieldOrder) throws Exception;
	
}
