package ui;

import domain.users.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appmanagement.AppContextManager;
import appmanagement.AuthManager;
import dao.CustomerDao;

import java.io.IOException;

@WebServlet("/loginAuthServlet")
public class LoginAuthServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		AuthManager authManager = AppContextManager.getAuthManager();
		AppUser appUser = authManager.getAppUserByCredentials(email, password);
		
		if (appUser.getId() != -1) {
			HttpSession session = request.getSession();
			session.setAttribute("appUser", appUser);
			response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
		} else {
			response.sendError(401);
		}
	}
}