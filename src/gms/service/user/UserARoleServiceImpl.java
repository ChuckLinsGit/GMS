package gms.service.user;

import gms.DAO.user.UserARoleDao;
import gms.entry.user.UserARole;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("UserARoleService")//ע��ʽ������ͬcontrollerһ���������������ļ�ʹ��context:component-scan��ǩ
public class UserARoleServiceImpl implements UserARoleService {

	@Resource(name="userARoleDao")
	private UserARoleDao useraroleDao;
	

	public void setUserARoleDao(UserARoleDao useraroleDao) {
		this.useraroleDao = useraroleDao;
	}


	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public void addUserARole(UserARole userarole) {
		useraroleDao.insertUserARole(userarole);
	}
	
	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public Integer selectRID(Integer UID) {
		return useraroleDao.selectRID(UID);
	}
}