package domain.store;

import dao.ProductDao;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
	return products;
    }

    public void setProducts(ArrayList<Product> products) {
	this.products = products;
    }

    /*
     * public void addProductById(int id) { ProductDao productDao = new
     * ProductDao(); Product product = productDao.getById(id);
     * products.add(product); }
     */

    /*
     * public void removeProductById (int id) { ProductDao productDao = new
     * ProductDao(); Product product = productDao.getById(id);
     * products.remove(product); }
     */

    public void clear() {
	products.clear();
    }

    public int calculateTotalPrice() {
	int totalPrice = 0;
	for (Product product : products) {
	    totalPrice = totalPrice + product.getPrice();
	}
	return totalPrice;
    }
}