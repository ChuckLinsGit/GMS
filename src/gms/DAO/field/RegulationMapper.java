package gms.DAO.field;

import java.util.List;

import gms.entry.field.Regulation;

public interface RegulationMapper {

	public void insertRegulation(Regulation regulation) throws Exception;
	public void deleteRegulation(Integer regulationid) throws Exception;
	public void updateRegulation(Regulation regulation) throws Exception;
	
	//查找,模糊查询,根据条例内容查询
	//前端还是后台操作？
	public List<Regulation> selectRegulationByName(String regname) throws Exception;
	//查找全部
	public List<Regulation> selectAll() throws Exception;
	
	public Regulation selectRegulationById(Integer regulationid) throws Exception;
	
	//回收
	public void recycleReg(Integer reglationid) throws Exception;
	
	//查找删除
	public List<Regulation> selectAllDel() throws Exception;
	
}
