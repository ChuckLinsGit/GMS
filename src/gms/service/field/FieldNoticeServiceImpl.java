package gms.service.field;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.field.FieldNoticeMapper;
import gms.entry.field.FieldNotice;
import gms.entry.field.NoticeWithFieldname;

@Component("FieldNoticeServiceImpl")
public class FieldNoticeServiceImpl implements FieldNoticeService {

	@Autowired
	private FieldNoticeMapper fieldNoticeDAO;
	
	@Transactional
	@Override
	public void addFNotice(FieldNotice fn) throws Exception {
		fieldNoticeDAO.insertNotice(fn);
	}

	@Transactional
	@Override
	public void delFNotice(Integer noticefid) throws Exception {
		fieldNoticeDAO.deleteNotice(noticefid);
		
	}

	@Transactional
	@Override
	public void changeFNotice(FieldNotice fn) throws Exception {
		fieldNoticeDAO.updateNotice(fn);
		
	}

	@Transactional
	@Override
	public FieldNotice getFNotice(Integer noticefid) throws Exception {
		return fieldNoticeDAO.selectNoticeById(noticefid);
	}

	@Transactional
	@Override
	public List<FieldNotice> getAll() throws Exception {
		return fieldNoticeDAO.selectAll();
	}

	@Transactional
	@Override
	public List<FieldNotice> selectNoticeByNameDim(String noticename) throws Exception {
		return fieldNoticeDAO.selectNoticeByNameDim(noticename);
	}

	@Transactional
	@Override
	public List<FieldNotice> selectFive() throws Exception {
		
		return fieldNoticeDAO.selectFive();
	}

	@Transactional
	@Override
	public List<NoticeWithFieldname> selectAllWfieldname() throws Exception {
		return fieldNoticeDAO.selectAllWfieldname();
	}

	@Transactional
	@Override
	public List<NoticeWithFieldname> selectAllWfDeleted() throws Exception {
		return fieldNoticeDAO.selectAllWfDeleted();
	}

	@Transactional
	@Override
	public void recycleNoticeDel(Integer noticefid) throws Exception {
		fieldNoticeDAO.recycleNotice(noticefid);
	}

}
