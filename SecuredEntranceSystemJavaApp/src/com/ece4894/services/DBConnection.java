package com.ece4894.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Sahil Gupta
 * 
 *         This class helps in creating and closing the connection with the data
 *         base.
 */

public class DBConnection {
	String connStr, user, password, driverName;
	String serverName = "localhost";
	String mydatabase = "ece4894project";

	public DBConnection() {

		connStr = "jdbc:mysql://" + serverName + "/" + mydatabase;
		user = "root";
		password = "ece4894project3#";
		driverName = "com.mysql.jdbc.Driver";
	}

	public Connection createConnection() {
		Connection conn = null;
		try {
			Class.forName(driverName).newInstance();
			conn = DriverManager.getConnection(connStr, user, password);
			if (!conn.isClosed()) {
				System.out.println("Successfully connected to "
						+ "MySQL server using TCP/IP...");
				return conn;
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		return null;
	}

	public boolean closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				return true;
			}
			return false;
		} catch (SQLException e) {
		}
		return false;
	}
}
