package ui;

import domain.store.ProductCatalog;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ProductCatalogListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        //setting productCatalog that will be used application-wide
        //use public no-arg constructor that populates productCatalog with data from persistent storage
        ProductCatalog productCatalog = new ProductCatalog();
        servletContext.setAttribute("productCatalog", productCatalog);
    }
}