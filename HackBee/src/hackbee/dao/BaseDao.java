package hackbee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hackbee.exceptions.DaoException;

public abstract class BaseDao {
	private String dbDriver;
	private String dbUrl;
	private String dbUser;
	private String dbPassword;

	public BaseDao() throws DaoException {
		dbDriver = "com.mysql.jdbc.Driver";
		dbUrl = "jdbc:mysql://localhost/hackbeedb";
		dbUser = "root";
		dbPassword = "password1";

		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			throw new DaoException("Unable to load the class", e);
		}
	}

	protected Connection getConnection() throws DaoException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			return connection;
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DaoException("Unable to get the connection", e);
		}
	}

	protected void closeConnection(Connection connection) throws DaoException {
		if (connection != null)
			try {

				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DaoException("Unable to close the connection", e);
			}
	}

	protected void closeStatement(Statement statement) throws DaoException {
		if (statement != null)
			try {

				statement.close();
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DaoException("Unable to close the statement", e);
			}
	}

	protected void closeResultSet(ResultSet resultSet) throws DaoException {
		if (resultSet != null)
			try {

				resultSet.close();
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DaoException("Unable to close the resultSet", e);
			}
	}

}