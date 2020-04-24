package com.portal.homeFellowship.model;


public class UserResp {
	private User1 userProfile;
	private String responseCode;
	private String responseMessage;
	
	
	public User1 getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(User1 userProfile) {
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
