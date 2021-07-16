package com.georgiimalyshev.storestudy.dao;

import org.springframework.stereotype.Repository;

import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@Repository
public class CustomerDao extends AppUserDao {
	public void persist(Customer customer) {
		entityManager.persist(customer);
	}
}