package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.appmanagement.AppContextManager;
import service.appmanagement.AuthService;
import service.domain.users.AppUser;

import java.io.IOException;

@WebServlet("/login-auth-servlet")
public class LoginAuthServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		AuthService authService = AppContextManager.getAuthManager();
		AppUser appUser = authService.getAppUserByCredentials(email, password);
		
		if (appUser.getId() != -1) {
			HttpSession session = request.getSession();
			session.setAttribute("appUser", appUser);
			response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
		} else {
			response.sendError(401);
		}
	}
}