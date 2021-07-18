package com.georgiimalyshev.storestudy.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

import com.georgiimalyshev.storestudy.service.AppUserService;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;

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
		AppUserService appUserService = applicationContext.getBean(AppUserService.class);
		// TODO validate entered data before passing it to the service layer
		Optional<AppUser> optionalOfAppUser = appUserService.findAppUserByCredentials(email, password);
		if (optionalOfAppUser.isPresent()) {
			AppUser appUser = optionalOfAppUser.get();
			HttpSession session = request.getSession();
			session.setAttribute("appUser", appUser); // TODO consider the security of this approach
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			response.sendRedirect(request.getContextPath() + "/login-error");
		}
	}
}