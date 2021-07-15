package com.georgiimalyshev.storestudy.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.georgiimalyshev.storestudy.service.domain.store.Product;
import com.georgiimalyshev.storestudy.service.domain.store.ProductCategory;

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

	public Set<Product> getAllProducts(ProductCategory productCategory) {
		beginTransaction();
		TypedQuery<Product> typedQuery = entityManager
				.createQuery("SELECT p FROM Product p WHERE p.productCategory LIKE :productCategory", Product.class);
		typedQuery.setParameter("productCategory", productCategory);
		List<Product> resultList = typedQuery.getResultList();
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

	public ProductCategory findByIdAndFetchProducts(int id) {
		beginTransaction();
		TypedQuery<ProductCategory> typedQuery = entityManager.createQuery(
				"SELECT pc FROM ProductCategory pc JOIN FETCH pc.products p WHERE pc.id = :id", ProductCategory.class);
		typedQuery.setParameter("id", id);
		ProductCategory productCategory = typedQuery.getSingleResult();
		commitTransaction();
		return productCategory;
	}
}