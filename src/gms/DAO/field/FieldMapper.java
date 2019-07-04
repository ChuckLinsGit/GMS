package gms.DAO.field;

import java.util.List;

import gms.entry.field.Field;

public interface FieldMapper {

	public void insertField(Field field) throws Exception;
	public void deleteField(Integer fieldid) throws Exception;
	public void updateField(Field field) throws Exception;
	
	//����1
	public Field selectFieldById(Integer fieldid) throws Exception;
	//����ȫ����δɾ����
	public List<Field> selectAll() throws Exception;
	//ģ����ѯ
//	public List<Field> selectFieldByName(String name) throws Exception;
	//��ѯ���ڡ�����
	public List<Field> findFieldByInOut(String fieldinout) throws Exception;
	
	//��ѯ�Ƿ����ԤԼ
	public List<Field> findFieldIfVisible(String fieldstate) throws Exception;
	
	//�ش����µĳ��ز�ѯ
	public List<Field> findFieldIfEvent(String fieldstate) throws Exception;
	
	//��ѯȫ��
	public List<Field> selectAllwDel() throws Exception;
	
	//�ָ�ɾ������
	public void setFCanbeUse(Integer fieldid) throws Exception;
	
	//���������ñ�����
	public void setRentOut(Integer fieldid) throws Exception;
	
	//��������Ϊ�ɽ���
	public void setRentOver(Integer fieldid) throws Exception;
	
}
