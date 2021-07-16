package com.georgiimalyshev.storestudy.dao;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.georgiimalyshev.storestudy.service.domain.store.ProductCatalog;

@Transactional
public class ProductCatalogDao {
	private EntityManager entityManager;

	public ProductCatalogDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Optional<ProductCatalog> findByIdAndFetchProductCategories(int id) {
		TypedQuery<ProductCatalog> typedQuery = entityManager.createQuery(
				"SELECT c FROM ProductCatalog c JOIN FETCH c.productCategories pc WHERE c.id = :id",
				ProductCatalog.class);
		typedQuery.setParameter("id", id);
		typedQuery.setMaxResults(1);
		Stream<ProductCatalog> resultStream = typedQuery.getResultStream();
		Optional<ProductCatalog> optionalOfProductCatalog = resultStream.findFirst();
		return optionalOfProductCatalog;
	}
}