package com.georgiimalyshev.storestudy.service;

import org.junit.Test;

public class AppUserServiceTest {

	private String email;
	private String password;
	
	@Test
	public void givenCorrectCredentialsWhenFindAppUserByCredentialsThenReturnOptionalOfAppUserWithThoseCredentials() {
		email = "correct_email@mail.com";
		password = "correct_password1";
		
	}
}
