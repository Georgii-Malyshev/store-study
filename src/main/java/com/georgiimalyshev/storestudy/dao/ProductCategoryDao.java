package com.georgiimalyshev.storestudy.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.georgiimalyshev.storestudy.service.domain.store.Product;
import com.georgiimalyshev.storestudy.service.domain.store.ProductCategory;

@Transactional
public class ProductCategoryDao {
	private EntityManager entityManager;

	public ProductCategoryDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Set<Product> getAllProducts(ProductCategory productCategory) {
		TypedQuery<Product> typedQuery = entityManager
				.createQuery("SELECT p FROM Product p WHERE p.productCategory LIKE :productCategory", Product.class);
		typedQuery.setParameter("productCategory", productCategory);
		List<Product> resultList = typedQuery.getResultList();
		Set<Product> products = new HashSet<Product>(resultList);
		return products;
	}

	public Optional<ProductCategory> findById(int id) {
		ProductCategory productCategory = entityManager.find(ProductCategory.class, id);
		return Optional.ofNullable(productCategory);
	}

	public Optional<ProductCategory> findByIdAndFetchProducts(int id) {
		TypedQuery<ProductCategory> typedQuery = entityManager.createQuery(
				"SELECT pc FROM ProductCategory pc JOIN FETCH pc.products p WHERE pc.id = :id", ProductCategory.class);
		typedQuery.setParameter("id", id);
		typedQuery.setMaxResults(1);
		Stream<ProductCategory> resultStream = typedQuery.getResultStream();
		Optional<ProductCategory> optionalOfProductCategory = resultStream.findFirst(); 
		return optionalOfProductCategory;
	}
}