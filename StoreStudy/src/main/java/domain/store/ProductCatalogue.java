package domain.store;

import dao.ProductCategoryDao;

import java.util.ArrayList;

public class ProductCatalogue {
    // no-arg constructor
    public ProductCatalogue() {
	ProductCategoryDao productCategoryDao = new ProductCategoryDao();
	productCategories = productCategoryDao.getAllProductCategories();
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
