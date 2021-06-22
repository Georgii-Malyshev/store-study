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
import java.io.IOException;

@WebServlet("/loginAuthServlet")
public class LoginAuthServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		//should probably move this somewhere else later
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HibernateJPA");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//try to find the appUser with provided credentials in the persistent storage
		
		if (appUser.getId() != -1) {
			HttpSession session = request.getSession();
			session.setAttribute("appUserId", AppuserId);
			response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
		} else {
			response.sendError(401);
		}
	}
}