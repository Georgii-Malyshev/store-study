package com.georgiimalyshev.storestudy.dao;

import java.util.Optional;

import javax.persistence.EntityManager;

import com.georgiimalyshev.storestudy.service.domain.store.Product;

public class ProductDao {
	private EntityManager entityManager;

	public ProductDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void persist(Product product) {
		entityManager.persist(product);
	}

	public Optional<Product> findById(int id) {
		Product product = entityManager.find(Product.class, id);
		return Optional.ofNullable(product);
	}
}