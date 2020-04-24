package com.portal.homeFellowship.model;

import java.io.Serializable;

public class PrayerRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8260821927528693361L;
	private String name;
	private String prayer;
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
		return "PrayerRequest [name=" + name + ", prayer=" + prayer + "]";
	}
	
	
}
