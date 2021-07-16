package com.georgiimalyshev.storestudy.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

import com.georgiimalyshev.storestudy.service.appmanagement.AuthService;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/login.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		ServletContext servletContext = request.getServletContext();
		ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
		AuthService authService = applicationContext.getBean(AuthService.class);
		// TODO validate entered data before passing it to the service layer
		Optional<AppUser> optionalOfAppUser = authService.findAppUserByCredentials(email, password);
		if (optionalOfAppUser.isPresent()) {
			AppUser appUser = optionalOfAppUser.get();
			HttpSession session = request.getSession();
			session.setAttribute("appUser", appUser); // TODO maybe try something less insecure later
			response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/jsp/error.jsp"); // TODO show specific error
		}
	}
}