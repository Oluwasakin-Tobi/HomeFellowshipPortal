package com.portal.homeFellowship.model;

import java.io.Serializable;

public class Response implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4119725042271587216L;
	private String responseCode;
	private String responseMessage;
	
	public Response(){ 
		this.responseCode="99";
		this.responseMessage="Oops, Something went wrong...";
	}
	
	public Response(String responseCode, String responseMessage){
		this.responseCode=responseCode;
		this.responseMessage=responseMessage;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	@Override
	public String toString() {
		return "Response [responseCode=" + responseCode + ", responseMessage=" + responseMessage + "]";
	}
	
	

}
