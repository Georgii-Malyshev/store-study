package com.georgiimalyshev.storestudy.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.georgiimalyshev.storestudy.dao.CartDao;
import com.georgiimalyshev.storestudy.service.domain.store.Cart;
import com.georgiimalyshev.storestudy.service.domain.store.CartItem;
import com.georgiimalyshev.storestudy.service.domain.store.Product;

@Service
public class CartService {
	private CartDao cartDao;
	
	public CartService(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	
	public void addProductToCart(Cart cart, Product product, int quantity) {		
		int cartId = cart.getId();
		Optional<Cart> optionalOfCart = cartDao.findByIdAndFetchCartItems(cartId);
		if (optionalOfCart.isPresent()) {
			cart = optionalOfCart.get();
			Set<CartItem> cartItems = cart.getCartItems();
			CartItem cartItem = new CartItem(cart, product, quantity);
			cartItems.add(cartItem);
			cart.setCartItems(cartItems);
			cartDao.persist(cart);
		}

		// TODO throw an exception if something went wrong and product wasn't added
	}
}