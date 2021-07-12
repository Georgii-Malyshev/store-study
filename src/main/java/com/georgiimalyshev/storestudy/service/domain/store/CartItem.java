package com.georgiimalyshev.storestudy.service.domain.store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item")
class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_generator")
	@SequenceGenerator(name = "cart_item_generator", sequenceName = "cart_item_id_seq")
	@Column(updatable = false, nullable = false)
	private int id;
	@ManyToOne
	private Product product;
	private int quantity;
}
