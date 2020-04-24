package com.portal.homeFellowship.model;

public class UserToRoleApp {
	
	private long usertoroleID;
	private String userName;
	private String roleName;
	private String stepName;
	private String productName;
	private String createdBy;
	
	
	
	
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public long getUsertoroleID() {
		return usertoroleID;
	}
	public void setUsertoroleID(long usertoroleID) {
		this.usertoroleID = usertoroleID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "UserToRoleApp [usertoroleID=" + usertoroleID + ", userName=" + userName + ", roleName=" + roleName
				+ ", stepName=" + stepName + ", productName=" + productName + ", createdBy=" + createdBy + "]";
	}


	
	
	
	
	
	

}
