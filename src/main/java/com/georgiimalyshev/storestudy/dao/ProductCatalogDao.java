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

	public ProductCatalog findByIdAndFetchProductCategories(int id) {
		beginTransaction();
		ProductCatalog productCatalog = (ProductCatalog) entityManager
				.createQuery("SELECT c FROM ProductCatalog c JOIN FETCH c.productCategories pc WHERE c.id = :id",
						ProductCatalog.class)
				.setParameter("id", id).getSingleResult();
		commitTransaction();
		return productCatalog;
	}
}