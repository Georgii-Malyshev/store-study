package com.georgiimalyshev.storestudy.dao;

import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.georgiimalyshev.storestudy.service.domain.store.ProductCatalog;

@Repository
@Transactional
public class ProductCatalogDao {
	@PersistenceContext private EntityManager entityManager;

	// TODO get rid of duplicate code in methods that use JPQL queries
	
	public Optional<ProductCatalog> findByIdAndFetchProductCategories(int id) {
		// TODO implement some checks to make sure that this code ALWAYS returns a single and correct result
		TypedQuery<ProductCatalog> typedQuery = entityManager.createQuery(
				"SELECT c FROM ProductCatalog c JOIN FETCH c.productCategories pc WHERE c.id = :id",
				ProductCatalog.class);
		typedQuery.setParameter("id", id);
		Stream<ProductCatalog> resultStream = typedQuery.getResultStream();
		Optional<ProductCatalog> optionalOfProductCatalog = resultStream.findFirst();
		return optionalOfProductCatalog;
	}
}