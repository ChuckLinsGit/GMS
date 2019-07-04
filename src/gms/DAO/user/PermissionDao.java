package gms.DAO.user;

import gms.entry.user.Permission;

import java.util.List;


public interface PermissionDao {
	void insertPermission(Permission permission);
	List<Permission> permissionList();
}