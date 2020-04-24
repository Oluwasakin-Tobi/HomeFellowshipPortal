package com.portal.homeFellowship.model;

import java.io.Serializable;

public class UserStepPosition implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6422908611235184138L;
	private long productID;
	private String userName;
	private String serverIP;
	private String serviceClient;
	private String productName; 
	
	
	
	
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
	public String getServiceClient() {
		return serviceClient;
	}
	public void setServiceClient(String serviceClient) {
		this.serviceClient = serviceClient;
	}
	
	

}
