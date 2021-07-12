package com.georgiimalyshev.storestudy.service.appmanagement;

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
	
	public Product getProductById(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductDao productDao = new ProductDao(entityManager);
		Product product = productDao.findById(id);
		entityManager.close();
		return product;
	}
	
	public ProductCategory getProductCategoryById(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductCategoryDao productCategoryDao = new ProductCategoryDao(entityManager);
		ProductCategory productCategory = productCategoryDao.findById(id);
		entityManager.close();
		return productCategory;
	}

	public ProductCategory getProductCategoryByIdAndFetchProducts(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductCategoryDao productCategoryDao = new ProductCategoryDao(entityManager);
		ProductCategory productCategory = productCategoryDao.findByIdAndFetchProducts(id);
		entityManager.close();
		return productCategory;
	}
	
	public ProductCatalog getProductCatalogByIdAndFetchCategories(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductCatalogDao productCatalogDao = new ProductCatalogDao(entityManager);
		ProductCatalog productCatalog = productCatalogDao.findByIdAndFetchProductCategories(id);
		entityManager.close();
		return productCatalog;
	}
}