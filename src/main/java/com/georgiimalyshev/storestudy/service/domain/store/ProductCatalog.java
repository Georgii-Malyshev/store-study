package com.georgiimalyshev.storestudy.service.domain.store;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

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

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_catalog_generator")
	@SequenceGenerator(name = "product_catalog_generator", sequenceName = "product_catalog_id_seq")
	@Column(updatable = false, nullable = false)
	private int id;

	@OneToMany(mappedBy = "productCatalog", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<ProductCategory> productCategories;

	public Set<ProductCategory> getProductCategories() {
		return productCategories;
	}
}
