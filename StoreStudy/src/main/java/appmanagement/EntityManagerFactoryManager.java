package appmanagement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class EntityManagerFactoryManager implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;
	private static Set<EntityManagerFactoryListener> listeners = new HashSet<EntityManagerFactoryListener>();
	private static Logger logger = LogManager.getLogger(EntityManagerFactoryManager.class);
	
	public void addListener(EntityManagerFactoryListener listener) {
		listeners.add(listener);
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA");
		logger.info("EntityManagerFactory instance created");
		// notify all listeners that an instance of entityManagerFactory is ready
		for (EntityManagerFactoryListener listener : listeners) {
			listener.entityManagerFactoryCreated();
			logger.debug("listeners notified that an instance of entityManagerFactory is ready");
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