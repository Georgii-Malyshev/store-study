package service.domain.store;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProductTest {
	private Product product1;
	private Product product2;
	private Product product3;
	private Product product4;
	private Product product5;
	
	@Before
	public void setUp() {
		System.out.println("initializing product objects for the test");
		product1 = new Product("name1", 10);
		product2 = new Product("name1", 10);
		product3 = new Product("name3", 10);
		product4 = new Product("name1", 40);
		product5 = new Product("name5", 50);
	}
	
	@Test
	public void whenEqualsEqualObjectsThenReturnTrue () {
		assertThat((product1.equals(product2)), is(true));
	}
	
	@Test
	public void whenEqualsNonEqualNamesEqualPricesThenReturnFalse () {
		assertThat((product1.equals(product3)), is(false));
	}
	
	@Test
	public void whenEqualsEqualNamesNonEqualPricesThenReturnFalse () {
		assertThat((product1.equals(product4)), is(false));
	}
	
	@Test
	public void whenEqualsNonEqualNamesAndPricesThenReturnFalse () {
		assertThat((product1.equals(product5)), is(false));
	}
}
