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

import com.georgiimalyshev.storestudy.domain.store.Product;
import com.georgiimalyshev.storestudy.service.ProductManagementService;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("product_id"));

		ServletContext servletContext = request.getServletContext();
		ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
		ProductManagementService productManagementService = applicationContext.getBean(ProductManagementService.class);
		Product product = productManagementService.getProductById(productId);

		request.setAttribute("product", product);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/product.jsp");
		requestDispatcher.forward(request, response);
	}
}
