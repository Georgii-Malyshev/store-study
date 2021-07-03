package service.domain.store;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Product {
	public Product() {
	}

	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
	@SequenceGenerator(name = "product_generator", sequenceName = "product_id_seq")
	@Column(updatable = false, nullable = false)
	private int id;
	private String name;
	private int price;
	@ManyToOne
	@JoinColumn(name = "product_category_id")
	private ProductCategory productCategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// TODO must double-check equals() and hashCode() later!
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (getClass() != object.getClass())
			return false;
		Product other = (Product) object;
		return (this.id == other.getId());
	}

	@Override 
	// can NOT use generated ID here because that ID doesn't exist before the entity is
	// persisted and the hash code isn't allowed to change after the object is added
	// to a Set
	// TODO try to think of some better implementation, current one is THE WORST possible in terms of performance
	public int hashCode() {
		int prime = 13;
		return prime;
	}
}
