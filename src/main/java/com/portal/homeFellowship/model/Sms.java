package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Sms implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5491865380079637372L;
	
	private String phoneNo;
	private String message;
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Sms [phoneNo=" + phoneNo + ", message=" + message + "]";
	}
	
	

}
