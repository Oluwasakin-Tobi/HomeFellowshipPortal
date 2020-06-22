package com.portal.homeFellowship.model;

import java.io.Serializable;

public class PrayerRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8260821927528693361L;
	private String name;
	private String prayer;
	private String prayerId;
	private String status;
	private String comment;
	
	
	public String getPrayerId() {
		return prayerId;
	}
	public void setPrayerId(String prayerId) {
		this.prayerId = prayerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrayer() {
		return prayer;
	}
	public void setPrayer(String prayer) {
		this.prayer = prayer;
	}
	@Override
	public String toString() {
		return "PrayerRequest [name=" + name + ", prayer=" + prayer + ", prayerId=" + prayerId + ", status=" + status
				+ ", comment=" + comment + "]";
	}
	
	
	
}
