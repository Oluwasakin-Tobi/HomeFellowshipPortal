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
	private int adultPresent;
	private int childPresent;
	private int visitors;
	
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
	public int getAdultPresent() {
		return adultPresent;
	}
	public void setAdultPresent(int adultPresent) {
		this.adultPresent = adultPresent;
	}
	public int getChildPresent() {
		return childPresent;
	}
	public void setChildPresent(int childPresent) {
		this.childPresent = childPresent;
	}
	public int getVisitors() {
		return visitors;
	}
	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
	
	

}
