package com.georgiimalyshev.storestudy.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.georgiimalyshev.storestudy.dao.AppUserDao;

public class AppUserServiceTest {

	private String email;
	private String password;

	@Mock
	private AppUserDao appUserDao;

	@InjectMocks
	private AppUserService appUserService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenCorrectCredentialsWhenFindAppUserByCredentialsThenReturnOptionalOfAppUserWithThoseCredentials() {
		email = "correct_email@mail.com";
		password = "correct_password1";

	}
}