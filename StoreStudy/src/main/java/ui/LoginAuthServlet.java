package ui;

import domain.users.AppUser;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import appmanagement.EntityManagerFactoryManager;
import dao.CustomerDao;

import java.io.IOException;

@WebServlet("/loginAuthServlet")
public class LoginAuthServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// TODO must change this code so that UI layer doesn't handle
		// persistence-related stuff such as EntityManager and dosens't communicate to
		// DAO layer directly
		EntityManager entityManager = EntityManagerFactoryManager.createEntityManager();
		CustomerDao customerDao = new CustomerDao(entityManager);
		// TODO change this so that abstract AppUser would be searched for, not Customer
		AppUser appUser = customerDao.findByCredentials(email, password);

		if (appUser.getId() != -1) {
			HttpSession session = request.getSession();
			session.setAttribute("appUser", appUser);
			response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
		} else {
			response.sendError(401);
		}
	}
}