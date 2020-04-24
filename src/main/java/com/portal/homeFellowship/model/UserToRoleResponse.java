package com.portal.homeFellowship.model;

import java.util.List;

public class UserToRoleResponse {

	private List<UserRole> userRole;
	private List<UserRoleOpp> userRoleOpp;
	
	public List<UserRole> getUserRole() {
		return userRole;
	}
	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}
	public List<UserRoleOpp> getUserRoleOpp() {
		return userRoleOpp;
	}
	public void setUserRoleOpp(List<UserRoleOpp> userRoleOpp) {
		this.userRoleOpp = userRoleOpp;
	}
}
