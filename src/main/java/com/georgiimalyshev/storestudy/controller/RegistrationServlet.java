package com.georgiimalyshev.storestudy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.georgiimalyshev.storestudy.service.appmanagement.RegistrationService;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/registration.jsp");
		requestDispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		ServletContext servletContext = request.getServletContext();
		ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
		RegistrationService registrationService = applicationContext.getBean(RegistrationService.class);
		boolean registrationSuccess = false;
		// TODO validate entered data before passing it to the service layer
		registrationSuccess = registrationService.registerNewCustomer(email, password);
		// TODO send to a servlet instead
		if (registrationSuccess) {
			response.sendRedirect("jsp/registration-successful.jsp"); 
		} else {
			response.sendRedirect("jsp/error.jsp");
		}
	}
}