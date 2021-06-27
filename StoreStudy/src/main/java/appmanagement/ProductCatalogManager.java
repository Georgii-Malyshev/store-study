package appmanagement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import domain.store.ProductCatalog;

@WebListener
public class ProductCatalogManager implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		// TODO modify this so that created productCatalog gets populated with data from persistent storage
		ProductCatalog productCatalog = new ProductCatalog();
		servletContext.setAttribute("ProductCatalog", productCatalog);
	}
}