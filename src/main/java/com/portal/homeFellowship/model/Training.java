package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Training implements Serializable{
	
	private String trainingName;
	private String userName;
	private String trainingDescription;
	private String dateCreated;
	
	
	
	
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getTrainingDescription() {
		return trainingDescription;
	}
	public void setTrainingDescription(String trainingDescription) {
		this.trainingDescription = trainingDescription;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Training [trainingName=" + trainingName + ", userName=" + userName + ", trainingDescription="
				+ trainingDescription + ", dateCreated=" + dateCreated + "]";
	}
	
	

}
