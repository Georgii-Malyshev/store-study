package com.georgiimalyshev.storestudy.service.domain.store;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.georgiimalyshev.storestudy.service.domain.users.Customer;

@Entity
public class Cart {
	@Id
	private int id;
	private HashSet<Product> products;
	@OneToOne
	@MapsId
	@JoinColumn(name = "id")
	private Customer customer;
	
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = (HashSet<Product>) products;
	}

	public void clear() {
		products.clear();
	}

	public int calculateTotalPrice() {
		int totalPrice = 0;
		for (Product product : products) {
			totalPrice = totalPrice + product.getPrice();
		}
		return totalPrice;
	}
}