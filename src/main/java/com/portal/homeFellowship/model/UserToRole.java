package com.portal.homeFellowship.model;

import java.io.Serializable;

public class UserToRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7610652311491208962L;
	private long userToRoleID;
	private String roleID;
	private long userID;
	private String stepID;
	private String userName1;
	private String userName;
	private String serverIP;
	private String serviceClient;

	
	
	

    public String getUserName1() {
		return userName1;
	}

	public void setUserName1(String userName1) {
		this.userName1 = userName1;
	}

	public String getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(String serviceClient) {
		this.serviceClient = serviceClient;
	}
	
	
	public long getUserToRoleID() {
		return userToRoleID;
	}
	public void setUserToRoleID(long userToRoleID) {
		this.userToRoleID = userToRoleID;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public String getStepID() {
		return stepID;
	}
	public void setStepID(String stepID) {
		this.stepID = stepID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	
	
	
	
	
}
