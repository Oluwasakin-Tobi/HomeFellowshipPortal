package com.portal.homeFellowship.model;

import java.io.Serializable;

public class UserRoleOpp implements Serializable {
	
	private long roleID;
	private String roleName;
	private boolean operationRole;
	private long stepID;
	private long productID;
	
	public long getRoleID() {
		return roleID;
	}
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public boolean isOperationRole() {
		return operationRole;
	}
	public void setOperationRole(boolean operationRole) {
		this.operationRole = operationRole;
	}
	public long getStepID() {
		return stepID;
	}
	public void setStepID(long stepID) {
		this.stepID = stepID;
	}
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}

}
