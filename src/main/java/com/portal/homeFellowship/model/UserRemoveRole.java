package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.util.Arrays;

public class UserRemoveRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7610652311491208962L;
	private long userToRoleID;
	private String roleID;
	private long userID;
	private long id;
	private long productID;
	private String stepID; 
	private String userName;
	private String serverIP;
	private String serviceClient;
	private String[] roleName;
	private String userBranch;
	private String affiliateCode;
	private String tokenGroup;
	private String userFullName;
	private String userEmailAdd; 
	private String userRoles;
	private String dateCreated;
	
	
	 
	
	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getUserBranch() {
		return userBranch;
	}

	public void setUserBranch(String userBranch) {
		this.userBranch = userBranch;
	}

	public String getAffiliateCode() {
		return affiliateCode;
	}

	public void setAffiliateCode(String affiliateCode) {
		this.affiliateCode = affiliateCode;
	}

	public String getTokenGroup() {
		return tokenGroup;
	}

	public void setTokenGroup(String tokenGroup) {
		this.tokenGroup = tokenGroup;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserEmailAdd() {
		return userEmailAdd;
	}

	public void setUserEmailAdd(String userEmailAdd) {
		this.userEmailAdd = userEmailAdd;
	}

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	public String[] getRoleName() {
		return roleName;
	}

	public void setRoleName(String[] roleName) {
		this.roleName = roleName;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserRemoveRole [userToRoleID=" + userToRoleID + ", roleID=" + roleID + ", userID=" + userID + ", id="
				+ id + ", productID=" + productID + ", stepID=" + stepID + ", userName=" + userName + ", serverIP="
				+ serverIP + ", serviceClient=" + serviceClient + ", roleName=" + Arrays.toString(roleName)
				+ ", userBranch=" + userBranch + ", affiliateCode=" + affiliateCode + ", tokenGroup=" + tokenGroup
				+ ", userFullName=" + userFullName + ", userEmailAdd=" + userEmailAdd + ", userRoles=" + userRoles
				+ ", dateCreated=" + dateCreated + "]";
	}



	
	
	
}
