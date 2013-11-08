package com.ece4894.model;

/**
 * @author Sahil Gupta
 * 
 *         This class represents a resident object which is a student residing
 *         in a residence hall.
 */

public class Resident extends Student {

	private String ResidenceHall;

	public String getResidenceHall() {
		return ResidenceHall;
	}

	public void setResidenceHall(String residenceHall) {
		ResidenceHall = residenceHall;
	}

}
