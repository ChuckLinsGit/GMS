package gms.service.field;

import java.util.List;

import gms.entry.field.FieldNotice;
import gms.entry.field.NoticeWithFieldname;

public interface FieldNoticeService {

	public void addFNotice(FieldNotice fn) throws Exception;
	public void delFNotice(Integer noticefid) throws Exception;
	public void changeFNotice(FieldNotice fn) throws Exception;
	
	//查找1
	public FieldNotice getFNotice(Integer noticefid) throws Exception;
	//查找全部
	public List<FieldNotice> getAll() throws Exception;
	//模糊
	public List<FieldNotice> selectNoticeByNameDim(String noticename) throws Exception;
	//查出5条未超时
	public List<FieldNotice> selectFive() throws Exception;
	
	//删除需要获得的各类数据
	public List<NoticeWithFieldname> selectAllWfieldname() throws Exception;
	//删除需要获得的各类数据
	public List<NoticeWithFieldname> selectAllWfDeleted() throws Exception;
	
	//恢复
	public void recycleNoticeDel(Integer noticefid) throws Exception;
}
