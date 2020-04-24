package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.util.Date;

public class Affiliate implements Serializable {

    private long affiliateID;
    private String affiliateCode;
    private String affiliateCurrency;
    private String language;
    private String clusterName;
    private String clusterCode;
    private boolean authorised;
    private String createUser;
    private String verifyUser;
    private Date verifyDate;
    private String transitAccount;
    private String userName;
    private String serverIP;
    private String serviceClient;


    public String getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(String serviceClient) {
		this.serviceClient = serviceClient;
	}



    public long getAffiliateID() {
        return affiliateID;
    }

    public void setAffiliateID(long affiliateID) {
        this.affiliateID = affiliateID;
    }

    public String getAffiliateCode() {
        return affiliateCode;
    }

    public void setAffiliateCode(String affiliateCode) {
        this.affiliateCode = affiliateCode;
    }

    public String getAffiliateCurrency() {
        return affiliateCurrency;
    }

    public void setAffiliateCurrency(String affiliateCurrency) {
        this.affiliateCurrency = affiliateCurrency;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterCode() {
        return clusterCode;
    }

    public void setClusterCode(String clusterCode) {
        this.clusterCode = clusterCode;
    }

    public boolean isAuthorised() {
        return authorised;
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    public String getTransitAccount() {
        return transitAccount;
    }

    public void setTransitAccount(String transitAccount) {
        this.transitAccount = transitAccount;
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

	@Override
	public String toString() {
		return "Affiliate [affiliateID=" + affiliateID + ", affiliateCode=" + affiliateCode + ", affiliateCurrency="
				+ affiliateCurrency + ", language=" + language + ", clusterName=" + clusterName + ", clusterCode="
				+ clusterCode + ", authorised=" + authorised + ", createUser=" + createUser + ", verifyUser="
				+ verifyUser + ", verifyDate=" + verifyDate + ", transitAccount=" + transitAccount + ", userName="
				+ userName + ", serverIP=" + serverIP + ", serviceClient=" + serviceClient + "]";
	}
    
    
}
