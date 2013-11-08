package com.ece4894.model;

import java.math.BigDecimal;

public class VisitorRequest extends Student {

	private int requestNumber;
	private BigDecimal dateTimeIn;
	private BigDecimal dateTimeOut;
	private Long GuestOfGTID;

	public int getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(int requestNumber) {
		this.requestNumber = requestNumber;
	}

	public BigDecimal getDateTimeIn() {
		return dateTimeIn;
	}

	public void setDateTimeIn(BigDecimal dateTimeIn) {
		this.dateTimeIn = dateTimeIn;
	}

	public BigDecimal getDateTimeOut() {
		return dateTimeOut;
	}

	public void setDateTimeOut(BigDecimal dateTimeOut) {
		this.dateTimeOut = dateTimeOut;
	}

	public Long getGuestOfGTID() {
		return GuestOfGTID;
	}

	public void setGuestOfGTID(Long guestOfGTID) {
		GuestOfGTID = guestOfGTID;
	}

}
