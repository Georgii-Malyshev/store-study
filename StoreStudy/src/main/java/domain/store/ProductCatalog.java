package domain.store;

import java.util.ArrayList;

public class ProductCatalog {
    // no-arg constructor
    public ProductCatalog() {
	//constructor implementation
    }

    private ArrayList<ProductCategory> productCategories;

    public ArrayList<ProductCategory> getProductCategories() {
	return productCategories;
    }

    public void setProductCategories(ArrayList<ProductCategory> productCategories) {
	this.productCategories = productCategories;
    }

    public void addProductCategory(ProductCategory productCategory) {
	productCategories.add(productCategory);
    }
}
