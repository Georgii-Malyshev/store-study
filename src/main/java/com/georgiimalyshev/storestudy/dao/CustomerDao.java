package com.georgiimalyshev.storestudy.dao;

import javax.persistence.EntityManager;

import com.georgiimalyshev.storestudy.service.domain.users.Customer;

public class CustomerDao extends AppUserDao {
	public CustomerDao(EntityManager entityManager) {
		super(entityManager);
	}

	public void persist(Customer customer) {
		beginTransaction();
		entityManager.persist(customer);
		commitTransaction();
	}
}