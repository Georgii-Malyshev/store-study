package domain.store;

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
		if ((object == null) || (getClass() != object.getClass()))
			return false;
		Product objectAsProduct = (Product) object;
		return (this.id == objectAsProduct.getId() && this.name == objectAsProduct.getName()
				&& this.price == objectAsProduct.getPrice());
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(id);
		return result;
	}
}
