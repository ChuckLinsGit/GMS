package gms.DAO.field;

import gms.entry.field.Pay;
import gms.entry.field.PayMent;

public interface PayMapper {

	//���Ӷ���
	public void insertPay(Pay pay) throws Exception;
	
	//�޸Ķ���֧��״̬
	public void upstatePay(Integer payid) throws Exception;
	//���ӽ���
	public void insertPayMent(PayMent payment) throws Exception;
	
	//ɾ��������ʵ���Ǹ��Ķ�����״̬��
	public void delPay(Integer payid) throws Exception;
}
