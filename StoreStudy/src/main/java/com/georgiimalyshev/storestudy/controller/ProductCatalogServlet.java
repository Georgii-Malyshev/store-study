package com.georgiimalyshev.storestudy.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.georgiimalyshev.storestudy.service.domain.store.ProductCatalog;
import com.georgiimalyshev.storestudy.service.domain.store.ProductCategory;

import java.io.IOException;
import java.util.HashSet;

@WebServlet("/catalog")
public class ProductCatalogServlet extends HttpServlet {
	
	private Logger logger = LogManager.getLogger(ProductCatalogServlet.class);
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/catalog.jsp");
		requestDispatcher.forward(request, response);
	}
}