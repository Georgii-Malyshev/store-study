package com.georgiimalyshev.storestudy;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.georgiimalyshev.storestudy.config.SpringConfig;

@WebListener
public final class ApplicationInitializer implements ServletContextListener {
	private static ServletContext servletContext;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		servletContext = event.getServletContext();
		servletContext.setAttribute("applicationContext", applicationContext);
		// TODO get rid of hardcoded catalog ID?
		int catalogId = 1;
		servletContext.setAttribute("catalogId", catalogId);
	}
}