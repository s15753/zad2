package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import domain.User;

public class ConnectHSQLDB {

	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;

	}
}
