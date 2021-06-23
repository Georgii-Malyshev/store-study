package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.users.Customer;

public class CustomerDao {
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public CustomerDao(EntityManager entityManager) {
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

	public void persist(String email, String password) {
		beginTransaction();
		Customer customer = new Customer(email, password);
		entityManager.persist(customer);
		commitTransaction();
	}

	public Customer findById(int id) {
		beginTransaction();
		Customer customer = entityManager.find(Customer.class, id);
		commitTransaction();
		return customer;
	}

	public Customer findByCredentials(String email, String password) {
		beginTransaction();
		Customer customer = entityManager
				.createQuery(
						"SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password",
						Customer.class)
				.setParameter("email", email).setParameter("password", password).getSingleResult();
		commitTransaction();
		return customer;
	}
}