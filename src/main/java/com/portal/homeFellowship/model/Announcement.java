package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.util.Date;

public class Announcement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1067981357309291460L;
	private String name;
	private String announcement;
	private Date eventDate;
	private String createdBy;
	private String category;
	
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
	public String getAnnouncement() {
		return announcement;
	}
	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		return "Announcement [name=" + name + ", announcement=" + announcement + ", eventDate=" + eventDate
				+ ", createdBy=" + createdBy + ", category=" + category + "]";
	}
	

	
	
}
