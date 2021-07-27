package com.georgiimalyshev.storestudy.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.georgiimalyshev.storestudy.dao.ProductCatalogDao;
import com.georgiimalyshev.storestudy.dao.ProductCategoryDao;
import com.georgiimalyshev.storestudy.dao.ProductDao;
import com.georgiimalyshev.storestudy.service.domain.store.Product;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;

@ExtendWith(MockitoExtension.class)
public class ProductManagementServiceTest {
	private int id;
	private Product product1 = new Product();
	
	@BeforeEach
	public void productSetUp() {
		id = 1;
		product1.setId(1);
		product1.setName("productName1");
		product1.setPrice(10);
	}
	
	@Mock
	private ProductCatalogDao productCatalogDao;
	@Mock
	private ProductCategoryDao productCategoryDao;
	@Mock
	private ProductDao productDao;
	
	@InjectMocks
	private ProductManagementService productManagementService;
	
	private void setUpProductDaoMockToReturnOptionalOfProductDao(Optional<Product> optional) {
		when(productDao.findById(id)).thenReturn(optional);
	}
	
	@Test
	public void givenCorrectId_whenGetProductById_thenReturnCorrespondingProduct() {
		id = 1;
		
		Optional<Product> optionalOfProduct1 = Optional.of(product1);
		setUpProductDaoMockToReturnOptionalOfProductDao(optionalOfProduct1);
		Product product = productManagementService.getProductById(id);
		
		// TODO use equals() instead
		assertAll(() -> assertEquals(1, product.getId()),
				() -> assertEquals("productName1", product.getName()),
				() -> assertEquals(10, product.getPrice()));
	}
}