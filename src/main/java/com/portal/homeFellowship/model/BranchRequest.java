package com.portal.homeFellowship.model;

import java.io.Serializable;

public class BranchRequest implements Serializable {
	
	private String affiliateCode;
	private String serviceClient;
	public String getAffiliateCode() {
		return affiliateCode;
	}
	public void setAffiliateCode(String affiliateCode) {
		this.affiliateCode = affiliateCode;
	}
	public String getServiceClient() {
		return serviceClient;
	}
	public void setServiceClient(String serviceClient) {
		this.serviceClient = serviceClient;
	}
	@Override
	public String toString() {
		return "BranchRequest [affiliateCode=" + affiliateCode + ", serviceClient=" + serviceClient + "]";
	}
	
	
	

}
