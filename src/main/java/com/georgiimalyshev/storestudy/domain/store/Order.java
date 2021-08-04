package com.georgiimalyshev.storestudy.domain.store;

import com.georgiimalyshev.storestudy.domain.users.AppUser;

public class Order {
	private int id;
	private AppUser user;
	private OrderStatus status;
	private String shippingAddress;
	private int price;
}
