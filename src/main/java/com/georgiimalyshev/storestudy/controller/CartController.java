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
import com.georgiimalyshev.storestudy.service.domain.store.CartItem;
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

	@GetMapping("/shopping-cart")
	public String cartPage(HttpSession httpSession, Model model) {
		Customer customer = (Customer) httpSession.getAttribute("user");
		if (customer != null) {
			Cart cart = customer.getCart();
			int cartId = cart.getId();
			cart = cartService.getCartByIdAndFetchCartItems(cartId);
			model.addAttribute("cart", cart);
		} else {
			model.addAttribute("errorMessage",
					"could not display shopping cart because no customer was found in session "
							+ "(not logged in or session expired?)");
			return "error";
		}
		return "cart";
	}

	@PostMapping("/add-to-cart")
	public String addToCart(@RequestParam(name = "id") int productId, @RequestParam(name = "quantity") int quantity,
			HttpSession httpSession) {
		String resultString = "error";
		// TODO cast will fail with exception if user is some other subtype of
		// AppUserAbstract
		Customer customer = (Customer) httpSession.getAttribute("user");
		Cart cart = customer.getCart();
		Product product = productManagementService.getProductById(productId);
		cartService.fetchCartItemsAndAddProductToCart(cart, product, quantity);
		resultString = "shopping-cart";
		// TODO handle possible NoSuchElementException, ClassCastException etc.
		return "redirect:" + resultString;
	}
}
