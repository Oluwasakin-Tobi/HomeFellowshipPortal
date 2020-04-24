package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UsertoRoleReq implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -8709365834150566087L;
	private String userID;
	private boolean active;
	private String userRoles;
	private String userName;
	private String serverIP;
	private String serviceClient;
	private String affiliateCode;
	private String product;
	private long productId;
	private String stepId;
	private String stepName;
	private String roleID; 
	private String scope;
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	
	
	
	
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
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
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
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
	public String getAffiliateCode() {
		return affiliateCode;
	}
	public void setAffiliateCode(String affiliateCode) {
		this.affiliateCode = affiliateCode;
	}
	@Override
	public String toString() {
		return "UsertoRoleReq [userID=" + userID + ", active=" + active + ", userRoles=" + userRoles + ", userName="
				+ userName + ", serverIP=" + serverIP + ", serviceClient=" + serviceClient + ", affiliateCode="
				+ affiliateCode + ", product=" + product + ", productId=" + productId + ", stepId=" + stepId
				+ ", stepName=" + stepName + ", roleID=" + roleID + ", scope=" + scope + "]";
	}
 
 

	

}
