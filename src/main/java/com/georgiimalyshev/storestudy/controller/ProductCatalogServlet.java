package com.georgiimalyshev.storestudy.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.georgiimalyshev.storestudy.service.appmanagement.ProductCatalogService;
import com.georgiimalyshev.storestudy.service.domain.store.ProductCatalog;

import java.io.IOException;

@WebServlet("/catalog")
public class ProductCatalogServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = request.getServletContext();
		// catalog ID used here is hardcoded in the app's initializer
		int catalogId = (int) servletContext.getAttribute("catalog_id"); 
		
		ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
		ProductCatalogService productCatalogService = applicationContext.getBean(ProductCatalogService.class);
		ProductCatalog productCatalog = productCatalogService.getProductCatalogByIdAndFetchCategories(catalogId);
		
		request.setAttribute("productCatalog", productCatalog);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/catalog.jsp");
		requestDispatcher.forward(request, response);
	}
}