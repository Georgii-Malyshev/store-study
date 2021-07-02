package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import service.domain.store.Product;
import service.domain.store.ProductCategory;
import service.domain.users.Customer;

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
				.createQuery("SELECT p FROM Product p WHERE p.productCategory LIKE :productCategory", Product.class)
				.setParameter("productCategory", productCategory)
				.getResultList();
		Set<Product> products = new HashSet<Product>(resultList);
		commitTransaction();
		return products;
	}
	
	public ProductCategory findById(int id) {
		beginTransaction();
		ProductCategory productCategory = entityManager.find(ProductCategory.class, id);
		commitTransaction();
		return productCategory;
	}
}