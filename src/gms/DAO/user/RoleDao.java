package gms.DAO.user;

import gms.entry.user.Role;

import java.util.List;


public interface RoleDao {
	void insertRole(Role role);
	List<Role> roleList();
}
