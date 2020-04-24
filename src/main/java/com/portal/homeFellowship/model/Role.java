package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Role implements Serializable {
	
	private String roleName;
	private long roleID;
	private boolean operationRole;
	private String username;
    private String serverIP;
    private String serviceClient;


    public String getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(String serviceClient) {
		this.serviceClient = serviceClient;
	}
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public long getRoleID() {
		return roleID;
	}
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}
	public boolean isOperationRole() {
		return operationRole;
	}
	public void setOperationRole(boolean operationRole) {
		this.operationRole = operationRole;
	}
	

}
