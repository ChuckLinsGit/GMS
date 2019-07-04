package gms.DAO.user;

import gms.entry.user.User;

import java.util.List;

public interface UserDao {
	
	void insertUser(User user);
	User login(User user);
	User check(User user);
	List<User> userList();
	User sePID(User UID);
}
