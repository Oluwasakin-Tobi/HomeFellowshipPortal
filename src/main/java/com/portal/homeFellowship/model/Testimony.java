package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Testimony implements Serializable{
	
	private String testifierName;
	private String testimonyTopic;
	private String testimony;
	private String dateCreated;
	private String centre;
	private String userName;
	
	
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCentre() {
		return centre;
	}
	public void setCentre(String centre) {
		this.centre = centre;
	}
	public String getTestifierName() {
		return testifierName;
	}
	public void setTestifierName(String testifierName) {
		this.testifierName = testifierName;
	}
	public String getTestimonyTopic() {
		return testimonyTopic;
	}
	public void setTestimonyTopic(String testimonyTopic) {
		this.testimonyTopic = testimonyTopic;
	}
	public String getTestimony() {
		return testimony;
	}
	public void setTestimony(String testimony) {
		this.testimony = testimony;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Override
	public String toString() {
		return "Testimony [testifierName=" + testifierName + ", testimonyTopic=" + testimonyTopic + ", testimony="
				+ testimony + ", dateCreated=" + dateCreated + ", centre=" + centre + ", userName=" + userName + "]";
	}
	
	

}
