package com.georgiimalyshev.storestudy.service.appmanagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.georgiimalyshev.storestudy.dao.AppUserDao;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;

@Service
public class RegistrationService {
	private EntityManagerFactory entityManagerFactory;

	public RegistrationService(@Autowired EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public AppUser registerNewCustomer(String email, String password) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		AppUserDao appUserDao = new AppUserDao(entityManager);
		// check if a user with this email already exists in persistent storage
		AppUser appUser = appUserDao.findByEmail(email);
		
		
		AppUser appUser;
		return appUser;
	}
}
