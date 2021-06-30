package appmanagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import domain.store.ProductCatalog;

@WebListener
public class AppContextManager implements ServletContextListener {
	
	private static Logger logger = LogManager.getLogger(AppContextManager.class);
	private static ServletContext servletContext;
	private static EntityManagerFactory entityManagerFactory;
	private static ProductCatalog productCatalog;	
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		servletContext = event.getServletContext();
		
		entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA");
		logger.info("EntityManagerFactory instance created");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();		
		ProductCatalog productCatalog =  entityManager.find(ProductCatalog.class, 1); // TODO get rid of hardcoded ID?
		logger.info("ProductCatalog loaded from persistent storage");
		
		servletContext.setAttribute("productcatalog", productCatalog);
		logger.info("productCatalog is available as servlet context attributes");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
	
	public static ProductCatalog getProductCatalog() {
		return productCatalog;
	}
}