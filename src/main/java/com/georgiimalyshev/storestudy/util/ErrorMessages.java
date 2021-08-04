package com.georgiimalyshev.storestudy.util;

public class ErrorMessages {
	public static String notLoggedInErrorMessage = "could not access requested resource: no customer was found in session; "
			+ "not logged in or session expired?";
	public static String notACustomerErrorMessage = "could not access requested resource: "
			+ "logged in as some type of user other than customer; "
			+ "log in using a customer account to be able to access requested resource";
	public static String userNotFoundByCredentials = "authentication failed; wrong email or password?";
}
