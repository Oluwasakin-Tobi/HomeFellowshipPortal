package com.portal.homeFellowship.model;


public class UserResp {
	private UserDetails userProfile;
	private String responseCode;
	private String responseMessage;
	
	
	public UserDetails getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserDetails userProfile) {
		this.userProfile = userProfile;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
