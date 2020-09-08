package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.util.Date;

public class Announcement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1067981357309291460L;
	private String name;
	private String announce;
	private String eventDate;
	private String createdBy;
	private String category;
	private String meetingLink;
	private String meetingType;
	private String sendTo;
	private String sendToAUser;
	private String training;
	
	
	
	
	
	
	public String getTraining() {
		return training;
	}
	public void setTraining(String training) {
		this.training = training;
	}
	public String getSendToAUser() {
		return sendToAUser;
	}
	public void setSendToAUser(String sendToAUser) {
		this.sendToAUser = sendToAUser;
	}
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getMeetingType() {
		return meetingType;
	}
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}
	public String getMeetingLink() {
		return meetingLink;
	}
	public void setMeetingLink(String meetingLink) {
		this.meetingLink = meetingLink;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getAnnounce() {
		return announce;
	}
	public void setAnnounce(String announce) {
		this.announce = announce;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	@Override
	public String toString() {
		return "Announcement [name=" + name + ", announce=" + announce + ", eventDate=" + eventDate + ", createdBy="
				+ createdBy + ", category=" + category + ", meetingLink=" + meetingLink + ", meetingType=" + meetingType
				+ ", sendTo=" + sendTo + ", sendToAUser=" + sendToAUser + ", training=" + training + "]";
	}
	



	

	

	
	
}
