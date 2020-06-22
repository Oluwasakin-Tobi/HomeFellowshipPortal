package com.portal.homeFellowship.model;

import java.io.Serializable;

public class WeeklyReport implements Serializable{
	
	private String centre;
	private String centreAddress;
	private String area;
	private String leaderName;
	private String asstLeader;
	private String intern;
	private String worshipLeader;
	private String outlineTopic;
	private String adultPresent;
	private String childPresent;
	private String visitors;
	private String hostName;
	private String hostPhoneNo;
	private String hostEmail;
	private String userName;
	private String offering;
	
	
	
	
	
	
	
	public String getOffering() {
		return offering;
	}
	public void setOffering(String offering) {
		this.offering = offering;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHostPhoneNo() {
		return hostPhoneNo;
	}
	public void setHostPhoneNo(String hostPhoneNo) {
		this.hostPhoneNo = hostPhoneNo;
	}
	public String getHostEmail() {
		return hostEmail;
	}
	public void setHostEmail(String hostEmail) {
		this.hostEmail = hostEmail;
	}
	public String getCentre() {
		return centre;
	}
	public void setCentre(String centre) {
		this.centre = centre;
	}
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
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getAsstLeader() {
		return asstLeader;
	}
	public void setAsstLeader(String asstLeader) {
		this.asstLeader = asstLeader;
	}
	public String getIntern() {
		return intern;
	}
	public void setIntern(String intern) {
		this.intern = intern;
	}
	public String getWorshipLeader() {
		return worshipLeader;
	}
	public void setWorshipLeader(String worshipLeader) {
		this.worshipLeader = worshipLeader;
	}
	public String getOutlineTopic() {
		return outlineTopic;
	}
	public void setOutlineTopic(String outlineTopic) {
		this.outlineTopic = outlineTopic;
	}
	public String getAdultPresent() {
		return adultPresent;
	}
	public void setAdultPresent(String adultPresent) {
		this.adultPresent = adultPresent;
	}
	public String getChildPresent() {
		return childPresent;
	}
	public void setChildPresent(String childPresent) {
		this.childPresent = childPresent;
	}
	public String getVisitors() {
		return visitors;
	}
	public void setVisitors(String visitors) {
		this.visitors = visitors;
	}
	@Override
	public String toString() {
		return "WeeklyReport [centre=" + centre + ", centreAddress=" + centreAddress + ", area=" + area
				+ ", leaderName=" + leaderName + ", asstLeader=" + asstLeader + ", intern=" + intern
				+ ", worshipLeader=" + worshipLeader + ", outlineTopic=" + outlineTopic + ", adultPresent="
				+ adultPresent + ", childPresent=" + childPresent + ", visitors=" + visitors + ", hostName=" + hostName
				+ ", hostPhoneNo=" + hostPhoneNo + ", hostEmail=" + hostEmail + ", userName=" + userName + ", offering="
				+ offering + "]";
	}
	

	


}
