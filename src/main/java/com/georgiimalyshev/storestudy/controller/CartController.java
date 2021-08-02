package com.georgiimalyshev.storestudy.controller;

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

	
	@ModelAttribute
	public Cart cart() {
		return new Cart();
	} // TODO consider if this block of code is actualy necessary
	
	@GetMapping("/cart")
	public String cartPage(HttpSession httpSession, Model model) {
		Logger logger = LogManager.getLogger();  // TODO remove
		logger.info("logger for CartController created");  // TODO remove
		Customer customer = (Customer) httpSession.getAttribute("user");		
		if (customer != null) {
			logger.info("got non-null customer from httpSession");  // TODO remove
			Cart cart = customer.getCart();
			logger.info("got customer's cart"); // TODO remove
			int cartId = cart.getId();
			cartService.getCartByIdAndFetchCartItems(cartId);
			model.addAttribute("cart", cart);
			logger.info("added cart to model");  // TODO remove
		} else {
			logger.info("got NULL customer from httpSession");  // TODO remove
		}
		
		return "cart";
	}
	
	@PostMapping("/add-to-cart")
	public String addToCart(@RequestParam(name = "id") int productId, @RequestParam(name = "quantity") int quantity,
			HttpSession httpSession) {
		String resultString = "error";
		// TODO cast will fail with exception if user is some other subtype of AppUserAbstract
		Customer customer = (Customer) httpSession.getAttribute("user");
		Cart cart = customer.getCart();
		Product product = productManagementService.getProductById(productId);
		cartService.fetchCartItemsAndAddProductToCart(cart, product, quantity);
		resultString = "cart";
		// TODO handle possible NoSuchElementException, ClassCastException etc. 

		return "redirect:" + resultString;
	}
}
