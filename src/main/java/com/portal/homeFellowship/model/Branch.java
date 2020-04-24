package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.util.Date;

public class Branch implements Serializable {
	
	private String branchCode;
	private String branchName;
	private String branchEMail;
	private String affiliateCode;
	private String createUser;
	private Date createDate;
	private String verifyUser;
	private Date verifyDate;
	private String status;
	private String serviceClient;
	
	
	public String getServiceClient() {
		return serviceClient;
	}
	public void setServiceClient(String serviceClient) {
		this.serviceClient = serviceClient;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchEMail() {
		return branchEMail;
	}
	public void setBranchEMail(String branchEMail) {
		this.branchEMail = branchEMail;
	}
	
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getVerifyUser() {
		return verifyUser;
	}
	public void setVerifyUser(String verifyUser) {
		this.verifyUser = verifyUser;
	}
	public Date getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAffiliateCode() {
		return affiliateCode;
	}
	public void setAffiliateCode(String affiliateCode) {
		this.affiliateCode = affiliateCode;
	}
	@Override
	public String toString() {
		return "Branch [branchCode=" + branchCode + ", branchName=" + branchName + ", branchEMail=" + branchEMail
				+ ", affiliateCode=" + affiliateCode + ", createUser=" + createUser + ", createDate=" + createDate
				+ ", verifyUser=" + verifyUser + ", verifyDate=" + verifyDate + ", status=" + status
				+ ", serviceClient=" + serviceClient + "]";
	}
	
	
	

}
