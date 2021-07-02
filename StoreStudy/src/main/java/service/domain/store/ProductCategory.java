package service.domain.store;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Table;

import dao.ProductCategoryDao;
import service.appmanagement.AppContextManager;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "product_category")
public class ProductCategory {
	public ProductCategory() {
		// constructor implementation
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_category_generator")
	@SequenceGenerator(name = "product_category_generator", sequenceName = "product_category_id_seq")
	@Column(updatable = false, nullable = false)
	private int id;
	@ManyToOne
	@JoinColumn(name = "product_catalog_id")
	private ProductCatalog productCatalog;
	private String name;
	@OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Product> products;

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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	// TODO must double-check equals() and hashCode() later!
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if ((object == null) || (getClass() != object.getClass()))
			return false;
		ProductCategory objectAsProductCategory = (ProductCategory) object;
		// TODO must check if products collections really are equal!
		return (this.id == objectAsProductCategory.getId() && this.name == objectAsProductCategory.getName()
				&& this.products == objectAsProductCategory.getProducts());
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(id);
		return result;
	}

	public void populateFromStorage() {
		EntityManagerFactory entityManagerFactory = AppContextManager.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductCategoryDao productCategoryDao = new ProductCategoryDao(entityManager);
		this.setProducts(productCategoryDao.getAllProducts(this));
	}
}