package com.georgiimalyshev.storestudy;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.georgiimalyshev.storestudy.config.SpringConfig;
import com.georgiimalyshev.storestudy.config.SpringWebMvcConfig;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { SpringConfig.class };
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { SpringWebMvcConfig.class };
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
