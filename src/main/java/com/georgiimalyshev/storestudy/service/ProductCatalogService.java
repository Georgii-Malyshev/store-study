package com.georgiimalyshev.storestudy.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.georgiimalyshev.storestudy.dao.ProductCatalogDao;
import com.georgiimalyshev.storestudy.dao.ProductCategoryDao;
import com.georgiimalyshev.storestudy.dao.ProductDao;
import com.georgiimalyshev.storestudy.service.domain.store.Product;
import com.georgiimalyshev.storestudy.service.domain.store.ProductCatalog;
import com.georgiimalyshev.storestudy.service.domain.store.ProductCategory;
  
@Service
public final class ProductCatalogService {
	private EntityManagerFactory entityManagerFactory;

	public ProductCatalogService(@Autowired EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public Product getProductById (int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductDao productDao = new ProductDao(entityManager);
		Optional<Product> optionalOfProduct = productDao.findById(id);
		entityManager.close();
		Product product = optionalOfProduct.orElseThrow();
		return product;
	}

	public ProductCategory getProductCategoryById(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductCategoryDao productCategoryDao = new ProductCategoryDao(entityManager);
		Optional<ProductCategory> optionalOfProductCategory = productCategoryDao.findById(id);
		entityManager.close();
		ProductCategory productCategory = optionalOfProductCategory.orElseThrow();
		return productCategory;
	}

	public ProductCategory getProductCategoryByIdAndFetchProducts(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductCategoryDao productCategoryDao = new ProductCategoryDao(entityManager);
		Optional<ProductCategory> optionalOfProductCategory = productCategoryDao.findByIdAndFetchProducts(id);
		entityManager.close();
		ProductCategory productCategory = optionalOfProductCategory.orElseThrow();
		return productCategory;
	}

	public ProductCatalog getProductCatalogByIdAndFetchCategories(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductCatalogDao productCatalogDao = new ProductCatalogDao(entityManager);
		Optional<ProductCatalog> optionalOfProductCatalog = productCatalogDao.findByIdAndFetchProductCategories(id);
		entityManager.close();
		ProductCatalog productCatalog = optionalOfProductCatalog.orElseThrow();
		return productCatalog;
	}
}