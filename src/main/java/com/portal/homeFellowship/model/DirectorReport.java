package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.util.Date;

public class DirectorReport implements Serializable{
	
	private String center;
	private String noOfCenter;
	private String noOfIndependentMember;
	private String avAttendanceOfMember;
	private String totalNoOfNewConvert;
	private String totalNoOfFirstTimer;
	private String noOfFirstTimerFollowUp;
	private String noOfFirstTimerCoverted;
	private String noOfWeeklyReportSubmitted;
	private String noOfEvanProject;
	private String noOfcommImpactProject;
	private String noOfNamingCeremony;
	private String noOfburial;
	private String noOfArea;
	private String noOfIntern;
	private String noOfAsstLeader;
	private String noOfLeader;
	private String noOfAreaSupervisor;
	private String totalOffering;
	private String noOfNewLeaderIntroduced;
	private String noOfNewLeaderTrained;
	private String noOfNewHostIntroduced;
	private String noOfNewHostTrained;
	private String name;
	private Date dateCreated;
	
	
	
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCenter() {
		return center;
	}
	public String getNoOfCenter() {
		return noOfCenter;
	}
	public void setNoOfCenter(String noOfCenter) {
		this.noOfCenter = noOfCenter;
	}
	public String getNoOfIndependentMember() {
		return noOfIndependentMember;
	}
	public void setNoOfIndependentMember(String noOfIndependentMember) {
		this.noOfIndependentMember = noOfIndependentMember;
	}
	public String getAvAttendanceOfMember() {
		return avAttendanceOfMember;
	}
	public void setAvAttendanceOfMember(String avAttendanceOfMember) {
		this.avAttendanceOfMember = avAttendanceOfMember;
	}
	public String getTotalNoOfNewConvert() {
		return totalNoOfNewConvert;
	}
	public void setTotalNoOfNewConvert(String totalNoOfNewConvert) {
		this.totalNoOfNewConvert = totalNoOfNewConvert;
	}
	public String getTotalNoOfFirstTimer() {
		return totalNoOfFirstTimer;
	}
	public void setTotalNoOfFirstTimer(String totalNoOfFirstTimer) {
		this.totalNoOfFirstTimer = totalNoOfFirstTimer;
	}
	public String getNoOfFirstTimerFollowUp() {
		return noOfFirstTimerFollowUp;
	}
	public void setNoOfFirstTimerFollowUp(String noOfFirstTimerFollowUp) {
		this.noOfFirstTimerFollowUp = noOfFirstTimerFollowUp;
	}
	public String getNoOfFirstTimerCoverted() {
		return noOfFirstTimerCoverted;
	}
	public void setNoOfFirstTimerCoverted(String noOfFirstTimerCoverted) {
		this.noOfFirstTimerCoverted = noOfFirstTimerCoverted;
	}
	public String getNoOfWeeklyReportSubmitted() {
		return noOfWeeklyReportSubmitted;
	}
	public void setNoOfWeeklyReportSubmitted(String noOfWeeklyReportSubmitted) {
		this.noOfWeeklyReportSubmitted = noOfWeeklyReportSubmitted;
	}
	public String getNoOfEvanProject() {
		return noOfEvanProject;
	}
	public void setNoOfEvanProject(String noOfEvanProject) {
		this.noOfEvanProject = noOfEvanProject;
	}
	public String getNoOfcommImpactProject() {
		return noOfcommImpactProject;
	}
	public void setNoOfcommImpactProject(String noOfcommImpactProject) {
		this.noOfcommImpactProject = noOfcommImpactProject;
	}
	public String getNoOfNamingCeremony() {
		return noOfNamingCeremony;
	}
	public void setNoOfNamingCeremony(String noOfNamingCeremony) {
		this.noOfNamingCeremony = noOfNamingCeremony;
	}
	public String getNoOfburial() {
		return noOfburial;
	}
	public void setNoOfburial(String noOfburial) {
		this.noOfburial = noOfburial;
	}
	public String getNoOfArea() {
		return noOfArea;
	}
	public void setNoOfArea(String noOfArea) {
		this.noOfArea = noOfArea;
	}
	public String getNoOfIntern() {
		return noOfIntern;
	}
	public void setNoOfIntern(String noOfIntern) {
		this.noOfIntern = noOfIntern;
	}
	public String getNoOfAsstLeader() {
		return noOfAsstLeader;
	}
	public void setNoOfAsstLeader(String noOfAsstLeader) {
		this.noOfAsstLeader = noOfAsstLeader;
	}
	public String getNoOfLeader() {
		return noOfLeader;
	}
	public void setNoOfLeader(String noOfLeader) {
		this.noOfLeader = noOfLeader;
	}
	public String getNoOfAreaSupervisor() {
		return noOfAreaSupervisor;
	}
	public void setNoOfAreaSupervisor(String noOfAreaSupervisor) {
		this.noOfAreaSupervisor = noOfAreaSupervisor;
	}
	public String getTotalOffering() {
		return totalOffering;
	}
	public void setTotalOffering(String totalOffering) {
		this.totalOffering = totalOffering;
	}
	public String getNoOfNewLeaderIntroduced() {
		return noOfNewLeaderIntroduced;
	}
	public void setNoOfNewLeaderIntroduced(String noOfNewLeaderIntroduced) {
		this.noOfNewLeaderIntroduced = noOfNewLeaderIntroduced;
	}
	public String getNoOfNewLeaderTrained() {
		return noOfNewLeaderTrained;
	}
	public void setNoOfNewLeaderTrained(String noOfNewLeaderTrained) {
		this.noOfNewLeaderTrained = noOfNewLeaderTrained;
	}
	public String getNoOfNewHostIntroduced() {
		return noOfNewHostIntroduced;
	}
	public void setNoOfNewHostIntroduced(String noOfNewHostIntroduced) {
		this.noOfNewHostIntroduced = noOfNewHostIntroduced;
	}
	public String getNoOfNewHostTrained() {
		return noOfNewHostTrained;
	}
	public void setNoOfNewHostTrained(String noOfNewHostTrained) {
		this.noOfNewHostTrained = noOfNewHostTrained;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	@Override
	public String toString() {
		return "DirectorReport [center=" + center + ", noOfCenter=" + noOfCenter + ", noOfIndependentMember="
				+ noOfIndependentMember + ", avAttendanceOfMember=" + avAttendanceOfMember + ", totalNoOfNewConvert="
				+ totalNoOfNewConvert + ", totalNoOfFirstTimer=" + totalNoOfFirstTimer + ", noOfFirstTimerFollowUp="
				+ noOfFirstTimerFollowUp + ", noOfFirstTimerCoverted=" + noOfFirstTimerCoverted
				+ ", noOfWeeklyReportSubmitted=" + noOfWeeklyReportSubmitted + ", noOfEvanProject=" + noOfEvanProject
				+ ", noOfcommImpactProject=" + noOfcommImpactProject + ", noOfNamingCeremony=" + noOfNamingCeremony
				+ ", noOfburial=" + noOfburial + ", noOfArea=" + noOfArea + ", noOfIntern=" + noOfIntern
				+ ", noOfAsstLeader=" + noOfAsstLeader + ", noOfLeader=" + noOfLeader + ", noOfAreaSupervisor="
				+ noOfAreaSupervisor + ", totalOffering=" + totalOffering + ", noOfNewLeaderIntroduced="
				+ noOfNewLeaderIntroduced + ", noOfNewLeaderTrained=" + noOfNewLeaderTrained
				+ ", noOfNewHostIntroduced=" + noOfNewHostIntroduced + ", noOfNewHostTrained=" + noOfNewHostTrained
				+ ", name=" + name + ", dateCreated=" + dateCreated + "]";
	}


	
	


	
	
	
	

}
