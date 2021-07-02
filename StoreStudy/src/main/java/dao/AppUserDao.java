package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.users.AppUser;
import domain.users.AppUserAbstract;

public class AppUserDao {
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public AppUserDao(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.entityTransaction = this.entityManager.getTransaction();
	}

	private void beginTransaction() {
		try {
			entityTransaction.begin();
		} catch (IllegalStateException exception) {
			rollbackTransaction();
		}
	}

	private void commitTransaction() {
		try {
			entityTransaction.begin();
		} catch (IllegalStateException exception) {
			rollbackTransaction();
		}
	}

	private void rollbackTransaction() {
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