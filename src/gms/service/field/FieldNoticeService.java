package gms.service.field;

import java.util.List;

import gms.entry.field.FieldNotice;
import gms.entry.field.NoticeWithFieldname;

public interface FieldNoticeService {

	public void addFNotice(FieldNotice fn) throws Exception;
	public void delFNotice(Integer noticefid) throws Exception;
	public void changeFNotice(FieldNotice fn) throws Exception;
	
	//����1
	public FieldNotice getFNotice(Integer noticefid) throws Exception;
	//����ȫ��
	public List<FieldNotice> getAll() throws Exception;
	//ģ��
	public List<FieldNotice> selectNoticeByNameDim(String noticename) throws Exception;
	//���5��δ��ʱ
	public List<FieldNotice> selectFive() throws Exception;
	
	//ɾ����Ҫ��õĸ�������
	public List<NoticeWithFieldname> selectAllWfieldname() throws Exception;
	//ɾ����Ҫ��õĸ�������
	public List<NoticeWithFieldname> selectAllWfDeleted() throws Exception;
	
	//�ָ�
	public void recycleNoticeDel(Integer noticefid) throws Exception;
}
