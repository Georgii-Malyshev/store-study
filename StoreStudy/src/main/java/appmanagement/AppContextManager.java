package appmanagement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// TODO consider making this class a singleton
@WebListener
public final class AppContextManager implements ServletContextListener {
	
	private static ServletContext servletContext;
	private static EntityManagerFactoryManager entityManagerFactoryManager;
	private static ProductCatalogManager productCatalogManager;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		servletContext = event.getServletContext();
		
		EntityManagerFactoryManager entityManagerFactoryManager = new EntityManagerFactoryManager();
		// TODO when entityManagerFactory is ready, inject it into productCatalog manager?
		ProductCatalogManager productCatalogManager = new ProductCatalogManager(servletContext);
		
	}
}