package com.georgiimalyshev.storestudy.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.georgiimalyshev.storestudy.service","com.georgiimalyshev.storestudy.dao" })
@EnableTransactionManagement
public class SpringConfig {
	@Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		return entityManagerFactoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return new JpaTransactionManager();
	}
}