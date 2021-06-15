package domain.store;

import java.util.ArrayList;

public class ProductCategory {
    private int id;
    private String name;
    private ArrayList<Product> products;

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

    public ArrayList<Product> getProducts() {
	return products;
    }

    public void setProducts(ArrayList<Product> products) {
	this.products = products;
    }

    public void addProduct(Product product) {
	products.add(product);
    }
}
