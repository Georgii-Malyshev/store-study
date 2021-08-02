package com.georgiimalyshev.storestudy.controller;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.georgiimalyshev.storestudy.service.CartService;
import com.georgiimalyshev.storestudy.service.ProductManagementService;
import com.georgiimalyshev.storestudy.service.domain.store.Cart;
import com.georgiimalyshev.storestudy.service.domain.store.Product;
import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@Controller
public class ProductController {
	@Autowired
	public ProductController(ProductManagementService productManagementService, CartService cartService) {
		this.productManagementService = productManagementService;
		this.cartService = cartService;
	}

	private ProductManagementService productManagementService;
	private CartService cartService;

	// TODO move the "add product to cart" logic to Cart-related classes?
	@PostMapping("/add-to-cart")
	public String addToCart(@RequestParam(name = "id") int productId, @RequestParam(name = "quantity") int quantity,
			HttpSession httpSession) {
		String resultString = "error";
		try {
			// TODO cast will fail with exception if user is some other subtype of AppUserAbstract
			Customer customer = (Customer) httpSession.getAttribute("user");
			Cart cart = customer.getCart();
			Product product = productManagementService.getProductById(productId);
			cartService.addProductToCart(cart, product, quantity);
			resultString = "cart";
		} catch (NoSuchElementException ex) {
			// TODO handle exception correctly
			ex.printStackTrace();
		} catch (ClassCastException ex) {
			// TODO handle exception correctly
			ex.printStackTrace();
		}

		return "redirect:" + resultString;

	}
}
