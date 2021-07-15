package com.georgiimalyshev.storestudy.service.appmanagement;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.georgiimalyshev.storestudy.dao.AppUserDao;
import com.georgiimalyshev.storestudy.dao.CustomerDao;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;
import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@Service
public class RegistrationService {
	private EntityManagerFactory entityManagerFactory;

	public RegistrationService(@Autowired EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public boolean registerNewCustomer(String email, String password) {
		boolean registrationSuccess = false;
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		AppUserDao appUserDao = new AppUserDao(entityManager);
		// check if a user with such an email already exists in persistent storage
		Optional<AppUser> optionalOfAppUser = appUserDao.findByEmail(email);
		if (optionalOfAppUser.isEmpty()) {
			Customer customer = new Customer(email, password);
			EntityManager entityManagerForCustomer = entityManagerFactory.createEntityManager();
			CustomerDao customerDao = new CustomerDao(entityManagerForCustomer);
			customerDao.persist(customer);
			registrationSuccess = true;
			entityManagerForCustomer.close();
		}
		entityManager.close();
		return registrationSuccess;
	}
}