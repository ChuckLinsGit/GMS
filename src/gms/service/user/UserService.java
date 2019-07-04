package gms.service.user;

import gms.entry.user.User;

import java.util.List;



public interface UserService {

	void addUser(User user);
	User login(User user);
	User check(User user);
	List<User> userList();
	User sePID(User UID);
}