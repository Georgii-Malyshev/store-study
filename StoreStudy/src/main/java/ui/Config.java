package ui;

import domain.store.ProductCatalog;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Config implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
	ServletContext servletContext = event.getServletContext();
	// setting productCatalog
	// use public no-arg constructor to instantiate ProductCatalog and populate it
	// with data from persistent storage
	ProductCatalog productCatalog = new ProductCatalog();
	servletContext.setAttribute("productCatalog", productCatalog);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
	// do nothing
    }

}