package ui;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appmanagement.AppContextManager;
import dao.ProductCategoryDao;
import domain.store.ProductCategory;

import java.io.IOException;

@WebServlet("/category")
public class ProductCategoryServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("category_id"));

		// TODO must change this code so that UI layer doesn't handle
		// persistence-related stuff such as EntityManager and dosen't communicate to
		// DAO layer directly
		EntityManagerFactory entityManagerFactory = AppContextManager.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductCategoryDao productCategoryDao = new ProductCategoryDao(entityManager);
		ProductCategory productCategory = productCategoryDao.findById(categoryId);
		entityManager.close();

		request.setAttribute("productCategory", productCategory);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/category.jsp");
		requestDispatcher.forward(request, response);
	}
}