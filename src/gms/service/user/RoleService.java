package gms.service.user;

import gms.entry.user.Role;

import java.util.List;


public interface RoleService {

	void addRole(Role role);
	List<Role> roleList();
}