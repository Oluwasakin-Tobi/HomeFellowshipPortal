package com.portal.homeFellowship.model;

import java.io.Serializable;

public class UserToRoleResp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6063902943885819210L;
	private long roleID;
	private long userID;
	private long stepID;
	private long productID;
	private String userName;
	private String roleName;
	private String stepName;
	private String productName;
	private String serverIP;
	private String serviceClient;
	
	
	public long getRoleID() {
		return roleID;
	}
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public long getStepID() {
		return stepID;
	}
	public void setStepID(long stepID) {
		this.stepID = stepID;
	}
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public String getServiceClient() {
		return serviceClient;
	}
	public void setServiceClient(String serviceClient) {
		this.serviceClient = serviceClient;
	}
	
	
	@Override
	public String toString() {
		return "UserToRoleResp [roleID=" + roleID + ", userID=" + userID + ", stepID=" + stepID + ", productID="
				+ productID + ", userName=" + userName + ", roleName=" + roleName + ", stepName=" + stepName
				+ ", productName=" + productName + ", serverIP=" + serverIP + ", serviceClient=" + serviceClient + "]";
	}


	
	
	
	
}
