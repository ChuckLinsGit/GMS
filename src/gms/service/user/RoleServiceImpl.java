package gms.service.user;

import gms.DAO.user.RoleDao;
import gms.entry.user.Role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("RoleService")
public class RoleServiceImpl implements RoleService {

	@Resource(name="roleDao")
	private RoleDao roleDao;
	

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}


	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public void addRole(Role role) {
		roleDao.insertRole(role);
	}
	
	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public List<Role> roleList() {
		List<Role> role=roleDao.roleList();
		return role;
	}
}