package gms.service.user;

import gms.entry.user.Permission;

import java.util.List;


public interface PermissionService {

	void addPermission(Permission permission);
	List<Permission> permissionList();
}