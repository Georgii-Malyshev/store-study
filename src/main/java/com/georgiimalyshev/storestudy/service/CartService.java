package com.georgiimalyshev.storestudy.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

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
		Set<CartItem> cartItems = cart.getCartItems();
		Product itemProduct;
		boolean itemFound = false;
		
		for (CartItem cartItem : cartItems) {
			itemProduct = cartItem.getProduct();
			if (itemProduct.equals(product)) {
				int updatedQuantity = (cartItem.getQuantity()) + quantity; 
				cartItem.setQuantity(updatedQuantity);
				itemFound = true;
				break;
			}
		}
		if (!itemFound) {
			CartItem cartItem = new CartItem(product, quantity);
			cart.addItem(cartItem);
		}
		
		cartDao.merge(cart);
	}

	@Transactional
	public void removeCartItemById(Cart cart, int itemId) {
		int id = cart.getId();
		cart = getCartByIdAndFetchCartItems(id);
		
		if (cart.getCartItems().removeIf( item -> item.getId() == itemId )) {
			cartDao.merge(cart);
		} else {
			throw new NoSuchElementException();
		} 
	}
	
	@Transactional
	public void clearCart(Cart cart) {
		int id = cart.getId();
		cart = getCartByIdAndFetchCartItems(id);
		cart.clearItems();
		cartDao.merge(cart);
	}
}