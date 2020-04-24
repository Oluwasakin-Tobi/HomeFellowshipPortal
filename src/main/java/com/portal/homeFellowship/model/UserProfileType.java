package com.portal.homeFellowship.model;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	
	MAKER("MAKER"),
	AUDIT("AUDIT"),
	ADMIN("ADMIN"), 
	OPERATIONS("OPERATIONS"),
	GROUPADMIN("GROUPADMIN");
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
