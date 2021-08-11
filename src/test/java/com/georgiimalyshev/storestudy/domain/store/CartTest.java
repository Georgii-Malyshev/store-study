package com.georgiimalyshev.storestudy.domain.store;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.georgiimalyshev.storestudy.dao.CartDao;

@ExtendWith(MockitoExtension.class)
public class CartTest {
	private Product product1 = new Product();
	private Product product2 = new Product();
	private Product product3 = new Product();
	private CartItem item1 = new CartItem();
	private CartItem item2 = new CartItem();
	private CartItem item3 = new CartItem();
	
	@BeforeEach
	public void productsSetUp() {
		product1.setName("productName1");
		product1.setPrice(10);
		product2.setName("productName2");
		product2.setPrice(200);
		product3.setName("productName3");
		product3.setPrice(3000);
	}
	
	@BeforeEach
	public void itemsSetUp() {
		item1.setProduct(product1);
		item1.setQuantity(100);
		item2.setProduct(product2);
		item2.setQuantity(20);
		item3.setProduct(product3);
		item3.setQuantity(3);
	}
	
	@Mock
	private CartDao cartDao;
	
	@InjectMocks
	private Cart cart;
	
	@Test
	public void givenCartHasSeveralItems_whenCalculateTotalPrice_thenReturnTotalPrice() {
		cart.addItem(item1);
		cart.addItem(item2);
		cart.addItem(item3);
		
		int totalPrice = cart.calculateTotalPrice();
		int expectedTotalPrice = (100 * 10) + (20 * 200) + (3 * 3000);
		
		assertEquals(expectedTotalPrice, totalPrice);
	}
}
