package gms.service.user;

import gms.DAO.user.UserDao;
import gms.entry.user.User;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("UserService")//ע��ʽ������ͬcontrollerһ���������������ļ�ʹ��context:component-scan��ǩ
public class UserServiceImpl implements UserService {

	/*@Autowired
	@Qualifier("StudentDao")*/
	
	//ע��ʽ����ʱ��dao��ע������MapperScannerConfigurer�Զ�������������������������Ҵ˴���������Ȼ����
	@Resource(name="userDao")
	private UserDao userDao;
	

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public void addUser(User user) {
		userDao.insertUser(user);
	}
	
	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public User login(User user) {
		User user2=userDao.login(user);
		return user2;
	}
	
	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public User check(User user) {
		User user2=userDao.check(user);
		return user2;
	}
	
	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public List<User> userList() {
		return userDao.userList();
	}
	
	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public User sePID(User UID) {
		return userDao.sePID(UID);
	}
}