package gms.DAO.field;

import java.util.List;

import gms.entry.field.FieldNotice;
import gms.entry.field.NoticeWithFieldname;


public interface FieldNoticeMapper {

	public void insertNotice(FieldNotice fn) throws Exception;
	public void deleteNotice(Integer noticefid) throws Exception;
	public void updateNotice(FieldNotice fn) throws Exception;
	
	//ģ����ѯ
	public List<FieldNotice> selectNoticeByNameDim(String noticename) throws Exception;
	
	//����1
	public FieldNotice selectNoticeById(Integer noticefid) throws Exception;
	//����ȫ��
	public List<FieldNotice> selectAll() throws Exception;
	
	//����ǰ����
	public List<FieldNotice> selectFive() throws Exception;
	
	public List<NoticeWithFieldname> selectAllWfieldname() throws Exception;
	
	public List<NoticeWithFieldname> selectAllWfDeleted() throws Exception;
	
	public void recycleNotice(Integer noticefid) throws Exception;
}
