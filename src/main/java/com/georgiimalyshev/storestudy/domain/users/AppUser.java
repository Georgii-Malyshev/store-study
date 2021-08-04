package com.georgiimalyshev.storestudy.domain.users;

public interface AppUser {
	public int getId();

	public void setId(int id);

	public String getEmail();

	public void setEmail(String email);

	public String getPassword();

	public void setPassword(String password);
}