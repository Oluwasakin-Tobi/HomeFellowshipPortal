package com.portal.homeFellowship.model;

import java.io.Serializable;

public class SmsResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7457731937774147316L;
	
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SmsResponse [data=" + data + "]";
	}
	
	

}
