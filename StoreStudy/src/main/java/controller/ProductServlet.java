package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.appmanagement.AppContextManager;
import service.appmanagement.ProductCatalogManager;
import service.domain.store.Product;

import java.io.IOException;

@WebServlet("/product") // should decide on the mapping later
public class ProductServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("product_id"));

		// TODO must change this code so that UI layer doesn't handle
		// persistence-related stuff such as EntityManager and dosen't communicate to
		// DAO layer directly
		/*
		EntityManagerFactory entityManagerFactory = AppContextManager.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductDao productDao = new ProductDao(entityManager);
		Product product = productDao.findById(productId);
		entityManager.close();
		*/
		ProductCatalogManager productCatalogManager = AppContextManager.getProductCatalogManager();
		Product product = productCatalogManager.getProductById(productId);

		request.setAttribute("product", product);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/product.jsp");
		requestDispatcher.forward(request, response);
	}
}
