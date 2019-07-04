package gms.service.field;

import java.util.List;

//import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import gms.DAO.field.RegulationMapper;
import gms.entry.field.Regulation;

@Component("RegFieldServiceImpl")
public class RegFieldServiceImpl implements RegFieldService {

//	@Resource(name="RegulationMapper")
	@Autowired
	private RegulationMapper regulationDAO;
	
	@Transactional
	@Override
	public void addFieldReg(Regulation reg) throws Exception {
		regulationDAO.insertRegulation(reg);
	}

	@Transactional
	@Override
	public void delFieldReg(Integer regulationid) throws Exception {
		regulationDAO.deleteRegulation(regulationid);
	}

	@Transactional
	@Override
	public void changeFieldReg(Regulation reg) throws Exception {
		regulationDAO.updateRegulation(reg);
	}

	@Transactional
	@Override
	public List<Regulation> getRegByName(String regname) throws Exception {
		return regulationDAO.selectRegulationByName(regname);
	}

	@Transactional
	@Override
	public List<Regulation> getAll() throws Exception {
		return regulationDAO.selectAll();
	}

	@Transactional
	@Override
	public Regulation getOneRegById(Integer regulationid) throws Exception {
		return regulationDAO.selectRegulationById(regulationid);
	}




	@Transactional
	@Override
	public List<Regulation> getAllD() throws Exception {
		return regulationDAO.selectAllDel();
	}

	
	@Transactional
	@Override
	public void RecycleDelReg(Integer regulationid) throws Exception {
		regulationDAO.recycleReg(regulationid);
	}

}
