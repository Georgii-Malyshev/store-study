package com.georgiimalyshev.storestudy.service.domain.users;

public interface AppUser {
    public int getId();

    public void setId(int id);
    
    public String getEmail();
    
    public void setEmail(String email);

    public String getPassword();

    public void setPassword(String password);
}