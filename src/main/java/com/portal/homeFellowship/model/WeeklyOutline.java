package com.portal.homeFellowship.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class WeeklyOutline implements Serializable{
	
	private String topic;
	private String outline;
	private String name;
	private MultipartFile outlineFile;
	private String uploadFlag;
	private String outlineID;
	private String dateCreated;
	
	
	
	
	
	
	
	
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getOutlineID() {
		return outlineID;
	}
	public void setOutlineID(String outlineID) {
		this.outlineID = outlineID;
	}
	public String getUploadFlag() {
		return uploadFlag;
	}
	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	public MultipartFile getOutlineFile() {
		return outlineFile;
	}
	public void setOutlineFile(MultipartFile outlineFile) {
		this.outlineFile = outlineFile;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getOutline() {
		return outline;
	}
	public void setOutline(String outline) {
		this.outline = outline;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "WeeklyOutline [topic=" + topic + ", outline=" + outline + ", name=" + name + ", outlineFile="
				+ outlineFile + ", uploadFlag=" + uploadFlag + ", outlineID=" + outlineID + ", dateCreated="
				+ dateCreated + "]";
	}
	

	

	

	
	

}
