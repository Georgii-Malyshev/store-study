package dao;

import domain.store.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao implements Dao<Product> {
    @Override
    public Product getById(int id) throws NoSuchRecordException {
	ConnectionManager connectionManager = new ConnectionManager();
	Connection connection = connectionManager.getNewConnection();
	PreparedStatement preparedStatement = null;
	String selectById = "SELECT * FROM products WHERE product_id=?";
	ResultSet resultSet = null;
	Product product = null;

	try {
	    preparedStatement = connection.prepareStatement(selectById);
	    preparedStatement.setInt(1, id);
	    resultSet = preparedStatement.executeQuery();
	    if (resultSet.next()) {
		product = new Product();
		product.setId(resultSet.getInt("product_id"));
		product.setName(resultSet.getString("product_name"));
		product.setPrice(resultSet.getInt("product_price"));
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
	return product;
    }
}
