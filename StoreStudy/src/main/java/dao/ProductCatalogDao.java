package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.store.ProductCategory;

public class ProductCatalogDao {
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public ProductCatalogDao(EntityManager entityManager) {
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
	
	/*public ArrayList<ProductCategory> populateFromStorage() {
		beginTransaction();
		ArrayList<ProductCategory> productCategories;
		
		commitTransaction();
		return productCategories;
	}*/
}