package com.georgiimalyshev.storestudy.domain.store;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.georgiimalyshev.storestudy.domain.users.Customer;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@Column(name = "customer_id")
	private int id;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<CartItem> cartItems = new HashSet<CartItem>();
	@OneToOne(fetch = FetchType.LAZY) // TODO add optional=false?
	@MapsId
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Cart() {
	}
	
	public Cart(Customer customer) {
		this.customer = customer;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<CartItem> getCartItems() {
		return this.cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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