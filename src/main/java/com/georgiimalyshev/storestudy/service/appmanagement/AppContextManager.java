package com.georgiimalyshev.storestudy.service.appmanagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.georgiimalyshev.storestudy.SpringApplicationContextConfig;
import com.georgiimalyshev.storestudy.service.domain.store.ProductCatalog;

@WebListener
public final class AppContextManager implements ServletContextListener {

	private static Logger logger = LogManager.getLogger(AppContextManager.class);
	private static ServletContext servletContext;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringApplicationContextConfig.class);
		servletContext = event.getServletContext();
		servletContext.setAttribute("applicationContext", applicationContext);
		
		EntityManagerFactory entityManagerFactory = applicationContext.getBean(EntityManagerFactory.class);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		// TODO fetch only categories' names and/or IDs, not the whole catalog
		// TODO get rid of hardcoded catalog ID
		ProductCatalog productCatalog = entityManager.find(ProductCatalog.class, 1);
		servletContext.setAttribute("productCatalog", productCatalog);
		logger.info("productCatalog is available as servlet context attribute");
		entityManager.close();
	}
}