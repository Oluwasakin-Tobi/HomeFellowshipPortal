package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Otp implements Serializable{
	
	private String phoneNo;
	private String sessionID;
	private String otp;
	private String mail;
	
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "Otp [phoneNo=" + phoneNo + ", sessionID=" + sessionID + ", otp=" + otp + ", mail=" + mail + "]";
	}
	
	

}
