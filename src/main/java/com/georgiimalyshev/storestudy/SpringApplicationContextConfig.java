package com.georgiimalyshev.storestudy;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan
public class SpringApplicationContextConfig {
	@Bean
	@Scope("singleton")
	public EntityManagerFactory entityManagerFactory() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA");
		return entityManagerFactory;
	} // TODO should close entityManagerFactory when the application is
		// stopped/undeployed

	// TODO use this commented-out code instead of EntityManagerFactory bean to
	// switch to
	// Spring-based transaction management (don't forget to add
	// @EnableTransactionManagement annotation to this class and the necessary
	// Spring dependencies to pom.xml)
	/*
	 * @Bean public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	 * LocalContainerEntityManagerFactoryBean entityManager = new
	 * LocalContainerEntityManagerFactoryBean(); return entityManager; }
	 */
}
