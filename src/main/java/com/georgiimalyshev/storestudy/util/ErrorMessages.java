package com.georgiimalyshev.storestudy.util;

public class ErrorMessages {
	public static String notLoggedInErrorMessage = "could not access shopping cart: no customer was found in session; "
			+ "not logged in or session expired?";
	public static String notACustomerErrorMessage = "could not access shopping cart: "
			+ "logged in as some type of user other than customer; "
			+ "log in using a customer account to be able to access a shopping cart";
}
