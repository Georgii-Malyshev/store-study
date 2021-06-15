package ui;

import domain.store.ProductCatalogue;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Config implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
	ServletContext servletContext = event.getServletContext();
	// setting productCatalogue
	// use public no-arg constructor to instantiate ProductCatalogue and populate it
	// with data from persistent storage
	ProductCatalogue productCatalogue = new ProductCatalogue();
	servletContext.setAttribute("productCatalogue", productCatalogue);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
	// do nothing
    }

}