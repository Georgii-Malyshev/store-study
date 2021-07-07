package com.georgiimalyshev.storestudy.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.georgiimalyshev.storestudy.service.appmanagement.AppContextManager;
import com.georgiimalyshev.storestudy.service.appmanagement.ProductCatalogService;
import com.georgiimalyshev.storestudy.service.domain.store.Product;

import java.io.IOException;

@WebServlet("/product") // should decide on the mapping later
public class ProductServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("product_id"));

		ProductCatalogService productCatalogService = AppContextManager.getProductCatalogManager();
		Product product = productCatalogService.getProductById(productId);

		request.setAttribute("product", product);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/product.jsp");
		requestDispatcher.forward(request, response);
	}
}
