package com.georgiimalyshev.storestudy.controller;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.georgiimalyshev.storestudy.service.domain.store.Cart;
import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@Controller
public class CartController {
	@ModelAttribute
	public Cart cart() {
		return new Cart();
	} // TODO consider if this block of code is actualy necessary
	
	@GetMapping("/cart")
	public String cartPage(HttpSession httpSession, Model model) {
		Logger logger = LogManager.getLogger();
		logger.info("logger for CartController created");
		Customer customer = (Customer) httpSession.getAttribute("user");		
		if (customer != null) {
			logger.info("got non-null customer from httpSession");
			Cart cart = customer.getCart();
			logger.info("got customer's cart");
			// TODO fetch only the necessary collection(s) of cart attributes
			model.addAttribute("cart", cart);
			logger.info("added cart to model");
		} else {
			logger.info("got NULL customer from httpSession");
		}
		
		return "cart";
	}
}
