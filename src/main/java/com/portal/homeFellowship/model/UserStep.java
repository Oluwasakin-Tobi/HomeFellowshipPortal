package com.portal.homeFellowship.model;

import java.io.Serializable;

public class UserStep implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -296075707905914450L;
	private String userStep;

	public String getUserStep() {
		return userStep;
	}

	public void setUserStep(String userStep) {
		this.userStep = userStep;
	}

	@Override
	public String toString() {
		return "UserStep [userStep=" + userStep + "]";
	}
	
	

}
