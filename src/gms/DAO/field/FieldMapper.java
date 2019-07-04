package gms.DAO.field;

import java.util.List;

import gms.entry.field.Field;

public interface FieldMapper {

	public void insertField(Field field) throws Exception;
	public void deleteField(Integer fieldid) throws Exception;
	public void updateField(Field field) throws Exception;
	
	//查找1
	public Field selectFieldById(Integer fieldid) throws Exception;
	//查找全部（未删除）
	public List<Field> selectAll() throws Exception;
	//模糊查询
//	public List<Field> selectFieldByName(String name) throws Exception;
	//查询室内、室外
	public List<Field> findFieldByInOut(String fieldinout) throws Exception;
	
	//查询是否可以预约
	public List<Field> findFieldIfVisible(String fieldstate) throws Exception;
	
	//重大赛事的场地查询
	public List<Field> findFieldIfEvent(String fieldstate) throws Exception;
	
	//查询全部
	public List<Field> selectAllwDel() throws Exception;
	
	//恢复删除场地
	public void setFCanbeUse(Integer fieldid) throws Exception;
	
	//订单：设置被租赁
	public void setRentOut(Integer fieldid) throws Exception;
	
	//订单：设为可借用
	public void setRentOver(Integer fieldid) throws Exception;
	
}
