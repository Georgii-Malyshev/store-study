package com.georgiimalyshev.storestudy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.georgiimalyshev.storestudy.domain.users.AppUser;
import com.georgiimalyshev.storestudy.domain.users.Customer;
import com.georgiimalyshev.storestudy.service.AppUserService;

@ExtendWith(MockitoExtension.class)
public class CustomerLoginControllerTest {
	@Mock
	private AppUserService appUserService;

	@Mock
	private HttpSession httpSession;

	@Mock
	private Model model;

	@InjectMocks
	private CustomerLoginController customerLoginController;

	@Test
	public void givenCorrectCredentials_whenLogin_thenSetCorrespondingCustomerAsSessionAttributeAndRedirectToHome() {
		String email = "correctemail@mail.com";
		String password = "correctpassword";
		String resultString = "";
		Customer correspondingCustomer = new Customer(email, password);
		Optional<AppUser> optionalOfAppUser = Optional.of(correspondingCustomer);
		// TODO should test case when service throws an exception
		when(appUserService.findAppUserByCredentials(email, password)).thenReturn(optionalOfAppUser);

		resultString = customerLoginController.login(httpSession, email, password, model);

		verify(httpSession, times(1)).setAttribute("user", correspondingCustomer);
		assertEquals("redirect:home", resultString);
	}
}