package com.georgiimalyshev.storestudy.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.georgiimalyshev.storestudy.service.domain.store.Product;

@Repository
@Transactional
public class ProductDao {
	@PersistenceContext private EntityManager entityManager;

	public void persist(Product product) {
		entityManager.persist(product);
	}

	public Optional<Product> findById(int id) {
		Product product = entityManager.find(Product.class, id);
		return Optional.ofNullable(product);
	}
}