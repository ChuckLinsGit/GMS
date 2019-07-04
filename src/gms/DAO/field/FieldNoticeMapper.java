package gms.DAO.field;

import java.util.List;

import gms.entry.field.FieldNotice;
import gms.entry.field.NoticeWithFieldname;


public interface FieldNoticeMapper {

	public void insertNotice(FieldNotice fn) throws Exception;
	public void deleteNotice(Integer noticefid) throws Exception;
	public void updateNotice(FieldNotice fn) throws Exception;
	
	//模糊查询
	public List<FieldNotice> selectNoticeByNameDim(String noticename) throws Exception;
	
	//查找1
	public FieldNotice selectNoticeById(Integer noticefid) throws Exception;
	//查找全部
	public List<FieldNotice> selectAll() throws Exception;
	
	//公告前五条
	public List<FieldNotice> selectFive() throws Exception;
	
	public List<NoticeWithFieldname> selectAllWfieldname() throws Exception;
	
	public List<NoticeWithFieldname> selectAllWfDeleted() throws Exception;
	
	public void recycleNotice(Integer noticefid) throws Exception;
}
