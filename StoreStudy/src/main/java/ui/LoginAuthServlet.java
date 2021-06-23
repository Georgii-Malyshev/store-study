package ui;

import domain.users.AppUser;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDao;

import java.io.IOException;

@WebServlet("/loginAuthServlet")
public class LoginAuthServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// should probably move entityManager creation somewhere else
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		// must change this so UI layer communicates to Domain layer, not directly
		// to DAO layer
		// must change this so that abstract AppUser would be searched for, not Customer
		CustomerDao customerDao = new CustomerDao(entityManager);
		AppUser appUser = customerDao.findByCredentials(email, password);

		if (appUser.getId() != -1) {
			HttpSession session = request.getSession();
			session.setAttribute("appUserId", appUser.getId());
			response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
		} else {
			response.sendError(401);
		}
	}
}