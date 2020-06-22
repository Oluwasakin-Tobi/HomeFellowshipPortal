package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.util.Date;

public class CalendarDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2729707475484678920L;
	private String event;
	private String eventDate;
	private String status;
	private String createdBy;
	private String calendarID;
	private String sendTo;
	private Date eventDateD;
	private String sendToUser;
	
	
	
	
	
	public String getSendToUser() {
		return sendToUser;
	}
	public void setSendToUser(String sendToUser) {
		this.sendToUser = sendToUser;
	}
	public Date getEventDateD() {
		return eventDateD;
	}
	public void setEventDateD(Date eventDateD) {
		this.eventDateD = eventDateD;
	}
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getCalendarID() {
		return calendarID;
	}
	public void setCalendarID(String calendarID) {
		this.calendarID = calendarID;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		return "CalendarDetail [event=" + event + ", eventDate=" + eventDate + ", status=" + status + ", createdBy="
				+ createdBy + ", calendarID=" + calendarID + ", sendTo=" + sendTo + ", eventDateD=" + eventDateD
				+ ", sendToUser=" + sendToUser + "]";
	}
	

	

	

	

}
