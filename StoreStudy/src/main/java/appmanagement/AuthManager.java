package appmanagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.CustomerDao;
import domain.users.AppUser;

public final class AuthManager {
	
	private EntityManagerFactory entityManagerFactory;
	
	public AuthManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public AppUser getAppUserByCredentials(String email, String password) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// TODO change this to AppUserDao
		CustomerDao customerDao = new CustomerDao(entityManager);
		AppUser appUser = customerDao.findByCredentials(email, password);

		entityManager.close();
		
		return appUser;
	}
}
