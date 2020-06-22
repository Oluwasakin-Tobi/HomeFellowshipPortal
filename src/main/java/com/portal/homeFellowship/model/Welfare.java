package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Welfare implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 923500799112015923L;

	private String name;
	private String welfare;
	private String welfareId;
	private String status;
	private String comment
	
	;
	public String getWelfareId() {
		return welfareId;
	}
	public void setWelfareId(String welfareId) {
		this.welfareId = welfareId;
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
	public String getWelfare() {
		return welfare;
	}
	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}
	@Override
	public String toString() {
		return "Welfare [name=" + name + ", welfare=" + welfare + ", welfareId=" + welfareId + ", status=" + status
				+ ", comment=" + comment + "]";
	}
	

	
	
}
