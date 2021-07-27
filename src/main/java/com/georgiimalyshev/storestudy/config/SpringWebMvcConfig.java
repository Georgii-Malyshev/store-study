package com.georgiimalyshev.storestudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.georgiimalyshev.storestudy.controller"} )
public class SpringWebMvcConfig implements WebMvcConfigurer {
	// create SpringResourceTemplateResolver
	@Bean
	public SpringResourceTemplateResolver springResourceTemplateResolver() {
		SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
		springResourceTemplateResolver.setPrefix("classpath:/templates/"); // TODO consider other ways to point to templates
		springResourceTemplateResolver.setSuffix(".html");
		springResourceTemplateResolver.setTemplateMode("HTML5");
		return springResourceTemplateResolver;
	}
	// create SpringTemplateEngine
	@Bean
	public SpringTemplateEngine springTemplateEngine() {
		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.setTemplateResolver(springResourceTemplateResolver());
		springTemplateEngine.setEnableSpringELCompiler(true);
		return springTemplateEngine;
	}
	// register ThymeleafViewResolver in Spring WebMVC
	@Override
	public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(springTemplateEngine());
		viewResolverRegistry.viewResolver(thymeleafViewResolver);
	}
}