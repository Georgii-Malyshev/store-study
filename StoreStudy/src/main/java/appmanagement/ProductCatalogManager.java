package appmanagement;

import domain.store.ProductCatalog;

public class ProductCatalogManager implements EntityManagerFactoryListener {

	private static ProductCatalog productCatalog; 
	
	@Override
	public void entityManagerFactoryCreated() {
		productCatalog = new ProductCatalog();
	}
	
	public ProductCatalog getProductCatalog() {
		return productCatalog;
	}
}