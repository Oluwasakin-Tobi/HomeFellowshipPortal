package com.portal.homeFellowship.model;

import java.io.Serializable;

public class UserResponse implements Serializable {
	
	
	private String adUserName;
	private String serverIP;
	private String serviceClient;
	
	
	public String getAdUserName() {
		return adUserName;
	}
	public void setAdUserName(String adUserName) {
		this.adUserName = adUserName;
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

}
