package com.georgiimalyshev.storestudy.controller;

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

import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AppUser appUser = (AppUser) session.getAttribute("appUser");
		try {
			Cart cart = ((Customer) appUser).getCart();
			request.setAttribute("cart", cart);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/product.jsp");
			requestDispatcher.forward(request, response);
		} catch (ClassCastException ex) {
			// handle an exception
		} catch (NullPointerException ex) {
			// handle an exception
		}
	}
}