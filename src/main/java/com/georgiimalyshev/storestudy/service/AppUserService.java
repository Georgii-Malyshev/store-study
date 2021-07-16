package com.georgiimalyshev.storestudy.service;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.georgiimalyshev.storestudy.dao.AppUserDao;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;

@Service
public final class AppUserService {
	private EntityManagerFactory entityManagerFactory;

	public AppUserService(@Autowired EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public Set<AppUser> getAllUsers() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		AppUserDao appUserDao = new AppUserDao(entityManager);
		Set<AppUser> appUsers = appUserDao.getAllUsers();
		entityManager.close();
		return appUsers;
	}
}
