package com.georgiimalyshev.storestudy.service.appmanagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.georgiimalyshev.storestudy.dao.AppUserDao;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;
//TODO consider making this class a singleton
public final class AuthService {
	
	private EntityManagerFactory entityManagerFactory;
	
	public AuthService(EntityManagerFactory entityManagerFactory) {
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