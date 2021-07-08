package com.georgiimalyshev.storestudy;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApplicationContextConfig {
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA"); 
		return entityManagerFactory;
	}
}
