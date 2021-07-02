package appmanagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.AppUserDao;
import domain.users.AppUser;

public final class AuthManager {
	
	private EntityManagerFactory entityManagerFactory;
	
	public AuthManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public AppUser getAppUserByCredentials(String email, String password) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		AppUserDao appUserDao = new AppUserDao(entityManager);
		AppUser appUser = appUserDao.findByCredentials(email, password);

		entityManager.close();
		
		return appUser;
	}
}