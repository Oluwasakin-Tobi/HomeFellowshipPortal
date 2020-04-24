package com.portal.homeFellowship.model;

import java.io.Serializable;

public class UserClient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4347075014016256412L;
	
	private String userID;
	private String userToRoleID;
	private String accountNumber;
	private String role;
	private String affiliate;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserToRoleID() {
		return userToRoleID;
	}
	public void setUserToRoleID(String userToRoleID) {
		this.userToRoleID = userToRoleID;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAffiliate() {
		return affiliate;
	}
	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}
	@Override
	public String toString() {
		return "UserClient [userID=" + userID + ", userToRoleID=" + userToRoleID + ", accountNumber=" + accountNumber
				+ ", role=" + role + ", affiliate=" + affiliate + "]";
	}
	
	

}
