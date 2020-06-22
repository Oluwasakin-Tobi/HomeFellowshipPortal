package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Incident implements Serializable{
	
	private String name;
	private String incident;
	private String incidentId;
	private String status;
	private String comment;
	
	
	public String getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIncident() {
		return incident;
	}
	public void setIncident(String incident) {
		this.incident = incident;
	}
	@Override
	public String toString() {
		return "Incident [name=" + name + ", incident=" + incident + ", incidentId=" + incidentId + ", status=" + status
				+ ", comment=" + comment + "]";
	}
	

	

}
