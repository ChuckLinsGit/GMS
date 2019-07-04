package gms.service.field;

import java.util.List;

import gms.entry.field.Regulation;

public interface RegFieldService {
	public void addFieldReg(Regulation reg) throws Exception;
	public void delFieldReg(Integer regulationid) throws Exception;
	public void changeFieldReg(Regulation reg) throws Exception;
	
	public Regulation getOneRegById(Integer regulationid) throws Exception;
	public List<Regulation> getRegByName(String regname) throws Exception;
	public List<Regulation> getAll() throws Exception;
	
	public void RecycleDelReg(Integer regulationid) throws Exception;
	public List<Regulation> getAllD() throws Exception;
}
