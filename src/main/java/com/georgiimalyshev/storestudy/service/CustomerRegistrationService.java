package com.georgiimalyshev.storestudy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.georgiimalyshev.storestudy.dao.CustomerDao;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;
import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@Service
public class CustomerRegistrationService {
	private CustomerDao customerDao;
	
	public CustomerRegistrationService(@Autowired CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	public boolean registerNewCustomer(String email, String password) {
		boolean registrationSuccess = false;
		if (!(password.isBlank())) {
			Optional<AppUser> optionalOfAppUser = customerDao.findByEmail(email);
			if (optionalOfAppUser.isEmpty()) {
				Customer customer = new Customer(email, password);
				customerDao.persist(customer);
				registrationSuccess = true;
			}
		}
		return registrationSuccess;
	}
}