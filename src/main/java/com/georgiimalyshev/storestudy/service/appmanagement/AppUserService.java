package com.georgiimalyshev.storestudy.service.appmanagement;

import java.util.HashSet;

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
	
	// TODO rename method to something more obvious
	public getAllUsersAndFetchMainInfo() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		AppUserDao appUserDao = new AppUserDao(entityManager);
		HashSet<AppUser> appUsers = appUserDao.getAllUsersAndFetchMainInfo();
		entityManager.close();
		return appUsers;
	}
	
	
}
