package gms.service.user;

import gms.DAO.user.RoleAPermissionDao;
import gms.entry.user.RoleAPermission;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("RoleAPermissionService")//ע��ʽ������ͬcontrollerһ���������������ļ�ʹ��context:component-scan��ǩ
public class RoleAPermissionServiceImpl implements RoleAPermissionService {

	@Resource(name="roleAPermissionDao")
	private RoleAPermissionDao roleAPermissionDao;
	

	public void setRoleAPermissionDao(RoleAPermissionDao roleAPermissionDao) {
		this.roleAPermissionDao = roleAPermissionDao;
	}


	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public void addRoleAPermission(RoleAPermission roleAPermission) {
		roleAPermissionDao.insertRoleAPermission(roleAPermission);
	}
}