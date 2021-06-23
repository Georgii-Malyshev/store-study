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

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		// create ProductCatalog instance to be used application-wide
		// must modify this code so that it populates productCatalog with data from
		// persistent storage
		ProductCatalog productCatalog = new ProductCatalog();
		servletContext.setAttribute("productCatalog", productCatalog);
		// create EntityManagerFactory instance to be used application-wide
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		servletContext.setAttribute("entityManager", entityManager);
	}

	public void contextDestroyed(ServletContextEvent evt) {

		// must close entityManagerFactory when application stops
	}
}