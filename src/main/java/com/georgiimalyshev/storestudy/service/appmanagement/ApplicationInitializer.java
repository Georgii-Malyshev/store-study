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
public final class ApplicationInitializer implements ServletContextListener {

	private static ServletContext servletContext;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringApplicationContextConfig.class);
		servletContext = event.getServletContext();
		servletContext.setAttribute("applicationContext", applicationContext);
		
		// TODO get rid of hardcoded catalog ID?
		// TODO move productCatalog-related code to ProductCatalogServlet
		int id = 1;
		ProductCatalogService productCatalogService = applicationContext.getBean(ProductCatalogService.class);
		ProductCatalog productCatalog = productCatalogService.getProductCatalogByIdAndFetchCategories(id);
		servletContext.setAttribute("productCatalog", productCatalog);
	}
}