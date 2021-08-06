package com.georgiimalyshev.storestudy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public Cart getCartByIdAndFetchCartItems(int id) {
		Optional<Cart> optionalOfCart = cartDao.findByIdAndFetchCartItems(id);
		Cart cart = optionalOfCart.orElseThrow();
		return cart;
	}

	@Transactional
	public void addProductToCart(Cart cart, Product product, int quantity) {
		int id = cart.getId();
		cart = getCartByIdAndFetchCartItems(id);
		CartItem cartItem = new CartItem(cart, product, quantity);
		cart.addItem(cartItem);
		cartDao.merge(cart);
		// TODO throw an informative exception if something went wrong
	}

	@Transactional
	public void clearCart(Cart cart) {
		int id = cart.getId();
		cart = getCartByIdAndFetchCartItems(id);
		cart.clearItems();
		cartDao.merge(cart);
	}
}