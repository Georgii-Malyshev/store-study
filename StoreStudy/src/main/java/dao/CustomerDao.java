package dao;

import javax.persistence.EntityManager;

import service.domain.users.Customer;

public class CustomerDao extends AppUserDao {
	public CustomerDao(EntityManager entityManager) {
		super(entityManager);
	}

	public void persist(String email, String password) {
		beginTransaction();
		Customer customer = new Customer(email, password);
		entityManager.persist(customer);
		commitTransaction();
	}
}