package com.georgiimalyshev.storestudy.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.georgiimalyshev.storestudy.dao.CartDao;
import com.georgiimalyshev.storestudy.domain.store.Cart;
import com.georgiimalyshev.storestudy.domain.store.CartItem;
import com.georgiimalyshev.storestudy.domain.store.Product;

@Service
public class CartService {
	private CartDao cartDao;

	public CartService(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public Cart getCartByIdAndFetchCartItems(int id) {
		Optional<Cart> optionalOfCart = cartDao.findByIdAndFetchCartItems(id);
		Cart cart = optionalOfCart.orElseThrow();
		return cart;
	}

	public void fetchCartItemsAndAddProductToCart(Cart cart, Product product, int quantity) {
		int cartId = cart.getId();
		cart = getCartByIdAndFetchCartItems(cartId);
		Set<CartItem> cartItems = cart.getCartItems();
		CartItem cartItem = new CartItem(cart, product, quantity);
		cartItems.add(cartItem);
		cart.setCartItems(cartItems);
		cartDao.merge(cart);
		// TODO throw an informative exception if something went wrong and product
		// wasn't added
	}
}