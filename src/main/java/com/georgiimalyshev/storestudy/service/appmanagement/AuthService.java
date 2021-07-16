package com.georgiimalyshev.storestudy.service.appmanagement;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.georgiimalyshev.storestudy.dao.AppUserDao;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;

@Service
public final class AuthService {
	private EntityManagerFactory entityManagerFactory;
	
	public AuthService(@Autowired EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public Optional<AppUser> findAppUserByCredentials(String email, String password) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		AppUserDao appUserDao = new AppUserDao(entityManager);
		Optional<AppUser> optionalOfAppUser = appUserDao.findByCredentials(email, password);
		entityManager.close();
		// TODO move login logic here from controller layer
		return optionalOfAppUser;
	}
}