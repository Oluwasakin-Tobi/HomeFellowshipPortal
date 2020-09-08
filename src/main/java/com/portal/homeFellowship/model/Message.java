package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Message implements Serializable{
	
	private String sender;
	private String message;
	private String sendTo;
	private String dateCreated;
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Override
	public String toString() {
		return "Message [sender=" + sender + ", message=" + message + ", sendTo=" + sendTo + ", dateCreated="
				+ dateCreated + ", type=" + type + "]";
	}
	
	

}
