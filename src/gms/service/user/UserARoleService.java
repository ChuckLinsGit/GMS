package gms.service.user;

import gms.entry.user.UserARole;


public interface UserARoleService {

	void addUserARole(UserARole userarole);
	Integer selectRID(Integer UID);
}