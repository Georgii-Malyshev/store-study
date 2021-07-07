package com.georgiimalyshev.storestudy.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.georgiimalyshev.storestudy.service.domain.store.ProductCatalog;
import com.georgiimalyshev.storestudy.service.domain.store.ProductCategory;

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

	public Set<ProductCategory> getAllCategories(ProductCatalog productCatalog) {
		beginTransaction();
		List<ProductCategory> resultList = entityManager
				.createQuery("SELECT pc FROM ProductCategory pc WHERE pc.productCatalog LIKE :productCatalog",
						ProductCategory.class)
				.setParameter("productCatalog", productCatalog)
				.getResultList();
		Set<ProductCategory> productCategories = new HashSet<ProductCategory>(resultList);
		commitTransaction();
		return productCategories;
	}
}