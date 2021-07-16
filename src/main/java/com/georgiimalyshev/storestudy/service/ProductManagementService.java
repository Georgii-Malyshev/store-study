package com.georgiimalyshev.storestudy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.georgiimalyshev.storestudy.dao.ProductCatalogDao;
import com.georgiimalyshev.storestudy.dao.ProductCategoryDao;
import com.georgiimalyshev.storestudy.dao.ProductDao;
import com.georgiimalyshev.storestudy.service.domain.store.Product;
import com.georgiimalyshev.storestudy.service.domain.store.ProductCatalog;
import com.georgiimalyshev.storestudy.service.domain.store.ProductCategory;

@Service
public final class ProductManagementService {
	private ProductCatalogDao productCatalogDao;
	private ProductCategoryDao productCategoryDao;
	private ProductDao productDao;

	public ProductManagementService(@Autowired ProductCatalogDao productCatalogDao,
			@Autowired ProductCategoryDao productCategoryDao, @Autowired ProductDao productDao) {
		this.productCatalogDao = productCatalogDao;
		this.productCategoryDao = productCategoryDao;
		this.productDao = productDao;
	}

	public Product getProductById(int id) {
		Optional<Product> optionalOfProduct = productDao.findById(id);
		Product product = optionalOfProduct.orElseThrow();
		return product;
	}

	public ProductCategory getProductCategoryById(int id) {
		Optional<ProductCategory> optionalOfProductCategory = productCategoryDao.findById(id);
		ProductCategory productCategory = optionalOfProductCategory.orElseThrow();
		return productCategory;
	}

	public ProductCategory getProductCategoryByIdAndFetchProducts(int id) {
		Optional<ProductCategory> optionalOfProductCategory = productCategoryDao.findByIdAndFetchProducts(id);
		ProductCategory productCategory = optionalOfProductCategory.orElseThrow();
		return productCategory;
	}

	public ProductCatalog getProductCatalogByIdAndFetchCategories(int id) {
		Optional<ProductCatalog> optionalOfProductCatalog = productCatalogDao.findByIdAndFetchProductCategories(id);
		ProductCatalog productCatalog = optionalOfProductCatalog.orElseThrow();
		return productCatalog;
	}
}