package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.appmanagement.AppContextManager;
import service.appmanagement.ProductCatalogManager;
import service.domain.store.ProductCategory;

import java.io.IOException;

@WebServlet("/category")
public class ProductCategoryServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("category_id"));

		ProductCatalogManager productCatalogManager = AppContextManager.getProductCatalogManager();
		ProductCategory productCategory = productCatalogManager.getProductCategoryById(categoryId);
		
		request.setAttribute("productCategory", productCategory);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/category.jsp");
		requestDispatcher.forward(request, response);
	}
}