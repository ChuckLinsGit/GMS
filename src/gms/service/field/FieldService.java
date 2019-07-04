package gms.service.field;

import java.util.List;

import gms.entry.field.Field;

public interface FieldService {
	public void addField(Field field);
	public void delField(Integer fieldid);
	public void changeField(Field field);
	
	public Field getFieldById(Integer fieldid) throws Exception;
	public List<Field> getFieldByInOut(String fieldinout) throws Exception; 
	public List<Field> getFieldAll() throws Exception;
	public List<Field> getFieldIfVisible(String fieldstate) throws Exception;
	public List<Field> getFieldIfEvent(String fieldstate) throws Exception;
	
	public List<Field> getFieldAllwDel() throws Exception;
	
	public void recycleField(Integer fieldid) throws Exception;
	
	public void setRent(Integer fieldid) throws Exception;
	public void overRent(Integer fieldid) throws Exception;
	

}
