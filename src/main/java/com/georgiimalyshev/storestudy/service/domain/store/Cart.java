package com.georgiimalyshev.storestudy.service.domain.store;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	private int id;
	@OneToMany(mappedBy = "cart")
	private Set<CartItem> cartItems = new HashSet<CartItem>();
	@OneToOne
	@MapsId
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void clear() {
		// TODO implement method to clear all items from the cart
	}

	public int calculateTotalPrice() {
		int totalPrice = 0;
		// TODO implement method to calculate total price
		return totalPrice;
	}
}