package com.ece4894.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import java.sql.SQLException;

/**
 * @author Sahil Gupta
 * 
 *         This class contains methods to validate if the user exists in the
 *         database.
 */

public class StudentUserDao {

	public static boolean isUserExistent(String username, String password) {
		DBConnection connection = new DBConnection();

		Connection conn = connection.createConnection();
		try {
			String statement = "SELECT Username, Password from students ";
			PreparedStatement prep = conn.prepareStatement(statement);
			ResultSet rs = (ResultSet) prep.executeQuery();
			while (rs.next()) {
				String userNameString = rs.getString("Username");
				String passwordString = rs.getString("Password");
				if (username.equals(userNameString)
						&& password.equals(passwordString)) {
					prep.close();
					connection.closeConnection(conn);
					return true;
				}
			}
			prep.close();
			connection.closeConnection(conn);
			return false;
		} catch (SQLException e) {
		}
		connection.closeConnection(conn);
		return false;
	}

}
