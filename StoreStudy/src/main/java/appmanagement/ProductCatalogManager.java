package appmanagement;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import domain.store.ProductCatalog;

//TODO consider making this class a singleton
public class ProductCatalogManager implements EntityManagerFactoryListener {
	public ProductCatalogManager(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	private ServletContext servletContext;
	private ProductCatalog productCatalog;
	private Logger logger = LogManager.getLogger(ProductCatalogManager.class);
	
	@Override
	public void entityManagerFactoryCreated() {
		productCatalog = new ProductCatalog();
		logger.debug("ProductCatalog instance created by ProductCatalogManager");
		productCatalog.populateFromStorage();
		servletContext.setAttribute("product_catalog", productCatalog);
		logger.debug("ProductCatalog instance put in servletContext by ProductCatalogManager");
	}
}