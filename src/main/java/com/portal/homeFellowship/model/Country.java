package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Country implements Serializable{
	
	private String countryName;
	private String countryCode;
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	@Override
	public String toString() {
		return "Country [countryName=" + countryName + ", countryCode=" + countryCode + "]";
	}
	
	

}
