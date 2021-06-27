package appmanagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// TODO rename this class to something less ugly?

@WebListener
public class EntityManagerFactoryManager implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public static EntityManager createEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException("Trying to create entityManager but entityManagerFactory was not initialized yet");
		}
		return entityManagerFactory.createEntityManager();  
	}
}