package com.georgiimalyshev.storestudy.controller;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

import com.georgiimalyshev.storestudy.service.appmanagement.AppContextManager;
import com.georgiimalyshev.storestudy.service.appmanagement.AuthService;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;

import java.io.IOException;

@WebServlet("/login-auth-servlet")
public class LoginAuthServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		ServletContext servletContext = request.getServletContext();
		ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
		
		EntityManagerFactory entityManagerFactory = applicationContext.getBean(EntityManagerFactory.class);
		
		AuthService authService = new AuthService(entityManagerFactory);
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