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
import com.georgiimalyshev.storestudy.service.domain.store.ProductCategory;

import java.io.IOException;

@WebServlet("/category")
public class ProductCategoryServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("category_id"));

		ServletContext servletContext = request.getServletContext();
		ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
		ProductCatalogService productCatalogService = applicationContext.getBean(ProductCatalogService.class);
		ProductCategory productCategory = productCatalogService.getProductCategoryById(categoryId);
		
		request.setAttribute("productCategory", productCategory);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/category.jsp");
		requestDispatcher.forward(request, response);
	}
}