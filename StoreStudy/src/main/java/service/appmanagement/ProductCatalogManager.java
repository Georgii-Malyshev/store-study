package service.appmanagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.ProductDao;
import service.domain.store.Product;
// TODO consider making this class a singleton
public final class ProductCatalogManager {
	private EntityManagerFactory entityManagerFactory;
	
	public ProductCatalogManager(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public Product getProductById(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		ProductDao productDao = new ProductDao(entityManager);
		Product product = productDao.findById(id);
		
		entityManager.close();
		return product;
	}
}
