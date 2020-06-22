package com.portal.homeFellowship.model;

import java.io.Serializable;

public class ChangePassword implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6678384512247276320L;
	private String oldPassword;
	private String newPassword;
	private String name;
	private long userID;
	
	
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ChangePassword [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", name=" + name
				+ ", userID=" + userID + "]";
	}
	
	
	

}
