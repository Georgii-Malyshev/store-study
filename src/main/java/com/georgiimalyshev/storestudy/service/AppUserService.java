package com.georgiimalyshev.storestudy.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.georgiimalyshev.storestudy.dao.AppUserDao;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;

@Service
public class AppUserService {
	private AppUserDao appUserDao;
	
	public AppUserService(AppUserDao appUserDao) {
		this.appUserDao = appUserDao;
	}
	
	public Optional<AppUser> findAppUserByCredentials(String email, String password) {
		Optional<AppUser> optionalOfAppUser = appUserDao.findByCredentials(email, password);
		return optionalOfAppUser;
	}

	public Set<AppUser> getAllUsers() {
		Set<AppUser> appUsers = appUserDao.getAllUsers();
		return appUsers;
	}
}