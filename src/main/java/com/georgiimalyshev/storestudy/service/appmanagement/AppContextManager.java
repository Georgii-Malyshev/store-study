package com.georgiimalyshev.storestudy.service.appmanagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.georgiimalyshev.storestudy.service.domain.store.ProductCatalog;

@WebListener
public final class AppContextManager implements ServletContextListener {

	private static Logger logger = LogManager.getLogger(AppContextManager.class);
	private static ServletContext servletContext;
	private static EntityManagerFactory entityManagerFactory;
	private static AuthService authService;
	private static ProductCatalogService productCatalogService;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		servletContext = event.getServletContext();
		// create entityManagerFactory
		entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA");
		logger.info("EntityManagerFactory instance created");
		// create productCatalog
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		// TODO fetch only categories' names and/or IDs, not the whole catalog
		// TODO get rid of hardcoded catalog ID
		ProductCatalog productCatalog = entityManager.find(ProductCatalog.class, 1);
		servletContext.setAttribute("productCatalog", productCatalog);
		logger.info("productCatalog is available as servlet context attribute");
		entityManager.close();
		// create managers
		authService = new AuthService(entityManagerFactory);
		productCatalogService = new ProductCatalogService(entityManagerFactory);
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

	public static AuthService getAuthManager() {
		return authService;
	}
	
	public static ProductCatalogService getProductCatalogManager() {
		return productCatalogService;
	}
}