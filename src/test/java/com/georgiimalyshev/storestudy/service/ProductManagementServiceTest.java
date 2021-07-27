package com.georgiimalyshev.storestudy.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
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
import com.georgiimalyshev.storestudy.service.domain.store.ProductCategory;
import com.georgiimalyshev.storestudy.service.domain.users.AppUser;

@ExtendWith(MockitoExtension.class)
public class ProductManagementServiceTest {
	private int id;
	private Product product1 = new Product();
	private ProductCategory productCategory1 = new ProductCategory();

	@BeforeEach
	public void productSetUp() {
		product1.setId(1);
		product1.setName("productName1");
		product1.setPrice(10);
	}

	@BeforeEach
	public void productCategorySetUp() {
		productCategory1.setId(1);
		productCategory1.setName("productCategoryName1");
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

	private void setUpProductCategoryDaoMockToReturnOptionalOfProductCategoryDao(Optional<ProductCategory> optional) {
		when(productCategoryDao.findById(id)).thenReturn(optional);
	}

	@Test
	public void givenCorrectId_whenGetProductById_thenReturnCorrespondingProduct() {
		id = 1;

		Optional<Product> optionalOfProduct1 = Optional.of(product1);
		setUpProductDaoMockToReturnOptionalOfProductDao(optionalOfProduct1);
		Product product = productManagementService.getProductById(id);

		// TODO use equals() instead
		assertAll(() -> assertEquals(1, product.getId()), () -> assertEquals("productName1", product.getName()),
				() -> assertEquals(10, product.getPrice()));
	}

	@Test
	public void givenNonExistingId_whenGetProductById_thenThrowNoSuchElementException() {
		id = 99;

		assertThrows(NoSuchElementException.class, () -> {
			productManagementService.getProductById(id);
		});
	}

	@Test
	public void givenCorrectId_whenGetProductCategoryById_thenReturnCorrespondingProductCategory() {
		id = 1;

		Optional<ProductCategory> optionalOfProductCategory1 = Optional.of(productCategory1);
		setUpProductCategoryDaoMockToReturnOptionalOfProductCategoryDao(optionalOfProductCategory1);
		ProductCategory productCategory = productManagementService.getProductCategoryById(id);

		// TODO use equals() instead
		assertAll(() -> assertEquals(1, productCategory.getId()),
				() -> assertEquals("productCategoryName1", productCategory.getName()));
	}
}