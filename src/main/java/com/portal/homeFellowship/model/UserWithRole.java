package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class UserWithRole implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -8709365834150566087L;
	private long userID;
	private long affiliateID;
	private BigDecimal userTransactionLimit;
	private boolean active;
	private String userRoles;
	private String stepName;
	private String product;
	private boolean operationUser;
	private String userFullName;
	private String userEmailAdd;
	private String userBranch;
	private String passwordExpiryPolicy;
	private boolean authorisedUserFlag;
	private String userName;
	private String serverIP;
	private String serviceClient;
	private String affiliateCode;
	private Set<UserProfile> userProfiles = new HashSet<>();
	private  String tokenGroup;
	private String createdBy;
	private String customerAcNo;
	private String currency;
	private long usertoroleID;
	private String accountNumber;
	private String glOwner;
	private String dateCreated;
	private String password;
	
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getGlOwner() {
		return glOwner;
	}

	public void setGlOwner(String glOwner) {
		this.glOwner = glOwner;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomerAcNo() {
		return customerAcNo;
	}

	public void setCustomerAcNo(String customerAcNo) {
		this.customerAcNo = customerAcNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public long getUsertoroleID() {
		return usertoroleID;
	}

	public void setUsertoroleID(long usertoroleID) {
		this.usertoroleID = usertoroleID;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getUserTransactionLimit() {
		return userTransactionLimit;
	}

	public void setUserTransactionLimit(BigDecimal userTransactionLimit) {
		this.userTransactionLimit = userTransactionLimit;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getTokenGroup() {
		return tokenGroup;
	}

	public void setTokenGroup(String tokenGroup) {
		this.tokenGroup = tokenGroup;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

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

	public String getAffiliateCode() {
		return affiliateCode;
	}

	public void setAffiliateCode(String affiliateCode) {
		this.affiliateCode = affiliateCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((affiliateCode == null) ? 0 : affiliateCode.hashCode());
		result = prime * result + (int) (affiliateID ^ (affiliateID >>> 32));
		result = prime * result + (authorisedUserFlag ? 1231 : 1237);
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((customerAcNo == null) ? 0 : customerAcNo.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((glOwner == null) ? 0 : glOwner.hashCode());
		result = prime * result + (operationUser ? 1231 : 1237);
		result = prime * result + ((passwordExpiryPolicy == null) ? 0 : passwordExpiryPolicy.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((serverIP == null) ? 0 : serverIP.hashCode());
		result = prime * result + ((serviceClient == null) ? 0 : serviceClient.hashCode());
		result = prime * result + ((stepName == null) ? 0 : stepName.hashCode());
		result = prime * result + ((tokenGroup == null) ? 0 : tokenGroup.hashCode());
		result = prime * result + ((userBranch == null) ? 0 : userBranch.hashCode());
		result = prime * result + ((userEmailAdd == null) ? 0 : userEmailAdd.hashCode());
		result = prime * result + ((userFullName == null) ? 0 : userFullName.hashCode());
		result = prime * result + (int) (userID ^ (userID >>> 32));
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userProfiles == null) ? 0 : userProfiles.hashCode());
		result = prime * result + ((userRoles == null) ? 0 : userRoles.hashCode());
		result = prime * result + ((userTransactionLimit == null) ? 0 : userTransactionLimit.hashCode());
		result = prime * result + (int) (usertoroleID ^ (usertoroleID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserWithRole other = (UserWithRole) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (active != other.active)
			return false;
		if (affiliateCode == null) {
			if (other.affiliateCode != null)
				return false;
		} else if (!affiliateCode.equals(other.affiliateCode))
			return false;
		if (affiliateID != other.affiliateID)
			return false;
		if (authorisedUserFlag != other.authorisedUserFlag)
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (customerAcNo == null) {
			if (other.customerAcNo != null)
				return false;
		} else if (!customerAcNo.equals(other.customerAcNo))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (glOwner == null) {
			if (other.glOwner != null)
				return false;
		} else if (!glOwner.equals(other.glOwner))
			return false;
		if (operationUser != other.operationUser)
			return false;
		if (passwordExpiryPolicy == null) {
			if (other.passwordExpiryPolicy != null)
				return false;
		} else if (!passwordExpiryPolicy.equals(other.passwordExpiryPolicy))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (serverIP == null) {
			if (other.serverIP != null)
				return false;
		} else if (!serverIP.equals(other.serverIP))
			return false;
		if (serviceClient == null) {
			if (other.serviceClient != null)
				return false;
		} else if (!serviceClient.equals(other.serviceClient))
			return false;
		if (stepName == null) {
			if (other.stepName != null)
				return false;
		} else if (!stepName.equals(other.stepName))
			return false;
		if (tokenGroup == null) {
			if (other.tokenGroup != null)
				return false;
		} else if (!tokenGroup.equals(other.tokenGroup))
			return false;
		if (userBranch == null) {
			if (other.userBranch != null)
				return false;
		} else if (!userBranch.equals(other.userBranch))
			return false;
		if (userEmailAdd == null) {
			if (other.userEmailAdd != null)
				return false;
		} else if (!userEmailAdd.equals(other.userEmailAdd))
			return false;
		if (userFullName == null) {
			if (other.userFullName != null)
				return false;
		} else if (!userFullName.equals(other.userFullName))
			return false;
		if (userID != other.userID)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userProfiles == null) {
			if (other.userProfiles != null)
				return false;
		} else if (!userProfiles.equals(other.userProfiles))
			return false;
		if (userRoles == null) {
			if (other.userRoles != null)
				return false;
		} else if (!userRoles.equals(other.userRoles))
			return false;
		if (userTransactionLimit == null) {
			if (other.userTransactionLimit != null)
				return false;
		} else if (!userTransactionLimit.equals(other.userTransactionLimit))
			return false;
		if (usertoroleID != other.usertoroleID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserWithRole [userID=" + userID + ", affiliateID=" + affiliateID + ", userTransactionLimit="
				+ userTransactionLimit + ", active=" + active + ", userRoles=" + userRoles + ", stepName=" + stepName
				+ ", product=" + product + ", operationUser=" + operationUser + ", userFullName=" + userFullName
				+ ", userEmailAdd=" + userEmailAdd + ", userBranch=" + userBranch + ", passwordExpiryPolicy="
				+ passwordExpiryPolicy + ", authorisedUserFlag=" + authorisedUserFlag + ", userName=" + userName
				+ ", serverIP=" + serverIP + ", serviceClient=" + serviceClient + ", affiliateCode=" + affiliateCode
				+ ", userProfiles=" + userProfiles + ", tokenGroup=" + tokenGroup + ", createdBy=" + createdBy
				+ ", customerAcNo=" + customerAcNo + ", currency=" + currency + ", usertoroleID=" + usertoroleID
				+ ", accountNumber=" + accountNumber + ", glOwner=" + glOwner + ", dateCreated=" + dateCreated
				+ ", password=" + password + "]";
	}



	
	








 

 

}
