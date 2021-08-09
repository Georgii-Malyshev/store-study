package com.georgiimalyshev.storestudy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.georgiimalyshev.storestudy.domain.store.Cart;
import com.georgiimalyshev.storestudy.domain.store.Product;
import com.georgiimalyshev.storestudy.domain.users.Customer;
import com.georgiimalyshev.storestudy.service.CartService;
import com.georgiimalyshev.storestudy.service.ProductManagementService;
import com.georgiimalyshev.storestudy.util.ErrorMessages;

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
		String resultString = "error";
		Customer customer = (Customer) httpSession.getAttribute("user");
		try {
			if (customer != null) {
				Cart cart = customer.getCart();
				int cartId = cart.getId();
				cart = cartService.getCartByIdAndFetchCartItems(cartId);
				model.addAttribute("cart", cart);
				resultString = "cart";
			} else {
				model.addAttribute("errorMessage", ErrorMessages.notLoggedInErrorMessage);
			}
		} catch (ClassCastException ex) {
			model.addAttribute("errorMessage", ErrorMessages.notACustomerErrorMessage);
		}
		return resultString;
	}

	@PostMapping("/add-to-cart")
	public String addToCart(@RequestParam(name = "id") int productId, @RequestParam(name = "quantity") int quantity,
			HttpSession httpSession, Model model) {
		String resultString = "error";
		try {
			Customer customer = (Customer) httpSession.getAttribute("user");
			if (customer != null) {
				Cart cart = customer.getCart(); // every customer MUST ALWAYS have a corresponding cart
				Product product = productManagementService.getProductById(productId);
				cartService.addProductToCart(cart, product, quantity);
				resultString = "redirect:shopping-cart";
			} else {
				model.addAttribute("errorMessage", ErrorMessages.notLoggedInErrorMessage);
			}
		} catch (ClassCastException ex) {
			model.addAttribute("errorMessage", ErrorMessages.notACustomerErrorMessage);
		}
		return resultString;
	}

	@PostMapping("/remove-cart-item")
	public String removeCartItem(@RequestParam(name = "id") int itemId, HttpSession httpSession, Model model) {
		String resultString = "error";
		try {
			Customer customer = (Customer) httpSession.getAttribute("user");
			if (customer != null) {
				Cart cart = customer.getCart();
				cartService.removeCartItemById(cart, itemId);
				resultString = "redirect:shopping-cart";
			} else {
				model.addAttribute("errorMessage", ErrorMessages.notLoggedInErrorMessage);
			}
		} catch (ClassCastException ex) {
			model.addAttribute("errorMessage", ErrorMessages.notACustomerErrorMessage);
		}
		return resultString;
	}
}
