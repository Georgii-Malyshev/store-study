package ui;

import domain.store.ProductCatalog;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
	
	EntityManagerFactory entityManagerFactory;
	ProductCatalog productCatalog;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		// create EntityManagerFactory instance to be used application-wide
		entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA");
		servletContext.setAttribute("entityManagerFactory", entityManagerFactory);
		// create ProductCatalog instance to be used application-wide
		// must modify this code so that it populates productCatalog with data from
		// persistent storage
		productCatalog = new ProductCatalog();
		servletContext.setAttribute("productCatalog", productCatalog);
	}
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		entityManagerFactory.close();
	}
}