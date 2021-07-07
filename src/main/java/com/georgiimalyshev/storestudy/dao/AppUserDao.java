package com.georgiimalyshev.storestudy.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.georgiimalyshev.storestudy.service.domain.users.AppUser;
import com.georgiimalyshev.storestudy.service.domain.users.AppUserAbstract;

public class AppUserDao {
	protected EntityManager entityManager;
	protected EntityTransaction entityTransaction;

	public AppUserDao(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.entityTransaction = this.entityManager.getTransaction();
	}

	protected void beginTransaction() {
		try {
			entityTransaction.begin();
		} catch (IllegalStateException exception) {
			rollbackTransaction();
		}
	}

	protected void commitTransaction() {
		try {
			entityTransaction.begin();
		} catch (IllegalStateException exception) {
			rollbackTransaction();
		}
	}

	protected void rollbackTransaction() {
		try {
			entityTransaction.rollback();
		} catch (IllegalStateException exception) {
			exception.printStackTrace();
		}
	}

	public AppUser findById(int id) {
		beginTransaction();
		AppUser appUser = entityManager.find(AppUserAbstract.class, id);
		commitTransaction();
		return appUser;
	}

	public AppUser findByCredentials(String email, String password) {
		beginTransaction();
		AppUser appUser = entityManager
				.createQuery(
						"select u from AppUserAbstract u where u.email = :email and u.password = :password",
						AppUserAbstract.class)
				.setParameter("email", email).setParameter("password", password).getSingleResult();
		commitTransaction();
		return appUser;
	}
}