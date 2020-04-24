package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.util.Date;

public class AuditMasterDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2962345405542092086L;
	private String eventDesc;
    private String eventSrc;
    private String userName;
    private String serverIp;
    private String transRef;
    private String dateCreated;
    
    private String extRef;
    
    
	public String getExtRef() {
		return extRef;
	}
	public void setExtRef(String extRef) {
		this.extRef = extRef;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public String getEventSrc() {
		return eventSrc;
	}
	public void setEventSrc(String eventSrc) {
		this.eventSrc = eventSrc;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getTransRef() {
		return transRef;
	}
	public void setTransRef(String transRef) {
		this.transRef = transRef;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Override
	public String toString() {
		return "AuditMasterDetail [eventDesc=" + eventDesc + ", eventSrc=" + eventSrc + ", userName=" + userName
				+ ", serverIp=" + serverIp + ", transRef=" + transRef + ", dateCreated=" + dateCreated + "]";
	}
    
    
	

}
