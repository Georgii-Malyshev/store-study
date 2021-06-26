package dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.store.Product;

public class ProductCategoryDao {
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public ProductCategoryDao(EntityManager entityManager) {
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
	
	/**
	* Takes ProductCategory's ID as a parameter id
	*/ 
	/*public ArrayList<Product> populateFromStorage(int id) {
		beginTransaction();
		ArrayList<Product> products;
		
		//query?
		
		commitTransaction();
		return products;
	}*/
}