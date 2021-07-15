package com.georgiimalyshev.storestudy.controller;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.georgiimalyshev.storestudy.service.appmanagement.AppUserService;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;

@WebServlet("/admin-panel")
public class AdminPanelServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = request.getServletContext();
		ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
		AppUserService appUserService = applicationContext.getBean(AppUserService.class);
		HashSet<AppUser> appUsers = (HashSet<AppUser>) appUserService.getAllUsers();
		request.setAttribute("appUsers", appUsers);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/admin-panel.jsp");
		requestDispatcher.forward(request, response);
	}
}