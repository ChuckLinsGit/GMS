package gms.service.user;

import gms.DAO.user.PFunctionDao;
import gms.entry.user.PFunction;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("PFunctionService")//ע��ʽ������ͬcontrollerһ���������������ļ�ʹ��context:component-scan��ǩ
public class PFunctionServiceImpl implements PFunctionService {

	@Resource(name="PFunctionDao")
	private PFunctionDao pfunctionDao;
	

	public void setPFunction(PFunctionDao pfunctionDao) {
		this.pfunctionDao = pfunctionDao;
	}


	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public void addPFunction(PFunction pfunction) {
		pfunctionDao.insertPFunction(pfunction);
	}
	
	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public List<PFunction> pfunctionList() {
		List<PFunction> pfunction=pfunctionDao.pfunctionList();
		return pfunction;
	}
}