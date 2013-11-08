package com.ece4894.model;

/**
 * @author Sahil Gupta
 * 
 *         This class represents a student object with it's several attributes.
 */

public class Student {

	private String UserName;
	private String Password;

	protected String FirstName;
	protected String LastName;
	protected Long GTID;

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public Long getGTID() {
		return GTID;
	}

	public void setGTID(Long gTID) {
		GTID = gTID;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
