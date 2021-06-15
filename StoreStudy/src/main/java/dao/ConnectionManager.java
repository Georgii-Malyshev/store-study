package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ConnectionManager {
    private Connection connection = null;

    public Connection getNewConnection() {
	try {
	    String jdbcUrl = "jdbc:postgresql://127.0.0.1:5432/storedb_dev";
	    String jdbcDriver = "org.postgresql.Driver";
	    Class.forName(jdbcDriver);
	    connection = DriverManager.getConnection(jdbcUrl, "postgres", "password");
	} catch (ClassNotFoundException exception) {
	    System.out.println("Error when loading JDBC driver. See stack trace.");
	    exception.printStackTrace();
	} catch (SQLException exception) {
	    System.out.println("Error when connecting to database. See stack trace.");
	    exception.printStackTrace();
	}
	return connection;
    }

    public void closeConnectionAndResources(Connection connection, PreparedStatement preparedStatement,
	    ResultSet resultSet) {
	try {
	    resultSet.close();
	    preparedStatement.close();
	    connection.close();
	} catch (SQLException exception) {
	    System.out.println("Error closing result set, statement or connection. See stack trace.");
	    exception.printStackTrace();
	} catch (NullPointerException exception) {
	    System.out.println(
		    "Error closing result set, statement or connection (Null Pointer Exception). See stack trace.");
	    exception.printStackTrace();
	}
    }
}
