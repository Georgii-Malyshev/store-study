package ui;

import domain.store.ProductCatalogue;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextConfig implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        //setting productCatalogue that will be used application-wide
        //use public no-arg constructor that populates productCatalogue with data from persistent storage
        ProductCatalogue productCatalogue = new ProductCatalogue();
        servletContext.setAttribute("productCatalogue", productCatalogue);
    }
}