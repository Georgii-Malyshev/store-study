package com.georgiimalyshev.storestudy.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.georgiimalyshev.storestudy.dao.AppUserDao;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;
import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@ExtendWith(MockitoExtension.class)
public class AppUserServiceTest {
	private String email;
	private String password;
	private AppUser customer1 = new Customer();

	@Mock
	private AppUserDao appUserDao;

	@InjectMocks
	private AppUserService appUserService;

	// TODO write test cases for other classes implementing AppUser as well

	@BeforeEach
	public void customerSetUp() {
		email = "email1@mail.com";
		password = "password1";
		customer1.setId(1);
		customer1.setEmail(email);
		customer1.setPassword(password);
	}

	@Test
	public void givenCorrectCredentials_WhenFindAppUserByCredentials_ThenReturnOptionalOfCorrespondingAppUser() {
		email = "email1@mail.com";
		password = "password1";

		Optional<AppUser> optionalOfCustomer1 = Optional.of(customer1);
		when(appUserDao.findByCredentials(email, password)).thenReturn(optionalOfCustomer1);
		Optional<AppUser> optionalOfAppUser = appUserService.findAppUserByCredentials(email, password);

		assertAll(() -> assertEquals(1, optionalOfAppUser.get().getId()),
				() -> assertEquals("email1@mail.com", optionalOfAppUser.get().getEmail()),
				() -> assertEquals("password1", optionalOfAppUser.get().getPassword()));
	}

	@Test
	public void givenEmptyCredentials_WhenFindAppUserByCredentials_ThenReturnEmptyOptional() {
		email = "";
		password = "";

		when(appUserDao.findByCredentials(email, password)).thenReturn(Optional.empty());
		Optional<AppUser> optionalOfAppUser = appUserService.findAppUserByCredentials(email, password);

		assertTrue(optionalOfAppUser.isEmpty());
	}
}