package com.portal.homeFellowship.model;

import java.io.Serializable;

public class MonthlyReport implements Serializable{
	
	private String name;
	private String dateVisited;
	private String visitedBy;
	private String visitationReport;
	private String leaderRemark;
	private String week;
	
	
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateVisited() {
		return dateVisited;
	}
	public void setDateVisited(String dateVisited) {
		this.dateVisited = dateVisited;
	}
	public String getVisitedBy() {
		return visitedBy;
	}
	public void setVisitedBy(String visitedBy) {
		this.visitedBy = visitedBy;
	}
	public String getVisitationReport() {
		return visitationReport;
	}
	public void setVisitationReport(String visitationReport) {
		this.visitationReport = visitationReport;
	}
	public String getLeaderRemark() {
		return leaderRemark;
	}
	public void setLeaderRemark(String leaderRemark) {
		this.leaderRemark = leaderRemark;
	}
	@Override
	public String toString() {
		return "MonthlyReport [name=" + name + ", dateVisited=" + dateVisited + ", visitedBy=" + visitedBy
				+ ", visitationReport=" + visitationReport + ", leaderRemark=" + leaderRemark + ", week=" + week + "]";
	}
	

	
	

}
