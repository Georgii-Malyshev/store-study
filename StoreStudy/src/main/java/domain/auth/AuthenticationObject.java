package domain.auth;

import dao.UserDao;
import domain.users.AppUser;

public class AuthenticationObject {

    public int getUserIdByCredentials(String email, String password) {
	UserDao userDao = new UserDao();
	AppUser user = userDao.findByCredentials(email, password);
	return user.getId();
    }

    public AppUser getUserByCredentials(String email, String password) {
	UserDao userDao = new UserDao();
	return userDao.findByCredentials(email, password);
    }
}