package gms.service.user;

import gms.DAO.user.PAPFunctionDao;
import gms.entry.user.PAPFunction;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("PAPFunctionService")//ע��ʽ������ͬcontrollerһ���������������ļ�ʹ��context:component-scan��ǩ
public class PAPFunctionServiceImpl implements PAPFunctionService {

	@Resource(name="PAPFunctionDao")
	private PAPFunctionDao pAPFunctionDao;
	

	public void setPAPFunction(PAPFunctionDao pAPFunctionDao) {
		this.pAPFunctionDao = pAPFunctionDao;
	}


	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public void addPAPFunction(PAPFunction pAPFunction) {
		pAPFunctionDao.insertPAPFunction(pAPFunction);
	}
}