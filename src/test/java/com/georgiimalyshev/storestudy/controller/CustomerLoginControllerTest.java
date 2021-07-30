package com.georgiimalyshev.storestudy.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.georgiimalyshev.storestudy.service.AppUserService;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;
import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@ExtendWith(MockitoExtension.class)
public class CustomerLoginControllerTest {
	@Mock
	private AppUserService appUserService;
	
	@Mock
	private Model model;
	
	@InjectMocks
	private CustomerLoginController customerLoginController;
	// TODO unit tests for CustomerLoginController
	@Test
	public void givenCorrectCredentials_whenLogin_thenAddCorrespondingCustomerAsModelAttributeAndRedirectToHome() {
		String email = "correctemail@mail.com";
		String password = "correctpassword"; 
		String resultString = "";
		Customer correspondingCustomer = new Customer(email, password);
		Optional<AppUser> optionalOfAppUser = Optional.of(correspondingCustomer);
		// TODO should test case when service throws an exception 
		when(appUserService.findAppUserByCredentials(email, password)).thenReturn(optionalOfAppUser);
		
		resultString = customerLoginController.login(model, email, password);
		
		verify(model, times(1)).addAttribute("user", correspondingCustomer);
		assertEquals("redirect:home", resultString);
	}
}