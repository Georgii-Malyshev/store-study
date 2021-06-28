package appmanagement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import domain.store.ProductCatalog;

public class ProductCatalogManager implements ServletContextListener, EntityManagerFactoryListener {
	private static ServletContext servletContext;
	private static ProductCatalog productCatalog; 
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		servletContext = event.getServletContext();
	}
	
	@Override
	public void entityManagerFactoryCreated() {
		productCatalog = new ProductCatalog();
		servletContext.setAttribute("product_catalog", productCatalog);
	}
}