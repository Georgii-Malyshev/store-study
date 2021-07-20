package com.georgiimalyshev.storestudy.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.georgiimalyshev.storestudy.dao.AppUserDao;

@ExtendWith(MockitoExtension.class)
public class AppUserServiceTest {

	private String email;
	private String password;

	@Mock
	private AppUserDao appUserDao;

	@InjectMocks
	private AppUserService appUserService;

	@Test
	public void givenCorrectCredentialsWhenFindAppUserByCredentialsThenReturnOptionalOfAppUserWithThoseCredentials() {
		email = "correct_email@mail.com";
		password = "correct_password1";

	}
}