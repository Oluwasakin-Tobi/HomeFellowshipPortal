package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Welfare implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 923500799112015923L;

	private String name;
	private String welfare;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWelfare() {
		return welfare;
	}
	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}
	@Override
	public String toString() {
		return "Welfare [name=" + name + ", welfare=" + welfare + "]";
	}
	
	
}
