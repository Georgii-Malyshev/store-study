package appmanagement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EntityManagerFactoryManager implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;
	private static Set<EntityManagerFactoryListener> listeners = new HashSet<EntityManagerFactoryListener>();

	@Override
	public void contextInitialized(ServletContextEvent event) {
		entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA");

		// notify all listeners that an instance of entityManagerFactory is ready
		for (EntityManagerFactoryListener listener : listeners) {
			listener.entityManagerFactoryCreated();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public static EntityManager createEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException(
					"Trying to create entityManager but entityManagerFactory was not initialized yet");
		}
		return entityManagerFactory.createEntityManager();
	}
}