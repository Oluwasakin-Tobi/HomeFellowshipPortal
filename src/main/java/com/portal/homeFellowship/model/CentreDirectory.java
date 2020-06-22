package com.portal.homeFellowship.model;

import java.io.Serializable;

import javax.mail.Multipart;

public class CentreDirectory implements Serializable{
	
	private String interns;
	private String assistantLeader;
	private String leader;
	private String host;
	private String assistantAreaSupervisor;
	private String areaSupervisor;
	private String assistantZonalPastor;
	private String zonalPastor; 
	private String assistantDistrictPastor;
	private String districtPastor;
	private String centre;
	private Multipart[] documents;
	public String getInterns() {
		return interns;
	}
	public void setInterns(String interns) {
		this.interns = interns;
	}
	public String getAssistantLeader() {
		return assistantLeader;
	}
	public void setAssistantLeader(String assistantLeader) {
		this.assistantLeader = assistantLeader;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getAssistantAreaSupervisor() {
		return assistantAreaSupervisor;
	}
	public void setAssistantAreaSupervisor(String assistantAreaSupervisor) {
		this.assistantAreaSupervisor = assistantAreaSupervisor;
	}
	public String getAreaSupervisor() {
		return areaSupervisor;
	}
	public void setAreaSupervisor(String areaSupervisor) {
		this.areaSupervisor = areaSupervisor;
	}
	public String getAssistantZonalPastor() {
		return assistantZonalPastor;
	}
	public void setAssistantZonalPastor(String assistantZonalPastor) {
		this.assistantZonalPastor = assistantZonalPastor;
	}
	public String getZonalPastor() {
		return zonalPastor;
	}
	public void setZonalPastor(String zonalPastor) {
		this.zonalPastor = zonalPastor;
	}
	public String getAssistantDistrictPastor() {
		return assistantDistrictPastor;
	}
	public void setAssistantDistrictPastor(String assistantDistrictPastor) {
		this.assistantDistrictPastor = assistantDistrictPastor;
	}
	public String getDistrictPastor() {
		return districtPastor;
	}
	public void setDistrictPastor(String districtPastor) {
		this.districtPastor = districtPastor;
	}
	public String getCentre() {
		return centre;
	}
	public void setCentre(String centre) {
		this.centre = centre;
	}
	public Multipart[] getDocuments() {
		return documents;
	}
	public void setDocuments(Multipart[] documents) {
		this.documents = documents;
	}
	@Override
	public String toString() {
		return "CentreDirectory [interns=" + interns + ", assistantLeader=" + assistantLeader + ", leader=" + leader
				+ ", host=" + host + ", assistantAreaSupervisor=" + assistantAreaSupervisor + ", areaSupervisor="
				+ areaSupervisor + ", assistantZonalPastor=" + assistantZonalPastor + ", zonalPastor=" + zonalPastor
				+ ", assistantDistrictPastor=" + assistantDistrictPastor + ", districtPastor=" + districtPastor
				+ ", centre=" + centre + ", documents=" + documents + "]";
	}
	
	

}
