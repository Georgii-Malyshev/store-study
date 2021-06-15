package domain.auth;

import dao.UserDao;
import domain.users.User;

public class AuthenticationObject {

    public int getUserIdByCredentials(String email, String password) {
	UserDao userDao = new UserDao();
	User user = userDao.findByCredentials(email, password);
	return user.getId();
    }

    public User getUserByCredentials(String email, String password) {
	UserDao userDao = new UserDao();
	return userDao.findByCredentials(email, password);
    }
}