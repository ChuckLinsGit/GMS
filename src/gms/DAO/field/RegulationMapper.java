package gms.DAO.field;

import java.util.List;

import gms.entry.field.Regulation;

public interface RegulationMapper {

	public void insertRegulation(Regulation regulation) throws Exception;
	public void deleteRegulation(Integer regulationid) throws Exception;
	public void updateRegulation(Regulation regulation) throws Exception;
	
	//����,ģ����ѯ,�����������ݲ�ѯ
	//ǰ�˻��Ǻ�̨������
	public List<Regulation> selectRegulationByName(String regname) throws Exception;
	//����ȫ��
	public List<Regulation> selectAll() throws Exception;
	
	public Regulation selectRegulationById(Integer regulationid) throws Exception;
	
	//����
	public void recycleReg(Integer reglationid) throws Exception;
	
	//����ɾ��
	public List<Regulation> selectAllDel() throws Exception;
	
}
