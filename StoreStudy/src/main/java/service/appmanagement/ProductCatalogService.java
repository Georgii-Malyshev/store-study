package service.appmanagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.ProductCategoryDao;
import dao.ProductDao;
import service.domain.store.Product;
import service.domain.store.ProductCategory;
// TODO consider making this class a singleton
public final class ProductCatalogService {
	private EntityManagerFactory entityManagerFactory;
	
	public ProductCatalogService(EntityManagerFactory entityManagerFactory) {
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
}
