package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.store.Product;

public class ProductDao {
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public ProductDao(EntityManager entityManager) {
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
	public void persist(String name, int price) {
		beginTransaction();
		Product product = new Product(name, price);
		entityManager.persist(product);
		commitTransaction();
	}

	public Product findById(int id) {
		beginTransaction();
		Product product = entityManager.find(Product.class, id);
		commitTransaction();
		return product;
	}
	
	public Product findByName(String name) {
		beginTransaction();
		// Product product = 
		commitTransaction();
		return product;
	}
}