package com.georgiimalyshev.storestudy.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.georgiimalyshev.storestudy.service.domain.store.ProductCatalog;

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
			entityTransaction.commit();
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
		TypedQuery<ProductCatalog> typedQuery = entityManager.createQuery(
				"SELECT c FROM ProductCatalog c JOIN FETCH c.productCategories pc WHERE c.id = :id",
				ProductCatalog.class);
		typedQuery.setParameter("id", id);
		ProductCatalog productCatalog = typedQuery.getSingleResult();
		commitTransaction();
		return productCatalog;
	}
}