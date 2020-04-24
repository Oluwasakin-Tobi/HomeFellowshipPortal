package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserReport implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -8709365834150566087L;
	private long userID;
	private String userFullName;
	private String affiliateCode;
    private String userName;
	private String userRoles;
	private String status;
	private String lastLoginTime;
	private Date dateCreated;
	private String emailAdd;
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getAffiliateCode() {
		return affiliateCode;
	}
	public void setAffiliateCode(String affiliateCode) {
		this.affiliateCode = affiliateCode;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getEmailAdd() {
		return emailAdd;
	}
	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}
	@Override
	public String toString() {
		return "UserReport [userID=" + userID + ", userFullName=" + userFullName + ", affiliateCode=" + affiliateCode
				+ ", userName=" + userName + ", userRoles=" + userRoles + ", status=" + status + ", lastLoginTime="
				+ lastLoginTime + ", dateCreated=" + dateCreated + ", emailAdd=" + emailAdd + "]";
	}
	
	
	
}
