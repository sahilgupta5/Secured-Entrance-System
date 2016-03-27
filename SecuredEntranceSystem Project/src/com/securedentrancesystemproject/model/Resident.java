package com.securedentrancesystemproject.model;

public class Resident {
	
	private String rFirstName;
	private String rLastName;
	private String rGTid;
	
	public String getrFirstName() {
		return rFirstName;
	}
	public void setrFirstName(String rFirstName) {
		this.rFirstName = rFirstName;
	}
	public String getrLastName() {
		return rLastName;
	}
	public void setrLastName(String rLastName) {
		this.rLastName = rLastName;
	}
	public String getrGTid() {
		return rGTid;
	}
	public void setrGTid(String rGTid) {
		this.rGTid = rGTid;
	}
	
}
