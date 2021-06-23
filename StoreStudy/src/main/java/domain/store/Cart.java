package domain.store;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import domain.users.Customer;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_generator")
	@SequenceGenerator(name = "cart_generator", sequenceName = "cart_id_seq")
	@Column(updatable = false, nullable = false)
	private int id;
	private ArrayList<Product> products;
	@OneToOne(mappedBy = "cart")
	private Customer customer;

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	/*
	 * public void addProductById(int id) { ProductDao productDao = new
	 * ProductDao(); Product product = productDao.getById(id);
	 * products.add(product); }
	 */

	/*
	 * public void removeProductById (int id) { ProductDao productDao = new
	 * ProductDao(); Product product = productDao.getById(id);
	 * products.remove(product); }
	 */

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