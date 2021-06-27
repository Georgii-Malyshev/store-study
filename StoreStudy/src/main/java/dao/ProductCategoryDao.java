package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import domain.store.Product;
import domain.store.ProductCategory;

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

	public Set<Product> getAllProducts(ProductCategory productCategory) {
		beginTransaction();
		List<Product> resultList = entityManager
				.createQuery("SELECT p FROM product p WHERE p.productCategory LIKE :productCategory", Product.class)
				.setParameter("productCategory", productCategory)
				.getResultList();
		Set<Product> products = new HashSet<Product>(resultList);
		commitTransaction();
		return products;
	}
}