package com.georgiimalyshev.storestudy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.georgiimalyshev.storestudy.service.domain.store.Cart;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;
import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AppUser appUser = (AppUser) session.getAttribute("appUser");

		// TODO implement some better exception handling logic
		try {
			Cart cart = ((Customer) appUser).getCart();
			request.setAttribute("cart", cart);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/cart.jsp");
			requestDispatcher.forward(request, response);
		} catch (ClassCastException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/not-a-customer-error.jsp");
			requestDispatcher.forward(request, response);
		} catch (NullPointerException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/error.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}