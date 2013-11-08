package com.ece4894.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ece4894.model.VisitorRequest;
import com.mysql.jdbc.ResultSet;

/**
 * @author Sahil Gupta
 * 
 *         This class contains methods to establish connection with the
 *         database.
 */

public class RequestDao {

	public static boolean putRequest(VisitorRequest request) {
		DBConnection connection = new DBConnection();

		Connection conn = connection.createConnection();
		try {
			String statement = "INSERT INTO visitorrequest(RequestNum, GTID, FirstName, LastName, TimeIn, TimeOut, GuestOf) VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prep = conn.prepareStatement(statement);
			prep.setInt(1, request.getRequestNumber());
			prep.setLong(2, request.getGTID());
			prep.setString(3, request.getFirstName());
			prep.setString(4, request.getLastName());
			prep.setBigDecimal(5, request.getDateTimeIn());
			prep.setBigDecimal(6, request.getDateTimeOut());

			prep.setLong(7, request.getGuestOfGTID());
			prep.executeUpdate();
			prep.close();
			connection.closeConnection(conn);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static int countRequest() {
		DBConnection connection = new DBConnection();
		int count = 0;

		Connection conn = connection.createConnection();
		try {
			String statement = "SELECT RequestNum FROM VISITORREQUEST";
			PreparedStatement prep = conn.prepareStatement(statement);
			ResultSet rs = (ResultSet) prep.executeQuery();
			while (rs.next()) {
				count++;
			}
			prep.close();
			connection.closeConnection(conn);
			return count;
		} catch (SQLException e) {
		}
		connection.closeConnection(conn);
		return count;
	}

	public static boolean isRequestValid(VisitorRequest request) {
		DBConnection connection = new DBConnection();

		Connection conn = connection.createConnection();
		try {
			// check first if the GTID and other information is valid for the
			// visitor from the database using the student table
			String statement = "SELECT GTID, FirstName, LastName FROM students";
			PreparedStatement prep = conn.prepareStatement(statement);
			ResultSet rs = (ResultSet) prep.executeQuery();
			while (rs.next()) {
				if (new Long(rs.getLong("GTID")).equals(request.getGTID())
						&& rs.getString("FirstName").equals(
								request.getFirstName())
						&& rs.getString("LastName").equals(
								request.getLastName())) {
					return true;
				}
			}
			
			prep.close();
			connection.closeConnection(conn);
		} catch (SQLException e) {
		}
		connection.closeConnection(conn);

		return false;
	}
	
	public static String getBuilding(Long GTIDNumber) {
		DBConnection connection = new DBConnection();

		Connection conn = connection.createConnection();
		try {
			String statement = "SELECT GTID, Residence FROM students";
			PreparedStatement prep = conn.prepareStatement(statement);
			ResultSet rs = (ResultSet) prep.executeQuery();
			while (rs.next()) {
				if(new Long(rs.getLong("GTID")).equals(GTIDNumber)){
					return rs.getString("Residence");
				}
			}
			
			prep.close();
			connection.closeConnection(conn);
		} catch (SQLException e) {
		}
		connection.closeConnection(conn);

		return "";
	}

}
