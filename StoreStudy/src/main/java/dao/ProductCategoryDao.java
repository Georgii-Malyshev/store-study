package dao;

import domain.store.ProductCategory;

import java.sql.*;
import java.util.ArrayList;

public class ProductCategoryDao implements Dao<ProductCategory> {
    private ProductCategory getFromResultSet(ResultSet resultSet) throws SQLException {
	ProductCategory productCategory = new ProductCategory();
	if (resultSet.next()) {
	    productCategory.setId(resultSet.getInt("product_category_id"));
	    productCategory.setName(resultSet.getString("product_category_name"));
	}
	return productCategory;
    }

    @Override
    public ProductCategory getById(int id) {
	ConnectionManager connectionManager = new ConnectionManager();
	Connection connection = connectionManager.getNewConnection();
	PreparedStatement preparedStatement = null;
	String selectById = "SELECT * FROM product_categories WHERE product_category_id=?;";
	ResultSet resultSet = null;
	ProductCategory productCategory = null;

	try {
	    preparedStatement = connection.prepareStatement(selectById);
	    preparedStatement.setInt(1, id);
	    resultSet = preparedStatement.executeQuery();

	    if (resultSet.next()) {
		productCategory = this.getFromResultSet(resultSet);
	    } else {
		throw new NoSuchRecordException("No matching record for this ID was found in the data storage.");
	    }
	} catch (SQLException exception) {
	    exception.printStackTrace();
	} catch (NoSuchRecordException exception) {
	    exception.printStackTrace();
	} finally {
	    connectionManager.closeConnectionAndResources(connection, preparedStatement, resultSet);
	}
	return productCategory;
    }

    public ArrayList<ProductCategory> getAllProductCategories() {
	ConnectionManager connectionManager = new ConnectionManager();
	Connection connection = connectionManager.getNewConnection();
	PreparedStatement preparedStatement = null;
	String selectById = "SELECT * FROM product_categories;";
	ResultSet resultSet = null;
	ArrayList<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();

	try {
	    preparedStatement = connection.prepareStatement(selectById, ResultSet.TYPE_SCROLL_INSENSITIVE,
		    ResultSet.CONCUR_READ_ONLY);
	    resultSet = preparedStatement.executeQuery();

	    // determine how many rows resultSet contains
	    resultSet.last();
	    int numberOfRows = resultSet.getRow();
	    resultSet.beforeFirst();

	    // iterate through the resultSet rows
	    int i = 1;
	    while (i <= numberOfRows) {
		productCategoryList.add(this.getFromResultSet(resultSet));
		i++;
	    }
	} catch (SQLException exception) {
	    exception.printStackTrace();
	} finally {
	    connectionManager.closeConnectionAndResources(connection, preparedStatement, resultSet);
	}

	return productCategoryList;
    }
}
