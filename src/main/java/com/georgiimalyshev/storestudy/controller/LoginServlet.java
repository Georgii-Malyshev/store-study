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

		AppUser appUser = authService.getAppUserByCredentials(email, password);

		// TODO use null object pattern or implement some credential validation logic
		// in AuthService to handle wrong credentials;
		// right now if user can't be found by received credentials
		// application simply throws an exception "No entity found for query"
		HttpSession session = request.getSession();
		session.setAttribute("appUser", appUser); // TODO maybe try something less insecure later
		response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");

	}
}