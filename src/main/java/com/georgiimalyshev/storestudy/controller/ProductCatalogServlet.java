package com.georgiimalyshev.storestudy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.georgiimalyshev.storestudy.domain.store.ProductCatalog;
import com.georgiimalyshev.storestudy.service.ProductManagementService;

@WebServlet("/catalog")
public class ProductCatalogServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext servletContext = request.getServletContext();
		// catalog ID used here is hardcoded in the app's initializer
		int catalogId = (int) servletContext.getAttribute("catalogId");
		ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
		ProductManagementService productManagementService = applicationContext.getBean(ProductManagementService.class);
		ProductCatalog productCatalog = productManagementService.getProductCatalogByIdAndFetchCategories(catalogId);
		request.setAttribute("productCatalog", productCatalog);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/catalog.jsp");
		requestDispatcher.forward(request, response);
	}
}