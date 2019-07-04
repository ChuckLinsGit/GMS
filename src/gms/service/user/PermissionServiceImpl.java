package gms.service.user;

import gms.DAO.user.PermissionDao;
import gms.entry.user.Permission;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("PermissionService")//ע��ʽ������ͬcontrollerһ���������������ļ�ʹ��context:component-scan��ǩ
public class PermissionServiceImpl implements PermissionService {

	@Resource(name="permissionDao")
	private PermissionDao permissionDao;
	

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}


	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public void addPermission(Permission permission) {
		permissionDao.insertPermission(permission);
	}
	
	@Override
	/*ʹ��Ĭ�ϵ���������*/
	@Transactional
	public List<Permission> permissionList() {
		List<Permission> permission=permissionDao.permissionList();
		return permission;
	}
}