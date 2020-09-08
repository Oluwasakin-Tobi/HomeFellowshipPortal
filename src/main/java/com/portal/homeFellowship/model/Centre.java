package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Centre implements Serializable{
	
	private String userName;
	private String centre;
	private String parentCentre;
	private String leaderInCharge;
	private String dateCreated;
	private String type;
	private String area;
	private String zone;
	private String district;
	private String centreAddress;
	
	
	
	
	
	
	public String getCentreAddress() {
		return centreAddress;
	}
	public void setCentreAddress(String centreAddress) {
		this.centreAddress = centreAddress;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCentre() {
		return centre;
	}
	public void setCentre(String centre) {
		this.centre = centre;
	}
	public String getParentCentre() {
		return parentCentre;
	}
	public void setParentCentre(String parentCentre) {
		this.parentCentre = parentCentre;
	}
	public String getLeaderInCharge() {
		return leaderInCharge;
	}
	public void setLeaderInCharge(String leaderInCharge) {
		this.leaderInCharge = leaderInCharge;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Override
	public String toString() {
		return "Centre [userName=" + userName + ", centre=" + centre + ", parentCentre=" + parentCentre
				+ ", leaderInCharge=" + leaderInCharge + ", dateCreated=" + dateCreated + ", type=" + type + ", area="
				+ area + ", zone=" + zone + ", district=" + district + ", centreAddress=" + centreAddress + "]";
	}
	
	

}
