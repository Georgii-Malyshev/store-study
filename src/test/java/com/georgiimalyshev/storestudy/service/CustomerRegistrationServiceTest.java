package com.georgiimalyshev.storestudy.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.georgiimalyshev.storestudy.dao.CustomerDao;
import com.georgiimalyshev.storestudy.domain.users.AppUser;
import com.georgiimalyshev.storestudy.domain.users.Customer;

@ExtendWith(MockitoExtension.class)
public class CustomerRegistrationServiceTest {
	private String email;
	private String password;
	boolean registrationSuccess;

	@Mock
	private CustomerDao customerDao;

	@InjectMocks
	private CustomerRegistrationService customerRegistrationService;

	private void setUpCustomerDaoMockToReturnOptionalOfAppUser(Optional<AppUser> optional) {
		when(customerDao.findByEmail(email)).thenReturn(optional);
	}

	@BeforeEach
	public void registrationSuccessReset() {
		registrationSuccess = false;
	}

	@Test
	public void givenNonOccupiedEmailAndNonEmptyPassword_whenRegisterNewCustomer_thenReturnTrue() {
		email = "email1@mail.com";
		password = "password1";

		registrationSuccess = customerRegistrationService.registerNewCustomer(email, password);

		assertTrue(registrationSuccess);
	}

	@Test
	public void givenOccupiedEmailAndNonEmptyPassword_whenRegisterNewCustomer_thenReturnFalse() {
		email = "email2@mail.com";
		password = "password1";
		AppUser customer = new Customer();
		customer.setEmail(email);
		customer.setPassword(password);
		setUpCustomerDaoMockToReturnOptionalOfAppUser(Optional.of(customer));

		registrationSuccess = customerRegistrationService.registerNewCustomer(email, password);

		assertFalse(registrationSuccess);
	}

	@Test
	public void givenNonOccupiedEmailAndEmptyPassword_whenRegisterNewCustomer_thenReturnFalse() {
		email = "email1@mail.com";
		password = "";

		registrationSuccess = customerRegistrationService.registerNewCustomer(email, password);

		assertFalse(registrationSuccess);
	}
}