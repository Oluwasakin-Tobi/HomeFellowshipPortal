package com.portal.homeFellowship.model;

import java.io.Serializable;

public class EditUser implements Serializable {
 
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8587129268154294109L;
	private long userID;
    private long affiliateID;
    private long oldUserTransactionLimit;
    private long newUserTransactionLimit;
    private boolean active;
    private String userRoles;
    private boolean operationUser;
    private String oldUserFullName;
    private String newUserFullName;
    private String oldUserEmailAdd;
    private String newUserEmailAdd;
    private String oldUserBranch;
    private String newUserBranch;
    private String passwordExpiryPolicy;
    private boolean authorisedUserFlag;
    private String userName;
    private String serverIP;
    private String serviceClient;
    private String oldAffiliateCode;
    private String newAffiliateCode;
	private String tokenGroup;
	private String createdBy;
	private String deleteFlag;
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public long getAffiliateID() {
		return affiliateID;
	}
	public void setAffiliateID(long affiliateID) {
		this.affiliateID = affiliateID;
	}
	public long getOldUserTransactionLimit() {
		return oldUserTransactionLimit;
	}
	public void setOldUserTransactionLimit(long oldUserTransactionLimit) {
		this.oldUserTransactionLimit = oldUserTransactionLimit;
	}
	public long getNewUserTransactionLimit() {
		return newUserTransactionLimit;
	}
	public void setNewUserTransactionLimit(long newUserTransactionLimit) {
		this.newUserTransactionLimit = newUserTransactionLimit;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}
	public boolean isOperationUser() {
		return operationUser;
	}
	public void setOperationUser(boolean operationUser) {
		this.operationUser = operationUser;
	}
	public String getOldUserFullName() {
		return oldUserFullName;
	}
	public void setOldUserFullName(String oldUserFullName) {
		this.oldUserFullName = oldUserFullName;
	}
	public String getNewUserFullName() {
		return newUserFullName;
	}
	public void setNewUserFullName(String newUserFullName) {
		this.newUserFullName = newUserFullName;
	}
	public String getOldUserEmailAdd() {
		return oldUserEmailAdd;
	}
	public void setOldUserEmailAdd(String oldUserEmailAdd) {
		this.oldUserEmailAdd = oldUserEmailAdd;
	}
	public String getNewUserEmailAdd() {
		return newUserEmailAdd;
	}
	public void setNewUserEmailAdd(String newUserEmailAdd) {
		this.newUserEmailAdd = newUserEmailAdd;
	}
	public String getOldUserBranch() {
		return oldUserBranch;
	}
	public void setOldUserBranch(String oldUserBranch) {
		this.oldUserBranch = oldUserBranch;
	}
	public String getNewUserBranch() {
		return newUserBranch;
	}
	public void setNewUserBranch(String newUserBranch) {
		this.newUserBranch = newUserBranch;
	}
	public String getPasswordExpiryPolicy() {
		return passwordExpiryPolicy;
	}
	public void setPasswordExpiryPolicy(String passwordExpiryPolicy) {
		this.passwordExpiryPolicy = passwordExpiryPolicy;
	}
	public boolean isAuthorisedUserFlag() {
		return authorisedUserFlag;
	}
	public void setAuthorisedUserFlag(boolean authorisedUserFlag) {
		this.authorisedUserFlag = authorisedUserFlag;
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
	public String getServiceClient() {
		return serviceClient;
	}
	public void setServiceClient(String serviceClient) {
		this.serviceClient = serviceClient;
	}
	public String getOldAffiliateCode() {
		return oldAffiliateCode;
	}
	public void setOldAffiliateCode(String oldAffiliateCode) {
		this.oldAffiliateCode = oldAffiliateCode;
	}
	public String getNewAffiliateCode() {
		return newAffiliateCode;
	}
	public void setNewAffiliateCode(String newAffiliateCode) {
		this.newAffiliateCode = newAffiliateCode;
	}
	public String getTokenGroup() {
		return tokenGroup;
	}
	public void setTokenGroup(String tokenGroup) {
		this.tokenGroup = tokenGroup;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EditUser [userID=" + userID + ", affiliateID=" + affiliateID + ", oldUserTransactionLimit="
				+ oldUserTransactionLimit + ", newUserTransactionLimit=" + newUserTransactionLimit + ", active="
				+ active + ", userRoles=" + userRoles + ", operationUser=" + operationUser + ", oldUserFullName="
				+ oldUserFullName + ", newUserFullName=" + newUserFullName + ", oldUserEmailAdd=" + oldUserEmailAdd
				+ ", newUserEmailAdd=" + newUserEmailAdd + ", oldUserBranch=" + oldUserBranch + ", newUserBranch="
				+ newUserBranch + ", passwordExpiryPolicy=" + passwordExpiryPolicy + ", authorisedUserFlag="
				+ authorisedUserFlag + ", userName=" + userName + ", serverIP=" + serverIP + ", serviceClient="
				+ serviceClient + ", oldAffiliateCode=" + oldAffiliateCode + ", newAffiliateCode=" + newAffiliateCode
				+ ", tokenGroup=" + tokenGroup + ", createdBy=" + createdBy + ", deleteFlag=" + deleteFlag + "]";
	}

 

}
