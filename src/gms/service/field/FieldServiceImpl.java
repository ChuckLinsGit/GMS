package gms.service.field;

import java.util.List;

//import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.field.FieldMapper;
import gms.entry.field.Field;

@Component("FieldServiceImpl")
public class FieldServiceImpl implements FieldService {
	
//	@Resource(name="FieldMapper")
	@Autowired
	private FieldMapper fieldDAO;

	@Transactional
	@Override
	public void addField(Field field) {
		
		try {
			fieldDAO.insertField(field);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Transactional
	@Override
	public void delField(Integer fieldid) {
		try {
			fieldDAO.deleteField(fieldid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Transactional
	@Override
	public void changeField(Field field) {
		try {
			fieldDAO.updateField(field);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Transactional
	@Override
	public List<Field> getFieldByInOut(String fieldinout) throws Exception {
		
		return fieldDAO.findFieldByInOut(fieldinout);
	}

	@Transactional
	@Override
	public List<Field> getFieldAll() throws Exception {
		return fieldDAO.selectAll();
	}

	@Transactional
	@Override
	public List<Field> getFieldIfVisible(String fieldstate) throws Exception {
		
		return fieldDAO.findFieldIfVisible(fieldstate);
	}

	@Transactional
	@Override
	public Field getFieldById(Integer fieldid) throws Exception {
		return fieldDAO.selectFieldById(fieldid);
	}

	@Transactional
	@Override
	public List<Field> getFieldIfEvent(String fieldstate) throws Exception {
		return fieldDAO.findFieldIfEvent(fieldstate);
	}

	@Transactional
	@Override
	public List<Field> getFieldAllwDel() throws Exception {
		return fieldDAO.selectAllwDel();
	}
	
	@Transactional
	@Override
	public void recycleField(Integer fieldid){
		try {
			fieldDAO.setFCanbeUse(fieldid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public void setRent(Integer fieldid) throws Exception {
		fieldDAO.setRentOut(fieldid);
	}

	@Transactional
	@Override
	public void overRent(Integer fieldid) throws Exception {
		fieldDAO.setRentOver(fieldid);
	}

}
