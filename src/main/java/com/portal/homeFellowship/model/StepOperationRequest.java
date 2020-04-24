package com.portal.homeFellowship.model;

public class StepOperationRequest {
	 private  long stepid;
	 private  long productid;
	 private String userName; 
	 private String serverIP;
	 private String serviceClient;
	 
	 
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	public long getStepid() {
		return stepid;
	}
	public void setStepid(long stepid) {
		this.stepid = stepid;
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
	 
	 

}
