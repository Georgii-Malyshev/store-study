package dao;

import domain.users.UserNull;
import domain.users.User;
import domain.users.UserReal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements Dao<User> {

    private User getFromResultSet(ResultSet resultSet) throws SQLException {
	User user = new UserReal();
	do {
	    user.setId(resultSet.getInt("user_id"));
	    user.setEmail(resultSet.getString("user_email"));
	    user.setPassword(resultSet.getString("user_password"));
	    user.setRoleId(resultSet.getInt("role_id"));
	    user.setMobilePhoneNumber(resultSet.getString("user_mobile_phone_number"));
	    user.setFirstName(resultSet.getString("user_first_name"));
	    user.setLastName(resultSet.getString("user_last_name"));
	    user.setShippingAddress(resultSet.getString("user_shipping_address"));
	} while (resultSet.next());
	return user;
    }

    @Override
    public User getById(int id) {
	ConnectionManager connectionManager = new ConnectionManager();
	Connection connection = connectionManager.getNewConnection();
	PreparedStatement preparedStatement = null;
	String selectById = "SELECT * FROM users WHERE user_id=?;";
	ResultSet resultSet = null;
	User user = null;

	try {
	    preparedStatement = connection.prepareStatement(selectById);
	    preparedStatement.setInt(1, id);
	    resultSet = preparedStatement.executeQuery();

	    if (resultSet.next()) {
		user = this.getFromResultSet(resultSet);
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
	return user;
    }

    public User findByCredentials(String email, String password) {
	ConnectionManager connectionManager = new ConnectionManager();
	Connection connection = connectionManager.getNewConnection();
	PreparedStatement preparedStatement = null;
	String selectByCredentials = "SELECT * FROM users WHERE user_email=? AND user_password=?;";
	ResultSet resultSet = null;
	User user = null;

	try {
	    preparedStatement = connection.prepareStatement(selectByCredentials);
	    preparedStatement.setString(1, email);
	    preparedStatement.setString(2, password);
	    resultSet = preparedStatement.executeQuery();

	    if (resultSet.next()) {
		user = this.getFromResultSet(resultSet);
	    } else {
		user = new UserNull();
	    }
	} catch (SQLException exception) {
	    System.out.println("Error selecting user from database. See stack trace.");
	    exception.printStackTrace();
	} finally {
	    connectionManager.closeConnectionAndResources(connection, preparedStatement, resultSet);
	}
	return user;
    }
}