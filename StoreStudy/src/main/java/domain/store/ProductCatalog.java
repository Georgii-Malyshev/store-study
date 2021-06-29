package domain.store;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import appmanagement.EntityManagerFactoryManager;
import dao.ProductCatalogDao;
import dao.ProductCategoryDao;

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
		// constructor implementation
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_catalog_generator")
	@SequenceGenerator(name = "product_catalog_generator", sequenceName = "product_catalog_id_seq")
	@Column(updatable = false, nullable = false)
	private int id;

	@OneToMany(mappedBy = "productCatalog", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProductCategory> productCategories;

	public Set<ProductCategory> getProductCategories() {
		return productCategories;
	}
	
	public void setProductCategories(Set<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	public void populateFromStorage() {
		EntityManager entityManager = EntityManagerFactoryManager.createEntityManager();
		ProductCatalogDao productCatalogDao = new ProductCatalogDao(entityManager);
		this.setProductCategories(productCatalogDao.getAllCategories(this));
	}
}
