package gms.DAO.user;

import gms.entry.user.UserARole;


public interface UserARoleDao {
	void insertUserARole(UserARole useraole);
	Integer selectRID(Integer UID);
}