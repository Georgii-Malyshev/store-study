package com.georgiimalyshev.storestudy.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.georgiimalyshev.storestudy.dao.CartDao;
import com.georgiimalyshev.storestudy.dao.CustomerDao;
import com.georgiimalyshev.storestudy.domain.store.Cart;
import com.georgiimalyshev.storestudy.domain.users.AppUser;
import com.georgiimalyshev.storestudy.domain.users.Customer;

@Service
public class CustomerRegistrationService {
	private CustomerDao customerDao;
	private CartDao cartDao;

	public CustomerRegistrationService(CustomerDao customerDao, CartDao cartDao) {
		this.customerDao = customerDao;
		this.cartDao = cartDao;
	}
	
	public Optional<AppUser> findAppUserByEmail(String email) {
		return customerDao.findByEmail(email);
	}

	@Transactional
	public boolean registerNewCustomer(String email, String password) {
		boolean registrationSuccess = false;
		if (!(password.isBlank())) {
			Optional<AppUser> optionalOfAppUser = this.findAppUserByEmail(email);
			if (optionalOfAppUser.isEmpty()) {
				Customer customer = new Customer(email, password);
				customerDao.persist(customer);
				Cart cart = new Cart();
				cart.setCustomer(customer);
				cartDao.persist(cart);
				registrationSuccess = true;
			}
		}
		return registrationSuccess;
	}
}