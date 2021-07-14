package com.georgiimalyshev.storestudy.service.domain.users;
// TODO consider making specific interfaces for Customer, Admin etc. and coding to them (SRP principle)
public interface AppUser {
    public int getId();

    public void setId(int id);
    
    void setEmail(String email);

	String getEmail();

    public String getPassword();

    public void setPassword(String password);
}