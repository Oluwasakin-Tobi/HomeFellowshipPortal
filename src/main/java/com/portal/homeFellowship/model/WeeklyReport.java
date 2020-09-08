package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.util.Arrays;

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
	private String noOfMale;
	private String noOfFemale;
	
	private String[] visitorNames;
	private String[] visitorPhoneNos;
	private String[] visitorEmails;
	private String[] visitorGenders;
	
	private String zone;
	private String district;
	
	private String visitorName;
	private String visitorPhoneNo;
	private String visitorEmail;
	private String visitorGender;
	private String dateCreated;
	
	private String[] testifierNames;
	private String[] testimonyTopics;
	private String[] testimonys;
	
	private String[] attend;
	
	
	
	
	public String[] getAttend() {
		return attend;
	}
	public void setAttend(String[] attend) {
		this.attend = attend;
	}
	public String[] getTestifierNames() {
		return testifierNames;
	}
	public void setTestifierNames(String[] testifierNames) {
		this.testifierNames = testifierNames;
	}
	public String[] getTestimonyTopics() {
		return testimonyTopics;
	}
	public void setTestimonyTopics(String[] testimonyTopics) {
		this.testimonyTopics = testimonyTopics;
	}
	public String[] getTestimonys() {
		return testimonys;
	}
	public void setTestimonys(String[] testimonys) {
		this.testimonys = testimonys;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOffering() {
		return offering;
	}
	public void setOffering(String offering) {
		this.offering = offering;
	}
	public String getNoOfMale() {
		return noOfMale;
	}
	public void setNoOfMale(String noOfMale) {
		this.noOfMale = noOfMale;
	}
	public String getNoOfFemale() {
		return noOfFemale;
	}
	public void setNoOfFemale(String noOfFemale) {
		this.noOfFemale = noOfFemale;
	}
	public String[] getVisitorNames() {
		return visitorNames;
	}
	public void setVisitorNames(String[] visitorNames) {
		this.visitorNames = visitorNames;
	}
	public String[] getVisitorPhoneNos() {
		return visitorPhoneNos;
	}
	public void setVisitorPhoneNos(String[] visitorPhoneNos) {
		this.visitorPhoneNos = visitorPhoneNos;
	}
	public String[] getVisitorEmails() {
		return visitorEmails;
	}
	public void setVisitorEmails(String[] visitorEmails) {
		this.visitorEmails = visitorEmails;
	}
	public String[] getVisitorGenders() {
		return visitorGenders;
	}
	public void setVisitorGenders(String[] visitorGenders) {
		this.visitorGenders = visitorGenders;
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
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public String getVisitorPhoneNo() {
		return visitorPhoneNo;
	}
	public void setVisitorPhoneNo(String visitorPhoneNo) {
		this.visitorPhoneNo = visitorPhoneNo;
	}
	public String getVisitorEmail() {
		return visitorEmail;
	}
	public void setVisitorEmail(String visitorEmail) {
		this.visitorEmail = visitorEmail;
	}
	public String getVisitorGender() {
		return visitorGender;
	}
	public void setVisitorGender(String visitorGender) {
		this.visitorGender = visitorGender;
	}
	@Override
	public String toString() {
		return "WeeklyReport [centre=" + centre + ", centreAddress=" + centreAddress + ", area=" + area
				+ ", leaderName=" + leaderName + ", asstLeader=" + asstLeader + ", intern=" + intern
				+ ", worshipLeader=" + worshipLeader + ", outlineTopic=" + outlineTopic + ", adultPresent="
				+ adultPresent + ", childPresent=" + childPresent + ", visitors=" + visitors + ", hostName=" + hostName
				+ ", hostPhoneNo=" + hostPhoneNo + ", hostEmail=" + hostEmail + ", userName=" + userName + ", offering="
				+ offering + ", noOfMale=" + noOfMale + ", noOfFemale=" + noOfFemale + ", visitorNames="
				+ Arrays.toString(visitorNames) + ", visitorPhoneNos=" + Arrays.toString(visitorPhoneNos)
				+ ", visitorEmails=" + Arrays.toString(visitorEmails) + ", visitorGenders="
				+ Arrays.toString(visitorGenders) + ", zone=" + zone + ", district=" + district + ", visitorName="
				+ visitorName + ", visitorPhoneNo=" + visitorPhoneNo + ", visitorEmail=" + visitorEmail
				+ ", visitorGender=" + visitorGender + ", dateCreated=" + dateCreated + ", testifierNames="
				+ Arrays.toString(testifierNames) + ", testimonyTopics=" + Arrays.toString(testimonyTopics)
				+ ", testimonys=" + Arrays.toString(testimonys) + ", attend=" + Arrays.toString(attend) + "]";
	}
	


	
	
	
	

	


}
