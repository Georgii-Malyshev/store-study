package service.domain.store;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Table;

import appmanagement.AppContextManager;
import dao.ProductCatalogDao;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

@Entity
@Table(name = "product_catalog")
public class ProductCatalog {
	// no-arg constructor
	public ProductCatalog() {
		// TODO constructor implementation
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_catalog_generator")
	@SequenceGenerator(name = "product_catalog_generator", sequenceName = "product_catalog_id_seq")
	@Column(updatable = false, nullable = false)
	private int id;

	@OneToMany(mappedBy = "productCatalog", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<ProductCategory> productCategories;

	public Set<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(Set<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}
}
