package appmanagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// TODO rename this class to something less ugly

@WebListener
public class EntityManagerFactoryManager implements ServletContextListener {

	EntityManagerFactory entityManagerFactory;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA");
		servletContext.setAttribute("entityManagerFactory", entityManagerFactory);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
}