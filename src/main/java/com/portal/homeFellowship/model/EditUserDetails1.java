package com.portal.homeFellowship.model;

import java.io.Serializable;

public class EditUserDetails1 implements Serializable {
	
	
	private String userID;
	private String userName;
	private String userRoles;
	private String serverIP;
	private String serviceClient;
	private String affiliateCode;
	private String product;
	private long productId;
	private long stepId;
	private String stepName;
	private String userFullName;
	private String userEmailAdd;
	private String userBranch;
	private long userTransactionLimit;
	private String editusername;
	private String flag;
	
	
	
	public String getEditusername() {
		return editusername;
	}
	public void setEditusername(String editusername) {
		this.editusername = editusername;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	public String getUserBranch() {
		return userBranch;
	}
	public void setUserBranch(String userBranch) {
		this.userBranch = userBranch;
	}
	public long getUserTransactionLimit() {
		return userTransactionLimit;
	}
	public void setUserTransactionLimit(long userTransactionLimit) {
		this.userTransactionLimit = userTransactionLimit;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	
	public String getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
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
	public String getAffiliateCode() {
		return affiliateCode;
	}
	public void setAffiliateCode(String affiliateCode) {
		this.affiliateCode = affiliateCode;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getStepId() {
		return stepId;
	}
	public void setStepId(long stepId) {
		this.stepId = stepId;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	@Override
	public String toString() {
		return "EditUserDetails1 [userID=" + userID + ", userName=" + userName + ", userRoles=" + userRoles
				+ ", serverIP=" + serverIP + ", serviceClient=" + serviceClient + ", affiliateCode=" + affiliateCode
				+ ", product=" + product + ", productId=" + productId + ", stepId=" + stepId + ", stepName=" + stepName
				+ ", userFullName=" + userFullName + ", userEmailAdd=" + userEmailAdd + ", userBranch=" + userBranch
				+ ", userTransactionLimit=" + userTransactionLimit + ", editusername=" + editusername + ", flag=" + flag
				+ "]";
	}


	

}
