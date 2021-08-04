package com.georgiimalyshev.storestudy.controller;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.georgiimalyshev.storestudy.service.CartService;
import com.georgiimalyshev.storestudy.service.ProductManagementService;
import com.georgiimalyshev.storestudy.service.domain.store.Cart;
import com.georgiimalyshev.storestudy.service.domain.store.Product;
import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@Controller
public class CartController {
	@Autowired
	public CartController(CartService cartService, ProductManagementService productManagementService) {
		this.cartService = cartService;
		this.productManagementService = productManagementService;
	}

	private CartService cartService;
	private ProductManagementService productManagementService;
	private String NotLoggedInErrorMessage = "could not access shopping cart: no customer was found in session; "
			+ "not logged in or session expired?";
	private String NotACustomerErrorMessage = "could not access shopping cart: "
			+ "logged in as some type of user other than customer; "
			+ "log in using a customer account to be able to access a shopping cart";

	@GetMapping("/shopping-cart")
	public String cartPage(HttpSession httpSession, Model model) {
		String resultString = "error";
		Customer customer = (Customer) httpSession.getAttribute("user");
		if (customer != null) {
			Cart cart = customer.getCart();
			int cartId = cart.getId();
			cart = cartService.getCartByIdAndFetchCartItems(cartId);
			model.addAttribute("cart", cart);
			resultString = "cart";
		} else {
			model.addAttribute("errorMessage", NotLoggedInErrorMessage);
		}
		return resultString;
	}

	@PostMapping("/add-to-cart")
	public String addToCart(@RequestParam(name = "id") int productId, @RequestParam(name = "quantity") int quantity,
			HttpSession httpSession, Model model) {
		String resultString = "error";
		try {
			Customer customer = (Customer) httpSession.getAttribute("user");
			Cart cart = customer.getCart(); // every customer MUST ALWAYS have a corresponding cart
			Product product = productManagementService.getProductById(productId);
			cartService.fetchCartItemsAndAddProductToCart(cart, product, quantity);
			resultString = "redirect:shopping-cart";
		} catch (ClassCastException ex) {
			model.addAttribute("errorMessage", NotACustomerErrorMessage);
		} catch (NoSuchElementException ex) {
			model.addAttribute("errorMessage", NotLoggedInErrorMessage);
		}
		return resultString;
	}
}
